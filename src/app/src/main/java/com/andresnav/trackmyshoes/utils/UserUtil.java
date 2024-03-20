package com.andresnav.trackmyshoes.utils;

import static com.andresnav.trackmyshoes.utils.FirebaseUtil.currentUserId;
import static com.andresnav.trackmyshoes.utils.FirebaseUtil.isLoggedIn;
import static com.andresnav.trackmyshoes.utils.FirebaseUtil.signOut;

import android.util.Log;

import androidx.annotation.NonNull;

import com.andresnav.trackmyshoes.data.model.UserModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserUtil {

    public interface CurrentUserCallback {
        void onUserLoaded(UserModel user);
        void onFailed(String errorMessage);
    }

    public static void getCurrentUser(final CurrentUserCallback callback){
        if (!isLoggedIn()) {
            callback.onFailed("User is not logged in.");
            return;
        }

        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("users").document(currentUserId());

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        try {
                            UserModel user = documentSnapshot.toObject(UserModel.class);
                            callback.onUserLoaded(user);
                        } catch (RuntimeException exception) {
                            callback.onFailed("The current user document cannot be assigned to the UserModel class");
                            signOut();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onFailed("Cannot get the current user document");
                    }
                });
    }
}
