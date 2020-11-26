package com.example.snowtam_pointet_vallee.View;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.snowtam_pointet_vallee.Model.Snowtam;
import com.example.snowtam_pointet_vallee.R;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class ResultPage extends AppCompatActivity {

    private SlidrInterface slidr;

    TextView data;

    Snowtam s = new Snowtam("");

    String jsonBrute = "A)UUEE B)11160303\\nC)06L F)1/1/1 H)5/5/5 N)NIL\\nC)06R F)1/1/1 H)5/5/5 N)NIL\\nR)NIL S)11160700)\\nCREATED: 16 Nov 2020 04:08:00 \\nSOURCE: UUUUYNYX";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultpage);

        slidr = Slidr.attach(this);

        data = findViewById(R.id.show_data);

        /*
        Snowtam [] results;

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        Snowtam d = extras.get("table");
        */

        data.setText(jsonBrute.toString());

    }
}