package com.example.saufio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.saufio.sampledata.DatabaseHandler;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ac_aufgaben extends AppCompatActivity {

    String menu_loeschen;
    String menu_bearbeiten;

    ListView listView;
    List<String> aufgabe;
    ArrayAdapter<String> arrayAdapter;
    int auswahl;
    DatabaseHandler db = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_aufgaben);
        //Listview adapter
        listView = (ListView) findViewById(R.id.listview);
        aufgabe = db.getAllAufgabeString();
        aufgabe.add(0,"Aufgabe hinzufügen");
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, aufgabe);
        listView.setAdapter(arrayAdapter);
        registerForContextMenu(listView);
        menu_bearbeiten="bearbeiten";
        menu_loeschen="löschen";

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                int selectedFromList =(int) (listView.getItemIdAtPosition(myItemInt));
                Log.i("DATABASE",String.valueOf(myItemInt));
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        ListView lv = (ListView) listView;
        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
        auswahl= (int) lv.getItemIdAtPosition(acmi.position);
        menu.setHeaderTitle("Datensatz");
        menu.add(0, v.getId(), 0, menu_bearbeiten);
        menu.add(0, v.getId(), 0, menu_loeschen);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (menu_loeschen==item.getTitle()) {
            db.deleteAufgabe(db.sucheUeberTxt(aufgabe.get(auswahl)));
            listviewRefresh();

        } else if (menu_bearbeiten==item.getTitle()){
            //Todo: Aufgabe bearbeiten
        }
        return true;
    }

    public void listviewRefresh(){
        aufgabe.clear();
        aufgabe=db.getAllAufgabeString();
        arrayAdapter.clear();
        aufgabe.add(0,"Aufgabe hinzufügen");
        arrayAdapter.addAll(aufgabe);
        listView.setAdapter(arrayAdapter);
    }
}