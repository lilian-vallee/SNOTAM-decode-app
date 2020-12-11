package com.example.snowtam_pointet_vallee.Controller;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.snowtam_pointet_vallee.Model.Airport;

import java.util.HashMap;


public class SwipeAdapter extends FragmentStatePagerAdapter {

    HashMap<Integer,Airport> listSnowtam = new HashMap<>();         //map with data we need

    String TAG = "SwipeAdapter";

    public SwipeAdapter(FragmentManager fm, HashMap<Integer, Airport> listSnowtam){
        super(fm);
        this.listSnowtam = listSnowtam;
    }

    @NonNull
    @Override
    public androidx.fragment.app.Fragment getItem(int position) {

        //create a fragment page
        Fragment pageFragment = new FragmentPage();

        Bundle bundle = new Bundle();               //this bundle is use to share data we need with fragment page
        bundle.putInt("pageNumber",position);       //depending of the page we want to show
        bundle.putInt("numberPage",getCount());     //the number of differents pages
        Log.i(TAG, "getItem: " + position + " / " + getCount());

        bundle.putString("data", (String) ((Airport) listSnowtam.get(position)).getSnowtamOriginal());
        bundle.putString("decodeData", (String) ((Airport) listSnowtam.get(position)).getSnowtamDecode());
        bundle.putString("airportName",(String) ((Airport) listSnowtam.get(position)).getAirportName());
        bundle.putDoubleArray("coordonates",((Airport) listSnowtam.get(position)).getCoordonates());

        //add data in fragment page
        pageFragment.setArguments(bundle);

        return pageFragment;
    }

    /**
     * return the number of different airport we have
     *
     * @return int
     */
    @Override
    public int getCount() {
        return listSnowtam.size();      //number of different airport
    }


}
