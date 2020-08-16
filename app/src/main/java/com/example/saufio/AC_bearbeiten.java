package com.example.saufio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AC_bearbeiten extends AppCompatActivity {

Aufgabe aufgabe;
Button btn_add;
EditText et_Aufgabe;
Spinner spinner;
int id;
    DatabaseHandler db = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_c_bearbeiten);
        btn_add = (Button) findViewById(R.id.btn_add);
        et_Aufgabe = (EditText) findViewById(R.id.et_Aufgabe);
        id = getIntent().getIntExtra("IDAufgabe", 0);
        if (id != 0) {
            aufgabe = db.getAufgabe(id);
            Log.i("TTEEEEST",aufgabe.getKategorie().toString());
            et_Aufgabe.setText(aufgabe.getAufgabe());
            Log.i("TEST", String.valueOf(id));
            btn_add.setText("Aufgabe Updaten");
        }

        //todo: automatische übergabe spinner setzen
        //Spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter;
        List<String> list;
        list = db.getKategorien();
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        for (int i = 0; i < list.size(); i++) {
            if (aufgabe.getKategorie() == list.get(i)) {
                Log.i("FOTZEHUREARschloch",aufgabe.getKategorie());
                spinner.setSelection(i);
            }
        }
    }


    public void aufgabe_update_add(View view) {
        aufgabe.setAufgabe(et_Aufgabe.getText().toString());
        if (id!=0) {
            db.updateAufgabe(aufgabe);
        }   else {

        }
        startActivity(new Intent(AC_bearbeiten.this,ac_aufgaben.class));
    }
}