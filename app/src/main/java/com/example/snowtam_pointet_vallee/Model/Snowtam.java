package com.example.snowtam_pointet_vallee.Model;

public class Snowtam implements java.io.Serializable{

    private String original;
    private String decode;

    public Snowtam(String original) {
        this.original = original;
        System.out.println(original);
    }

    public Snowtam() {
        this.original = "pas de snowtam pour cet aeroport";
    }

    public String getOriginal() {
        return original;
    }
}
