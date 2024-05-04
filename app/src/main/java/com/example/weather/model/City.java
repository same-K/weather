package com.example.weather.model;


public class City {
    public int id;
    public String name;

    public Coord coord;


    public String country;

    public int population;

    public int timezone;

    public long sunrise;

    public long sunset;

    public class Coord{
        public float lat;
        public float lon;
    }
}
