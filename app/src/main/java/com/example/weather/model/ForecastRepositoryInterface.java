package com.example.weather.model;

// TODO 名前
public interface ForecastRepositoryInterface {


    OpenWeatherResponse getForecast(int cityId);

    void store(OpenWeatherResponse forecast);
}
