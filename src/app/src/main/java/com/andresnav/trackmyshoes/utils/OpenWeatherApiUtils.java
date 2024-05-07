package com.andresnav.trackmyshoes.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import static com.andresnav.trackmyshoes.utils.Utils.print;

import androidx.appcompat.app.AppCompatActivity;

import com.andresnav.trackmyshoes.data.model.WeatherApi;
import com.andresnav.trackmyshoes.data.model.WeatherModel;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

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


    public static void getWeather(AppCompatActivity context, final WeatherCallback callback) {
        LocationProvider locationProvider = new LocationProvider(context);

        locationProvider.getCurrentLocation(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                print(String.format("location %s", location));
                if (location != null) {
                    Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                    List<Address> addresses = null;
                    try {
                        addresses = geocoder.getFromLocation(
                                location.getLatitude(),
                                location.getLongitude(),
                                1);

                        if (!addresses.isEmpty()) {
                            String city = addresses.get(0).getLocality();
                            getWeatherFromCity(city, callback);
                        }
                    } catch (IOException e) {
                        print("error");
                        throw new RuntimeException(e);
                    }
                }
            }
        });

    }

    private static void getWeatherFromCity(String city, final WeatherCallback callback) {
        if (city.isEmpty()) {
            callback.onFailed("Cannot detect the city");
        }

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

