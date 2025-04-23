package com.helix.labtests;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LabTestDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        // Initialize UI elements
        ImageButton backButton = findViewById(R.id.backButton);
        ImageButton cartButton = findViewById(R.id.cartButton);
        ImageButton notificationButton = findViewById(R.id.notificationButton);
        ImageButton favoriteButton = findViewById(R.id.favoriteButton);
        ImageButton shareButton = findViewById(R.id.shareButton);
        Button bookButton = findViewById(R.id.bookButton);

        // Bottom Navigation
        ImageButton homeButton = findViewById(R.id.homeButton);
        ImageButton likesButton = findViewById(R.id.likesButton);
        ImageButton plusButton = findViewById(R.id.plusButton);
        ImageButton reportsButton = findViewById(R.id.reportsButton);
        ImageButton settingsButton = findViewById(R.id.settingsButton);

        // Set click listeners
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LabTestDetailsActivity.this, "Cart clicked", Toast.LENGTH_SHORT).show();
            }
        });

        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LabTestDetailsActivity.this, "Notifications clicked", Toast.LENGTH_SHORT).show();
            }
        });

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LabTestDetailsActivity.this, "Added to favorites", Toast.LENGTH_SHORT).show();
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LabTestDetailsActivity.this, "Share clicked", Toast.LENGTH_SHORT).show();
            }
        });

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LabTestDetailsActivity.this, "Test booked successfully", Toast.LENGTH_SHORT).show();
            }
        });

        // Bottom navigation listeners
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LabTestDetailsActivity.this, "Home clicked", Toast.LENGTH_SHORT).show();
            }
        });

        likesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LabTestDetailsActivity.this, "Likes clicked", Toast.LENGTH_SHORT).show();
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LabTestDetailsActivity.this, "Add clicked", Toast.LENGTH_SHORT).show();
            }
        });

        reportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LabTestDetailsActivity.this, "Reports clicked", Toast.LENGTH_SHORT).show();
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LabTestDetailsActivity.this, "Settings clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
