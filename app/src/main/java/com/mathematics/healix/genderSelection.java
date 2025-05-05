package com.mathematics.healix;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class genderSelection extends AppCompatActivity {

    LinearLayout female_gender, male_gender, other_gender;
    MaterialButton gender_to_nextbutton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender_selection);

        female_gender = findViewById(R.id.female_gender);
        male_gender = findViewById(R.id.male_gender);
        other_gender = findViewById(R.id.other_gender);
        gender_to_nextbutton = findViewById(R.id.gender_to_nextbutton);
        female_gender.setOnClickListener(v -> {
            if (female_gender.isSelected()) {
                female_gender.setSelected(false);
            } else {
                resetSelection();
                female_gender.setSelected(true);
            }
            updateNextButtonVisibility();
        });

        male_gender.setOnClickListener(v -> {
            if (male_gender.isSelected()) {
                male_gender.setSelected(false);
            } else {
                resetSelection();
                male_gender.setSelected(true);
            }
            updateNextButtonVisibility();
        });

        other_gender.setOnClickListener(v -> {
            if (other_gender.isSelected()) {
                other_gender.setSelected(false);
            } else {
                resetSelection();
                other_gender.setSelected(true);
            }
            updateNextButtonVisibility();
        });



    }
    private void resetSelection() {
        female_gender.setSelected(false);
        male_gender.setSelected(false);
        other_gender.setSelected(false);
    }
    private void updateNextButtonVisibility() {
        if (female_gender.isSelected() || male_gender.isSelected() || other_gender.isSelected()) {
            gender_to_nextbutton.setVisibility(View.VISIBLE);
        } else {
            gender_to_nextbutton.setVisibility(View.INVISIBLE);
        }
    }


}
