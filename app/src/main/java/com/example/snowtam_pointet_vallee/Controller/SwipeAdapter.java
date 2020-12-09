package com.example.snowtam_pointet_vallee.Controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.snowtam_pointet_vallee.Model.Airport;

import java.util.HashMap;


public class SwipeAdapter extends FragmentStatePagerAdapter {

    HashMap<Integer,Airport> listSnowtam = new HashMap<>();

    public SwipeAdapter(FragmentManager fm, HashMap<Integer, Airport> listSnowtam){
        super(fm);
        this.listSnowtam = listSnowtam;
        System.out.println("list size " + listSnowtam.size());
    }


    @NonNull
    @Override
    public androidx.fragment.app.Fragment getItem(int position) {
        Fragment pageFragment = new FragmentPage();
        Bundle bundle = new Bundle();
        bundle.putInt("pageNumber",position);
        System.out.println("position " + position);
        bundle.putString("data", (String) ((Airport) listSnowtam.get(position)).getSnowtamOriginal());
        System.out.println("data " + (String) listSnowtam.get(position).getSnowtamOriginal());
        bundle.putString("airportName",(String) ((Airport) listSnowtam.get(position)).getAirportName());
        System.out.println("airportName " + (String) listSnowtam.get(position).getAirportName());
        bundle.putDoubleArray("coordonates",((Airport) listSnowtam.get(position)).getCoordonates());
        pageFragment.setArguments(bundle);

        return pageFragment;
    }

    @Override
    public int getCount() {
        //return 1;
        return listSnowtam.size();
    }
}
