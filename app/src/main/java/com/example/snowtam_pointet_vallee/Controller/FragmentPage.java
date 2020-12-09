package com.example.snowtam_pointet_vallee.Controller;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.snowtam_pointet_vallee.R;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class FragmentPage extends Fragment {

    private MapView myOpenMapView;

    private Button map_visibilityON;
    private Button map_visibilityOFF;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view;
        Bundle bundle = getArguments();
        String name = bundle.getString("airportName");          //get the airport name
        String data = bundle.getString("data");                 //get the airport snowtan
        double position [] = (double[]) bundle.get("coordonates");   //get the airport coordonates


        view = inflater.inflate(R.layout.activity_resultpage,container,false);      //initiate the view
        TextView textName = (TextView) view.findViewById(R.id.airportName);                 //get item airportName
        textName.setText(name);                                                                 //set airport name
        TextView textData = (TextView) view.findViewById(R.id.show_data);                   //get item show_data
        textData.setText(data);                                                                 //set snowtam



        myOpenMapView = (MapView) view.findViewById(R.id.mapview);                          //get item map
        myOpenMapView.setBuiltInZoomControls(true);                                         //initiate map
        myOpenMapView.setClickable(true);
        myOpenMapView.getController().setZoom(14);
        myOpenMapView.getController().setCenter(new GeoPoint(position[0], position[1]));

        map_visibilityON = view.findViewById(R.id.show_map);
        map_visibilityON.setVisibility(View.VISIBLE);
        map_visibilityOFF = view.findViewById(R.id.hide_map);

        map_visibilityON.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                myOpenMapView.setVisibility(View.VISIBLE);
                map_visibilityON.setVisibility(View.INVISIBLE);
                map_visibilityOFF.setVisibility(View.VISIBLE);
            }
        });

        map_visibilityOFF.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    myOpenMapView.setVisibility(View.INVISIBLE);
                    map_visibilityON.setVisibility(View.VISIBLE);
                    map_visibilityOFF.setVisibility(View.INVISIBLE);
            }
        });



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
