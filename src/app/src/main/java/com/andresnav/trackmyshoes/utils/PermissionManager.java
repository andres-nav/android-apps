package com.andresnav.trackmyshoes.utils;


import android.Manifest;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class PermissionManager {

    private AppCompatActivity activity;
    private LocationProvider locationProvider;

    //1
    private final ActivityResultLauncher<String> locationPermissionProvider =
            activity.registerForActivityResult(
                    new ActivityResultContracts.RequestPermission(),
                    isGranted -> {
                        if (isGranted) {
                            locationProvider.getUserLocation();
                        }
                    });

    public PermissionManager(AppCompatActivity activity, LocationProvider locationProvider) {
        this.activity = activity;
        this.locationProvider = locationProvider;
    }

    //2
    public void requestUserLocation() {
        locationPermissionProvider.launch(Manifest.permission.ACCESS_FINE_LOCATION);
    }
}
