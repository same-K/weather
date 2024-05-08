package com.example.weather.model;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.ColumnInfo;

import com.example.weather.BuildConfig;
import com.example.weather.database.AppDatabase;
import com.example.weather.database.Forecast;
import com.example.weather.database.ForecastDAO;
import com.example.weather.database.Location;
import com.example.weather.database.LocationDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastRepository implements ForecastRepositoryInterface{

    public interface OnFailureListener{
        void onFailure();
    }

    private AppDatabase database;
    private ForecastDAO forecastDAO;
    private LocationDao locationDao;

    public ForecastRepository(AppDatabase database){
        this.database = database;
        forecastDAO = database.forecastDAO();
        locationDao = database.locationDao();
    }

    @Override
    public LiveData<Location> getLocation(int cityId) {
        return locationDao.get(cityId);
    }

    @Override
    public LiveData<List<Location>> getAllLocation() {
        return locationDao.getAll();
    }


    private MutableLiveData<List<Forecast>> forecasts = new MutableLiveData<>();


    public MutableLiveData<List<Forecast>> load(int cityId){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->{
            Log.d("ForecastRepository", "execute");
            Location location = locationDao.getSync(cityId);
            List<Forecast> temp = forecastDAO.getAll();
            List<Forecast> forecasts = forecastDAO.get(cityId);
            for(Forecast t : temp){
                Log.d("ForecastRepository", "id;"+t.cityId+",date:"+t.datetime);
            }

            if(forecasts != null && !forecasts.isEmpty()){
                long now = System.currentTimeMillis() / 1000;
                long oldestDate = getOldestDate(forecasts);
                if(oldestDate + 24*60*60 > now){
                    Log.d("ForecastRepository", "return cache");
                    this.forecasts.postValue(forecasts);
                    return;
                }
            }
            // TODO
            /*
            int result = forecastDAO.deleteByCityId(cityId);
            Log.d("ForecastRepository", "delete result:"+result);

            Log.d("ForecastRepository", "call web api");
            OpenWeatherApi api = OpenWeatherService.getService();
            api.getForecast(location.cityName, "metric", "ja", BuildConfig.API_KEY)
                .enqueue(callback);
             */
        });
        Log.d("ForecastRepository", "return forecast");
        return forecasts;
    }

    private Callback<OpenWeatherResponse> callback = new Callback<OpenWeatherResponse>() {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        @Override
        public void onResponse(Call<OpenWeatherResponse> call, Response<OpenWeatherResponse> response) {
            if(response.body() == null){
                // TODO error
                Log.d("ForecastRepository", "onResponse:body:null");
                return;
            }
            Log.d("ForecastRepository", "onResponse:convert");
            List<Forecast> newForecast = new ArrayList<>();
            for(com.example.weather.model.Forecast forecastSource : response.body().list)
            {
                Forecast forecast = new Forecast();
                forecast.cityId = response.body().city.id;
                Log.d("ForecastRepository", response.body().city.name);
                forecast.datetime = forecastSource.dt;
                forecast.temperature = forecastSource.main.temp;
                forecast.feelsLike = forecastSource.main.feels_like;
                forecast.temperatureMin = forecastSource.main.temp_min;
                forecast.temperatureMax = forecastSource.main.temp_max;
                forecast.pressure = forecastSource.main.pressure;
                forecast.seaLevel = forecastSource.main.sea_level;
                forecast.groundLevel = forecastSource.main.grnd_level;
                forecast.humidity = forecastSource.main.humidity;
                forecast.temp_kf = forecastSource.main.temp_kf;
                com.example.weather.model.Forecast.Weather weather = forecastSource.weather.get(0);
                forecast.weatherId = weather.id;
                forecast.main = weather.main;
                forecast.description = weather.description;
                forecast.icon = weather.icon;
                forecast.clouds = forecastSource.clouds.all;
                forecast.speed = forecastSource.wind.speed;
                forecast.degree = forecastSource.wind.deg;
                forecast.gust = forecastSource.wind.gust;
                forecast.visibility = forecastSource.visibility;
                forecast.rainVolumeForLast3Hour = forecastSource.rain == null
                        ? 0 : forecastSource.rain.threeHour;
                forecast.snowVolumeForLast3Hour = forecastSource.snow == null
                        ? 0 : forecastSource.snow.threeHour;
                forecast.probabilityOfPrecipitation = forecastSource.pop;
                forecast.partOfDay = forecastSource.sys.pod;
                forecast.datetimeText = forecastSource.dt_txt;

                newForecast.add(forecast);

                Log.d("ForecastRepository", "id:"+forecast.cityId+", datetime:"+forecast.datetime);

            }
            executorService.execute(()-> {
                // TODO 要確認
                forecastDAO.insert(newForecast);
            });
            Log.d("ForecastRepository", "postValue");
            forecasts.setValue(newForecast);
        }

        @Override
        public void onFailure(Call<OpenWeatherResponse> call, Throwable throwable) {
            // TODO
            Log.d("ForecastRepository", "onFailure");
        }
    };


    private long getOldestDate(List<Forecast> forecasts){
        Forecast forecast = Collections.min(forecasts, new CompareDate());
        return forecast.datetime;
    }

    private class CompareDate implements Comparator<Forecast>
    {
        @Override
        public int compare(Forecast o1, Forecast o2) {
            return Long.compare(o2.datetime, o1.datetime);
        }
    }

    @Override
    public void delete(int cityId){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(()->{
            forecastDAO.deleteByCityId(cityId);
        });
    }

    @Override
    public void store(OpenWeatherResponse forecast) {
        // TODO
    }
}
