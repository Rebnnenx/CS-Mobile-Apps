package com.cornez.taxcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private TextView output;
    private RadioGroup status;
    private RadioButton single;
    private RadioButton marriedJoint;
    private RadioButton marriedSeparate;
    private RadioButton head;
    private Button calc;
    private EditText income;
    private double taxableIncome;
    private double tax;
    private boolean isValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calc = (Button) findViewById((R.id.btnCalc));
        TextView output = (TextView) findViewById(R.id.EditTxtTaxIncome);
        RadioGroup status = (RadioGroup) findViewById((R.id.radGrpFilingStatus));
        RadioButton single = (RadioButton) findViewById(R.id.radSingle);
        RadioButton marriedJoint = (RadioButton) findViewById(R.id.radMarriedJoint);
        RadioButton marriedSeparate = (RadioButton) findViewById(R.id.radMarriedSeparete);
        RadioButton head = (RadioButton) findViewById(R.id.radHead);
        EditText income = (EditText) findViewById(R.id.EditTxtTaxIncome);

        calc.setOnClickListener(calculate);
    }

    private final View.OnClickListener calculate =
            new View.OnClickListener() {


                public void onClick(View btn) {

                    isValid = true;

                    try {

                        String str = income.getText().toString();
                        str = str.replaceAll("[,]", "");

                        taxableIncome = Double.parseDouble(str);

                        //taxableIncome = Double.parseDouble((income.getText().toString()));

                        if (taxableIncome < 0) {
                            isValid = false;
                        }

                    } catch (NumberFormatException e) {

                        isValid = false;
                    }

                    if (isValid){

                        if(single.isChecked()) {
                            if (taxableIncome > 49300)
                                tax = 11159.50 + (taxableIncome - 49300) * .31;
                            else
                                tax = 0;
                        }
                        else if (marriedJoint.isChecked()) {
                            if (taxableIncome > 34000 && taxableIncome < 82150)
                                tax = 51000 + (taxableIncome - 34000) * .28;
                            else if (taxableIncome > 82150)
                                tax = 18582 + (taxableIncome - 82150) * .31;
                            else
                                tax = 0;
                        }
                        else if (marriedSeparate.isChecked()) {
                            if (taxableIncome > 41075)
                                tax = 9291 + (taxableIncome - 41075) * .31;
                            else
                                tax = 0;
                        }
                        else if(head.isChecked()) {
                            if (taxableIncome > 27300 && taxableIncome < 70450)
                                tax = 4095 + (taxableIncome - 27300) * .28;
                            else if (taxableIncome > 70450)
                                tax = 16177 + (taxableIncome - 70450) * .31;
                            else
                                tax = 0;
                        }else
                            isValid = false;

                    if(isValid)
                        output.setText("$" + Double.toString(tax));
                    else
                        output.setText("Invalid Input, Please select a Status");
                    }
                    else{
                        output.setText("Invalid Input, Please insert a valid non-negative number.");
                    }

                }


            };

}
