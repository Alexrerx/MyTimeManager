package com.example.alexey.mytimemanager;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityAdd extends AppCompatActivity implements View.OnClickListener {
    DataHelper dbHelp;
    Button btnFinish,btnRead;
    EditText etCase,etTime;
    Intent intent;
    String LOG_TAG = "MyLogs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnFinish = (Button)findViewById(R.id.btnFinish);
        btnRead = (Button) findViewById(R.id.btnRead);
        etCase = (EditText) findViewById(R.id.etCase);
        etTime = (EditText) findViewById(R.id.etTime);
        btnFinish.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        dbHelp = new DataHelper(this);
    }

    @Override
    public void onClick(View v) {
        ContentValues contentValues = new ContentValues();
        String activity = etCase.getText().toString();
        String time = etTime.getText().toString();
        SQLiteDatabase database =  dbHelp.getWritableDatabase();

        switch (v.getId()){

            case R.id.btnFinish:

                contentValues.put("activity",activity);
                contentValues.put("time",time);
                database.insert("timetable",null,contentValues);
                finish();
                break;
        }
    }
}
