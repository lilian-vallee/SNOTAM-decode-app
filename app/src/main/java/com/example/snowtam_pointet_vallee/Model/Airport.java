package com.example.snowtam_pointet_vallee.Model;

import android.content.res.Resources;

import com.example.snowtam_pointet_vallee.R;

public class Airport {

    private String snowtamOriginal;
    private String snowtamDecode;
    private String airportName = "name";
    private float longitude;
    private float lattitude;

    public String getSnowtamOriginal() {
        return snowtamOriginal;
    }

    public String getSnowtamDecode() {
        return snowtamDecode;
    }

    public String getAirportName() {
        return airportName;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLattitude() {
        return lattitude;
    }

    public Airport(String original) {
        this.snowtamOriginal = original;
        System.out.println(original);
    }

    public Airport() {}
}
