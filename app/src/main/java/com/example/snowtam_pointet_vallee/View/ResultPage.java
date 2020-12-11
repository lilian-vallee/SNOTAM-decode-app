package com.example.snowtam_pointet_vallee.View;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.snowtam_pointet_vallee.Controller.SwipeAdapter;
import com.example.snowtam_pointet_vallee.Model.Airport;
import com.example.snowtam_pointet_vallee.R;

import org.osmdroid.config.Configuration;

import java.util.HashMap;


public class ResultPage extends AppCompatActivity {

    HashMap<Integer, Airport> listSnowtam = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * here we change property of application so map tile will be charge localy and not in an extern stockage anymore
         * without this, the map will not load tiles
         */
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        

        setContentView(R.layout.activity_resultpage);

        Bundle bundle = this.getIntent().getExtras();       //we take data send by controller when switch activity
        if(bundle != null) {
            listSnowtam = (HashMap<Integer, Airport>) bundle.getSerializable("answersList");
        }

        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(listSnowtam.size());        //number of page load initialy and keep in memory by the app
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager(),listSnowtam);
        viewPager.setAdapter(swipeAdapter);
        viewPager.setCurrentItem(0);            //will show the fisrt page
    }
}