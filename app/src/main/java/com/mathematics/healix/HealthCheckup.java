package com.example.helixapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HealthCheckupActivity extends AppCompatActivity {

    private Button bookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_checkup);

        // Initialize views
        bookButton = findViewById(R.id.btnBook);

        // Set click listeners
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HealthCheckupActivity.this, "Booking initiated", Toast.LENGTH_SHORT).show();
                // Add booking logic here
            }
        });
    }
}
