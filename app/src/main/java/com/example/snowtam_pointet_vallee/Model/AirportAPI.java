package com.example.snowtam_pointet_vallee.Model;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.JsonReader;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class AirportAPI {

    /**
     * Make a request to the API with an url
     * @param url
     * @param context
     * @param callBack
     * @return
     */
    public void request(String url, Context context, final VolleyCallBack callBack) {

        Log.i("FormulairePage", "Sending identification Request ...");

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i("FormulairePage", "Response received");

                        Airport airport = ResponseParser(new ByteArrayInputStream(response.getBytes()));

                        callBack.onSuccess(airport);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error");
            }
        });
        queue.add(stringRequest);
    }

    /**
     * Simulation of an identification request.
     * Read 4 JSON files in the assets for the usual process
     * @param applicationContext
     * @return Booleans
     */
    public Airport[] RequestTest(Context applicationContext){

        Airport[] airports = new Airport[4];

        try {

            AssetManager assetManager = applicationContext.getAssets();

            Log.i("FormulairePage", "Loading identification files ...");

            InputStream inputStream = assetManager.open("identification UUEE.json");
            airports[0] = ResponseParser(inputStream);

            inputStream = assetManager.open("identification ENBO.json");
            airports[1] = ResponseParser(inputStream);

            inputStream = assetManager.open("identification ENVA.json");
            airports[2] = ResponseParser(inputStream);

            inputStream = assetManager.open("identification ENGM.json");
            airports[3] =  ResponseParser(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return airports;
    }


    /**
     * Get the response of the API in a input stream format and parse him to find wanted informations and saved them
     * @param in
     * @return Booleans
     */
    protected Airport ResponseParser(InputStream in) {

        Airport airport = new Airport();
        double lattitude = 0.0;
        double longitude = 0.0;

        JsonReader reader = null;
        try {
            reader = new JsonReader(new InputStreamReader(in, "UTF-8"));

            reader.beginArray();

            if (reader.hasNext()){
                reader.beginObject();

                while(reader.hasNext()){
                    String attribut = reader.nextName();

                    switch (attribut)
                    {
                        case "Location_Name" :
                            airport.setAirportName(reader.nextString());
                            break;
                        case "Latitude" :
                            lattitude = reader.nextDouble();
                            break;
                        case "Longitude" :
                            longitude = reader.nextDouble();
                            break;
                        default:
                            reader.skipValue();
                    }

                }
                reader.endObject();
            }
                reader.endArray();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        airport.setCoordonates(new double[] {lattitude, longitude});
        return airport;
    }
}
