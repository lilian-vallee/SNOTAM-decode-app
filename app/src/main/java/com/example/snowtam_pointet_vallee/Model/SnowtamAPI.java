package com.example.snowtam_pointet_vallee.Model;

import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;


public class SnowtamAPI {

    private Snowtam[] answerList;

    public SnowtamAPI() {
    }

    public void Request(String[] requestList, Context applicationContext) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(applicationContext);

        String url = requestList[0];

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //TODO

                        System.out.println("Reponse : ");
                        System.out.println(response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error");
            }
        });

        queue.add(jsonArrayRequest);
    }
}