package com.example.snowtam_pointet_vallee.Controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.snowtam_pointet_vallee.Model.Snowtam;

import java.util.HashMap;


public class SwipeAdapter extends FragmentStatePagerAdapter {

    HashMap listSnowtam;

    public SwipeAdapter(FragmentManager fm, HashMap<Integer, Snowtam> listSnowtam){
        super(fm);
        this.listSnowtam = listSnowtam;
    }


    @NonNull
    @Override
    public androidx.fragment.app.Fragment getItem(int position) {
        Fragment pageFragment = new FragmentPage();
        Bundle bundle = new Bundle();
        bundle.putInt("pageNumber",position);
        bundle.putString("data", listSnowtam.get(position).toString());
        pageFragment.setArguments(bundle);

        return pageFragment;
    }

    @Override
    public int getCount() {
        return listSnowtam.size();
    }
}
