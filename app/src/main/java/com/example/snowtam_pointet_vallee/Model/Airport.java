package com.example.snowtam_pointet_vallee.Model;


import android.util.Log;

public class Airport implements java.io.Serializable{

    //=====================================
    //Attributs
    //=====================================

    private String codeICAO;
    private String snowtamOriginal; // SNOWTAM undecoded
    private String snowtamDecode; // SNOWTAM decoded
    private String airportName = "default name";
    private double[] coordonates; // coordonates ==> longitude, latitude

    //=====================================
    //Getters / Setters
    //=====================================

    public void setSnowtamOriginal(String snowtamOriginal) {
        this.snowtamOriginal = snowtamOriginal;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public void setCoordonates(double[] coordonates) {
        if(coordonates.length == 2){
            this.coordonates = coordonates;
        }
        else{
            this.coordonates = new double[] {0, 0} ;
            Log.e("AirportBuilder","Error coordonates");
        }
    }

    public void setCodeICAO(String codeICAO) {
        this.codeICAO = codeICAO;
    }

    public String getSnowtamOriginal() {
        return snowtamOriginal;
    }

    public String getSnowtamDecode() {
        return snowtamDecode;
    }

    public String getAirportName() {
        return airportName;
    }

    public double[] getCoordonates() { return coordonates; }

    //=====================================
    //Methodes
    //=====================================

    public void decode() {

        if(snowtamOriginal == null){
            snowtamDecode = " ";
        }
        else{
            String snowtam = snowtamOriginal;
            snowtam = decodeA(snowtam);
            snowtam = decodeB(snowtam);

            Log.i("Airport", "New decoded SNOWTAM : "+ snowtam);

            snowtamDecode = snowtam;
        }
    }

    private String decodeA(String snowtam) {

        int begin = snowtam.indexOf("A)") +2;
        int end = snowtam.indexOf("B)");

        snowtam = snowtam.replace(snowtam.substring(begin,end), " "+ airportName +"\n");

        return snowtam;
    }

    private String decodeB(String snowtam) {

        int begin = snowtam.indexOf("B)") +2;
        int end = snowtam.indexOf("C)");

    }
}
