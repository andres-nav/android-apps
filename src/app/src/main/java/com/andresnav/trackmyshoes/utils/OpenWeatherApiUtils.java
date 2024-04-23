package com.andresnav.trackmyshoes.utils;

import static com.andresnav.trackmyshoes.utils.Utils.print;

import com.andresnav.trackmyshoes.data.model.WeatherApi;
import com.andresnav.trackmyshoes.data.model.WeatherModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenWeatherApiUtils  {
    public static String OPEN_WEATHER="https://api.openweathermap.org/data/2.5/";
    public static String API_KEY = "16da0bcc1f7295172bc543ef3c2979a7";

    public interface WeatherCallback {
        void onSuccess(WeatherModel weather);
        void onFailed(String errorMessage);
    }

    public static void getWeather(final WeatherCallback callback) {
        String city = "Madrid";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OPEN_WEATHER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApi weatherApi= retrofit.create(WeatherApi.class);
        Call<WeatherModel> weather = weatherApi.getWeather(API_KEY, city);

        weather.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                callback.onFailed("Error fetching weather");
            }
        });
    }
}

