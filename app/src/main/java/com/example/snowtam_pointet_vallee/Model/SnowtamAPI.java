package com.example.snowtam_pointet_vallee.Model;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class SnowtamAPI extends AsyncTask {

    private ArrayList<String> answerList = new ArrayList<String>();

    public SnowtamAPI() {
    }

    public void Request(String[] requestList) {


    }

    @Override
    protected Object doInBackground(Object[] objects) {

        for (int i=0; i < objects.length; i++) {

            JSONObject jsonObject = null;

            try {

                System.out.println("sending request : "+ objects[i]);
                jsonObject = readJsonFromUrl((String) objects[i]);
                System.out.println("read answer");
                System.out.println(jsonObject.toString());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return answerList;
    }

//    public void Request(ArrayList<String> requestList){
//
//        for (int i=0; i < requestList.size(); i++) {
//
//            JSONObject jsonObject = null;
//
//            try {
//
//                System.out.println("sending request : "+ requestList.get(i));
//                jsonObject = readJsonFromUrl(requestList.get(i));
//                System.out.println("read answer");
//                System.out.println(jsonObject.toString());
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    private static String readAll(Reader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        while ((i = reader.read()) != -1) {
            stringBuilder.append((char) i);
        }
        return stringBuilder.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(reader);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

}

//        public static void main(String[] args) throws IOException, JSONException {
//            JSONObject json = readJsonFromUrl("https://swapi.co/api/people/1/");
//            System.out.println(json.toString());
//            System.out.println(json.get("id"));
//        }
