package com.cornez.greetings;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;


public class MainActivity extends AppCompatActivity {

    private TextView greetingTextView;
    private boolean isHello;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        greetingTextView = (TextView) findViewById(R.id.editText);

        initializeGreeting();

        Button greetButton = (Button) findViewById(R.id.button);
        greetButton.setOnClickListener(toggleGreeting);
    }

    private final View.OnClickListener toggleGreeting =

            new View.OnClickListener() {

                public void onClick(View btn) {
                    if (isHello) {
                        isHello = false;
                        greetingTextView.setText(R.string.goodbye);
                    } else {
                        isHello = true;
                        greetingTextView.setText (R.string.hello);
                    }
                }
            };

    private void initializeGreeting(){
        isHello = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

       /* if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }
}

