package com.cornez.flagsofstates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.util.Arrays;
import java.util.Random;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] array = { 0, 1, 2, 3, 4};

        final Random rand = new Random();
//        for (int i = 0; i < array.length; i++) {
//            int randomIndexToSwap = rand.nextInt(array.length);
//            int temp = array[randomIndexToSwap];
//            array[randomIndexToSwap] = array[i];
//            array[i] = temp;
//        }

        correct = rand.nextInt(5);
        final String correctName = getStateName(correct);

        //Set imageview to the correct flag state
//        switch(correct) {
//            case 0:
//                findViewById(R.id.flag).setBackgroundResource(R.mipmap.ohio);
//            case 1:
//                findViewById(R.id.flag).setBackgroundResource(R.mipmap.california);
//            case 2:
//                findViewById(R.id.flag).setBackgroundResource(R.mipmap.newyork);
//            case 3:
//                findViewById(R.id.flag).setBackgroundResource(R.mipmap.texas);
//            case 4:
//                findViewById(R.id.flag).setBackgroundResource(R.mipmap.maine);
//        }

        final Button state1 =(Button) findViewById(R.id.state1);
        state1.setText(getStateName(array[0]));
        final Button state2 =(Button) findViewById(R.id.state2);
        state2.setText(getStateName(array[1]));
        final Button state3 =(Button) findViewById(R.id.state3);
        state3.setText(getStateName(array[2]));
        final Button state4 =(Button) findViewById(R.id.state4);
        state4.setText(getStateName(array[3]));
        final Button state5 =(Button) findViewById(R.id.state5);
        state5.setText(getStateName(array[4]));
        final Button skip =(Button) findViewById(R.id.skip);
        skip.setText(getStateName(array[correct]));
        skip.setText("Skip Flag");

        final TextView answer = (TextView) findViewById(R.id.answer);

        state1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = state1.getText().toString();
                if(text == correctName) {
                    answer.setText("Correct!");
                    skip.setText("Next Flag");
                }
                else {
                    answer.setText("Incorrect");
                }
            }
        });

        state2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = state2.getText().toString();
                if(text == correctName) {
                    answer.setText("Correct!");
                    skip.setText("Next Flag");
                }
                else {
                    answer.setText("Incorrect");
                }
            }
        });

        state3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = state3.getText().toString();
                if(text == correctName) {
                    answer.setText("Correct!");
                    skip.setText("Next Flag");
                }
                else {
                    answer.setText("Incorrect");
                }
            }
        });

        state4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = state4.getText().toString();
                if(text == correctName) {
                    answer.setText("Correct!");
                    skip.setText("Next Flag");
                }
                else {
                    answer.setText("Incorrect");
                }
            }
        });

        state5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = state5.getText().toString();
                if(text == correctName) {
                    answer.setText("Correct!");
                    skip.setText("Next Flag");
                }
                else {
                    answer.setText("Incorrect");
                }
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
    }

    protected String getStateName(int index) {
        switch(index) {
            case 0:
                findViewById(R.id.flag).setBackgroundResource(R.mipmap.ohio);
                return "Ohio";
            case 1:
                findViewById(R.id.flag).setBackgroundResource(R.mipmap.california);
                return "California";
            case 2:
                findViewById(R.id.flag).setBackgroundResource(R.mipmap.newyork);
                return "New York";
            case 3:
                findViewById(R.id.flag).setBackgroundResource(R.mipmap.texas);
                return "Texas";
            case 4:
                findViewById(R.id.flag).setBackgroundResource(R.mipmap.maine);
                return "Maine";
            default:
                return "";
        }
    }

    protected int getStateIndex(String name) {
        switch(name) {
            case "Ohio":
                return 0;
            case "California":
                return 1;
            case "New York":
                return 2;
            case "Texas":
                return 3;
            case "Maine":
                return 4;
            default:
                return 0;
        }
    }

    protected void nextFlag() {
        try { Thread.sleep(3000); }
        catch (InterruptedException ex) { android.util.Log.d("YourApplicationName", ex.toString()); }
        finish();
        startActivity(getIntent());
    }

}
