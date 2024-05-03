package com.example.weather.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Room;


@Database(entities = {Location.class, Forecast.class, Sun.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ForecastDAO forecastDAO();
    public abstract SunDao sunDao();
    public abstract LocationDao locationDao();

    private static volatile AppDatabase instance;

    public static AppDatabase getDatabase(Context context)
    {
        if(instance == null){
            synchronized (AppDatabase.class){
                instance = Room.databaseBuilder(context.getApplicationContext()
                        , AppDatabase.class, "app_database").build();
            }
        }
        return instance;
    }
}
