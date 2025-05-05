package com.mathematics.healix;

import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
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

        // Setup height picker (range: 50 to 250 cm)
        heightPicker.setMinValue(50);
        heightPicker.setMaxValue(250);
        heightPicker.setValue(170);

        // Button is always enabled since there's a default value
        continueButton.setEnabled(true);

        continueButton.setOnClickListener(v -> {
            int selectedHeight = heightPicker.getValue();  // Height in cm

            // Pass height to weight activity
            Intent intent = new Intent(Person_height.this, Enter_weight.class);
            intent.putExtra("height_cm", selectedHeight);
            startActivity(intent);
        });
    }
}
