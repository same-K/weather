package com.example.weather.viewModel;


import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.example.weather.database.AppDatabase;
import com.example.weather.database.Location;
import com.example.weather.model.ForecastRepository;
import com.example.weather.model.SummaryItem;

import java.util.ArrayList;
import java.util.List;


public class HomeViewModel extends ViewModel {

    ForecastRepository repository;

    public HomeViewModel(ForecastRepository repository){
        this.repository = repository;
    }

    public static final ViewModelInitializer<HomeViewModel> initializer = new ViewModelInitializer<>(
            HomeViewModel.class,
            creationExtras -> {
                Application app = creationExtras.get(APPLICATION_KEY);
                ForecastRepository repository = new ForecastRepository(AppDatabase.getDatabase(app.getApplicationContext()));
                return new HomeViewModel(repository);
            }
    );

    public LiveData<List<SummaryItem>> summaries;

    public LiveData<List<Location>> load(){
        return repository.getAllLocation();
    }
}
