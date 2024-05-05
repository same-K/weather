package com.example.weather.model;

import androidx.lifecycle.LiveData;

import com.example.weather.database.AppDatabase;
import com.example.weather.database.Location;

import java.util.List;

public class ForecastRepository implements ForecastRepositoryInterface{

    private AppDatabase database;

    public ForecastRepository(AppDatabase database){
        this.database = database;
    }

    @Override
    public OpenWeatherResponse getForecast(int cityId) {
        return null;
    }

    @Override
    public void store(OpenWeatherResponse forecast) {
        // TODO
    }

    public LiveData<List<Location>> getAllLocation()
    {
        return database.locationDao().getAll();
    }
}
