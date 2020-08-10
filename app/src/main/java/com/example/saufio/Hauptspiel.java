package com.example.saufio;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

public class Hauptspiel extends AppCompatActivity {

    //ImageView
    ImageView img_muenze;
    //Button
    Button btn_zahl, btn_kopf, btn_zufall;
    //TextView
    TextView tv_spieler1111;
    //INT
    int zufallszahl, zufallspieler;
    //Imports generierung
    //Zufallgenerator
    Random r= new java.util.Random();
    DBAufgaben dbAufgaben=new DBAufgaben();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hauptspiel);
        tv_spieler1111 = (TextView)findViewById(R.id.tv_spieleranzeige);
        TextView tv =(TextView)findViewById(R.id.textView);
        //dbAufgaben.CreateRecord(getBaseContext(),"WerrdeichgenommenFotze");
        tv.setText(dbAufgaben.Querywert(getBaseContext(),1));
    }

    @Override
    public void onBackPressed(){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Todo: Spielerstatistiken und Gesamtstatistik
                        Intent intent = new Intent(Hauptspiel.this,MainActivity.class);
                        startActivity(intent);

                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.alter_spielende_title));
        builder.setMessage(getResources().getString(R.string.alter_spielende_title)).setPositiveButton(getResources().getString(R.string.yes), dialogClickListener)
                .setNegativeButton(getResources().getString(R.string.no), dialogClickListener).show();
    }

    //Buttons
    public void btn_zahl(View view){
        kopf_o_zahl(0);
    }
    public void btn_kopf(View view){
        kopf_o_zahl(1);
    }
    public void btn_zufall(View view){
       Spielerauswaehlen();
       einblenden_k_o_z();
    }
    public void btn_spielerloeschen(View view){
        //Todo: einzelnen Spieler löschen
        DBAufgaben dbAufgaben = new DBAufgaben();
        tv_spieler1111.setText(dbAufgaben.Querywert(getBaseContext(),2));
        //ArrayList<String> Spieler = (ArrayList<String>) getIntent().getSerializableExtra("key");
        //Spieler.remove(1);
    }

    //Eigene Methoden
    //Suche einen Zufälligen  Spieler
    public void Spielerauswaehlen(){
        ArrayList<String> Spieler = (ArrayList<String>) getIntent().getSerializableExtra("key");
        zufallspieler = r.nextInt(Spieler.size());
        tv_spieler1111.setText(Spieler.get(zufallszahl)+getResources().getString(R.string.entscheidung));
        img_muenze=(ImageView) findViewById(R.id.img_muenze);
        img_muenze.setVisibility(View.INVISIBLE);
    }

    //ermittle welches Ergebnis
    public void kopf_o_zahl(int wahl){
        img_muenze=(ImageView) findViewById(R.id.img_muenze);
        img_muenze.setVisibility(View.INVISIBLE);
        zufallszahl = r.nextInt(2);
        if (zufallszahl == 1) {
            if (zufallszahl == wahl) {
                tv_spieler1111.setText(getResources().getString(R.string.glueck_kopf));
            } else {
                tv_spieler1111.setText(getResources().getString(R.string.pech_kopf));
            }
            img_muenze.setImageResource(R.drawable.kopf);
            img_muenze.setVisibility(View.VISIBLE);
        } else {
            if (zufallszahl == wahl) {
                tv_spieler1111.setText(getResources().getString(R.string.glueck_zahl));
            } else {
                tv_spieler1111.setText(getResources().getString(R.string.pech_zahl));
            }
            img_muenze.setImageResource(R.drawable.zahl);
            img_muenze.setVisibility(View.VISIBLE);
        }
        ausblenden_k_o_z();
    }
    //Ausblenden von Kopf und Zahl Buttons -> Zufallspieler wird angezeigt
    public void ausblenden_k_o_z(){
        btn_kopf = (Button)findViewById(R.id.btn_kopf);
        btn_zahl = (Button)findViewById(R.id.btn_zahl);
        btn_zufall = (Button)findViewById(R.id.btn_zufall);

        btn_zahl.setVisibility(View.INVISIBLE);
        btn_kopf.setVisibility(View.INVISIBLE);
        btn_zufall.setVisibility(View.VISIBLE);
    }

    //Einblenden von Kopf und Zahl -> Zufallspieler wird nicht angezeigt
    public void einblenden_k_o_z(){
        btn_kopf = (Button)findViewById(R.id.btn_kopf);
        btn_zahl = (Button)findViewById(R.id.btn_zahl);
        btn_zufall = (Button)findViewById(R.id.btn_zufall);

        btn_zahl.setVisibility(View.VISIBLE);
        btn_kopf.setVisibility(View.VISIBLE);
        btn_zufall.setVisibility(View.INVISIBLE);
    }

    public void aufgabebekommen(){
        DBAufgaben dbAufgaben=new DBAufgaben();
        int id = dbAufgaben.Highid(getBaseContext());
        String aufgabe = null;
        while (aufgabe == null) {
            id = r.nextInt(id);
            aufgabe=dbAufgaben.Querywert(getBaseContext(),id);
        }
        id = r.nextInt(3)+1;

        //Todo: Rdm schluck setzen
        //Todo: Textview setzen/erstellen
        //TEXTVIEW setzen
    }
}