package com.example.abhi.loginapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class BMI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        final TextView heightUnit = (TextView)findViewById(R.id.heightUnit);
        final TextView weightUnit = (TextView)findViewById(R.id.weightUnit);
        final Button button = (Button) findViewById(R.id.submit);
        final TextView resultArea = (TextView) findViewById(R.id.bmiResult);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checked)
            {
                switch(checked)
                {
                    case R.id.imperial:
                        heightUnit.setText(R.string.inches);
                        weightUnit.setText(R.string.pounds);
                        break;
                    case R.id.metric:
                        heightUnit.setText(R.string.cm);
                        weightUnit.setText(R.string.kg);
                        break;
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double bmi;
                    int height = Integer.parseInt(((EditText) findViewById(R.id.heightValue)).getText().toString());
                    int weight = Integer.parseInt(((EditText) findViewById(R.id.weightValue)).getText().toString());
                    if ((heightUnit.getText().toString()).equals("Inches")){
                        bmi = calculateBMIImperial((double) height,(double) weight);
                    }
                    else{
                        bmi = calculateBMIMetric((double) height,(double) weight);
                    }
                    resultArea.setText("BMI: "+bmi);
                }
                catch (Exception a){
                    resultArea.setText("Must enter proper values for height and weight.");
                }

            }
        });

    }

    public double calculateBMIImperial(double height, double weight){
        double result = (weight * 703)/ Math.pow(height, 2);
        return result;
    }
    public double calculateBMIMetric(double height, double weight){
        double result = ((weight / height)/height) *10000.0;
        return result;
    }



}
