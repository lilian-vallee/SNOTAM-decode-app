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

    FormController controller = new FormController(this);                                   //create the controller

    Intent formulaire = getIntent();

    TextView airport;           //where the user write the airport's code he wants

    TextView showCode;          //where the user read the codes he already wrote

    ImageButton show_result;    //go to the next page whit results
    ImageButton addAirport;     //add the code to a list which is used to show results

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);

        /***
         * we use the findViewById method to link the xml object whit the code
         */
        airport = findViewById(R.id.airport1);

        showCode = findViewById(R.id.afficheText);

        show_result = findViewById(R.id.button_showResult);
        addAirport = findViewById(R.id.button_addAirport);


        addAirport.setOnClickListener(new View.OnClickListener() {      //click on the button +
            @Override
            public void onClick(View v) {
                CharSequence airportCode = airport.getText();           //get the code the user put on 'airport'

                //check the validity of the code
                if(controller.addAirport(airportCode)){                 //if check is ok
                    airport.setText("");                                //reset the view on 'airport'
                    showCode.setText((String) showCode.getText() + '\n' + airportCode);
                                                                        //add and show the new code
                }
                else {                                                  //if check isn't ok
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

        show_result.setOnClickListener(new View.OnClickListener() {     //click on the button V
            @Override
            public void onClick(View v) {
                controller.RequeteAPI();
            }
        });

        //listener unfocus les saisies des Aéroports pour check validité (methode : controller.CheckAirportValidity() )
        // non valide => champ de saisie rouge

    }
}