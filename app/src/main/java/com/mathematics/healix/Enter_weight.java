package com.mathematics.healix;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class Enter_weight extends AppCompatActivity {

    EditText weightInput;
    MaterialButton kgButton, lbsButton;
    MaterialButtonToggleGroup unitToggleGroup;
    TextView bmiResult;
    com.google.android.material.button.MaterialButton nextButton;

    double heightInInches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_weight);

        // Initialize UI elements
        weightInput = findViewById(R.id.weightInput);
        kgButton = findViewById(R.id.kgButton);
        lbsButton = findViewById(R.id.lbsButton);
        unitToggleGroup = findViewById(R.id.unitToggleGroup);
        bmiResult = findViewById(R.id.bmiResult);
        nextButton = findViewById(R.id.weight_to_nextbutton);

        // Get height from intent and convert to inches
        double heightInCm = getIntent().getDoubleExtra("height_cm", 0);
        heightInInches = heightInCm * 0.393701;

        weightInput.requestFocus();

        // Calculate BMI on button click
        nextButton.setOnClickListener(view -> calculateBMI());
    }

    private void calculateBMI() {
        String weightStr = weightInput.getText().toString();
        if (weightStr.isEmpty()) {
            bmiResult.setText("Please enter your weight.");
            return;
        }

        double weight = Double.parseDouble(weightStr);

        // Check which unit is selected
        boolean isLbsSelected = unitToggleGroup.getCheckedButtonId() == R.id.lbsButton;
        double weightInLbs = isLbsSelected ? weight : weight * 2.20462;

        // BMI formula for imperial units
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
