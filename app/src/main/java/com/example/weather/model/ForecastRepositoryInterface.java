package com.example.weather.model;

import androidx.lifecycle.LiveData;

import com.example.weather.database.Location;

import java.util.List;

public interface ForecastRepositoryInterface {

    LiveData<Location> getLocation(int cityId);

    LiveData<List<Location>> getAllLocation();

    void delete(int cityId);

    void store(OpenWeatherResponse forecast);
}
