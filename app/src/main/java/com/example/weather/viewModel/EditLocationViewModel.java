package com.example.weather.viewModel;

import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.example.weather.database.AppDatabase;
import com.example.weather.database.Location;
import com.example.weather.model.ForecastRepository;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

    public LiveData<List<Location>> loadLocations(){
        return repository.getAllLocation();
    }
}
