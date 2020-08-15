package com.example.saufio;

import android.content.Context;

import java.util.Random;

public class Aufgabe {

    int id;
    String aufgabe;

    public Aufgabe(){}
    public Aufgabe(int id, String aufgabe){
        this.id=id;
        this.aufgabe=aufgabe;
    }

    //Getter und Setter ID
    public int getId(){return this.id;}
    public void setId(int id){this.id=id;}

    //Getter und Setter aufgabe
    public String getAufgabe(){return this.aufgabe;}
    public void  setAufgabe(String aufgabe){this.aufgabe=aufgabe;
    }

    public String getAufgabeDB (Context c){
        DatabaseHandler d = new DatabaseHandler(c);
        Aufgabe aufgabe;
        do {
            aufgabe=d.getAufgabe(new Random().nextInt(d.getLastID()+1));
        } while (aufgabe==null);
        return aufgabe.getAufgabe();
    }

    public void getAddGameaufgabe(Context c){
        DatabaseHandler d = new DatabaseHandler(c);
        Aufgabe a = new Aufgabe(1,"");
        a.setAufgabe("Ich soll geseztz werden");
        d.addAufgabe(a);
        a.setAufgabe("Ich soll geseztz werden2");
        d.addAufgabe(a);
        a.setAufgabe("Ich soll geseztz werden");
        d.addAufgabe(a);
        a.setAufgabe("Ich soll geseztz werden2");
        d.addAufgabe(a);
        a.setAufgabe("Ich soll geseztz werden");
        d.addAufgabe(a);
        a.setAufgabe("Ich soll geseztz werden2");
        d.addAufgabe(a);
        a.setAufgabe("Ich soll geseztz werden");
        d.addAufgabe(a);
        a.setAufgabe("Ich soll geseztz werden2");
        d.addAufgabe(a);
        a.setAufgabe("Ich soll geseztz werden");
        d.addAufgabe(a);
        a.setAufgabe("Ich soll geseztz werden2");
        d.addAufgabe(a);
        a.setAufgabe("Ich soll geseztz werden");
        d.addAufgabe(a);
        a.setAufgabe("Ich soll geseztz werden2");
        d.addAufgabe(a);
        a.setAufgabe("Ich soll geseztz werden");
        d.addAufgabe(a);
        a.setAufgabe("Ich soll geseztz werden2");
        d.addAufgabe(a);
        a.setAufgabe("Ich soll geseztz werden");
        d.addAufgabe(a);
        a.setAufgabe("Ich soll geseztz werden2");
        d.addAufgabe(a);
        a.setAufgabe("Ich soll geseztz werden");
        d.addAufgabe(a);
        a.setAufgabe("Ich soll geseztz werden2");
        d.addAufgabe(a);
        a.setAufgabe("Ich soll geseztz werden");
        d.addAufgabe(a);
        a.setAufgabe("Ich soll geseztz werden2");
        d.addAufgabe(a);
        a.setAufgabe("Ich soll geseztz werden");
        d.addAufgabe(a);
        a.setAufgabe("Ich soll geseztz werden2_neu");
        d.addAufgabe(a);
    }
}












