package com.example.alexey.mytimemanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv;
    ListView lvMain;
    Button btnAdd, btnShow, btnClear;
    DataHelper dh;
    SQLiteDatabase db;
    String[] activities;

    String LOG_TAG = "myLogsMain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnShow = (Button) findViewById(R.id.btnShow);
        btnClear = (Button) findViewById(R.id.btnClear);
        lvMain = (ListView) findViewById(R.id.lvMain);
        tv = (TextView) findViewById(R.id.tv);
        btnAdd.setOnClickListener(this);
        btnShow.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        dh = new DataHelper(this);
        SQLiteDatabase db = dh.getReadableDatabase();
        tv.setMovementMethod(new ScrollingMovementMethod());
        Cursor c = db.query("timetable",
                null, null,
                null, null,
                null, null);
        Show(c,activities);
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,activities);
        lvMain.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        SQLiteDatabase db = dh.getReadableDatabase();
        switch (v.getId()) {
            case R.id.btnClear:
                tv.setText("");
                break;
            case R.id.btnAdd:
                intent = new Intent(this, ActivityAdd.class);
                startActivity(intent);
                break;

            case R.id.btnShow:
                Log.d(LOG_TAG, "--- Rows in mytable: ---");
                // делаем запрос всех данных из таблицы mytable, получаем Cursor
                Cursor c = db.query("timetable",
                        null, null,
                        null, null,
                        null, null);
                break;
            default:
                break;
        }
    }

    public TextView Show(TextView tv, Cursor cursor) {
        if (cursor.moveToFirst()) {
            int activityColIndex = cursor.getColumnIndex("activity");
            int timeColIndex = cursor.getColumnIndex("time");
            int i = 0;
            do {
                activities[i] = cursor.getString(activityColIndex);
                tv.setText(tv.getText() + " activity = "
                        + cursor.getString(activityColIndex) + ", time = "
                        + cursor.getString(timeColIndex) + "\n");

            } while (cursor.moveToNext());
        }
        cursor.close();
        return tv;
    }
    public String[] Show(Cursor c,String[] arr) {

        if (c.moveToFirst()) {
            int activityColIndex = c.getColumnIndex("activity");
            int timeColIndex = c.getColumnIndex("time");
            int i = 0;
            do {
                arr[i] = c.getString(activityColIndex);
                i++;
            } while (c.moveToNext());
        }
        c.close();
        return arr;
    }
}
