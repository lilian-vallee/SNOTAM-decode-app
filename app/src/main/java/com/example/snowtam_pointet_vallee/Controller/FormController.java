package com.example.snowtam_pointet_vallee.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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

    protected Boolean test = false; // trigger (true) or not (false) all API call.

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
     *
     * @param formPage
     */
    public FormController(Formulaire formPage) {
        this.formPage = formPage;

        airportBuilder = new AirportBuilder();
    }


    //=====================================
    //Methodes
    //=====================================

    /**
     * Take all the airports initialized in the aiportBuilder for switching activity
     * if test are enable the SwitchActivity methode take what the airportTest methode of the aiportBuilder send him
     */
    public void RequeteAPI() {

        Boolean isReady = false;

        for (int i = 0 ; i<airportBuilder.getAirports().size() ; i++){
            if(airportBuilder.getAirports().get((Integer) i).getReady()){
                isReady = true;
            }
            else{
                isReady = false;
                break;
            }
        }

        if(isReady){
            if (!test) {
                SwitchActivity(airportBuilder.getAirports());
            } else {
                SwitchActivity(airportBuilder.airportTest(formPage.getApplicationContext()));
            }
        }


    }

    /**
     * Send the user to the result, so he can see the snowtam
     *
     * @param answers
     */
    private void SwitchActivity(HashMap<Integer, Airport> answers) {

        Log.i("FormulairePage", "Initialise Bundle");

        Intent intent = new Intent(formPage.getApplicationContext(), ResultPage.class);
        Bundle extras = new Bundle();

        Log.i("FormulairePage", "Loading " + answers.size() + " aiports.");

        extras.putSerializable("answersList", answers);
        intent.putExtras(extras);

        Log.i("FormulairePage", "Switching activity");

        formPage.startActivity(intent);
    }

    /**
     * Add an aiport to the airportList once they are checked
     *
     * @param airport
     * @return booleans
     */
    public boolean addAirport(CharSequence airport) {
        if (CheckAirportValidity(airport)) {
            airportsList.add(airport);
            return true;
        }
        return false;
    }

    /**
     * Check the validity of an airport code by calling the identificationAPI
     * in the case of test it will return true
     *
     * @param airportCode
     * @return Booleans
     */
    public boolean CheckAirportValidity(CharSequence airportCode) {
        if (!test) {
            if (airportCode.length() != 4) {
                return false;
            } else if (airportCode.toString().matches("[a-z]+")){
                return false;
            }
            else if (airportCode.toString().startsWith("I")) {
                return false;
            } else if (airportCode.toString().startsWith("J")) {
                return false;
            } else if (airportCode.toString().startsWith("X")) {
                return false;
            } else {
                airportBuilder.checkAirport(airportCode.toString(), formPage.getApplicationContext());
                return true;
            }
        }
        else {
            return true;
        }
    }
}