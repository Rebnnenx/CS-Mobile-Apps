package com.example.groupproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {

    private Button home;
    private Button playAgain;
    private TextView Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        Intent gameOverIntent = getIntent();
        String score = gameOverIntent.getStringExtra("Score");

        Score = (TextView) findViewById(R.id.txtInst1);

        Score.setText("Your score is: " + score + " Points!!");
        home = (Button) findViewById(R.id.btnPlayAgain);
        playAgain = (Button) findViewById(R.id.btnHome);

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
