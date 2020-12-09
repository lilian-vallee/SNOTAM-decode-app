package com.example.snowtam_pointet_vallee.Model;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.JsonReader;

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
import java.util.HashMap;


public class SnowtamAPI {

    /**
     * Constructor
     */
    public SnowtamAPI() { }


    //=====================================
    //Methodes
    //=====================================

    /**
     * Make a request to the API with the url in the input table
     * @param url
     * @param context
     * @return Booleans
     */
    public String request(String url, Context context) {

        final String[] snowtam = new String[1]; // needed by the inner class of Volley.

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(
            Request.Method.GET,
            url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                   InputStream in = new ByteArrayInputStream(response.getBytes());
                   snowtam[0] = ResponseParser(in);
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error");
            }
        });
        queue.add(stringRequest);

        return snowtam[0];
    }

    /**
     * Simulation of a request.
     * Read 4 JSON files in the assets for the usual process
     * (SNOWTAM, SNOWTAM, NO SNOWTAM, SNOWTAM)
     * @param applicationContext
     * @return Booleans
     */
    public String[] RequestTest(Context applicationContext){

        String[] snowtams = new String[4];

        try {

            AssetManager assetManager = applicationContext.getAssets();

            InputStream inputStream = assetManager.open("Realtime NOTAMS UUEE.json");
            snowtams[0] = ResponseParser(inputStream);

            inputStream = assetManager.open("Realtime NOTAMS ENBO.json");
            snowtams[1] = ResponseParser(inputStream);

            inputStream = assetManager.open("Realtime NOTAMS ENVA.json");
            snowtams[2] = ResponseParser(inputStream);

            inputStream = assetManager.open("Realtime NOTAMS ENGM.json");
            snowtams[3] =  ResponseParser(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return snowtams;
    }


    /**
     * Get the response of the API in a input stream format and parse hin to find the SNOWTAM information and saved it
     * @param in
     * @return Booleans
     */
    protected String ResponseParser(InputStream in) {

        JsonReader reader = null;
        try {
            reader = new JsonReader(new InputStreamReader(in, "UTF-8"));

            reader.beginArray();

            while(reader.hasNext()){
                reader.beginObject();
                while(reader.hasNext()){

                    String attribut = reader.nextName();

                    if(attribut.equals("all")){
                        String text = reader.nextString();

                        if(text.contains("SNOWTAM")) {
                            return CreateSnowtam(text);
                        }

                    }
                    else {
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
        return null;
    }


    /**
     * Create an object Snowtam whith the SNOWTAM information given in the entree.
     * @param text
     * @return
     */
    protected String CreateSnowtam(String text) {

        int begin = text.indexOf("A)");
        int end = text.indexOf(")\nCREATED");
        System.out.println("SNOWTAM aquired");

        return (String) text.subSequence(begin,end);
    }
}