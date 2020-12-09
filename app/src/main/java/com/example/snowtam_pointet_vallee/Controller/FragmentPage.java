package com.example.snowtam_pointet_vallee.Controller;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.snowtam_pointet_vallee.R;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class FragmentPage extends Fragment {

    private MapView myOpenMapView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view;
        Bundle bundle = getArguments();
        String data = bundle.getString("data");
        String name = bundle.getString("airportName");
        double position [] = (double[]) bundle.get("coordonates");


        view = inflater.inflate(R.layout.activity_resultpage,container,false);
        TextView textView = (TextView) view.findViewById(R.id.show_data);
        textView.setText(data);
        TextView textName = (TextView) view.findViewById(R.id.airportName);
        textName.setText(name);


        myOpenMapView = (MapView) view.findViewById(R.id.mapview);
        myOpenMapView.setBuiltInZoomControls(true);
        myOpenMapView.setClickable(true);
        myOpenMapView.getController().setZoom(10);
        myOpenMapView.getController().setCenter(new GeoPoint(position[0], position[1]));



        LocationListener ecouteurGPS = new LocationListener() {
            @Override
            public void onLocationChanged(Location localisation) {
                myOpenMapView.getController().setCenter(new GeoPoint(localisation.getLatitude(), localisation.getLongitude()));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        return view;
    }
}
