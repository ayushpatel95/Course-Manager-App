package com.example.jarvis.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "users.db";
    static final int DB_VERSION = 1;
    public DatabaseOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("Create table "+ NotesTable.TABLENAME+" (" + NotesTable.COLUMN_ID +" integer auto_increment," + NotesTable.COLUMN_first_name+ " text ," + NotesTable.COLUMN_Last_name + " text, " + NotesTable.COLUMN_Username + " text, " + NotesTable.COLUMN_Password +" text, image longblob);");
        try{
            db.execSQL(sb.toString());
        }catch (SQLiteException ex){
            ex.printStackTrace();
        }
        StringBuilder sb1 = new StringBuilder();
        sb1.append("Create table Instructors ( id integer auto_increment, firstname text, lastname text, email text, Personal_Website text, image longblob, user_id integer, Foreign key(user_id) references Users(user_id) );");
        try{
            db.execSQL(sb1.toString());
        }catch (SQLiteException ex){
            ex.printStackTrace();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Create table Course ( id integer auto_increment primary key, instructor_id integer, user_id integer , title text , day text, time text, semester integer , credit integer, foreign key(user_id) references User(id), foreign key(instructor_id) references Instructors(id));");
        try{
            db.execSQL(sb2.toString());
        }catch (SQLiteException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        NotesTable.onUpgrade(db, oldVersion, newVersion);
    }
}
