package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;




public class SnakeGame extends AppCompatActivity {

    //creates an instance of snake engine
    SnakeEngine snakeEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake_game);

            // Get the dimensions of the screen
            Display display = getWindowManager().getDefaultDisplay();

            // Set pip size
            Point size = new Point();
            display.getSize(size);

            // Create an instance of SnakeEngine class
            snakeEngine = new SnakeEngine(this, size);

            // Set snakeEngine to the view of Activity
            setContentView(snakeEngine);
    }

    @Override
    protected void resume() {
        super.resume();
        snakeEngine.Resume();
    }

    @Override
    protected void pause() {
        super.pause();
        snakeEngine.pause();
    }

}
