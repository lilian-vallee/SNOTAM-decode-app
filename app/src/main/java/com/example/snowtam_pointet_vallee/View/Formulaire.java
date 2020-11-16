package com.example.snowtam_pointet_vallee.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.autofill.AutofillId;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.snowtam_pointet_vallee.Controller.FormController;
import com.example.snowtam_pointet_vallee.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Formulaire extends AppCompatActivity {

    FormController controller = new FormController(this);

    ArrayList codeAirport = new ArrayList();

    Intent formulaire = getIntent();

    TextView airport;

    ImageButton show_result;
    ImageButton addAirport;

    public ArrayList getAirportData () {

        codeAirport.add(findViewById(R.id.airport1));
        codeAirport.add(findViewById(R.id.airport2));
        codeAirport.add(findViewById(R.id.airport3));
        codeAirport.add(findViewById(R.id.airport4));
        return codeAirport;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);

        int id = 0;

        FormController controller = new FormController(this);

        airport = findViewById(R.id.airport1);


        show_result = findViewById(R.id.button_showResult);
        addAirport = findViewById(R.id.button_addAirport);


        addAirport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.addAirport(airport.getText());
            }
        });

        show_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //method getAirportData => List(codeAirports)

                controller.RequeteAPI();

            }
        });

        //listener unfocus les saisies des Aéroports pour check validité (methode : controller.CheckAirportValidity() )
        // non valide => champ de saisie rouge

    }
}