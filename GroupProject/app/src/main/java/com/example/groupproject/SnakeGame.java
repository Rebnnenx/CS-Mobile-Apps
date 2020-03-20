package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;
import java.util.Random;

public class SnakeGame extends AppCompatActivity {

    //initializes the Canvas and Bitmap Variables
    Canvas canvas;
    SnakeView snakeView;
    Bitmap headBitmap;
    Bitmap bodyBitmap;
    Bitmap tailBitmap;
    Bitmap appleBitmap;

    //NOTE Replace with our new sound effects and add music?
    //initializes the sound variables
    private SoundPool soundPool;
    int appleSound = -1; //Getting apple sound effect
    int deathSound = -1; //Death sound

    //sets the initial direction for the snake
    int directionOfTravel=0;
    //0 is up, 1 is right, 2 is down, 3 is left

    //Initializes other useful variables
    int screenWidth;
    int screenHeight;
    int topGap;
    long lastFrameTime;
    int fps;
    int score;
    int hi;
    int [] snakeX;
    int [] snakeY;
    int snakeLength;
    int appleX;
    int appleY;
    int blockSize;
    int numBlocksWide;
    int numBlocksHigh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Loads the sound
        loadSound();

        //Starts the view for the game
        configureDisplay();
        snakeView = new SnakeView(this);
        setContentView(snakeView);

    }

    class SnakeView extends SurfaceView implements Runnable {
        //Starts the thread for the game
        Thread ourThread = null;
        SurfaceHolder ourHolder;
        //Sets the boolean value that tracks if the game is running or paused
        volatile boolean playingSnake;
        Paint paint;

        public SnakeView(Context context) {
            super(context);
            ourHolder = getHolder();
            paint = new Paint();

            //Sets an Integer array for the snake segments
            snakeX = new int[200];
            snakeY = new int[200];

            //initializes the snake
            getSnake();
            //initializes the first apple
            getApple();
        }

        public void getSnake(){
            //initializes the starting length of the snake
            snakeLength = 3;
            //starts the snake in the middle of screen
            snakeX[0] = numBlocksWide/2;
            snakeY[0] = numBlocksHigh /2;
            snakeX[1] = snakeX[0]-1;
            snakeY[1] = snakeY[0];
            snakeX[1] = snakeX[1]-1;
            snakeY[1] = snakeY[0];
        }

        public void getApple(){
            //Creates an apple in a random spot on the canvas
            Random random = new Random();
            appleX = random.nextInt(numBlocksWide-1)+1;
            appleY = random.nextInt(numBlocksHigh-1)+1;
        }

        @Override
        //Runs the game
        public void run() {
            while (playingSnake) {
                updateGame();
                drawGame();
                controlFPS();
            }
        }

        //Updates on each frame
        public void updateGame() {

            //Checks if the snake is touching an apple
            if(snakeX[0] == appleX && snakeY[0] == appleY){
                //grows the snake
                snakeLength++;
                //replaces the apple
                getApple();
                //adds to the score
                score = score + 10;
                //Plays the get apple sound effect
                soundPool.play(appleSound, 1, 1, 0, 0, 1);
            }

            //move the body - starting at the back
            for(int i=snakeLength; i >0 ; i--){
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }

            //Move the head in the right direction
            switch (directionOfTravel){
                case 0://up
                    snakeY[0]  --;
                    break;

                case 1://right
                    snakeX[0] ++;
                    break;

                case 2://down
                    snakeY[0] ++;
                    break;

                case 3://left
                    snakeX[0] --;
                    break;
            }

            //Checks collision
            boolean dead = false;
            //Checks wall collision
            if(snakeX[0] == -1)dead=true;
            if(snakeX[0] >= numBlocksWide)dead=true;
            if(snakeY[0] == -1)dead=true;
            if(snakeY[0] == numBlocksHigh)dead=true;
            //Checks snake collision with itself
            for (int i = snakeLength-1; i > 0; i--) {
                if ((i > 4) && (snakeX[0] == snakeX[i]) && (snakeY[0] == snakeY[i])) {
                    dead = true;
                }
            }

//NOTE change this method with a better one. Maybe add a game over message?
            if(dead){
                //restarts the game
                soundPool.play(deathSound, 1, 1, 0, 0, 1);
                //score = 0;
                //getSnake();

                //Adding game over screen
                String strScore = Integer.toString(score);
                Intent gameOverIntent = new Intent(getBaseContext(), GameOver.class);
                gameOverIntent.putExtra("Score", strScore);
                startActivity(gameOverIntent);

            }

        }

        public void drawGame() {

            //draws the canvas for the game screen
            if (ourHolder.getSurface().isValid()) {
                canvas = ourHolder.lockCanvas();
                canvas.drawColor(Color.BLACK);//the background
                paint.setColor(Color.argb(255, 255, 255, 255));
                paint.setTextSize(topGap/2);
                canvas.drawText("Score:" + score + "  Hi:" + hi, 10, topGap-6, paint);

                //draws a border
                paint.setStrokeWidth(3);//4 pixel border
                canvas.drawLine(1,topGap,screenWidth-1,topGap,paint);
                canvas.drawLine(screenWidth-1,topGap,screenWidth-1,topGap+(numBlocksHigh*blockSize),paint);
                canvas.drawLine(screenWidth-1,topGap+(numBlocksHigh*blockSize),1,topGap+(numBlocksHigh*blockSize),paint);
                canvas.drawLine(1,topGap, 1,topGap+(numBlocksHigh*blockSize), paint);

                //draws the snake
                canvas.drawBitmap(headBitmap, snakeX[0]*blockSize, (snakeY[0]*blockSize)+topGap, paint);
                //Draws the body
                for(int i = 1; i < snakeLength-1;i++){
                    canvas.drawBitmap(bodyBitmap, snakeX[i]*blockSize, (snakeY[i]*blockSize)+topGap, paint);
                }
                //draws the tail
                canvas.drawBitmap(tailBitmap, snakeX[snakeLength-1]*blockSize, (snakeY[snakeLength-1]*blockSize)+topGap, paint);

                //draws the apple
                canvas.drawBitmap(appleBitmap, appleX*blockSize, (appleY*blockSize)+topGap, paint);

                ourHolder.unlockCanvasAndPost(canvas);
            }

        }

        //Makes sure the frame rate is consistent
        public void controlFPS() {
            long timeThisFrame = (System.currentTimeMillis() - lastFrameTime);
            long timeToSleep = 100 - timeThisFrame;
            if (timeThisFrame > 0) {
                fps = (int) (1000 / timeThisFrame);
            }
            if (timeToSleep > 0) {
                try {
                    ourThread.sleep(timeToSleep);
                } catch (InterruptedException e) {
                    Log.e("error", "failed to load sound files");
                }
            }
            lastFrameTime = System.currentTimeMillis();
        }


        //Pauses the game thread
        public void pause() {
            playingSnake = false;
            try {
                ourThread.join();
            } catch (InterruptedException e) {
            }

        }

        //Resumes the game thread
        public void resume() {
            playingSnake = true;
            ourThread = new Thread(this);
            ourThread.start();
        }

        //NOTE We might need to fix the game controls in this method? Doesn't work well with mouse, we need to test it on an actual phone
        //Adds the game controls
        @Override
        public boolean onTouchEvent(MotionEvent motionEvent) {

            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_UP:
                    if (motionEvent.getX() >= screenWidth / 2) {
                        //turn right
                        directionOfTravel ++;
                        if(directionOfTravel == 4) {//no such direction
                            //loop back to 0(up)
                            directionOfTravel = 0;
                        }
                    } else {
                        //turn left
                        directionOfTravel--;
                        if(directionOfTravel == -1) {//no such direction
                            //loop back to 0(up)
                            directionOfTravel = 3;
                        }
                    }
            }
            return true;
        }


    }

    //Calls the pause method the the activity calls onStop()
    @Override
    protected void onStop() {
        super.onStop();

        while (true) {
            snakeView.pause();
            break;
        }

        finish();
    }

    //Calls the resume method the the activity calls onResume()
    @Override
    protected void onResume() {
        super.onResume();
        snakeView.resume();
    }

    //Calls the pause method the the activity calls onPause()
    @Override
    protected void onPause() {
        super.onPause();
        snakeView.pause();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            snakeView.pause();



            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        return false;
    }

    //NOTE Replace the sound effects with our own ones
    //Loads in the sounds from the resource manager
    public void loadSound(){
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        try {
            //Create objects of the 2 required classes
            AssetManager assetManager = getAssets();
            AssetFileDescriptor descriptor;

            //create our three fx in memory ready for use
            descriptor = assetManager.openFd("appleSound.ogg");
            appleSound = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("deathSound.ogg");
            deathSound = soundPool.load(descriptor, 0);
            //Catches the cases where the sounds don't load
        } catch (IOException e) {
            //Print an error message to the console
            Log.e("error", "failed to load sound files");

        }
    }

    //Sets the display size to match the phone's resolution
    public void configureDisplay(){
        //find out the width and height of the screen
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        topGap = screenHeight/14;

        //sets the pixel size in the game to the phone's scale
        blockSize = screenWidth/40;
        //Leaves one pixel for the score at the top
        numBlocksWide = 40;
        numBlocksHigh = ((screenHeight - topGap ))/blockSize;

//NOTE Replace with our own images that use the same name and resolutions
        //loads and scales all of the bitmaps
        headBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.head);
        bodyBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.body);
        tailBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tail);
        appleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.apple);

        //scales the bitmaps to match the block size
        headBitmap = Bitmap.createScaledBitmap(headBitmap, blockSize, blockSize, false);
        bodyBitmap = Bitmap.createScaledBitmap(bodyBitmap, blockSize, blockSize, false);
        tailBitmap = Bitmap.createScaledBitmap(tailBitmap, blockSize, blockSize, false);
        appleBitmap = Bitmap.createScaledBitmap(appleBitmap, blockSize, blockSize, false);

    }


}