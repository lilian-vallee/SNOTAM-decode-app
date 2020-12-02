package com.example.snowtam_pointet_vallee.Model;


public class Airport implements java.io.Serializable{

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
