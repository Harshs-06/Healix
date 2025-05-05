package com.mathematics.healix;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.health.connect.client.HealthConnectClient;
import androidx.health.connect.client.records.TotalCaloriesBurnedRecord;
import androidx.health.connect.client.request.ReadRecordsRequest;
import androidx.health.connect.client.time.TimeRangeFilter;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class calorie_burn extends AppCompatActivity {
    BottomNavigationView bottomNav;
    private HealthConnectClient healthConnectClient;

    // Declare permissionLauncher as a class-level variable
    private ActivityResultLauncher<String[]> permissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_burn);

        // Set status bar color
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.blue));

        // Setup bottom navigation
        bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setSelectedItemId(R.id.nav_home);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                return true;
            } else if (id == R.id.nav_lab) {
                startActivity(new Intent(this, home_lab_test.class));
                return true;
            } else if (id == R.id.nav_community) {
                startActivity(new Intent(this, CommunityForum.class));
                return true;
            } else if (id == R.id.nav_diet) {
                startActivity(new Intent(this, diet_planning_page.class));
                return true;
            }
            return false;
        });

        // Back button
        ImageButton backButton4 = findViewById(R.id.backButton4);
        backButton4.setOnClickListener(v -> {
            Intent intent = new Intent(this, home_page.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        // Initialize Health Connect client
        healthConnectClient = HealthConnectClient.getOrCreate(this);

        // Initialize permissionLauncher
        permissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                result -> {
                    Boolean granted = result.get("androidx.health.permission.READ_TOTAL_CALORIES_BURNED");
                    if (granted != null && granted) {
                        fetchCaloriesData();
                    } else {
                        Toast.makeText(this, "Permission denied, cannot fetch calories data.", Toast.LENGTH_SHORT).show();
                    }
                });

        // Request permissions
        requestCaloriesPermission();

        // Apply system inset padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNav.setSelectedItemId(R.id.nav_home);
    }

    private void requestCaloriesPermission() {
        // Request permission for reading total calories burned
        permissionLauncher.launch(new String[]{"androidx.health.permission.READ_TOTAL_CALORIES_BURNED"});
    }

    private void fetchCaloriesData() {
        if (healthConnectClient == null) {
            healthConnectClient = HealthConnectClient.getOrCreate(this);
        }

        Instant now = Instant.now();
        Instant startTime = now.minus(1, ChronoUnit.DAYS);
        Instant endTime = now;

        TimeRangeFilter timeRangeFilter = TimeRangeFilter.between(startTime, endTime);

        // Create the request with the appropriate filter
//        ReadRecordsRequest<TotalCaloriesBurnedRecord> request = new ReadRecordsRequest.Builder<>(TotalCaloriesBurnedRecord.class)
//                .setTimeRangeFilter(timeRangeFilter)
//                .build();  // Build the request
//
//        // Use healthConnectClient.readRecords with the correct method signature
//        Objects.requireNonNull(healthConnectClient.readRecords(request))
//                .addOnSuccessListener(response -> {
//                    List<TotalCaloriesBurnedRecord> calorieRecords = response.getRecords();
//                    double totalCalories = 0;
//                    for (TotalCaloriesBurnedRecord record : calorieRecords) {
//                        totalCalories += record.getEnergy().getKilocalories();
//                    }
//
//                    // Display the total calories burned
//                    TextView caloriesTextView = findViewById(R.id.calories_text);
//                    caloriesTextView.setText(String.format("Total Calories Burned: %s", totalCalories));
//                })
//                .addOnFailureListener(e -> {
//                    Toast.makeText(this, "Failed to fetch calories data.", Toast.LENGTH_SHORT).show();
//                    e.printStackTrace();
//                });
    }


}
