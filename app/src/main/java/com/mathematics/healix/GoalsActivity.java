package com.mathematics.healix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class GoalsActivity extends AppCompatActivity {

    List<CheckBox> checkBoxes = new ArrayList<>();
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_goals);

        // Initialize CheckBoxes
        checkBoxes.add(findViewById(R.id.checkbox_lose_weight));
        checkBoxes.add(findViewById(R.id.checkbox_maintain_weight));
        checkBoxes.add(findViewById(R.id.checkbox_gain_weight));
        checkBoxes.add(findViewById(R.id.checkbox_gain_muscle));
        checkBoxes.add(findViewById(R.id.checkbox_modify_diet));
        checkBoxes.add(findViewById(R.id.checkbox_plan_meals));
        checkBoxes.add(findViewById(R.id.checkbox_manage_stress));

        // Initialize and hide the button initially
        nextButton = findViewById(R.id.button_next);


        // Add listeners to each checkbox
        for (CheckBox checkBox : checkBoxes) {
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                boolean anyChecked = false;
                for (CheckBox cb : checkBoxes) {
                    if (cb.isChecked()) {
                        anyChecked = true;
                        break;
                    }
                }
                nextButton.setVisibility(anyChecked ? View.VISIBLE : View.INVISIBLE);
            });
        }

        // Set action for Next button
        nextButton.setOnClickListener(v -> {
            startActivity(new Intent(GoalsActivity.this, setting_up_data_page.class));
        });

        // Window inset handling
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
