package com.example.saufio;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    //EditText
    EditText tp_spieler;
    //TextView
    TextView tv_spieler1;
    //ArrayListe
        ArrayList<String> Spieler = new ArrayList<>();
    //Strings
    String Spielertxt;
    //int
    int numberpicker=1;
    //erstellen Objekte


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tp_spieler=(EditText)findViewById(R.id.tp_spieler);
        tv_spieler1 =(TextView) findViewById(R.id.tv_Spieler);

        Spielertxt=getResources().getString(R.string.spielernamen);
        //erstellen Numberpicker
        NumberPicker np = findViewById(R.id.numberPicker);
        np.setMinValue(1);
        np.setMaxValue(5);
        np.setOnValueChangedListener(onValueChangeListener);
        //Todo: erstellen der Database
        //Todo: aufrufen und erstellen der Aufgaben

        DatabaseHandler d = new DatabaseHandler(this);
        d.addAufgabe(new Aufgabe(1,"fotzkopf"));
        Log.i("TEST",String.valueOf(d.getAufgabeCount()));
        Aufgabe a=d.getAufgabe(d.getAufgabeCount()-1);
        String x=a.getAufgabe();
        Log.i("TEST",x);
    }

    public void btn_zumSpiel(View view) {
        if (Spieler.size()>=2) {
            Intent i = new Intent(MainActivity.this, Hauptspiel.class);
            i.putExtra("key", Spieler);
            i.putExtra("key2",numberpicker);
            startActivity(i);
        }
        //wenn zu wenig Spieler
        else {
            AlertDialog.Builder alterDialog = new AlertDialog.Builder(this);
            alterDialog.setTitle(getResources().getString(R.string.alter_spieler_title));
            alterDialog.setMessage(getResources().getString(R.string.alter_spieler_message));
            alterDialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    }
            });
            alterDialog.create();
            alterDialog.show();
        }
    }

    //Numberpicker
        public void btn_add(View view) {
        AlertDialog.Builder alterDialog = new AlertDialog.Builder(this);
        if (tp_spieler.getText().length() != 0){
            Spieler.add(tp_spieler.getText().toString());
            Spielertxt = Spielertxt +" "+tp_spieler.getText().toString() + ",";
            tv_spieler1.setText(Spielertxt.toString().substring(0,Spielertxt.length()-2));
            Toast.makeText(getApplicationContext(),tp_spieler.getText().toString()+" "+getResources().getString(R.string.t_add),Toast.LENGTH_SHORT).show();
            tp_spieler.setText("");
        } else
            alterDialog.setTitle(getResources().getString(R.string.alter_spielername_title));
        alterDialog.setMessage(getResources().getString(R.string.alter_spielername_title));
        alterDialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
    }

    NumberPicker.OnValueChangeListener onValueChangeListener = new 	NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    numberpicker=numberPicker.getValue();
                }
    };
}