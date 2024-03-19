package com.andresnav.trackmyshoes;

import static com.andresnav.trackmyshoes.utils.FirebaseUtil.loadUser;

import android.content.Intent;
import android.os.Bundle;

import com.andresnav.trackmyshoes.utils.FirebaseUtil;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import com.andresnav.trackmyshoes.databinding.ActivityMainBinding;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private TextView textViewUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadUser();

        textViewUserId = findViewById(R.id.textViewUserId);
        textViewUserId.setText(String.format("user_id: %s", FirebaseUtil.currentUserId()));

        binding.fabStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TrackActivity.class);
                startActivity(intent);
            }
        });
    }
}