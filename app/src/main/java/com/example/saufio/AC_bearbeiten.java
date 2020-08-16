package com.example.saufio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AC_bearbeiten extends AppCompatActivity {

Aufgabe aufgabe;
Button btn_add;
EditText et_Aufgabe;
int id;
    DatabaseHandler db = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_c_bearbeiten);
        btn_add = (Button)findViewById(R.id.btn_add);
        et_Aufgabe = (EditText)findViewById(R.id.et_Aufgabe);
        id = getIntent().getIntExtra("IDAufgabe",0);
        if (id!=0){
            aufgabe=db.getAufgabe(id);
            et_Aufgabe.setText(aufgabe.getAufgabe());
            Log.i("TEST",String.valueOf(id));
            btn_add.setText("Aufgabe Updaten");
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