package com.example.jarvis.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class NotesTable {
    static final String TABLENAME ="User";
    static final String COLUMN_ID ="id";
    static final String COLUMN_first_name ="first_name";
    static final String COLUMN_Last_name ="last_name";
    static final String COLUMN_Username ="username";
    static final String COLUMN_Password ="password";

    static public void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append(" CREATE TABLE "+ TABLENAME + " ( ");
        sb.append(COLUMN_ID + " integer autoincrement, ");
        sb.append(COLUMN_first_name + " text not null, ");
        sb.append(COLUMN_Last_name + " text not null,");
        sb.append(COLUMN_Username + " text primary key, ");
        sb.append(COLUMN_Password + " text not null ) ; ");
        try{
            db.execSQL(sb.toString());
        }catch (SQLiteException ex){
            ex.printStackTrace();
        }
    }

    static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ TABLENAME);
        NotesTable.onCreate(db);
    }
}
