package com.example.alexey.mytimemanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alexey on 07.04.18.
 */

public class DataHelper extends SQLiteOpenHelper {
    public DataHelper(Context context){
        super(context,"myDB",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table timetable ("
        +"id integer primary key autoincrement,"+
        "activity text,"+ "time text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
