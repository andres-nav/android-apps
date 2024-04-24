package com.andresnav.trackmyshoes.utils;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("MissingPermission")
public class LocationProvider {

    private FusedLocationProviderClient client;
    private AppCompatActivity activity;

    private final List<LatLng> locations = new ArrayList<>();

    private float distance = 0;

    final MutableLiveData<LatLng> liveLocation = new MutableLiveData<>();
    final MutableLiveData<List<LatLng>> liveLocations = new MutableLiveData<>();
    final MutableLiveData<Float> liveDistance = new MutableLiveData<>();


    private LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult result) {
            Location currentLocation = result.getLastLocation();
            LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());

            LatLng lastLocation = locations.isEmpty() ? null : locations.get(locations.size() - 1);

            if (lastLocation != null) {
                distance += Math.round(SphericalUtil.computeDistanceBetween(lastLocation, latLng));
                liveDistance.setValue(distance);
            }

            locations.add(latLng);
            liveLocations.setValue(locations);
        }
    };

    public LocationProvider(AppCompatActivity activity) {
        this.activity = activity;
        this.client = LocationServices.getFusedLocationProviderClient(activity);
    }

    public MutableLiveData<List<LatLng>> getLiveLocations() {
        return liveLocations;
    }

    public MutableLiveData<LatLng> getLiveLocation() {
        return liveLocation;
    }

    public MutableLiveData<Float> getLiveDistance() {
        return liveDistance;
    }

    public void trackUser() {
        //1
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);

        //2
        client.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }


    public void stopTracking() {
        client.removeLocationUpdates(locationCallback);
        locations.clear();
        distance = 0;
    }

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
