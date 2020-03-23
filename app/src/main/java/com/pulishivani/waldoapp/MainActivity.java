package com.pulishivani.waldoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class MainActivity extends AppCompatActivity {
    int[] layouts = {R.layout.waldo1, R.layout.waldo2, R.layout.waldo3, R.layout.waldo4, R.layout.waldo5};
    int count = 0;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int[] header = {R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4, R.id.textView5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();
        for (int x = 1; x <= header.length; x++) {
            editor.putString(x + "", "Level " + x + ": Incomplete");
            editor.apply();
        }
    }

    public void found(View view) {
        int x = count + 1;
        editor.putString(x + "", "Level " + x + ": Completed");
        editor.apply();
        TextView head = findViewById(header[count]);
        head.setText(sharedPreferences.getString(x + "", "Incomplete"));
        Toast.makeText(getApplicationContext(), "You Found Him!", Toast.LENGTH_SHORT).show();
    }

    public void next(View view) {
        count++;
        if (count >= layouts.length) {
            count = 0;
        }
        setContentView(layouts[count]);
        int x = count + 1;
        TextView head = findViewById(header[count]);
        head.setText(sharedPreferences.getString(x + "", "Incomplete"));

    }

    public void prev(View view) {
        count--;
        if (count < 0) {
            count = count + layouts.length;
        }
        setContentView(layouts[count]);
        int x = count + 1;
        TextView head = findViewById(header[count]);
        head.setText(sharedPreferences.getString(x + "", "Incomplete"));
        Log.i("next", "count=" + count);
    }

    public void home(View view) {
        setContentView(R.layout.activity_main);
        Gson gson = new GsonBuilder().create();
        Waldo[] myWaldo = {gson.fromJson("{ image:1, name: \"Carnival\" }", Waldo.class),gson.fromJson("{ image:2, name: \"Ski Lodge\" }", Waldo.class),gson.fromJson("{ image:3, name: \"Outer Space\" }", Waldo.class),gson.fromJson("{ image:4, name: \"Factory\" }", Waldo.class),gson.fromJson("{ image:5, name: \"Beach\" }", Waldo.class)};
        TextView myText=findViewById(R.id.currently);
        myText.setText("Currently on Level " + myWaldo[count].image + ": " + myWaldo[count].name);
        Log.i("json", gson.toJson(myWaldo[count]));
        count--;
    }


    public class Waldo {
        public int image;
        public String name;
    }
}