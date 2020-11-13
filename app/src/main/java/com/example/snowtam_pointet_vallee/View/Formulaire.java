package com.example.snowtam_pointet_vallee.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.snowtam_pointet_vallee.Controller.FormController;
import com.example.snowtam_pointet_vallee.R;

public class Formulaire extends AppCompatActivity {

    Intent formulaire = getIntent();
    TextView airport1;
    TextView airport2;
    TextView airport3;
    TextView airport4;

    ImageButton show_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);

        FormController controller = new FormController(this);

        airport1 = findViewById(R.id.airport1);
        airport2 = findViewById(R.id.airport2);
        airport3 = findViewById(R.id.airport3);
        airport4 = findViewById(R.id.airport4);
        show_result = findViewById(R.id.button_showResult);

        show_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

    }
}