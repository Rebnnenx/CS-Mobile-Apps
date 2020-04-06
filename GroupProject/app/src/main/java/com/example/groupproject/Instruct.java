package com.example.groupproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Instruct extends AppCompatActivity {

    private Button home;
    private Button play;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruct);

        home = (Button) findViewById(R.id.btnPlayAgain);
        play = (Button) findViewById(R.id.btnPlay);

    }

    public void onPlayClick(View v){
        Intent PlayIntent = new Intent(getBaseContext(),   SnakeGame.class);
        startActivity(PlayIntent);
    }

    public void onHomeClick(View v){
        Intent HomeIntent = new Intent(getBaseContext(),   MainActivity.class);
        startActivity(HomeIntent);
    }

}
