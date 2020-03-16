package com.example.groupproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {

    private Button home;
    private Button playAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        home = (Button) findViewById(R.id.btnHome);
        playAgain = (Button) findViewById(R.id.btnPlayAgain);

    }

    public void onPlayAgainClick(View v){
        Intent PlayAgainIntent = new Intent(getBaseContext(),   SnakeGame.class);
        startActivity(PlayAgainIntent);
    }

    public void onHomeClick(View v){
        Intent HomeIntent = new Intent(getBaseContext(),   MainActivity.class);
        startActivity(HomeIntent);
    }

}
