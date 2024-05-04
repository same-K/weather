package com.example.weather.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class OpenWeatherService {
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build();

    public static OpenWeatherApi getService()
    {
        return retrofit.create(OpenWeatherApi.class);
    }
}
