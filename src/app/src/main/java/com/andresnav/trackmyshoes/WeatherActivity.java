package com.andresnav.trackmyshoes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.andresnav.trackmyshoes.utils.OpenWeatherApiUtils;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        OpenWeatherApiUtils.getWeather("Madrid");

    }
}