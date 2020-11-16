package com.example.snowtam_pointet_vallee.Controller;

import com.example.snowtam_pointet_vallee.Model.SnowtamAPI;
import com.example.snowtam_pointet_vallee.View.Formulaire;

import java.util.ArrayList;

public class FormController {

    private Formulaire formPage;
    private ArrayList<CharSequence> airportsList;
    private SnowtamAPI snowtamAPI;
    private ArrayList<String> requestList;

    public FormController(Formulaire formPage){
        this.formPage = formPage;
    }

    public void RequeteAPI() {
        for (int i=0; i > airportsList.size(); i++){
            String request = "https://applications.icao.int/dataservices/api/notams-realtime-list?api_key=19569950-27e2-11eb-bb91-378bd10b6324&format=json&criticality=1&locations="+ airportsList.get(i);
            requestList.add(request);
        }
        snowtamAPI = new SnowtamAPI();
        snowtamAPI.Request(requestList);
    }


    public boolean addAirport(CharSequence airport) {
        if(CheckAirportValidity(airport)) {
            airportsList.add(airport);
            return true;
        }
        return false;
    }

    public boolean CheckAirportValidity(CharSequence airport) {
        if (airport.length() == 4) {
            if (airport.charAt(0) != 'i')
                if (airport.charAt(0) != 'j')
                    if (airport.charAt(0) != 'x')
                        return true;
        }
        return false;
    }

    public ArrayList getAirportsList() {
        return airportsList;
    }
}
