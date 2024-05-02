package com.andresnav.trackmyshoes.data.model;

import android.location.Location;

import com.google.firebase.Timestamp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RunModel {

   private String name;
   private Timestamp timestamp;
   private Float totalKm;
   private Float totalTimeInMin;
   private ArrayList<Location> track;

   // TODO: add something related to the track taken

    public RunModel(String name, Timestamp timestamp, Float totalKm, Float totalTimeInMin, ArrayList<Location> track) {
        this.name = name;
        this.timestamp = timestamp;
        this.totalKm = totalKm;
        this.totalTimeInMin = totalTimeInMin;
        this.track = track;
    }

    public Float getTotalKm() {
        return totalKm;
    }

    public void setTotalKm(Float totalKm) {
        this.totalKm = totalKm;
    }

    public Float getTotalTimeInMin() {
        return totalTimeInMin;
    }

    public void setTotalTimeInMin(Float totalTimeInMin) {
        this.totalTimeInMin = totalTimeInMin;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Location> getTrack() {
        return this.track;
    }

    public void setTrack(ArrayList<Location> track) {
        this.track = track;
    }

    @Override
    public String toString() {
        return "RunModel{" +
                "name='" + name + '\'' +
                ", timestamp=" + timestamp +
                ", totalKm=" + totalKm +
                ", totalTimeInMin=" + totalTimeInMin +
                '}';
    }
}
