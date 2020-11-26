package com.example.snowtam_pointet_vallee.Controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.snowtam_pointet_vallee.R;

public class FragmentPage extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view;
        Bundle bundle = getArguments();
        int count = bundle.getInt("pageNumber");

        view = inflater.inflate(R.layout.activity_resultpage,container,false);
        TextView textView = (TextView) view.findViewById(R.id.show_data);
        textView.setText("Page " + count);

        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
