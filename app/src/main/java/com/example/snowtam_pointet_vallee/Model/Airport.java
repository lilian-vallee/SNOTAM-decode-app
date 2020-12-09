package com.example.snowtam_pointet_vallee.Model;


import android.util.Log;

public class Airport implements java.io.Serializable{

    private String codeICAO;
    private String snowtamOriginal;
    private String snowtamDecode;
    private String airportName = "name";
    private double[] coordonates;

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


    private void startDecode() {
        String snowtam = decodeA(snowtamOriginal);
    }

    public void decode() {

        if(snowtamOriginal == null){
            snowtamDecode = " ";
        }
        else{
            String snowtam = "";
            snowtam = decodeA(snowtam);

            Log.i("Airport", "New decoded SNOWTAM : "+ snowtam);
            snowtamDecode = snowtam;
        }
    }

    private String decodeA(String snowtam) {

        int begin = snowtamOriginal.indexOf("A)") +2;
        int end = snowtamOriginal.indexOf("B)");

        String entree = snowtamOriginal.substring(begin,end);
        entree = airportName;
        snowtam = snowtam + "A) "+ entree +"\n";

        return snowtam;
    }
}
