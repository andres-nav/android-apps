package com.andresnav.trackmyshoes.data.model;

import com.google.firebase.Timestamp;

public class UserModel {
    private String username;
    private Timestamp createdTimeStamp;

    public UserModel(String username, Timestamp createdTimeStamp) {
        this.username = username;
        this.createdTimeStamp = createdTimeStamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Timestamp createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }
}