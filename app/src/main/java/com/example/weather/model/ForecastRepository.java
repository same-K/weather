package com.example.weather.model;

import com.example.weather.database.AppDatabase;

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
}
