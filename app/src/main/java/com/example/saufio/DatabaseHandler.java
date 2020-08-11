package com.example.saufio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_AUFGABE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_AUFGABETXT + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUFGABE);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    void addAufgabe(Aufgabe aufgabe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_AUFGABETXT, aufgabe.getAufgabe());
        // Inserting Row
        db.insert(TABLE_AUFGABE, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    Aufgabe getAufgabe(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_AUFGABE, new String[]{KEY_ID,
                        KEY_AUFGABETXT}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Aufgabe aufgabe = new Aufgabe(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        // return contact
        return aufgabe;
    }

    // code to get all contacts in a list view
    public List<Aufgabe> getAllAufgabe() {
        List<Aufgabe> aufgabeList = new ArrayList<Aufgabe>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_AUFGABE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Aufgabe aufgabe = new Aufgabe();
                aufgabe.setId(Integer.parseInt(cursor.getString(0)));
                aufgabe.setAufgabe(cursor.getString(1));
                // Adding contact to list
                aufgabeList.add(aufgabe);
            } while (cursor.moveToNext());
        }

        // return contact list
        return aufgabeList;
    }

    // code to update the single contact
    public int updateAufgabe(Aufgabe aufgabe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_AUFGABETXT, aufgabe.getAufgabe());
        // updating row
        return db.update(TABLE_AUFGABE, values, KEY_ID + " = ?",
                new String[]{String.valueOf(aufgabe.getId())});
    }

    // Deleting single contact
    public void deleteAufgabe(Aufgabe aufgabe) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_AUFGABE, KEY_ID + " = ?",
                new String[]{String.valueOf(aufgabe.getId())});
        db.close();
    }

    // Getting contacts Count
    public int getAufgabeCount() {
        String countQuery = "SELECT  * FROM " + TABLE_AUFGABE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int x=cursor.getCount();
        cursor.close();
        // return count
        return x;
    }
}