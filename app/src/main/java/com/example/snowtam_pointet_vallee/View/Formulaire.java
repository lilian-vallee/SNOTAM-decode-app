package com.example.snowtam_pointet_vallee.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.autofill.AutofillId;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

    TextView showCode;

    ImageButton show_result;
    ImageButton addAirport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);

        FormController controller = new FormController(this);

        airport = findViewById(R.id.airport1);

        showCode = findViewById(R.id.afficheText);

        showCode.setText("");

        show_result = findViewById(R.id.button_showResult);
        addAirport = findViewById(R.id.button_addAirport);


        addAirport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence airportCode = airport.getText();
                if(controller.addAirport(airportCode)){
                    airport.clearComposingText();
                    showCode.setText((String) showCode.getText() + airportCode);
                }
                else {
                    Context context = getApplicationContext();
                    CharSequence text = "Le code " +
                            airportCode +
                            " n'est pas valide";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }
            }
        });

        show_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.RequeteAPI();
            }
        });

        //listener unfocus les saisies des Aéroports pour check validité (methode : controller.CheckAirportValidity() )
        // non valide => champ de saisie rouge

    }
}