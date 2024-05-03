package com.andresnav.trackmyshoes.data.model;

import android.location.Location;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.PropertyName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunModel implements Serializable {
    private String name;
    private long timestamp;
    private double totalKm;
    private double totalTimeInMin;
    private List<Coordinate> track;

    // Constructor
    public RunModel() {}


    public RunModel(String name, long timestamp, Float totalKm, Float totalTimeInMin, List<Coordinate> track) {
        this.name = name;
        this.timestamp = timestamp;
        this.totalKm = totalKm;
        this.totalTimeInMin = totalTimeInMin;
        this.track = track;
    }

    public RunModel(String name, long timestamp, Float totalKm, Float totalTimeInMin, ArrayList<Location> track) {
        this.name = name;
        this.timestamp = timestamp;
        this.totalKm = totalKm;
        this.totalTimeInMin = totalTimeInMin;
        this.track = convertLocationList(track);
    }

    public static List<Coordinate> convertLocationList(ArrayList<Location> locations) {
        List<Coordinate> track = new ArrayList<>();
        for (Location location : locations) {
            Coordinate coordinate = new Coordinate();
            coordinate.setLatitude(location.getLatitude());
            coordinate.setLongitude(location.getLongitude());
            track.add(coordinate);
        }
        return track;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getTotalKm() {
        return totalKm;
    }

    public void setTotalKm(double totalKm) {
        this.totalKm = totalKm;
    }

    public double getTotalTimeInMin() {
        return totalTimeInMin;
    }

    public void setTotalTimeInMin(double totalTimeInMin) {
        this.totalTimeInMin = totalTimeInMin;
    }

    public List<Coordinate> getTrack() {
        return track;
    }

    public void setTrack(List<Coordinate> track) {
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

    // Nested class for coordinates
    public static class Coordinate implements Serializable {
        private double latitude;
        private double longitude;

        // Constructor
        public Coordinate() {}

        // Getters and setters
        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }
}
