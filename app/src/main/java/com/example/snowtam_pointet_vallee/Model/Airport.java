package com.example.snowtam_pointet_vallee.Model;


import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Airport implements java.io.Serializable{

    //=====================================
    //Attributs
    //=====================================

    private String codeICAO;
    private String snowtamOriginal; // SNOWTAM undecoded
    private String snowtamDecoded; // SNOWTAM decoded
    private String airportName;
    private double[] coordonates; // coordonates ==> longitude, latitude
    private Boolean isReady = false;

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
        return snowtamDecoded;
    }

    public String getAirportName() {
        return airportName;
    }

    public double[] getCoordonates() { return coordonates; }

    public Boolean getReady() {
        return isReady;
    }

    //=====================================
    //Methodes
    //=====================================


    /**
     * Methode which initialise the process of decoding the SNOWTAM
     */
    public void decode() {

        if(snowtamOriginal == null){
            snowtamDecoded = " ";
        }
        else{
            String snowtam = snowtamOriginal;

            //formatage du snowtam pour traitement
            snowtam = snowtam.replace(")",") ");
            snowtam = snowtam.replaceAll("\\s+"," ");
            String[] indexSnowtam = snowtam.split(" ");


            indexSnowtam = decodeA(indexSnowtam);
            indexSnowtam = decodeB(indexSnowtam);
            indexSnowtam = decodeC(indexSnowtam);



            snowtamDecoded = indexSnowtam[0];
            for (int i = 1 ; i<indexSnowtam.length ; i++){
                snowtamDecoded = snowtamDecoded +" "+ indexSnowtam[i];
            }

            isReady = true;
            Log.i("Airport", snowtamDecoded);
        }
    }

    /**
     * Decode the A entree of a SNOWTAM which is the ICAO code to the actual airport name.
     * @param indexSnowtam
     * @return
     */
    private String[] decodeA(String[] indexSnowtam) {

        indexSnowtam[0] = indexSnowtam[0] +" ";

        indexSnowtam[1] = this.airportName;

        return indexSnowtam;
    }

    /**
     * Decode the B entree of a SNOWTAM which is a date in the MMddkkmm to d MMM kkmm z (11080850 ==> 11 august 08h50 UTC)
     * @param indexSnowtam
     * @return
     */
    private String[] decodeB(String[] indexSnowtam) {

        try {

            for (int i = 0 ; i < indexSnowtam.length; i++){

                if (indexSnowtam[i].equals("B)")){

                    indexSnowtam[i] = "\n"+ indexSnowtam[i] +" ";

                    String dateStr = indexSnowtam[i+1];

                    Date date = new SimpleDateFormat("MMddkkmm").parse(dateStr);

                    DateFormat dateFormat = new SimpleDateFormat("d MMM kk'h'mm z");
                    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

                    indexSnowtam[i+1] = dateFormat.format(date);
                    i++;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return indexSnowtam;
    }

    /**
     * Decode the C entree of a SNOWTAM which is the number of the runway.
     * @param indexSnowtam
     * @return
     */
    private String[] decodeC(String[] indexSnowtam) {

        for (int i = 0 ; i < indexSnowtam.length ; i++){

            if (indexSnowtam[i].equals("C)")){

                indexSnowtam[i] = "\n"+ indexSnowtam[i] + " ";

                if (indexSnowtam[i+1].equals("88")){
                    indexSnowtam[i+1] = "ALL RUNWAYS";
                }
                else if (indexSnowtam[i+1].length() == 2){
                    indexSnowtam[i+1] = "RUNWAY " + indexSnowtam[i+1] + " ";
                }
                else {
                    if (indexSnowtam[i+1].contains("R")){
                        indexSnowtam[i+1] = "RUNWAY "+ (Integer.valueOf(indexSnowtam[i+1].substring(0,2))+50) +" ";
                    }
                    else {
                        indexSnowtam[i+1] = "RUNWAY "+ indexSnowtam[i+1].substring(0,2) +" ";
                    }
                }
                i++;
            }

        }
        return indexSnowtam;
    }
}
