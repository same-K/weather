package com.example.weather.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ForecastDAO {

    @Insert
    void insert(Forecast forecast);

    @Insert
    void insert(List<Forecast> forecasts);

    @Delete
    void delete(Forecast forecast);

    @Query("DELETE FROM forecasts where city_id = :cityId")
    int deleteByCityId(int cityId);



    @Query("SELECT * FROM forecasts WHERE city_id = :cityId")
    List<Forecast> get(int cityId);

    @Query("SELECT * FROM forecasts")
    List<Forecast> getAll();
}
