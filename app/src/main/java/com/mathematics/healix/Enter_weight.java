package com.mathematics.healix;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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
        weightInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = weightInput.getText().toString();
                if (!input.isEmpty() && Integer.parseInt(input) > 0) {
                    nextButton.setVisibility(View.VISIBLE);
                } else {
                    nextButton.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


        nextButton.setOnClickListener(view -> startActivity(new Intent(Enter_weight.this, GoalsActivity.class)));
    }



}
