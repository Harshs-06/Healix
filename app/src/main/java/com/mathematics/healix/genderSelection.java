package com.mathematics.healix;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class genderSelection extends AppCompatActivity {

    LinearLayout female_gender, male_gender, other_gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        female_gender = findViewById(R.id.female_gender);
        male_gender = findViewById(R.id.male_gender);
        other_gender = findViewById(R.id.other_gender);

        female_gender.setOnClickListener(v -> {
            resetSelection();
            female_gender.setSelected(true);
        });

        male_gender.setOnClickListener(v -> {
            resetSelection();
            male_gender.setSelected(true);
        });

        other_gender.setOnClickListener(v -> {
            resetSelection();
            other_gender.setSelected(true);
        });


    }
    private void resetSelection() {
        female_gender.setSelected(false);
        male_gender.setSelected(false);
        other_gender.setSelected(false);
    }


}
