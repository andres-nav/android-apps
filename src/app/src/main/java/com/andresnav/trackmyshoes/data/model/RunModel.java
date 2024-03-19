package com.andresnav.trackmyshoes.data.model;

import com.google.firebase.Timestamp;

import java.util.HashMap;
import java.util.Map;

public class RunModel {

   private String name;
   private Timestamp date;
   private Float totalKm;
   private Float totalTimeInMin;

   // TODO: add something related to the track taken

    public RunModel(String name, Timestamp date, Float totalKm, Float totalTimeInMin) {
        this.name = name;
        this.date = date;
        this.totalKm = totalKm;
        this.totalTimeInMin = totalTimeInMin;
    }
}
