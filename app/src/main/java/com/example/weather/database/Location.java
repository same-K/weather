package com.example.weather.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "locations")
public class Location {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "city_id")
    public int cityId;

    @ColumnInfo(name = "city_name")
    public String cityName;

    @ColumnInfo(name = "latitude")
    public float latitude;

    @ColumnInfo(name = "longitude")
    public float longitude;

    @ColumnInfo(name = "country")
    public String country;

    @ColumnInfo(name = "population")
    public int population;

    @ColumnInfo(name = "timezone")
    public int timezone;
}
