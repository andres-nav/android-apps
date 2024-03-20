package com.andresnav.trackmyshoes.utils;


import static com.andresnav.trackmyshoes.utils.Utils.print;

import android.util.Log;

import androidx.annotation.NonNull;

import com.andresnav.trackmyshoes.data.model.LeaderboardModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LeaderboardUtil {

    public interface LeaderboardCallback {
        void onLeaderboardLoaded(LeaderboardModel leaderboard);
        void onFailed(String errorMessage);
    }

    public static void getLeaderboard(final LeaderboardCallback callback) {
        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("leaderboards").document("general");

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        try {
                            print(String.valueOf(documentSnapshot));
                            LeaderboardModel leaderboard = documentSnapshot.toObject(LeaderboardModel.class);
                            callback.onLeaderboardLoaded(leaderboard);
                        } catch (RuntimeException exception) {
                            callback.onFailed("The current leaderboard document cannot be assigned to the LeaderboardModel class");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onFailed("Cannot get the leaderboard document");
                    }
                });
    }


}
