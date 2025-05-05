package com.mathematics.healix;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class Person_age extends AppCompatActivity {

    private EditText ageInput;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_age);

        ageInput = findViewById(R.id.ageInput);
        ageInput.requestFocus();
          MaterialButton nextButton = findViewById(R.id.age_to_nextbutton);
        ageInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = ageInput.getText().toString();
                if (!input.isEmpty() && Integer.parseInt(input) > 0) {
                    nextButton.setVisibility(View.VISIBLE);
                } else {
                    nextButton.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Continue button click
        nextButton.setOnClickListener(view -> {
           startActivity(new Intent(Person_age.this, Person_height.class));
        });


    }
}
