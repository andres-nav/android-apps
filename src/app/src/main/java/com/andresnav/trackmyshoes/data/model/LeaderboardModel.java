package com.andresnav.trackmyshoes.data.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LeaderboardModel {

    private String gold_run_id, silver_run_id, bronce_run_id;

    // TODO: add something related to the track taken

    public LeaderboardModel(String gold_run_id, String silver_run_id, String bronce_run_id) {
        this.gold_run_id = gold_run_id;
        this.silver_run_id = silver_run_id;
        this.bronce_run_id = bronce_run_id;
    }

    public LeaderboardModel() {}

}
