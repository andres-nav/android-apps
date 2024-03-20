package com.andresnav.trackmyshoes;

import static com.andresnav.trackmyshoes.utils.LeaderboardUtil.getLeaderboard;
import static com.andresnav.trackmyshoes.utils.Utils.print;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.andresnav.trackmyshoes.data.model.LeaderboardModel;
import com.andresnav.trackmyshoes.utils.LeaderboardUtil;


public class LeaderboardActivity extends AppCompatActivity {

    private static final String TAG = "TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        getLeaderboard(new LeaderboardUtil.LeaderboardCallback() {
            @Override
            public void onLeaderboardLoaded(LeaderboardModel leaderboard) {
               print(String.valueOf(leaderboard.getGoldRunId()));
            }

            @Override
            public void onFailed(String errorMessage) {
                print(errorMessage);

            }
        });
    }
}