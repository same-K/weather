package com.example.weather.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LocationDao {
    @Insert
    void insert(Location location);

    @Delete
    void delete(Location location);

    @Query("SELECT * FROM locations")
    LiveData<List<Location>> getAll();
}
