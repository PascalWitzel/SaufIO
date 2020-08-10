package com.example.saufio;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import static android.content.Context.MODE_PRIVATE;


public class DBAufgaben{

    //private static String databasename;
    final String databasename = "database";
    final String tabelename = "aufgabetabele";
    final String ID = "ID";
    final String Aufgabe = "Aufgabe";


    //Create Database
    public void CreateDatabase(Context c){

        SQLiteDatabase aufgabe =c.openOrCreateDatabase(databasename,MODE_PRIVATE,null);

        //aufgabe.execSQL("Create TABLE "+ tabelename+"("+ID +" Integer primary key AUTOINCREMENT, "+Aufgabe + " Text)");
        Log.i("Database","Create Database");
        aufgabe.close();
    }
    //Creeate Record
    public void CreateRecord(Context c,String aufgabetext, int id){
        SQLiteDatabase aufgabe = c.openOrCreateDatabase(databasename,MODE_PRIVATE,null);
        aufgabe.execSQL("Insert into "+ tabelename +"("+ID+","+Aufgabe+")values ("+id+",'"+aufgabetext+"')");
        Log.i("Database","Create Record");
        //aufgabe.execSQL("Insert into "+ tabelename +"values ("+id+",'"+aufgabetext+"')");
        aufgabe.close();
    }
    //ermittle höchste ID
    public int Highid(Context c){
        int anzahl = 0;
        SQLiteDatabase aufgabe = c.openOrCreateDatabase(databasename,MODE_PRIVATE,null);
        Cursor cursor = aufgabe.rawQuery("select max("+ID+") from "+tabelename,null);
        cursor.moveToLast();
        anzahl=cursor.getInt(0);
        cursor.close();
        aufgabe.close();
        Log.i("Database","Highdatensatz: " +anzahl);
        return anzahl;
    }


    //Ermittle Aufgabe
    public String Querywert(Context c,int id){
        String aufgabetxt=null;
        SQLiteDatabase aufgabe = c.openOrCreateDatabase(databasename,MODE_PRIVATE,null);
        Cursor cursor = aufgabe.rawQuery("select "+Aufgabe+" from "+tabelename+" /*where "+ID+" ="+id,null);
        Log.i("Database","Cursor: " +cursor);
        //Cursor cursor = aufgabe.rawQuery("select "+Aufgabe+" from "+tabelename,null);
        cursor.moveToLast();
        Log.i("Database","Cursor: " +cursor.moveToLast());
        aufgabetxt=cursor.getString(0);
        Log.i("Database","Cursor: " +cursor.getString(0));
        cursor.close();
        aufgabe.close();
        Log.i("Database","Aufgabe: " +aufgabetxt);
        return aufgabetxt;
    }

    public void TabelleLeeren(Context c){
        SQLiteDatabase aufgabe = c.openOrCreateDatabase(databasename,MODE_PRIVATE,null);
        aufgabe.execSQL("Delete from "+tabelename);
        Log.i("Database","Tabelle wurde komplett gelöscht");
        aufgabe.close();
    }

    public void TabellensatzLoeschen(Context c, String id){
        SQLiteDatabase aufgabe = c.openOrCreateDatabase(databasename,MODE_PRIVATE,null);
        aufgabe.execSQL("Delete from "+tabelename+" where "+ID+"="+id);
        Log.i("Database","Datensatz mit der ID wurde gelöscht: "+id);
        aufgabe.close();
    }


    //Todo: mehr aufgaben ausdenken
    public static void erstelleAufgaben(){
        DBAufgaben dbAufgaben =new DBAufgaben();

    }


}
