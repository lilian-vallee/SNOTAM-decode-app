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

    public int getListSize() {
        return listSnowtam.size();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/*
        org.osmdroid.config.IConfigurationProvider osmConf = org.osmdroid.config.Configuration.getInstance();
        File basePath = new File(getCacheDir().getAbsolutePath(), "osmdroid");
        osmConf.setOsmdroidBasePath(basePath);
        File tileCache = new File(osmConf.getOsmdroidBasePath().getAbsolutePath(), "tile");
        osmConf.setOsmdroidTileCache(tileCache);
*/

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));


        setContentView(R.layout.activity_resultpage);


        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            listSnowtam = (HashMap<Integer, Airport>) bundle.getSerializable("answersList");
        }

        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(1);
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager(),listSnowtam);
        viewPager.setAdapter(swipeAdapter);
        viewPager.setCurrentItem(0);

    }
}