package com.andresnav.trackmyshoes;

import static com.andresnav.trackmyshoes.utils.Utils.print;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.andresnav.trackmyshoes.data.model.WeatherModel;
import com.andresnav.trackmyshoes.utils.OpenWeatherApiUtils;

import java.io.IOException;
import java.net.URL;

public class WeatherActivity extends AppCompatActivity {

    TextView textViewWeather, textViewLocation, textViewCurrentTemperature, textViewFeelsLike, textViewWind;
    ImageView imageViewToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

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

                loadImage(imageViewToday, weather.getWeatherIcon());
            }

            @Override
            public void onFailed(String errorMessage) {

            }
        });

    }

    public void loadImage(final ImageView imageView, final String icon) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(String.format("https://openweathermap.org/img/wn/%s@2x.png", icon));
                    final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    imageView.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bmp);
                        }
                    });
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.start();
    }
}