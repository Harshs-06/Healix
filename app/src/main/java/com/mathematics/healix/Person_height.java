package com.mathematics.healix;

import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.ImageButton;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class Person_height extends AppCompatActivity {

    private NumberPicker heightPicker;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_height);

        heightPicker = findViewById(R.id.heightPicker);
        continueButton = findViewById(R.id.continueButton);
        ImageButton backButton = findViewById(R.id.backButton);

        // Setup height picker (range: 50 to 250 cm)
        heightPicker.setMinValue(50);
        heightPicker.setMaxValue(250);
        heightPicker.setValue(170);

        // Enable continue button when value is selected
        continueButton.setEnabled(true);

        continueButton.setOnClickListener(v -> {
            int selectedHeight = heightPicker.getValue();
            // TODO: Store height or pass to next activity
            // Example: startActivity(new Intent(this, NextActivity.class));
        });

        backButton.setOnClickListener(v -> onBackPressed());
    }
}
