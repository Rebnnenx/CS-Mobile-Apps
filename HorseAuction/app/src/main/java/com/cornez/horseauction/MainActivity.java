package com.cornez.horseauction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.imgAbby).setVisibility(View.INVISIBLE);
        findViewById(R.id.imgRascal).setVisibility(View.INVISIBLE);
        findViewById(R.id.imgBoth).setVisibility(View.INVISIBLE);

        Button abbyButton =(Button) findViewById(R.id.btnAbby);
        abbyButton.setOnClickListener(toggleAbby);

        Button rascalButton =(Button) findViewById(R.id.btnRascal);
        rascalButton.setOnClickListener(toggleRascal);

        Button bothButton =(Button) findViewById(R.id.btnBoth);
        bothButton.setOnClickListener(toggleBoth);
    }

    private final View.OnClickListener toggleAbby =

            new View.OnClickListener() {
                public void onClick(View btn) {
                    findViewById(R.id.imgAbby).setVisibility(View.VISIBLE);
                    findViewById(R.id.imgRascal).setVisibility(View.INVISIBLE);
                    findViewById(R.id.imgBoth).setVisibility(View.INVISIBLE);
                }
            };

    private final View.OnClickListener toggleRascal =

            new View.OnClickListener() {
                public void onClick(View btn) {
                    findViewById(R.id.imgAbby).setVisibility(View.INVISIBLE);
                    findViewById(R.id.imgRascal).setVisibility(View.VISIBLE);
                    findViewById(R.id.imgBoth).setVisibility(View.INVISIBLE);
                }
            };

    private final View.OnClickListener toggleBoth =

            new View.OnClickListener() {
                public void onClick(View btn) {
                    findViewById(R.id.imgAbby).setVisibility(View.INVISIBLE);
                    findViewById(R.id.imgRascal).setVisibility(View.INVISIBLE);
                    findViewById(R.id.imgBoth).setVisibility(View.VISIBLE);
                }
            };

}
