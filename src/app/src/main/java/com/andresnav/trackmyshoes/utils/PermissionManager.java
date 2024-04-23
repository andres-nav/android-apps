package com.andresnav.trackmyshoes.utils;


import static com.andresnav.trackmyshoes.utils.Utils.print;

import android.Manifest;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class PermissionManager {

    private AppCompatActivity activity;
    private LocationProvider locationProvider;

    //1
    private final ActivityResultLauncher<String> locationPermissionLauncher;

    public PermissionManager(AppCompatActivity activity, LocationProvider locationProvider) {
        this.activity = activity;
        this.locationProvider = locationProvider;

        locationPermissionLauncher = activity.registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean isGranted) {
                        if (isGranted) {
                            locationProvider.getUserLocation();
                        } else {
                            // Handle permission denied
                        }
                    }
                });
    }

    //2
    public void requestUserLocation() {
        locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
    }
}
