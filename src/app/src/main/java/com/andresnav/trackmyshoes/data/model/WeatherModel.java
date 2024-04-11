package com.andresnav.trackmyshoes.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherModel {

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

    public static class Coord {
        double lon;
        double lat;

        // Getters and setters for lon and lat
    }

    public static class Weather {
        int id;
        String main;
        String description;
        String icon;

        // Getters and setters for all the fields
    }

    public static class Main {
        double temp;
        double feels_like;
        double temp_min;
        double temp_max;
        int pressure;
        int humidity;

        // Getters and setters for all the fields
    }

    public static class Wind {
        double speed;
        int deg;

        // Getters and setters for all the fields
    }

    public static class Clouds {
        int all;

        // Getters and setters for all the fields
    }

    public static class Sys {
        int type;
        int id;
        String country;
        long sunrise;
        long sunset;

        // Getters and setters for all the fields
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
