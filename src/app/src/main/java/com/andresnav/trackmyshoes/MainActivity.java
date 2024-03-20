package com.andresnav.trackmyshoes;

import static com.andresnav.trackmyshoes.utils.FirebaseUtil.signOut;
import static com.andresnav.trackmyshoes.utils.UserUtil.getCurrentUser;
import static com.andresnav.trackmyshoes.utils.Utils.print;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.andresnav.trackmyshoes.data.model.UserModel;
import com.andresnav.trackmyshoes.databinding.ActivityMainBinding;
import com.andresnav.trackmyshoes.utils.FirebaseUtil;
import com.andresnav.trackmyshoes.utils.UserUtil;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private TextView textViewUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getCurrentUser(new UserUtil.CurrentUserCallback() {
            @Override
            public void onUserLoaded(UserModel user) {
               print(String.valueOf(user.getEmail()));
            }

            @Override
            public void onFailed(String errorMessage) {

            }
        });

        textViewUserId = findViewById(R.id.textViewUserId);
        textViewUserId.setText(String.format("user_id: %s", FirebaseUtil.currentUserId()));

        binding.buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                Intent intent = new Intent(MainActivity.this, SplashScreen.class);
                startActivity(intent);
                finish();
            }
        });

        binding.fabStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TrackActivity.class);
                startActivity(intent);
            }
        });

        binding.fabLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LeaderboardActivity.class);
                startActivity(intent);
            }
        });

        binding.fabWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                startActivity(intent);
            }
        });
    }
}