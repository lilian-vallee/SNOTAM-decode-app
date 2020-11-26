package com.example.snowtam_pointet_vallee.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.snowtam_pointet_vallee.Model.Snowtam;
import com.example.snowtam_pointet_vallee.R;
import com.google.android.material.tabs.TabLayout;

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