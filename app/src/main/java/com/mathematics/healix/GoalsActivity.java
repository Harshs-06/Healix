package com.mathematics.healix;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

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
        checkBoxes.add(findViewById(R.id.checkbox_lose_weight));
        checkBoxes.add(findViewById(R.id.checkbox_maintain_weight));
        checkBoxes.add(findViewById(R.id.checkbox_gain_weight));
        checkBoxes.add(findViewById(R.id.checkbox_gain_muscle));
        checkBoxes.add(findViewById(R.id.checkbox_modify_diet));
        checkBoxes.add(findViewById(R.id.checkbox_plan_meals));
        checkBoxes.add(findViewById(R.id.checkbox_manage_stress));

        nextButton = findViewById(R.id.button_next);

        nextButton.setOnClickListener(v -> {
            int selectedCount = 0;
            for (CheckBox box : checkBoxes) {
                if (box.isChecked()) selectedCount++;
            }

            if (selectedCount == 0) {
                Toast.makeText(this, "Please select at least one goal.", Toast.LENGTH_SHORT).show();
            } else if (selectedCount > 3) {
                Toast.makeText(this, "You can select up to 3 goals only.", Toast.LENGTH_SHORT).show();
            } else {
                // Handle selected goals
                StringBuilder selectedGoals = new StringBuilder("Selected goals:\n");
                for (CheckBox box : checkBoxes) {
                    if (box.isChecked()) selectedGoals.append("- ").append(box.getText()).append("\n");
                }
                Toast.makeText(this, selectedGoals.toString(), Toast.LENGTH_LONG).show();
                // You can move to the next activity here
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}