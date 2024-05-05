package com.example.weather.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Location.class, Forecast.class, Sun.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ForecastDAO forecastDAO();
    public abstract SunDao sunDao();
    public abstract LocationDao locationDao();

    private static volatile AppDatabase instance;

    private static final ExecutorService executor = Executors.newFixedThreadPool(4);

    public static AppDatabase getDatabase(Context context)
    {
        if(instance == null){
            synchronized (AppDatabase.class){
                instance = Room.databaseBuilder(context.getApplicationContext()
                        , AppDatabase.class, "app_database").addCallback(callback).build();
            }
        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            executor.execute(()->{
                LocationDao ldao = instance.locationDao();
                ldao.insert(new Location(1850147,"東京", "JP", 139.691711f, 35.689499f));
                ldao.insert(new Location(1862047, "兵庫", "JP", 134.825974f, 35.040321f));
                ldao.insert(new Location(1854487, "大分", "JP", 131.433533f, 33.19899f));
                ldao.insert(new Location(2130037, "北海道", "JP", 141.346603f, 43.06451f));
            });
        }
    };
}
