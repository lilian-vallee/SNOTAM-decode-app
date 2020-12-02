package com.example.snowtam_pointet_vallee.View;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.snowtam_pointet_vallee.Controller.SwipeAdapter;
import com.example.snowtam_pointet_vallee.Model.Airport;
import com.example.snowtam_pointet_vallee.R;

import java.util.HashMap;

public class ResultPage extends AppCompatActivity {

    HashMap<Integer, Airport> listSnowtam = new HashMap<>();

    public int getListSize() {
        return listSnowtam.size();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultpage);


        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            listSnowtam = (HashMap<Integer, Airport>) bundle.getSerializable("AnswerList");
        }

        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(1);
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager(),listSnowtam);
        viewPager.setAdapter(swipeAdapter);
        viewPager.setCurrentItem(0);

    }
}