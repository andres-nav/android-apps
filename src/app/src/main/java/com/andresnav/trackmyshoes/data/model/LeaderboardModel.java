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
    // FIXME: does not work, cannot map the document in Firestore to here, gives error.

    public LeaderboardModel(String bronce_run_id, String gold_run_id, String silver_run_id) {
        this.bronce_run_id = bronce_run_id;
        this.gold_run_id = gold_run_id;
        this.silver_run_id = silver_run_id;
    }

    public LeaderboardModel() {}

    public String getGoldRunId() {
        return gold_run_id;
    }

    public void setGoldRunId(String gold_run_id) {
        this.gold_run_id = gold_run_id;
    }

    public String getSilverRunId() {
        return silver_run_id;
    }

    public void setSilverRunId(String silver_run_id) {
        this.silver_run_id = silver_run_id;
    }

    public String getBronceRunId() {
        return bronce_run_id;
    }

    public void setBronceRunId(String bronce_run_id) {
        this.bronce_run_id = bronce_run_id;
    }


}
