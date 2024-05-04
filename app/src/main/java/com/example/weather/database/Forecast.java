package com.example.weather.database;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "forecasts")
public class Forecast {
    @PrimaryKey()
    @ColumnInfo(name = "city_id")
    public int cityId;

    // TODO key
    @ColumnInfo(name = "datetime")
    public long datetime;

    // Main
    @ColumnInfo(name = "temperature")
    public float temperature;

    @ColumnInfo(name = "feels_like")
    public float feelsLike;

    @ColumnInfo(name = "temperature_min")
    public float temperatureMin;

    @ColumnInfo(name = "temperature_max")
    public float temperatureMax;

    @ColumnInfo(name = "pressure")
    public int pressure;

    @ColumnInfo(name = "sea_level")
    public int seaLevel;

    @ColumnInfo(name = "ground_level")
    public int groundLevel;

    @ColumnInfo(name = "humidity")
    public int humidity;

    @ColumnInfo(name = "temp_kf")
    public int temp_kf;

    // Weather
    @ColumnInfo(name = "weather_id")
    public int weatherId;

    @ColumnInfo(name = "main")
    public String main;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "icon")
    public String icon;

    // Clouds
    @ColumnInfo(name = "clouds")
    public int clouds;

    // Wind
    @ColumnInfo(name = "speed")
    public float speed;

    @ColumnInfo(name = "degree")
    public int degree;

    @ColumnInfo(name = "gust")
    public float gust;

    @ColumnInfo(name = "visibility")
    public int visibility;

    @ColumnInfo(name = "probability_of_precipitation")
    public int probabilityOfPrecipitation;

    @ColumnInfo(name = "rain_volume_last_3_hour")
    public int rainVolumeForLast3Hour;

    @ColumnInfo(name = "snow_volume_last_3_hour")
    public int snowVolumeForLast3Hour;

    @ColumnInfo(name = "part_of_day")
    public String partOfDay;

    @ColumnInfo(name = "datetime_text")
    public String datetimeText;
}