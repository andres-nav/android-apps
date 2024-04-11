package com.andresnav.trackmyshoes.utils;

import static com.andresnav.trackmyshoes.utils.Utils.print;

import com.andresnav.trackmyshoes.R;
import com.andresnav.trackmyshoes.data.model.ForecastApi;
import com.andresnav.trackmyshoes.data.model.ForecastModel;
import com.andresnav.trackmyshoes.data.model.WeatherApi;
import com.andresnav.trackmyshoes.data.model.WeatherModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenWeatherApiUtils  {
    public static String OPEN_WEATHER="https://api.openweathermap.org/data/2.5/";
    public static String API_KEY = "16da0bcc1f7295172bc543ef3c2979a7";

    public static void getWeather(String city) {
        print("getting forecast");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OPEN_WEATHER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApi weatherApi= retrofit.create(WeatherApi.class);
        Call<WeatherModel> weather = weatherApi.getWeather(API_KEY, "Madrid");

        weather.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                print("response" + response.body().toString());
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                print("Error fetching weather");
            }
        });
    }
}

