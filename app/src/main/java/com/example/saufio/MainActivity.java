package com.example.saufio;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saufio.sampledata.Allgemein;
import com.example.saufio.sampledata.Aufgabe;

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

        Aufgabe a = new Aufgabe(1,null);
        a.getAddGameaufgabe(this);
    }

    public void btn_zumSpiel(View view) {
        if (Spieler.size()>=2) {
            //Intent i = new Intent(MainActivity.this, Hauptspiel.class);
            Intent i = new Intent(MainActivity.this, ac_aufgaben.class);
            i.putExtra("key", Spieler);
            i.putExtra("key2",numberpicker);
            startActivity(i);
        }
        //wenn zu wenig Spieler
        else {
            Allgemein.alertOK(this,getResources().getString(R.string.alter_spielername_title),getResources().getString(R.string.alter_spielername_message),getResources().getString(R.string.ok));
        }
    }

    //Numberpicker
        public void btn_add(View view) throws InterruptedException {
        if (tp_spieler.getText().length() != 0){
            Spieler.add(tp_spieler.getText().toString());
            Spielertxt = Spielertxt +" "+tp_spieler.getText().toString() + ",";
            tv_spieler1.setText(Spielertxt.toString().substring(0,Spielertxt.length()-2));
            tp_spieler.setText("");
            // Verstecke Keybord
            InputMethodManager keybord = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            keybord.hideSoftInputFromWindow(tp_spieler.getWindowToken(), 0);
            Toast x= Toast.makeText(getApplicationContext(),tp_spieler.getText().toString()+" "+getResources().getString(R.string.t_add),Toast.LENGTH_SHORT);
            x.setGravity(Gravity.BOTTOM,0,0);
            x.show();
        } else
            Allgemein.alertOK(this,getResources().getString(R.string.alter_spielername_title),getResources().getString(R.string.alter_spielername_message),getResources().getString(R.string.ok));
    }

    NumberPicker.OnValueChangeListener onValueChangeListener = new 	NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    numberpicker=numberPicker.getValue();
                }
    };
}