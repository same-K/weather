package com.example.weather.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SunDao {

    @Insert
    void insert(Sun sun);

    @Delete
    void delete(Sun sun);

    @Update
    void Update(Sun sun);


    @Query("SELECT * FROM suns WHERE city_id = :cityId")
    Sun getSun(int cityId);
}
