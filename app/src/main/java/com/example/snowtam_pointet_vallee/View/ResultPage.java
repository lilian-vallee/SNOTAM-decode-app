package com.example.snowtam_pointet_vallee.View;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.snowtam_pointet_vallee.Model.Snowtam;
import com.example.snowtam_pointet_vallee.R;

public class ResultPage extends AppCompatActivity {

    TextView data;

    Snowtam s = new Snowtam("");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultpage);

        data = findViewById(R.id.show_data);

        /*
        Snowtam [] results;

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        Snowtam d = extras.get("table");
        */

        data.setText(s.toString());

    }
}