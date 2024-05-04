package com.andresnav.trackmyshoes;

import static com.andresnav.trackmyshoes.utils.Utils.print;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.andresnav.trackmyshoes.data.model.WeatherModel;
import com.andresnav.trackmyshoes.utils.OpenWeatherApiUtils;
import com.squareup.picasso.Picasso;

import org.conscrypt.Conscrypt;

import java.io.IOException;
import java.net.URL;
import java.security.Security;

public class WeatherActivity extends AppCompatActivity {

    TextView textViewWeather, textViewLocation, textViewCurrentTemperature, textViewFeelsLike, textViewWind;
    ImageView imageViewToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewWeather = findViewById(R.id.textViewWeather);
        textViewLocation = findViewById(R.id.textViewLocation);
        textViewCurrentTemperature = findViewById(R.id.textViewCurrentTemperature);
        textViewFeelsLike = findViewById(R.id.textViewFeelsLike);
        textViewWind = findViewById(R.id.textViewWind);

        imageViewToday = findViewById(R.id.imageViewToday);

        OpenWeatherApiUtils.getWeather(new OpenWeatherApiUtils.WeatherCallback() {
            @Override
            public void onSuccess(WeatherModel weather) {
                textViewLocation.setText(String.valueOf(weather.getLocation()));
                textViewCurrentTemperature.setText(String.format("%s °C", weather.getTemperatureNow()));
                textViewFeelsLike.setText(String.format("%s °C", weather.getFeelsLikeNow()));
                textViewWind.setText(String.format("%s km/h", weather.getWind()));

                int weatherStringId = weather.getWeatherStringId();
                if (weatherStringId != 0) {
                    textViewWeather.setText(getString(weatherStringId));
                }

                Security.insertProviderAt(Conscrypt.newProvider(), 1); // To enable TLS v1.3 for API 28 and below
                Picasso.get().load(String.format("https://openweathermap.org/img/wn/%s@2x.png", weather.getWeatherIcon())).into(imageViewToday);
            }

            @Override
            public void onFailed(String errorMessage) {

            }
        });

    }
}