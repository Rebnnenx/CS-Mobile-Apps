package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSnakeClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   SnakeGame.class);
        startActivity(myIntent);
    }

    public void onInstructClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Instruct.class);
        startActivity(myIntent);
    }
}
