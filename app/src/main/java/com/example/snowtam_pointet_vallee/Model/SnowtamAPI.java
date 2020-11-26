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

    //=====================================
    //Attributs
    //=====================================
    private HashMap<Integer,Snowtam> answers = new HashMap<>();

    //=====================================
    //Constructors
    //=====================================


    /**
     * Constructor
     */
    public SnowtamAPI() { }


    //=====================================
    //Methodes
    //=====================================

    /**
     * Make a request to the API with the url in the input table
     * @param requestList
     * @param applicationContext
     * @return Booleans
     */
    public Boolean Request(String[] requestList, Context applicationContext) {

        if(requestList.length == 0){
            return false;
        }

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(applicationContext);

        for (int i=0; i<requestList.length; i++){
            String url = requestList[i];

            StringRequest stringRequest = new StringRequest(
                    Request.Method.GET,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            InputStream in = new ByteArrayInputStream(response.getBytes());
                            if(!ResponseParser(in)){
                                CreateSnowtam();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("Error");
                }
            });

            queue.add(stringRequest);
        }
        return true;
    }

    /**
     * Simulation of a request.
     * Read 4 JSON files in the assets for the usual process
     * (SNOWTAM, SNOWTAM, NO SNOWTAM, SNOWTAM)
     * @param applicationContext
     * @return Booleans
     */
    public Boolean RequestTest(Context applicationContext){

        try {

            AssetManager assetManager = applicationContext.getAssets();
            InputStream inputStream = assetManager.open("Realtime NOTAMS UUEE.json");
            ResponseParser(inputStream);

            inputStream = assetManager.open("Realtime NOTAMS ENBO.json");
            ResponseParser(inputStream);

            inputStream = assetManager.open("Realtime NOTAMS ENVA.json");
            ResponseParser(inputStream);

            inputStream = assetManager.open("Realtime NOTAMS ENGM.json");
            ResponseParser(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * Get the response of the API in a input stream format and parse hin to find the SNOWTAM information and saved it
     * @param in
     * @return Booleans
     */
    protected Boolean ResponseParser(InputStream in) {

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
                        if(text.contains("SNOWTAM")){
                            CreateSnowtam(text);
                            return true;
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

        return false;
    }

    /**
     * Create an empty object Snowtam and save it in an hashmap
     * case where the response of the API doesn't contained any SNOWTAM
     */
    protected void CreateSnowtam() {
        Snowtam snowtam = new Snowtam();
        answers.put(answers.size(), snowtam);
    }


    /**
     * Create an object Snowtam whith the SNOWTAM information given in the entree.
     * @param text
     */
    protected void CreateSnowtam(String text) {

        int begin = text.indexOf("A)");
        int end = text.indexOf(")\nCREATED");
        System.out.println("SNOWTAM aquired");
        System.out.println(text.subSequence(begin,end));

        Snowtam snowtam = new Snowtam((String) text.subSequence(begin,end));
        answers.put(answers.size(), snowtam);
    }
}