package com.example.snowtam_pointet_vallee.Controller;

import android.content.Intent;
import android.os.Bundle;

import com.example.snowtam_pointet_vallee.Model.Snowtam;
import com.example.snowtam_pointet_vallee.Model.SnowtamAPI;
import com.example.snowtam_pointet_vallee.View.Formulaire;
import com.example.snowtam_pointet_vallee.View.ResultPage;

import java.util.ArrayList;
import java.util.HashMap;


public class FormController {

    //=====================================
    //Attributs
    //=====================================
    private Formulaire formPage;
    private ArrayList<CharSequence> airportsList = new ArrayList<>();
    private SnowtamAPI snowtamAPI;
    private String[] requests;


    //=====================================
    //Getters / Setters
    //=====================================
    public ArrayList getAirportsList() {
        return airportsList;
    }


    //=====================================
    //Constructors
    //=====================================

    /**
     * Constructor
     * @param formPage
     */
    public FormController(Formulaire formPage){
        this.formPage = formPage;
    }


    //=====================================
    //Methodes
    //=====================================

    /**
     * Take the airportList and for each element create a request for the API.
     * Each request is stocked in a table of String which is given to the SnowtamAPI class for the request.
     */
    public void RequeteAPI() {

        System.out.println("setup request");
        requests = new String[airportsList.size()];

        for (int i=0; i < airportsList.size(); i++){
            String request = "https://applications.icao.int/dataservices/api/notams-realtime-list?api_key=19569950-27e2-11eb-bb91-378bd10b6324&format=json&criticality=1&locations="+ airportsList.get(i);
            requests[i] = request;
            System.out.println(request);
        }

        snowtamAPI = new SnowtamAPI();
        //snowtamAPI.Request(requests, formPage.getApplicationContext());
        snowtamAPI.RequestTest(formPage.getApplicationContext());
        SwitchActivity(snowtamAPI.getAnswers());

    }

    private void SwitchActivity(HashMap<Integer, Snowtam> answers) {
        Intent intent = new Intent(formPage.getApplicationContext(), ResultPage.class);
        Bundle extras = new Bundle();
        extras.putSerializable("answersList",answers);
        intent.putExtras(extras);
        System.out.println("WAZZZAAAAAAAA");
        formPage.startActivity(intent);
    }

    /**
     * Add an aiport to the airportList once they are checked
     * @param airport
     * @return booleans
     */
    public boolean addAirport(CharSequence airport) {
        if(CheckAirportValidity(airport)) {
            airportsList.add(airport);
            System.out.println("airport add"+airport);
            return true;
        }
        return false;
    }


    /**
     * Check the validity of an airport code
     * Work in progress
     * (can be better with a comparason with the database of all airports code)
     * @param airport
     * @return Booleans
     */
    public boolean CheckAirportValidity(CharSequence airport) {
        if (airport.length() == 4) {
            if (airport.charAt(0) != 'i')
                if (airport.charAt(0) != 'j')
                    if (airport.charAt(0) != 'x')
                        return true;
        }
        return false;
    }


}
