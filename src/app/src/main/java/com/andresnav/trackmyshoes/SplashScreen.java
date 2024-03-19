package com.andresnav.trackmyshoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.andresnav.trackmyshoes.utils.FirebaseUtil;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("test", "logg" + FirebaseUtil.isLoggedIn());
                Log.d("test", "user id" + FirebaseUtil.currentUserId());

                if(FirebaseUtil.isLoggedIn()){
                    Log.d("test", "login true");
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                }
                else {
                    Log.d("test", "login false");
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                }
                finish();
            }
        }, 1000);
    }
}