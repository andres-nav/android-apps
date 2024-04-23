package com.andresnav.trackmyshoes;

import static com.andresnav.trackmyshoes.utils.Utils.print;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.andresnav.trackmyshoes.utils.LocationProvider;
import com.andresnav.trackmyshoes.utils.PermissionManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class TrackActivity extends AppCompatActivity implements OnMapReadyCallback {

    private SupportMapFragment map;

    private LocationProvider locationProvider;
    private PermissionManager permissionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        locationProvider = new LocationProvider(this);
        permissionManager = new PermissionManager(this, locationProvider);

        map = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        map.getMapAsync(this);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        locationProvider.getLiveLocation().observe(this, new Observer<LatLng>() {
            @Override
            public void onChanged(LatLng latLng) {
                print("moveedd");
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14f));
            }
        });

        permissionManager.requestUserLocation();

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMyLocationEnabled(true);
    }
}