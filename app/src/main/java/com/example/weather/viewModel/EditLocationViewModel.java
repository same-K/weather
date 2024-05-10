package com.example.weather.viewModel;

import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.example.weather.database.AppDatabase;
import com.example.weather.model.ForecastRepository;

public class EditLocationViewModel extends ViewModel {

    ForecastRepository repository;

    public EditLocationViewModel(ForecastRepository repository)
    {
        this.repository = repository;
    }

    public static final ViewModelInitializer<EditLocationViewModel> initializer = new ViewModelInitializer<>(
            EditLocationViewModel.class,
            creationExtras -> {
                Application app = creationExtras.get(APPLICATION_KEY);
                ForecastRepository repository = new ForecastRepository(AppDatabase.getDatabase(app.getApplicationContext()));
                return new EditLocationViewModel(repository);
            }
    );
}
