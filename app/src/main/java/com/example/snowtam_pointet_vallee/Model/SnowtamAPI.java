package com.example.snowtam_pointet_vallee.Model;

import java.util.ArrayList;

public class SnowtamAPI {


    public void Request(ArrayList<String> requestList) {



    }
}


//    public class JsonReader {
//
//        private static String readAll(Reader rd) throws IOException {
//            StringBuilder sb = new StringBuilder();
//            int cp;
//            while ((cp = rd.read()) != -1) {
//                sb.append((char) cp);
//            }
//            return sb.toString();
//        }
//
//        public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
//            InputStream is = new URL(url).openStream();
//            try {
//                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//                String jsonText = readAll(rd);
//                JSONObject json = new JSONObject(jsonText);
//                return json;
//            } finally {
//                is.close();
//            }
//        }
//
//        public static void main(String[] args) throws IOException, JSONException {
//            JSONObject json = readJsonFromUrl("https://swapi.co/api/people/1/");
//            System.out.println(json.toString());
//            System.out.println(json.get("id"));
//        }
//    }