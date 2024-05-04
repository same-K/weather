package com.example.weather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OpenWeatherResponse {

    @SerializedName("cod")
    public int cod;

    public int message;

    public int cnt;

    public List<Forecast> list;

    public City city;
}
