package com.mathematics.healix;

import android.content.Intent;
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
    MaterialButton nextButton;

    double heightInInches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_weight);

        // Initialize UI elements
        weightInput = findViewById(R.id.weightInput);

        nextButton = findViewById(R.id.weight_to_nextbutton);



        weightInput.requestFocus();


        nextButton.setOnClickListener(view -> startActivity(new Intent(Enter_weight.this,setting_up_data_page.class)));
    }



}
