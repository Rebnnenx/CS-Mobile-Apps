package com.cornez.coinscollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView output;
    private EditText name;
    private EditText quarters;
    private EditText dimes;
    private EditText nickles;
    private EditText pennies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = (TextView) findViewById(R.id.txtOutput);
        name = (EditText) findViewById(R.id.txtCustomer);
        quarters = (EditText) findViewById(R.id.txtQuarters);
        dimes = (EditText) findViewById(R.id.txtDimes);
        nickles = (EditText) findViewById(R.id.txtNickels);
        pennies = (EditText) findViewById(R.id.txtPennies);

        Button calculate = (Button) findViewById(R.id.btnCalc);
        calculate.setOnClickListener(toogleCalc);

    }

    private final View.OnClickListener toogleCalc =
            new View.OnClickListener() {

                public void onClick(View btn){
                    output.setText("");

                    int intQuarters;
                    int intDimes;
                    int intNickles;
                    int intPennies;
                    int intTotal;
                    int intDollars;
                    int intCents;

                    intQuarters = Integer.parseInt(quarters.getText().toString());
                    intDimes = Integer.parseInt(dimes.getText().toString());
                    intNickles = Integer.parseInt(nickles.getText().toString());
                    intPennies = Integer.parseInt(pennies.getText().toString());

                    //Perform division
                    intTotal = (intQuarters * 25) + (intDimes * 10) + (intNickles * 5) + (intPennies * 1);

                    intDollars = intTotal / 100;

                    intCents = intTotal % 100;

                    String strName = name.getText().toString();


                    //Output our final text
                    output.setText("Hello " + strName + "\nYour coins are worth " +
                            intDollars + " dollars and " + intCents + " cents. Bye!");

                }

  };




}
