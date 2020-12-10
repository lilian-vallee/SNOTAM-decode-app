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

    //=====================================
    //Methodes
    //=====================================

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
            //indexSnowtam = decodeRunway(indexSnowtam);



            snowtamDecoded = indexSnowtam[0];
            for (int i = 1 ; i<indexSnowtam.length ; i++){
                snowtamDecoded = snowtamDecoded +" "+ indexSnowtam[i];
            }


            Log.i("Airport", snowtamDecoded);
        }
    }

    private String[] decodeA(String[] indexSnowtam) {

        indexSnowtam[0] = indexSnowtam[0] +" ";

        indexSnowtam[1] = this.airportName;

        return indexSnowtam;
    }

    private String[] decodeB(String[] indexSnowtam) {

        try {

            for (int i = 1 ; i < indexSnowtam.length; i++){

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

//    private String decodeRunway(String snowtam) {
//
//        String snowtamAlt = snowtam;
//
//        while(snowtamAlt.contains("C)")){
//
//            int begin = snowtamAlt.indexOf("C)");
//            int end = snowtamAlt.split(" ");
//
//            String value = snowtamAlt.substring(begin,end);
//            snowtamAlt = snowtamAlt.replace(value,"");
//
//            value = value.replaceAll("\\s","");
//            if(value.length()==2){
//                if(value.equals("88")){
//                    value = "ALL RUNWAYS";
//                }
//                else{
//                    value = "RUNWAY "+ value;
//                }
//            }
//            else{
//                if(value.contains("R")){
//                    value = "RUNWAY "+ Integer.valueOf(value.substring(0,2))+50;
//                }
//                else{
//                    value = "RUNWAY "+ Integer.valueOf(value.substring(0,2));
//                }
//            }
//
//            snowtam = snowtam.replace(snowtam.substring(begin+2,end), " "+ value +" ");
//
//        }
//
//        return snowtam;
//    }
}
