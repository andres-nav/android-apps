package com.andresnav.trackmyshoes.data.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherApi {

    // FIXME: maybe i can join both api creating two calls
    @GET("weather")
    Call<WeatherModel> getWeather(@Query("APPID") String api_key, @Query("q") String city);
}
