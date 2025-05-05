package com.mathematics.healix;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class Enter_weight extends AppCompatActivity {

    EditText weightInput;
    RadioButton kgButton, lbsButton;
    TextView bmiResult;
    Button continueButton;

    double heightInInches;

    // Sample height in inches (for example: 5'6" = 66 inches)
    final double HEIGHT_INCHES = 66;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_weight);

        weightInput = findViewById(R.id.weightInput);
        kgButton = findViewById(R.id.kgButton);
        lbsButton = findViewById(R.id.lbsButton);
        bmiResult = findViewById(R.id.bmiResult);
        continueButton = findViewById(R.id.continueButton);

        double heightInCm = getIntent().getDoubleExtra("height_cm", 0);
        heightInInches = heightInCm * 0.393701;  // Convert to inches

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String weightStr = weightInput.getText().toString();
        if (weightStr.isEmpty()) {
            bmiResult.setText("Please enter your weight.");
            return;
        }

        double weight = Double.parseDouble(weightStr);
        double weightInLbs = lbsButton.isChecked() ? weight : weight * 2.20462;

        double bmi = (weightInLbs * 703) / (heightInInches * heightInInches);

        String status;
        if (bmi < 18.5) {
            status = "underweight";
        } else if (bmi <= 24.9) {
            status = "normal";
        } else if (bmi <= 29.9) {
            status = "overweight";
        } else {
            status = "obese";
        }

        bmiResult.setText(String.format("Your BMI of %.1f is considered %s.", bmi, status));
    }

}
