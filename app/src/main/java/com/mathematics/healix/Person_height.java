package com.mathematics.healix;

import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class Person_height extends AppCompatActivity {

    private NumberPicker heightPicker;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_height);

        heightPicker = findViewById(R.id.heightPicker);
        nextButton = findViewById(R.id.height_to_nextbutton);


        heightPicker.setMinValue(50);
        heightPicker.setMaxValue(250);
        heightPicker.setValue(160);




        nextButton.setOnClickListener(v -> {
            int selectedHeight = heightPicker.getValue();

            // Pass height to weight activity
            Intent intent = new Intent(Person_height.this, Enter_weight.class);
            intent.putExtra("height_cm", selectedHeight);
            startActivity(intent);
        });
    }
}
