package com.example.saufio.sampledata;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.example.saufio.R;

public class Allgemein {
    public static void alertOK(Context c,String title,String message, String button){
        AlertDialog.Builder alterDialog = new AlertDialog.Builder(c);
        alterDialog.setTitle(title);
        alterDialog.setMessage(message);
        alterDialog.setPositiveButton(button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alterDialog.create();
        alterDialog.show();
    }
}
