package com.mathematics.healix;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class Person_age extends AppCompatActivity {

    private EditText ageInput;
    private Button continueButton;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_age);

        ageInput = findViewById(R.id.ageInput);
        continueButton = findViewById(R.id.continueButton);
        backButton = findViewById(R.id.backButton);

        // Listener for enabling the button
        ageInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = ageInput.getText().toString();
                if (!input.isEmpty() && Integer.parseInt(input) > 0) {
                    continueButton.setEnabled(true);
                    continueButton.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                } else {
                    continueButton.setEnabled(false);
                    continueButton.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Continue button click
        continueButton.setOnClickListener(view -> {
            String age = ageInput.getText().toString();
            Toast.makeText(Person_age.this, "Age entered: " + age, Toast.LENGTH_SHORT).show();
            // Intent to next screen can go here
        });

        // Back button click
        backButton.setOnClickListener(v -> finish());
    }
}
