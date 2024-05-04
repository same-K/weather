package com.example.weather.model;

import com.example.weather.database.Location;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherApi {
    @GET("forecast")
    Call<OpenWeatherResponse> getForecast(@Query("q") String cityName,
                               @Query("units") String units,
                               @Query("lang") String lang,
                               @Query("appid") String apiKey);
    
}
