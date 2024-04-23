package com.andresnav.trackmyshoes.utils;

import android.annotation.SuppressLint;
import android.location.Location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("MissingPermission")
public class LocationProvider {

    private FusedLocationProviderClient client;
    private AppCompatActivity activity;

    private final List<LatLng> locations = new ArrayList<>();

    final MutableLiveData<LatLng> liveLocation = new MutableLiveData<>();

    public LocationProvider(AppCompatActivity activity) {
        this.activity = activity;
        this.client = LocationServices.getFusedLocationProviderClient(activity);
    }

    public MutableLiveData<LatLng> getLiveLocation() {
        return liveLocation;
    }

    //4
    public void getUserLocation() {
        Task<android.location.Location> locationTask = client.getLastLocation();
        locationTask.addOnSuccessListener(activity, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(android.location.Location location) {
                if (location != null) {
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    locations.add(latLng);
                    liveLocation.setValue(latLng);
                }
            }
        });
    }
}
