package com.andresnav.trackmyshoes.data.model;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LeaderboardUtil {

    private static final String TAG = "GoogleFirestore";

    public static LeaderboardModel leaderboard = null;

    public static void getLeaderboard() {
        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("leaderboards").document("general");

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        try {
                            Log.d(TAG, String.join("document", String.valueOf(documentSnapshot)));
                            leaderboard = documentSnapshot.toObject(LeaderboardModel.class);
                            Log.d(TAG, String.join("good ", String.valueOf(leaderboard)));
                        } catch (RuntimeException exception) {
                            Log.d(TAG, "The current leaderboard document cannot be assigned to the LeaderboardModel class");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Cannot get the current user document");
                    }
                });
    }


}
