package com.andresnav.trackmyshoes;

import static com.andresnav.trackmyshoes.utils.Utils.print;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.andresnav.trackmyshoes.utils.LocationService;

import java.util.Objects;

public class TrackActivity extends AppCompatActivity {

    private LocationService.LocationServiceBinder locationService;

    private TextView textViewDistanceRun, textViewDuration, textViewAvgSpeed;
    private Button buttonStart, buttonPause, buttonSave;

    private static final int PERMISSION_GPS_CODE = 1;

    // will poll the location service for distance and duration
    private Handler postBack = new Handler();

    private ServiceConnection lsc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            locationService = (LocationService.LocationServiceBinder) iBinder;

            // if currently tracking then enable stopButton and disable startButton
            initButtons();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (locationService != null) {
                        // get the distance and duration from the surface
                        float d = (float) locationService.getDuration();
                        long duration = (long) d;  // in seconds
                        float distance = locationService.getDistance();

                        long hours = duration / 3600;
                        long minutes = (duration % 3600) / 60;
                        long seconds = duration % 60;

                        float avgSpeed = 0;
                        if(d != 0) {
                            avgSpeed = distance / (d / 3600);
                        }

                        final String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                        final String dist = String.format("%.2f KM", distance);
                        final String avgs = String.format("%.2f KM/H", avgSpeed);

                        postBack.post(new Runnable() {
                            @Override
                            public void run() {
                                // post back changes to UI thread
                                textViewDuration.setText(time);
                                textViewAvgSpeed.setText(avgs);
                                textViewDistanceRun.setText(dist);
                            }
                        });

                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            locationService = null;
        }
    };

    // whenever activity is reloaded while still tracking a journey (if back button is clicked for example)
    private void initButtons() {
        // no permissions means no buttons
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            buttonPause.setEnabled(false);
            buttonStart.setEnabled(false);
            buttonSave.setEnabled(false);
            return;
        }

        // if currently tracking then enable stopButton and disable startButton
        if(locationService != null && locationService.currentlyTracking()) {
            buttonPause.setEnabled(true);
            buttonStart.setEnabled(false);
            buttonSave.setEnabled(false);
        } else {
            buttonPause.setEnabled(false);
            buttonStart.setEnabled(true);
            buttonSave.setEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        textViewDistanceRun = findViewById(R.id.textViewDistanceRun);
        textViewDuration = findViewById(R.id.textViewDuration);
        textViewAvgSpeed = findViewById(R.id.textViewAvgSpeed);

        buttonStart = findViewById(R.id.buttonStart);
        buttonPause = findViewById(R.id.buttonPause);
        buttonSave = findViewById(R.id.buttonSave);

        buttonStart.setEnabled(false);
        buttonPause.setEnabled(false);
        buttonSave.setEnabled(false);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationService.playJourney();
                buttonStart.setEnabled(false);
                buttonPause.setEnabled(true);
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float distance = locationService.getDistance();
                locationService.saveJourney();

                buttonStart.setEnabled(false);
                buttonPause.setEnabled(false);

                DialogFragment modal = FinishedTrackingDialogue.newInstance(String.format("%.2f KM", distance));
                modal.show(getSupportFragmentManager(), "Finished");

            }
        });

        handlePermissions();

        startService(new Intent(this, LocationService.class));
        bindService(
                new Intent(this, LocationService.class), lsc, Context.BIND_AUTO_CREATE);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // unbind to the service (if we are the only binding activity then the service will be destroyed)
        if(lsc != null) {
            unbindService(lsc);
            lsc = null;
        }
    }

    public static class FinishedTrackingDialogue extends DialogFragment {
        public static  FinishedTrackingDialogue newInstance(String distance) {
            Bundle savedInstanceState = new Bundle();
            savedInstanceState.putString("distance", distance);
            FinishedTrackingDialogue frag = new FinishedTrackingDialogue();
            frag.setArguments(savedInstanceState);
            return frag;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Your Journey has been saved. You ran a total of " + getArguments().getString("distance") + " KM")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // go back to home screen
                            requireActivity().finish();
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

    @Override
    public void onRequestPermissionsResult(int reqCode, String[] permissions, int[] results) {
        super.onRequestPermissionsResult(reqCode, permissions, results);
        switch (reqCode) {
            case PERMISSION_GPS_CODE:
                if (results.length > 0 && results[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted
                    initButtons();
                    if (locationService != null) {
                        locationService.notifyGPSEnabled();
                    }
                } else {
                    // permission denied, disable GPS tracking buttons
                    buttonPause.setEnabled(false);
                    buttonStart.setEnabled(false);
                    buttonSave.setEnabled(false);
                }

        }
    }

    public static class NoPermissionDialogue extends DialogFragment {
        public static  NoPermissionDialogue newInstance() {
            NoPermissionDialogue frag = new NoPermissionDialogue();
            return frag;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(getString(R.string.dialog_gps))
                    .setPositiveButton(getString(R.string.text_enable_gps), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // user agreed to enable GPS
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_GPS_CODE);
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
        // if not granted the permission disable the start button
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                // the user has already declined request to allow GPS
                // give them a pop up explaining why its needed and re-ask
                DialogFragment modal = NoPermissionDialogue.newInstance();
                modal.show(getSupportFragmentManager(), "Permissions");
            } else {
                // request the permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_GPS_CODE);
            }
        }
    }
}