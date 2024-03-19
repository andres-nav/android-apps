package com.andresnav.trackmyshoes.utils;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.andresnav.trackmyshoes.LoginActivity;
import com.andresnav.trackmyshoes.SplashScreen;
import com.andresnav.trackmyshoes.data.model.UserModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseUtil {

    public static UserModel user = null;

    private static final String TAG = "GoogleFirestore";

    public static String currentUserId(){
        return FirebaseAuth.getInstance().getUid();
    }

    public static boolean isLoggedIn(){
        if(currentUserId()!=null){
            return true;
        }
        return false;
    }

    public static void signOut() {
        user = null;
        FirebaseAuth.getInstance().signOut();
        Log.d(TAG, "Signing out user");
    }

    public static void loadUser(){
        if (!isLoggedIn()) {
            return;
        }

        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("users").document(currentUserId());

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                try {
                    user = documentSnapshot.toObject(UserModel.class);
                    Log.d(TAG, user.getEmail());
                } catch (RuntimeException exception) {
                    Log.d(TAG, "The current user document cannot be assigned to the UserModel class");
                    signOut();
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
