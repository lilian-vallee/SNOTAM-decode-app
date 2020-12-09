package com.example.snowtam_pointet_vallee.Model;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;

public class AirportBuilder {

    //=====================================
    //Attributs
    //=====================================

    private HashMap<Integer, Airport> airports = new HashMap<>();// hashMap of all the create airport

    //Api instance
    private SnowtamAPI snowtamAPI = new SnowtamAPI();
    private AirportAPI airportAPI = new AirportAPI();

    //=====================================
    //Getters / Setters
    //=====================================

    public HashMap<Integer, Airport> getAirports() {
        return airports;
    }

    //=====================================
    //Methodes
    //=====================================


    /**
     * Check bay calling the identificationAPI
     * if an entree code are correct we keep the information and send them for getting a snowtam
     * if not the code are rejected
     * @param airportCode
     * @param context
     * @return
     */
    public boolean checkAirport(String airportCode, Context context) {

        String identifictionRequestUrl = "https://applications.icao.int/dataservices/api/doc7910?api_key=19569950-27e2-11eb-bb91-378bd10b6324&airports="+ airportCode +"&format=json";

        Log.i("FormulairePage", "New identification request : "+ identifictionRequestUrl);

        Airport airport = airportAPI.request(identifictionRequestUrl, context);
        if (!airport.getAirportName().equals(null)){

            Log.i("FormulairePage", "Request succesfull.");

            checkSnowtam(airport, airportCode, context);
            return true;
        }
        else {

            Log.i("FormulairePage", "Request error : ICAO iarport code rejected.");

            return false;
        }
    }

    /**
     * Get a SNOWTAM by calling the SnowtamAPI and put it in the aiport instance, then launch the decode process and stock it
     * @param airport
     * @param airportCode
     * @param context
     * @return
     */
    public void checkSnowtam(
            Airport airport,
            String airportCode,
            Context context)
    {

        airport.setCodeICAO(airportCode);

        String snowtamRequestUrl = "https://applications.icao.int/dataservices/api/notams-realtime-list?api_key=19569950-27e2-11eb-bb91-378bd10b6324&format=json&criticality=1&locations="+ airportCode;

        Log.i("AirportBuilder", "new SNOWTAM request : "+ snowtamRequestUrl);

        String snowtam = snowtamAPI.request(snowtamRequestUrl, context);
        airport.setSnowtamOriginal(snowtam);
        airport.decode();

        Log.i("FormulairePage", "New airport entree : "+ airportCode);

        airports.put(airports.size(),airport);
    }


    /**
     * Methode simulating the API call by reading json file of the early responses of the API
     * @param context
     * @return
     */
    public HashMap<Integer, Airport> airportTest(Context context) {

        Log.i("FormulairePage", "Initialise false request");

        String[] snowtamsTest = snowtamAPI.RequestTest(context);
        Airport[] airportsTest = airportAPI.RequestTest(context);

        for(int i = 0 ; i<4 ; i++){

            Airport airportTest = airportsTest[i];
            airportTest.setSnowtamOriginal(snowtamsTest[i]);

            airportTest.decode();

            Log.i("FormulairePage", "New false airport entree");

            airports.put(airports.size(),airportTest);
        }
        return airports;
    }
}