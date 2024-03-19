package com.andresnav.trackmyshoes.data.model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class UserModel {
    private String email;
    private ArrayList<RunModel> runs;

    public UserModel(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserModel() {}
}