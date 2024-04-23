package com.andresnav.trackmyshoes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class TrackActivity extends AppCompatActivity implements OnMapReadyCallback{

    SupportMapFragment map;

    //private final LocationProvider locationProvider = new LocationProvider(this);
    //private final PermissionManager permissionManager = new PermissionManager(this, locationProvider);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        map = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        map.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney"));
        // [START_EXCLUDE silent]
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
}