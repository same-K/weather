package com.example.weather.model;

import android.content.res.AssetManager;

import com.example.weather.WeatherApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class LocationList {

    private static LocationList instance = new LocationList();

    public static LocationList getInstance(){
        return instance;
    }

    private List<String> cityNames;

    public List<String> getCityNames(){
        return cityNames;
    }

    private Boolean isLoaded;

    public Boolean IsLoaded(){
        return isLoaded;
    }

    private List<onLoadedListener> onLoadedListeners = new ArrayList<>();

    public void AddOnLoadedListener(onLoadedListener listener){
        onLoadedListeners.add(listener);
    }

    public void RemoveOnLoadedListener(onLoadedListener listener){
        onLoadedListeners.remove(listener);
    }

    public void load(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()-> {
            cityNames = loadCityListJson();
            isLoaded = true;
            onLoadedListeners.forEach(onLoadedListener::onLoaded);
        });
    }

    private List<String> loadCityListJson(){
        String jsonText = "";
        try {
            AssetManager assetManager = WeatherApplication.getContext().getAssets();
            InputStream inputStream = assetManager.open("city.list.json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            Type collectionType = new TypeToken<Collection<City>>() {}.getType();
            Gson gson = new Gson();
            List<City> cities = gson.fromJson(bufferedReader, collectionType);
            List<String> mapped = cities.stream().map(city -> city.name).collect(Collectors.toList());

            bufferedReader.close();
            inputStream.close();
            return mapped;
        }catch (IOException ioException) {
            return Collections.unmodifiableList(new ArrayList<>());
        }
    }

    public interface onLoadedListener {
        void onLoaded();
    }

    class City {

        public int id;

        public String name;
        public String state;
        public String country;

        public Coord coord;

        class Coord {
            public float lon;
            public float lat;
        }
    }
}
