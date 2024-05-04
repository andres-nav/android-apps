package com.andresnav.trackmyshoes;

import static com.andresnav.trackmyshoes.utils.FirebaseUtil.signOutFirebase;
import static com.andresnav.trackmyshoes.utils.UserUtil.getCurrentUser;
import static com.andresnav.trackmyshoes.utils.RunUtil.getRunsOfUser;
import static com.andresnav.trackmyshoes.utils.Utils.print;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements RunsAdapter.ItemClickListener {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private TextView textViewUserId;
    private RecyclerView recyclerView;

    private ArrayList<RunModel> userRuns;
    private Context contextActivity;

    private RunsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(findViewById(R.id.toolbar));

        getCurrentUser(new UserUtil.CurrentUserCallback() {
            @Override
            public void onUserLoaded(UserModel user) {
               print(String.valueOf(user.getEmail()));
            }

            @Override
            public void onFailed(String errorMessage) {

            }
        });

        contextActivity = this;


        binding.fabStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TrackActivity.class);
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

    private void loadRuns() {
        getRunsOfUser(new RunUtil.RunsOfUserCallback() {
            @Override
            public void onRunsOfUserLoaded(ArrayList<RunModel> runs) {
                // Sort the runs by timestamp (most recent first)
                runs.sort(Comparator.comparingLong(RunModel::getTimestamp).reversed());

                userRuns = runs;

                recyclerView = findViewById(R.id.recyclerViewRuns);
                recyclerView.setLayoutManager(new LinearLayoutManager(contextActivity));
                adapter = new RunsAdapter(contextActivity, runs);
                adapter.setClickListener((RunsAdapter.ItemClickListener) contextActivity);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailed(String errorMessage) {
                print(errorMessage);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRuns();
    }

    @Override
    public void onItemClick(View view, int position) {
        print("You clicked " + adapter.getItem(position) + " on row number " + position);

        Intent intent = new Intent(MainActivity.this, ShowRouteActivity.class);
        RunModel runClicked = userRuns.get(position);
        intent.putExtra(getString(R.string.run_serialize_string), runClicked);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu); // Inflate the menu resource
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.item_signout) {
            signOutFirebase();
            Intent intent = new Intent(MainActivity.this, SplashScreen.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}