package com.pulishivani.waldoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    int[] layouts={};
    int count=1;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editor = sharedPreferences.edit();
        for(int x=0; x<layouts.length;x++)
            editor.putString(x+"","Level " + x + ": Incomplete");
    }
    public void next(View view){
        editor.putString(count+"","Level " + count + ": Completed");
        setContentView(layouts[count]);
        count++;
        if(count>=layouts.length){
            count=0;
        }

        Log.i("next", "count=" + count);
    }
    public void home(View view){
        setContentView(R.layout.activity_main);
    }

}
