package com.example.snowtam_pointet_vallee.Model;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;

public class AirportBuilder {

    private HashMap<Integer, Airport> airports = new HashMap<>();

    private SnowtamAPI snowtamAPI = new SnowtamAPI();
    private AirportAPI airportAPI = new AirportAPI();

    public HashMap<Integer, Airport> getAirports() {
        return airports;
    }

    public boolean checkAirport(String airportCode, Context context) {

        String identifictionRequestUrl = "https://applications.icao.int/dataservices/api/doc7910?api_key=19569950-27e2-11eb-bb91-378bd10b6324&airports="+ airportCode +"&format=json";
        Airport airport = airportAPI.request(identifictionRequestUrl, context);
        if (!airport.getAirportName().equals(null)){
            createAirport(airport, airportCode, context);
            return true;
        }
        else return false;
    }

    /**
     * @param airport
     * @param airportCode
     * @param context
     * @return
     */
    public void createAirport(
            Airport airport,
            String airportCode, Context context)
    {

        airport.setCodeICAO(airportCode);

        String snowtamRequestUrl = "https://applications.icao.int/dataservices/api/notams-realtime-list?api_key=19569950-27e2-11eb-bb91-378bd10b6324&format=json&criticality=1&locations="+ airportCode;
        Log.i("AirportBuilder", "new SNOWTAM request : "+ snowtamRequestUrl);
        String snowtam = snowtamAPI.request(snowtamRequestUrl, context);
        airport.setSnowtamOriginal(snowtam);
        airport.decode();

        airports.put(airports.size(),airport);
    }


    public HashMap<Integer, Airport> airportTest(Context context) {

        String[] snowtamsTest = snowtamAPI.RequestTest(context);
        Airport[] airportsTest = airportAPI.RequestTest(context);

        for(int i = 0 ; i<4 ; i++){

            Airport airportTest = airportsTest[i];
            airportTest.setSnowtamOriginal(snowtamsTest[i]);

            airportTest.decode();
            airports.put(airports.size(),airportTest);
        }
        return airports;
    }
}