package com.example.weather.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface ForecastDAO {

    @Insert
    void insert(Forecast forecast);

    @Delete
    void delete(Forecast forecast);
}
