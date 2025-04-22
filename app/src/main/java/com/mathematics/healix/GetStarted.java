package com.mathematics.healix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GetStarted extends AppCompatActivity {
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        TextView exploreText = findViewById(R.id.exploretext);
        Typeface typeface = ResourcesCompat.getFont(this,R.font.poppins_semibold);
        exploreText.setTypeface(typeface);
        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GetStarted.this, signup_Page.class);
                startActivity(i);
                finish();
            }
        });
    }
}