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

    private static final String TAG = "GoogleFirestore";

    public static String currentUserId(){
        return FirebaseAuth.getInstance().getUid();
    }

    public static boolean isLoggedIn(){
        if(currentUserId() != null){
            return true;
        }
        return false;
    }

    public static void signOutFirebase() {
        FirebaseAuth.getInstance().signOut();
        Log.d(TAG, "Signing out user");
    }

}
