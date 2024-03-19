package com.andresnav.trackmyshoes;

import static com.andresnav.trackmyshoes.data.model.LeaderboardUtil.getLeaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.andresnav.trackmyshoes.data.model.LeaderboardUtil;


public class LeaderboardActivity extends AppCompatActivity {

    private static final String TAG = "TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        getLeaderboard();
    }
}