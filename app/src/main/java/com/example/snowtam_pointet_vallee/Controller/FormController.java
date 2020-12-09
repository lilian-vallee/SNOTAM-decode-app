package com.example.snowtam_pointet_vallee.Controller;

import android.content.Intent;
import android.os.Bundle;

import com.example.snowtam_pointet_vallee.Model.Airport;
import com.example.snowtam_pointet_vallee.Model.AirportBuilder;
import com.example.snowtam_pointet_vallee.View.Formulaire;
import com.example.snowtam_pointet_vallee.View.ResultPage;

import java.util.ArrayList;
import java.util.HashMap;


public class FormController {

    //=====================================
    //Attributs
    //=====================================

    protected Boolean test = true; // trigger (true) or not (false) all API call.

    private Formulaire formPage;
    private ArrayList<CharSequence> airportsList = new ArrayList<>();
    AirportBuilder airportBuilder;


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

        airportBuilder = new AirportBuilder();
    }


    //=====================================
    //Methodes
    //=====================================

    /**
     * Take the airportList and for each element create a request for the API.
     * Each request is stocked in a table of String which is given to the SnowtamAPI class for the request.
     */
    public void RequeteAPI() {

        if (!test){
            SwitchActivity(airportBuilder.getAirports());
        }
        else{
            SwitchActivity(airportBuilder.airportTest(formPage.getApplicationContext()));
        }

    }



    private void SwitchActivity(HashMap<Integer, Airport> answers) {
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
        if(!test){
            return airportBuilder.checkAirport((String) airport,formPage.getApplicationContext());
        }
        else return true;
    }


}
