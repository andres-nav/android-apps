package com.andresnav.trackmyshoes;

import static com.andresnav.trackmyshoes.utils.LeaderboardUtil.getLeaderboard;
import static com.andresnav.trackmyshoes.utils.Utils.print;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.andresnav.trackmyshoes.data.model.LeaderboardModel;
import com.andresnav.trackmyshoes.utils.LeaderboardUtil;
import com.andresnav.trackmyshoes.utils.LocationProvider;
import com.andresnav.trackmyshoes.utils.PermissionManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class ShowRouteActivity extends AppCompatActivity implements OnMapReadyCallback {

    SupportMapFragment map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_route);

        map = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        map.getMapAsync(this);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney"));
        // [START_EXCLUDE silent]
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}