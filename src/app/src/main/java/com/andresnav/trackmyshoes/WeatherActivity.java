package com.andresnav.trackmyshoes;

import static com.andresnav.trackmyshoes.utils.Utils.print;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.andresnav.trackmyshoes.data.model.WeatherModel;
import com.andresnav.trackmyshoes.utils.LocationProvider;
import com.andresnav.trackmyshoes.utils.OpenWeatherApiUtils;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.Picasso;

import org.conscrypt.Conscrypt;

import java.io.IOException;
import java.net.URL;
import java.security.Security;
import java.util.List;
import java.util.Locale;

public class WeatherActivity extends AppCompatActivity {

    TextView textViewWeather, textViewLocation, textViewCurrentTemperature, textViewFeelsLike, textViewWind;
    ImageView imageViewToday;

    private static final int PERMISSION_GPS_CODE = 1;


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

        handlePermissions();

    }

    private void loadWeather() {
        OpenWeatherApiUtils.getWeather(this, new OpenWeatherApiUtils.WeatherCallback() {
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

    @Override
    public void onRequestPermissionsResult(int reqCode, String[] permissions, int[] results) {
        super.onRequestPermissionsResult(reqCode, permissions, results);
        switch (reqCode) {
            case PERMISSION_GPS_CODE:
                if (results.length > 0 && results[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted
                    loadWeather();
                }
        }
    }

    public static class NoPermissionDialogue extends DialogFragment {
        public static TrackActivity.NoPermissionDialogue newInstance() {
            TrackActivity.NoPermissionDialogue frag = new TrackActivity.NoPermissionDialogue();
            return frag;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(getString(R.string.dialog_weather))
                    .setPositiveButton(getString(R.string.text_enable_gps), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // user agreed to enable GPS
                            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_GPS_CODE);
                        }
                    })
                    .setNegativeButton(getString(R.string.text_cancel), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // dialogue was cancelled
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

    private void handlePermissions() {
        // if don't have GPS permissions then request this permission from the user.
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                // the user has already declined request to allow GPS
                // give them a pop up explaining why its needed and re-ask
                DialogFragment modal = TrackActivity.NoPermissionDialogue.newInstance();
                modal.show(getSupportFragmentManager(), "Permissions");
            } else {
                // request the permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_GPS_CODE);
            }
        } else {
            loadWeather();
        }

    }
}