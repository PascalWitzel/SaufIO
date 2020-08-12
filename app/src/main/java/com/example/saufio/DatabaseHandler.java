package com.example.saufio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "aufgabedb";
    private static final String TABLE_AUFGABE = "aufgabetb";
    private static final String KEY_ID = "id";
    private static final String KEY_AUFGABETXT = "aufgabetxt";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Erstelle Tabelle
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_AUFGABE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_AUFGABETXT + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        Log.i("Database","Create Table");
    }

    // Upgrade Database database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUFGABE);
        Log.i("Database","DROP Table");
        // Create tables again
        onCreate(db);
    }

    // aufgabe hinzufügen
    void addAufgabe(Aufgabe aufgabe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_AUFGABETXT, aufgabe.getAufgabe());
        // Inserting Row
        db.insert(TABLE_AUFGABE, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
        Log.i("Database","Aufgabe hinzugefügt: "+aufgabe.getAufgabe());
    }

    // einzelne Aufgabe bekommen
    Aufgabe getAufgabe(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_AUFGABE, new String[]{KEY_ID,
                        KEY_AUFGABETXT}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        try {
            Aufgabe aufgabe = new Aufgabe(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
            // return contact
            Log.i("Database","Aufgabe bekommmen: "+aufgabe.getAufgabe());
            return aufgabe;
        } catch (Exception e) {
            Log.i("Database", String.valueOf(e));
            return null;
        }
    }
        // alle aufgaben in List
    public List<Aufgabe> getAllAufgabe () {
        List<Aufgabe> aufgabeList = new ArrayList<Aufgabe>();
        String selectQuery = "SELECT  * FROM " + TABLE_AUFGABE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Aufgabe aufgabe = new Aufgabe();
                aufgabe.setId(Integer.parseInt(cursor.getString(0)));
                aufgabe.setAufgabe(cursor.getString(1));
                aufgabeList.add(aufgabe);
            } while (cursor.moveToNext());
        }
            // return list
        Log.i("Database","Aufgabe Liste: " + aufgabeList);
        return aufgabeList;
    }

        // einzelne Aufgabe upgrade
    public int updateAufgabe (Aufgabe aufgabe){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_AUFGABETXT, aufgabe.getAufgabe());
        // updating row
        Log.i("Database","Update Aufgabe:+ "+aufgabe.getAufgabe());
         return db.update(TABLE_AUFGABE, values, KEY_ID + " = ?",
            new String[]{String.valueOf(aufgabe.getId())});

    }

       // Lösche aufgabe
    public void deleteAufgabe (Aufgabe aufgabe){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_AUFGABE, KEY_ID + " = ?",
            new String[]{String.valueOf(aufgabe.getId())});
        db.close();
        Log.i("Database","Aufgabe gelöscht "+aufgabe.getId()+" "+aufgabe.getAufgabe());
        }

    public void deleteTabeleLeeren () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_AUFGABE, null, null);
        db.close();
        Log.i("Database","Tabelle geleert");
    }

    public int getLastID() {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT MAX(" + KEY_ID + ") FROM " + TABLE_AUFGABE, null);
        int maxid = (cursor.moveToFirst() ? cursor.getInt(0) : 0);
        try {
            Log.i("Database","Maxid: "+maxid);
            return maxid;
        } catch (Exception e) {
            Log.i("Database", String.valueOf(e));
            return 0;
        }
    }

    public int getFirstID() {
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT min("+KEY_ID+") FROM "+TABLE_AUFGABE, null);
        int minid = (cursor.moveToFirst() ? cursor.getInt(0) : 0);
        try {
            Log.i("Database","Maxid: "+minid);
            return minid;
        } catch (Exception e){
            Log.i("Database",String.valueOf(e));
            return 0;
        }
    }
}