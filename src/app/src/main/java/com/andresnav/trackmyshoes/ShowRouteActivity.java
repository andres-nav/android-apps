package com.andresnav.trackmyshoes;

import static com.andresnav.trackmyshoes.utils.LeaderboardUtil.getLeaderboard;
import static com.andresnav.trackmyshoes.utils.Utils.print;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.andresnav.trackmyshoes.data.model.LeaderboardModel;
import com.andresnav.trackmyshoes.data.model.RunModel;
import com.andresnav.trackmyshoes.utils.LeaderboardUtil;
import com.andresnav.trackmyshoes.utils.LocationProvider;
import com.andresnav.trackmyshoes.utils.PermissionManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;


public class ShowRouteActivity extends AppCompatActivity implements OnMapReadyCallback {

    SupportMapFragment map;

    RunModel run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_route);

        Intent intent = getIntent();
        run = (RunModel) intent.getSerializableExtra(getString(R.string.run_serialize_string));
        print(String.format("run: %s", run));

        map = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        map.getMapAsync(this);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        List<RunModel.Coordinate> track = run.getTrack();
        if (track != null && track.size() > 0) {
            // Add markers for start and end points
            LatLng startPoint = new LatLng(track.get(0).getLatitude(), track.get(0).getLongitude());
            LatLng endPoint = new LatLng(track.get(track.size() - 1).getLatitude(), track.get(track.size() - 1).getLongitude());

            googleMap.addMarker(new MarkerOptions().position(startPoint).title("Start Point"));
            googleMap.addMarker(new MarkerOptions().position(endPoint).title("End Point"));

            // Add polylines for the path
            PolylineOptions polylineOptions = new PolylineOptions();
            for (RunModel.Coordinate coordinate : track) {
                polylineOptions.add(new LatLng(coordinate.getLatitude(), coordinate.getLongitude()));
            }
            googleMap.addPolyline(polylineOptions);

            // Move camera to the start point
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startPoint, 14f));
        }
    }
}