package com.example.weather;

import android.app.Application;
import android.content.Context;

import com.example.weather.model.LocationList;

public class WeatherApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        LocationList.getInstance().load();
    }

    public static Context getContext(){
        return context;
    }
}
