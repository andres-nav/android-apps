package com.andresnav.trackmyshoes.utils;

import static com.andresnav.trackmyshoes.utils.FirebaseUtil.currentUserId;
import static com.andresnav.trackmyshoes.utils.FirebaseUtil.isLoggedIn;
import static com.andresnav.trackmyshoes.utils.FirebaseUtil.signOut;
import static com.andresnav.trackmyshoes.utils.Utils.print;

import androidx.annotation.NonNull;

import com.andresnav.trackmyshoes.data.model.RunModel;
import com.andresnav.trackmyshoes.data.model.UserModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Timestamp;
import java.util.ArrayList;

public class RunUtil {

    public interface RunsOfUserCallback {
        void onRunsOfUserLoaded(ArrayList<RunModel> runs);
        void onFailed(String errorMessage);
    }

    public static void getRunsOfUser(final RunsOfUserCallback callback){
        ArrayList<RunModel> runs = new ArrayList<>();

        CollectionReference documentReference = FirebaseFirestore.getInstance().collection(String.join("/", "users", currentUserId(), "runs"));

        documentReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot querySnapshot) {
                        try {
                            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                                RunModel run = document.toObject(RunModel.class);
                                runs.add(run);
                            }

                            callback.onRunsOfUserLoaded(runs);
                        } catch (RuntimeException exception) {
                            callback.onFailed("The run document cannot be assigned to the RunModel class");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onFailed("Cannot get the run document");
                    }
                });
    }

    public interface SaveRunCallback {
        void onSuccess();
        void onFailure(String errorMessage);
    }

    public static void saveRun(RunModel run, final SaveRunCallback callback) {
        CollectionReference runsCollection = FirebaseFirestore.getInstance()
                .collection(String.join("/", "users", FirebaseUtil.currentUserId(), "runs"));

        runsCollection.add(run)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        callback.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onFailure("Failed to save the run: " + e.getMessage());
                    }
                });
    }
}
