package com.example.weather.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.appcompat.content.res.AppCompatResources;
import com.example.weather.R;

public class WeatherIconUtil {

    // Group 2xx : Thunderstorm
    public static final int THUNDERSTORM_WITH_LIGHT_RAIN = 200;
    public static final int THUNDERSTORM_WITH_RAIN = 201;
    public static final int THUNDERSTORM_WITH_HEAVY_RAIN = 202;
    public static final int LIGHT_THUNDERSTORM = 210;
    public static final int THUNDERSTORM = 211;
    public static final int HEAVY_THUNDERSTORM = 212;
    public static final int RAGGED_THUNDERSTORM = 221;
    public static final int THUNDERSTORM_WITH_LIGHT_DRIZZLE = 230;
    public static final int THUNDERSTORM_WITH_DRIZZLE = 231;
    public static final int THUNDERSTORM_WITH_HEAVY_DRIZZLE = 232;

    // Group 3xx : Drizzle
    public static final int LIGHT_INTENSITY_DRIZZLE = 300;
    public static final int DRIZZLE = 301;
    public static final int HEAVY_INTENSITY_DRIZZLE = 302;
    public static final int LIGHT_INTENSITY_DRIZZLE_RAIN = 310;
    public static final int DRIZZLE_RAIN = 311;
    public static final int HEAVY_INTENSITY_DRIZZLE_RAIN = 312;
    public static final int SHOWER_RAIN_AND_DRIZZLE = 313;
    public static final int HEAVY_SHOWER_RAIN_AND_DRIZZLE = 314;
    public static final int SHOWER_DRIZZLE = 321;

    // Group 5xx : Rain
    public static final int LIGHT_RAIN = 500;
    public static final int MODERATE_RAIN = 501;
    public static final int HEAVY_INTENSITY_RAIN = 502;
    public static final int VERY_HEAVY_RAIN = 503;
    public static final int EXTREME_RAIN = 504;
    public static final int FREEZING_RAIN = 511;
    public static final int LIGHT_INTENSITY_SHOWER_RAIN = 520;
    public static final int SHOWER_RAIN = 521;
    public static final int HEAVY_INTENSITY_SHOWER_RAIN = 522;
    public static final int RAGGED_SHOWER_RAIN = 530;

    // Group 6xx : Snow
    public static final int LIGHT_SNOW = 600;
    public static final int SNOW = 601;
    public static final int HEAVY_SNOW = 602;
    public static final int SLEET = 611;
    public static final int LIGHT_SHOWER_SLEET = 612;
    public static final int SHOWER_SLEET = 613;
    public static final int LIGHT_RAIN_AND_SNOW = 615;
    public static final int RAIN_AND_SNOW = 616;
    public static final int LIGHT_SHOWER_SNOW = 620;
    public static final int SHOWER_SNOW = 621;
    public static final int HEAVY_SHOWER_SNOW = 622;

    // Group 7xx : Atmosphere
    public static final int MIST = 701;
    public static final int SMOKE = 711;
    public static final int HAZE = 712;
    public static final int SAND_OR_DUST_WHIRLS = 731;
    public static final int FOG = 741;
    public static final int SAND = 751;
    public static final int DUST = 761;
    public static final int VOLCANIC_ASH = 762;
    public static final int SQUALLS = 771;
    public static final int TORNADO = 781;

    // Group 800 : Clear
    public static final int CLEAR_SKY = 800;

    // Group 8xx : Clouds
    public static final int FEW_CLOUDS = 801;         // 11-25%
    public static final int SCATTERED_CLOUDS = 802;   // 25-50%
    public static final int BROKEN_CLOUDS = 803;      // 51-84%
    public static final int OVERCAST_CLOUDS = 804;    // 85-100%


    public static Drawable LoadDrawable(Context context, int id){
        // TODO theme
        // Group 2xx:Thunderstorm , all 11d, 11n
        switch (id){
            // Group 2xx : Thunderstorm
            case THUNDERSTORM_WITH_LIGHT_RAIN:
            case THUNDERSTORM_WITH_RAIN:
            case THUNDERSTORM_WITH_HEAVY_RAIN:
            case LIGHT_THUNDERSTORM:
            case THUNDERSTORM:
            case HEAVY_THUNDERSTORM:
            case RAGGED_THUNDERSTORM:
            case THUNDERSTORM_WITH_LIGHT_DRIZZLE:
            case THUNDERSTORM_WITH_DRIZZLE:
            case THUNDERSTORM_WITH_HEAVY_DRIZZLE:
                return AppCompatResources.getDrawable(context, R.drawable.weather_icon_11d);
            //return AppCompatResources.getDrawable(context, R.drawable.weather_icon_11n);

            // Group 3xx : Drizzle , all 09d, 09n
            case LIGHT_INTENSITY_DRIZZLE:
            case DRIZZLE:
            case HEAVY_INTENSITY_DRIZZLE:
            case LIGHT_INTENSITY_DRIZZLE_RAIN:
            case DRIZZLE_RAIN:
            case HEAVY_INTENSITY_DRIZZLE_RAIN:
            case SHOWER_RAIN_AND_DRIZZLE:
            case HEAVY_SHOWER_RAIN_AND_DRIZZLE:
            case SHOWER_DRIZZLE:
                return  AppCompatResources.getDrawable(context, R.drawable.weather_icon_09d);
            //return  AppCompatResources.getDrawable(context, R.drawable.weather_icon_09n);

            // Group 5xx : Rain
            case LIGHT_RAIN:
            case MODERATE_RAIN:
            case HEAVY_INTENSITY_RAIN:
            case VERY_HEAVY_RAIN:
            case EXTREME_RAIN:
                return AppCompatResources.getDrawable(context, R.drawable.weather_icon_10d);
            case FREEZING_RAIN:
                return AppCompatResources.getDrawable(context, R.drawable.weather_icon_13d);
            case LIGHT_INTENSITY_SHOWER_RAIN:
            case SHOWER_RAIN:
            case HEAVY_INTENSITY_SHOWER_RAIN:
            case RAGGED_SHOWER_RAIN:
                return AppCompatResources.getDrawable(context, R.drawable.weather_icon_09d);

            // Group 6xx : Snow, all 13d
            case LIGHT_SNOW:
            case SNOW:
            case HEAVY_SNOW:
            case SLEET:
            case LIGHT_SHOWER_SLEET:
            case SHOWER_SLEET:
            case LIGHT_RAIN_AND_SNOW:
            case RAIN_AND_SNOW:
            case LIGHT_SHOWER_SNOW:
            case SHOWER_SNOW:
            case HEAVY_SHOWER_SNOW:
                return AppCompatResources.getDrawable(context, R.drawable.weather_icon_13d);

            // Group 7xx : Atmosphere, all 50d
            case MIST:
            case SMOKE:
            case HAZE:
            case SAND_OR_DUST_WHIRLS:
            case FOG:
            case SAND:
            case DUST:
            case VOLCANIC_ASH:
            case SQUALLS:
            case TORNADO:
                return AppCompatResources.getDrawable(context, R.drawable.weather_icon_50d);

            // Group 800 : Clear
            case CLEAR_SKY:
                return AppCompatResources.getDrawable(context, R.drawable.weather_icon_01d);

            // Group 80x : Clouds
            case FEW_CLOUDS:
                return AppCompatResources.getDrawable(context, R.drawable.weather_icon_02d);
            case SCATTERED_CLOUDS:
                return AppCompatResources.getDrawable(context, R.drawable.weather_icon_03d);
            case BROKEN_CLOUDS:
                return AppCompatResources.getDrawable(context, R.drawable.weather_icon_04d);
            case OVERCAST_CLOUDS:
                return AppCompatResources.getDrawable(context, R.drawable.weather_icon_04d);
            default:
                // TODO undefined
                return AppCompatResources.getDrawable(context, R.drawable.weather_icon_04d);
        }
    }
}
