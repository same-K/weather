package com.example.weather.model;

import java.util.List;

public class Forecast {

    public long dt;

    public Main main;

    public List<Weather> weather;

    public Clouds clouds;

    public Wind wind;

    public int visibility;

    public float pop;

    public Rain rain;

    public Sys sys;

    public String dt_txt;


    public class Main {
        public float temp;

        public float feels_like;

        public float temp_min;

        public float temp_max;

        public int pressure;

        public int sea_level;

        public int grnd_level;

        public int humidity;

        public float temp_kf;
    }

    public class Weather {
        public int id;
        public String main;
        public String description;
        public String icon;
    }

    public class Clouds{
        public int all;
    }

    public class Wind{
        public float speed;
        public int deg;
        public float gust;
    }

    public class Rain {
        public float threeHour;
    }

    public class Sys{
        public String pod;
    }
}
