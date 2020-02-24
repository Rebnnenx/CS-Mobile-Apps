package com.cornez.rentalcar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Update names for all the components
    //Format is: private whateverType varName;
    private TextView output;
    // private placeholder driverNum;
    // private placeholder model
    // private placeholder insurance
    // private placeholder gas
    // private placeholder days


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Update names for each variable declared earlier
        //Format: varName = (whateverType) findViewById(R.id.referenceName);
        //e.g. output = (TextView) findViewById(R.id.outputBox);
         driverNum = (TextView) findViewById(R.id.txtDrivers);
         model = (Spinner) findViewById(R.id.spnVehicles);
         insurance = (Switch) findViewById(R.id.swInsurance);
         gas = (Switch) findViewById(R.id.swGas);
         days = (SeekBar) findViewById(R.id.skDays);

        //Update button name (btnCalc)
        Button calculate = (Button) findViewById(R.id.btnCalc);

        calculate.setOnClickListener(getTotal);
    }

    private final View.OnClickListener getTotal =
            new View.OnClickListener() {

                public void onClick(View btn){
                    double total = 0;

                    //Get all variable values from the components here

                     int driversNum = driverNum.placeholder;
                     String modelVal = model.placeholder;
                     Boolean uninsured = insurance.placeholder;
                     Boolean prepayGas = gas.placeholder;
                     int rentalDays = days.placeholder;

                    boolean isValid = true;

                    try{
                        //Get the number of drivers, update quarters name
                        //driversNum = Integer.parseInt(quarters.getText().toString());
                    }
                    catch ( NumberFormatException e){
                        isValid=false;
                    }

                    if(driversNum <= 0) {
                        isValid = false;
                    }

                    if (isValid){
                        //if modelVal == 'Jeep Wrangler'
                            //total += 55 * rentalDays
                        //else if modelVal == 'Jeep Grand Cherokee'
                            //total += 85 * rentalDays
                        //else
                            //total += 125 * rentalDays

                        //if driversNum > 1
                            //total += 22 * rentalDays

                        //if prepayGas is checked:
                            //total += 52;

                        //if uninsured is checked:
                            //total += 24 * rentalDays;

                        output.setText("The total cost of renting this car for " + rentalDays + "days is: $" + total + ".");
                    }
                    else {
                        output.setText("The inputted number of drivers is not valid. \nPlease make sure you only submit whole positive numbers");
                    }

                }

            };

}
