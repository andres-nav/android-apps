package com.andresnav.trackmyshoes;

import static com.andresnav.trackmyshoes.utils.FirebaseUtil.signOut;
import static com.andresnav.trackmyshoes.utils.UserUtil.getCurrentUser;
import static com.andresnav.trackmyshoes.utils.RunUtil.getRunsOfUser;
import static com.andresnav.trackmyshoes.utils.Utils.print;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andresnav.trackmyshoes.data.model.RunModel;
import com.andresnav.trackmyshoes.data.model.UserModel;
import com.andresnav.trackmyshoes.databinding.ActivityMainBinding;
import com.andresnav.trackmyshoes.utils.FirebaseUtil;
import com.andresnav.trackmyshoes.utils.RunUtil;
import com.andresnav.trackmyshoes.utils.UserUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RunsAdapter.ItemClickListener {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private TextView textViewUserId;

    private RunsAdapter adapter;

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

        Context context = this;

        getRunsOfUser(new RunUtil.RunsOfUserCallback() {
            @Override
            public void onRunsOfUserLoaded(ArrayList<RunModel> runs) {
                print("runs: " + runs.size());
                for (RunModel i : runs) {
                    print("run: " + i);
                }
                RecyclerView recyclerView = findViewById(R.id.recyclerViewRuns);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                adapter = new RunsAdapter(context, runs);
                adapter.setClickListener((RunsAdapter.ItemClickListener) context);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailed(String errorMessage) {
                print(errorMessage);
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

    @Override
    public void onItemClick(View view, int position) {
        print("You clicked " + adapter.getItem(position) + " on row number " + position);
    }
}