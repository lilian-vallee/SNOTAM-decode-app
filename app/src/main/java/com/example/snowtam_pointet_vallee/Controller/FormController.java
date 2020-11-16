package com.example.snowtam_pointet_vallee.Controller;

import com.example.snowtam_pointet_vallee.Model.SnowtamAPI;
import com.example.snowtam_pointet_vallee.View.Formulaire;

import java.util.ArrayList;

public class FormController {

    private Formulaire formPage;
    private ArrayList airportsList;
    private SnowtamAPI snowtamAPI;

    public FormController(Formulaire formPage){
        this.formPage = formPage;
    }

    public void RequeteAPI() {
        //take the list and make requete
    }

    public void addAirport(CharSequence text) {
        //Check Airport validity
        //add airportList
    }

    public boolean CheckAirportValidity(){
        //Check code airport
        if(){
            return true;
        }
        return false;
    }
}
