package com.example.saufio;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Random;

public class Hauptspiel extends AppCompatActivity {

    //ImageView
    ImageView img_muenze;
    //Button
    Button btn_zahl, btn_kopf, btn_zufall;
    //TextView
    TextView tv_spieler1111, tv_aufgabe;
    //INT
    int zufallszahl, zufallspieler;
    //Array
    ArrayList<String> Spieler;
    //Imports generierung
    //Zufallgenerator
    Random r= new java.util.Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hauptspiel);
        tv_spieler1111 = (TextView)findViewById(R.id.tv_spieleranzeige);
        tv_aufgabe=(TextView)findViewById(R.id.tv_aufgabe);
        TextView tv =(TextView)findViewById(R.id.textView);
        Spieler = (ArrayList<String>) getIntent().getSerializableExtra("key");
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
       //tODO: Farbwert + 1;
    }
    public void btn_spielerloeschen(View view){
        Spieler.add("Spieler hinzufügen");
        CharSequence spieler[] = Spieler.toArray(new CharSequence[Spieler.size()]);;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.alter_spielerfrage));
        builder.setItems(spieler, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (Spieler.size()-1==which){
                    //ToDo: Dialogbox zum eintragen Spielername
                    Spieler.remove(Spieler.size()-1);
                    Spieleradd();
                }
                else if (Spieler.size()>3){
                    Toast.makeText(getApplicationContext(),Spieler.get(which)+" "+ getResources().getString(R.string.t_loschen),Toast.LENGTH_SHORT).show();
                    Spieler.remove(which);
                    Spieler.remove(Spieler.size()-1);
                } else {
                    wenigSpieler();
                    Spieler.remove(Spieler.size()-1);
                }
                tv_aufgabe.setText(String.valueOf(which));
            }
        });
        builder.show();
    }

    //Eigene Methoden
    //Suche einen Zufälligen  Spieler
    public void Spielerauswaehlen(){
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

    //Alert wenn zu Wenig Spieler bei löschen
    public void wenigSpieler(){
        AlertDialog.Builder alterDialog = new AlertDialog.Builder(this);
        alterDialog.setTitle(getResources().getString(R.string.alter_spielerloeschen_title));
        alterDialog.setMessage(getResources().getString(R.string.alter_spieleerloeschen_message));
        alterDialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onBackPressed();
            }
        });
        alterDialog.create();
        alterDialog.show();
    }

    public void Spieleradd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.alter_spielerhinzufügen_title));
        builder.setMessage(getResources().getString(R.string.alter_spielerhinzufügen_message));
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_DATETIME_VARIATION_NORMAL);
        builder.setView(input);
        builder.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Spieler.add(input.getText().toString());
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.abbrechen), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}