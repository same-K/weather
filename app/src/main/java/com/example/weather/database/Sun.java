package com.example.weather.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "suns")
public class Sun {

    @PrimaryKey()
    @ColumnInfo(name = "city_id")
    public int cityId;

    @ColumnInfo(name = "sunrise")
    public Date sunrise;

    @ColumnInfo(name = "sunset")
    public Date sunset;

    // TODO 一日分？
}
