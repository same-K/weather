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
    public long sunrise;

    @ColumnInfo(name = "sunset")
    public long sunset;

    // TODO 一日分？
}
