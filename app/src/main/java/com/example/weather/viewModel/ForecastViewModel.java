package com.example.weather.viewModel;

import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.example.weather.database.AppDatabase;
import com.example.weather.database.Forecast;
import com.example.weather.database.Location;
import com.example.weather.model.ForecastRepository;

import java.util.List;

public class ForecastViewModel extends ViewModel {

    ForecastRepository repository;

    public ForecastViewModel(ForecastRepository repository){
        this.repository = repository;
    }

    public static final ViewModelInitializer<ForecastViewModel> initializer = new ViewModelInitializer<>(
            ForecastViewModel.class,
            creationExtras -> {
                Application app = creationExtras.get(APPLICATION_KEY);
                ForecastRepository repository = new ForecastRepository(AppDatabase.getDatabase(app.getApplicationContext()));
                return new ForecastViewModel(repository);
            }
    );

    public LiveData<Location> getLocation(int cityId){
        return repository.getLocation(cityId);
    }

    public MutableLiveData<List<Forecast>> load(int cityId)
    {
        Log.d("ForecastViewModel", "load");
        return repository.load(cityId);
    }
}
