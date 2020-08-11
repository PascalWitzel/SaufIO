package com.example.saufio;

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

}

