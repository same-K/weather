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
import com.example.weather.model.LocationList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class EditLocationViewModel extends ViewModel {

    ForecastRepository repository;

    public EditLocationViewModel(ForecastRepository repository)
    {
        this.repository = repository;
        _locations.setValue(repository.getAllLocation().getValue());
    }

    public static final ViewModelInitializer<EditLocationViewModel> initializer = new ViewModelInitializer<>(
            EditLocationViewModel.class,
            creationExtras -> {
                Application app = creationExtras.get(APPLICATION_KEY);
                ForecastRepository repository = new ForecastRepository(AppDatabase.getDatabase(app.getApplicationContext()));
                return new EditLocationViewModel(repository);
            }
    );

    private MutableLiveData<List<Location>> _locations = new MutableLiveData<List<Location>>();

    public LiveData<List<Location>> getLocations() {
        return _locations;
    }

    // TODO
    public void loadLocations(){
        _locations.setValue(repository.getAllLocation().getValue());
//        _locations.postValue(repository.getAllLocation().getValue());
    }

    public void delete(Location location){
        repository.deleteLocation(location);
    }

    public void add(Location location){
        repository.insertLocation(location);
        // TODO 要確認
        _locations.getValue().add(location);
    }

    List<String> cityNameList;
    public List<String> getCityCandidates(String text){
        cityNameList = LocationList.getInstance().getCityNames();
        if(cityNameList == null){
            return null;
        }

        List<String> filtered = cityNameList.stream().filter(city -> city.startsWith(text)).collect(Collectors.toList());
        return Collections.unmodifiableList(filtered);
    }
}
