package com.example.saufio;



import android.content.Context;
import android.util.Log;

import java.util.Random;

public class Aufgabe {

    int id;
    String aufgabe;
    String aenderung;
    String kategorie;

    public Aufgabe(){}
    public Aufgabe(int id, String aufgabe, String aenderung,String kategorie){
        this.id=id;
        this.aufgabe=aufgabe;
        this.aenderung=aenderung;
        this.kategorie=kategorie;
    }

    //Getter und Setter ID
    public int getId(){return this.id;}
    public void setId(int id){this.id=id;}

    //Getter und Setter aufgabe
    public String getAufgabe(){return this.aufgabe;}
    public void  setAufgabe(String aufgabe){this.aufgabe=aufgabe;
    }

    //Getter und Setter aenderung
    public String getAenderung(){return this.aenderung;}
    public void setAenderung(String aenderung){this.aenderung=aenderung;}

    //Getter und Setter Kategorie
    public String getKategorie(){return this.kategorie;}
    public void setKategorie(String kategorie){this.kategorie=kategorie;}


    public String getAufgabeDB (Context c){
        DatabaseHandler d = new DatabaseHandler(c);
        Aufgabe aufgabe;
        do {
            aufgabe=d.getAufgabe(new Random().nextInt(d.getLastID()+1));
        } while (aufgabe==null);
        return aufgabe.getAufgabe();
    }


    public static void getAddGameaufgabe(Context c){
        DatabaseHandler d = new DatabaseHandler(c);
        Aufgabe a = new Aufgabe(1,"TESTFOTZE","N","WennIchDuWäre");
        d.addAufgabe(a);


    }
}











