package com.andresnav.trackmyshoes.data.model;

import static com.andresnav.trackmyshoes.utils.Utils.print;

import android.content.Context;

import androidx.annotation.NonNull;

import com.andresnav.trackmyshoes.R;
import com.google.gson.annotations.SerializedName;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;


public class WeatherModel {
    public static final int KELVIN_TO_CELSIUS = 273;
    public static final double M_S_TO_KM_H = 3.6;

    @SerializedName("coord")
    Coord coord;

    @SerializedName("weather")
    List<Weather> weather;

    @SerializedName("base")
    String base;

    @SerializedName("main")
    Main main;

    @SerializedName("visibility")
    int visibility;

    @SerializedName("wind")
    Wind wind;

    @SerializedName("clouds")
    Clouds clouds;

    @SerializedName("dt")
    long dt;

    @SerializedName("sys")
    Sys sys;

    @SerializedName("timezone")
    int timezone;

    @SerializedName("id")
    int id;

    @SerializedName("name")
    String name;

    @SerializedName("cod")
    int cod;

    // Getters and setters for all the fields
    public String getLocation() {
        return this.name;
    }

    public int getTemperatureNow() {
        return (int) (this.main.temp - KELVIN_TO_CELSIUS); // pass from kelvin to degrees celsius
    }

    public int getFeelsLikeNow() {
        return (int) (this.main.feels_like - KELVIN_TO_CELSIUS);
    }

    public int getWind() {
        return (int) (this.wind.speed * M_S_TO_KM_H);
    }

    public float getClouds() {
        return (float) (this.clouds.all);
    }

    public int getWeatherStringId() {
        //get the most significant digit
        int id = this.weather.get(0).id;
        int description = 0;

        switch (id) {
            case 200: description = R.string.thunderstorm_light_rain; break;
            case 201: description = R.string.thunderstorm_rain; break;
            case 202: description = R.string.thunderstorm_heavy_rain; break;
            case 210: description = R.string.light_thunderstorm; break;
            case 211: description = R.string.thunderstorm; break;
            case 212: description = R.string.heavy_thunderstorm; break;
            case 221: description = R.string.ragged_thunderstorm; break;
            case 230: description = R.string.thunderstorm_light_drizzle; break;
            case 231: description = R.string.thunderstorm_drizzle; break;
            case 232: description = R.string.thunderstorm_heavy_drizzle; break;
            case 300: description = R.string.drizzle_light_intensity; break;
            case 301: description = R.string.drizzle; break;
            case 302: description = R.string.drizzle_heavy_intensity; break;
            case 310: description = R.string.drizzle_light_intensity_rain; break;
            case 311: description = R.string.drizzle_rain; break;
            case 312: description = R.string.drizzle_heavy_intensity_rain; break;
            case 313: description = R.string.shower_rain_and_drizzle; break;
            case 314: description = R.string.heavy_shower_rain_and_drizzle; break;
            case 321: description = R.string.shower_drizzle; break;
            case 500: description = R.string.rain_light; break;
            case 501: description = R.string.rain_moderate; break;
            case 502: description = R.string.rain_heavy_intensity; break;
            case 503: description = R.string.rain_very_heavy; break;
            case 504: description = R.string.rain_extreme; break;
            case 511: description = R.string.freezing_rain; break;
            case 520: description = R.string.shower_rain_light_intensity; break;
            case 521: description = R.string.shower_rain; break;
            case 522: description = R.string.shower_rain_heavy_intensity; break;
            case 531: description = R.string.ragged_shower_rain; break;
            case 600: description = R.string.snow_light; break;
            case 601: description = R.string.snow; break;
            case 602: description = R.string.snow_heavy; break;
            case 611: description = R.string.sleet; break;
            case 612: description = R.string.shower_sleet_light; break;
            case 613: description = R.string.shower_sleet; break;
            case 615: description = R.string.rain_and_snow_light; break;
            case 616: description = R.string.rain_and_snow; break;
            case 620: description = R.string.shower_snow_light; break;
            case 621: description = R.string.shower_snow; break;
            case 622: description = R.string.shower_snow_heavy; break;
            case 701: description = R.string.mist; break;
            case 711: description = R.string.smoke; break;
            case 721: description = R.string.haze; break;
            case 731: description = R.string.sand_dust_whirls; break;
            case 741: description = R.string.fog; break;
            case 751: description = R.string.sand; break;
            case 761: description = R.string.dust; break;
            case 762: description = R.string.volcanic_ash; break;
            case 771: description = R.string.squalls; break;
            case 781: description = R.string.tornado; break;
            case 800: description = R.string.clear_sky; break;
            case 801: description = R.string.few_clouds; break;
            case 802: description = R.string.scattered_clouds; break;
            case 803: description = R.string.broken_clouds; break;
            case 804: description = R.string.overcast_clouds; break;
            default: break;
        }

        return description;
    }

    public String getWeatherIcon() {
        return this.weather.get(0).icon;
    }

    public static class Coord {
        double lon;
        double lat;

        @NonNull
        @Override
        public String toString() {
            return "Coord{" +
                    "lon=" + lon +
                    ", lat=" + lat +
                    '}';
        }
    }

    public static class Weather {
        int id;
        String main;
        String description;
        String icon;

        @Override
        public String toString() {
            return "Weather{" +
                    "id=" + id +
                    ", main='" + main + '\'' +
                    ", description='" + description + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
        }
    }

    public static class Main {
        double temp;
        double feels_like;
        double temp_min;
        double temp_max;
        int pressure;
        int humidity;

        @Override
        public String toString() {
            return "Main{" +
                    "temp=" + temp +
                    ", feels_like=" + feels_like +
                    ", temp_min=" + temp_min +
                    ", temp_max=" + temp_max +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    '}';
        }
    }

    public static class Wind {
        double speed;
        int deg;

        @Override
        public String toString() {
            return "Wind{" +
                    "speed=" + speed +
                    ", deg=" + deg +
                    '}';
        }
    }

    public static class Clouds {
        int all;

        @Override
        public String toString() {
            return "Clouds{" +
                    "all=" + all +
                    '}';
        }
    }

    public static class Sys {
        int type;
        int id;
        String country;
        long sunrise;
        long sunset;

        @Override
        public String toString() {
            return "Sys{" +
                    "type=" + type +
                    ", id=" + id +
                    ", country='" + country + '\'' +
                    ", sunrise=" + sunrise +
                    ", sunset=" + sunset +
                    '}';
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("WeatherModel{")
                .append("coord=").append(coord)
                .append(", weather=").append(weather)
                .append(", base='").append(base).append('\'')
                .append(", main=").append(main)
                .append(", visibility=").append(visibility)
                .append(", wind=").append(wind)
                .append(", clouds=").append(clouds)
                .append(", dt=").append(dt)
                .append(", sys=").append(sys)
                .append(", timezone=").append(timezone)
                .append(", id=").append(id)
                .append(", name='").append(name).append('\'')
                .append(", cod=").append(cod)
                .append('}');
        return builder.toString();
    }
}
