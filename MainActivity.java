package com.example.advancedsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INTEGER(3), id INTEGER PRIMARY KEY)");
            sqLiteDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Bob', 23)");
            sqLiteDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Tommy', 21)");

            sqLiteDatabase.execSQL("DELETE FROM users WHERE id = 1");

            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users", null);

            int nameIndex = cursor.getColumnIndex("name");
            int ageIndex = cursor.getColumnIndex("age");
            int idIndex = cursor.getColumnIndex("id");

            cursor.moveToFirst();

            while (true) {
                Log.i("UserResult - name", cursor.getString(nameIndex));
                Log.i("UserResult - age", Integer.toString(cursor.getInt(ageIndex)));
                Log.i("UserResult - id", Integer.toString(cursor.getInt(idIndex)));
                cursor.moveToNext();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}