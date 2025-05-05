package com.mathematics.healix;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class StepTrackerActivity extends AppCompatActivity {

    private CircularProgressBar circularProgressBar;
    private TextView stepText;
    private Handler handler = new Handler();
    private Runnable updateStepsRunnable;
    private final int STEP_GOAL = 10000;
    private static final int PERMISSION_REQUEST_CODE = 100;
    BottomNavigationView bottomNav;
    long steps;
    private BarChart BarChart;
    private long[] stepsData = new long[7];
    private long lastStepCount = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_tracker);

        circularProgressBar = findViewById(R.id.circularProgressBar);
        stepText = findViewById(R.id.step_count_text);
        circularProgressBar.setProgressMax(STEP_GOAL);

        bottomNav =findViewById(R.id.bottomNavigationView);
        bottomNav.setSelectedItemId(R.id.nav_home);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {

                return true;
            }else if (id == R.id.nav_lab) {
                Intent intent = new Intent(this, home_lab_test.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

                return true;
            } else if (id==R.id.nav_community) {
                Intent intent = new Intent(this,CommunityForum.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                return true;

            }
            else if (id == R.id.nav_diet) {
                Intent intent = new Intent(this, diet_planning_page.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                return true;
            }
            return false;
        });


        BarChart = findViewById(R.id.barChart);



        ImageButton backButton4 = findViewById(R.id.backButton4);
        backButton4.setOnClickListener(v -> {
                    Intent intent = new Intent(this, home_page.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);

                }
        );


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, PERMISSION_REQUEST_CODE);
        } else {
            startStepService();
        }

        updateStepsRunnable = new Runnable() {
            @Override
            public void run() {
                steps = StepCounterService.stepsSinceBoot;
                circularProgressBar.setProgressWithAnimation(steps, 300L);
                stepText.setText(String.valueOf(steps));
                if (steps != lastStepCount) {
                    updateBarChart(steps);
                    lastStepCount = steps;  // Update the last recorded value
                }
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(updateStepsRunnable);

        //Schedule background step sync
        PeriodicWorkRequest stepRequest = new PeriodicWorkRequest.Builder(
                StepWorker.class, 15, TimeUnit.MINUTES).build();

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
                "StepCounterWork", ExistingPeriodicWorkPolicy.UPDATE, stepRequest);
    }

    private void startStepService() {
        Intent intent = new Intent(this, StepCounterService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(updateStepsRunnable);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNav.setSelectedItemId(R.id.nav_home);
    }

    @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE &&
                    grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startStepService();
            } else {
                Toast.makeText(this, "Permission denied. Step tracking won't work.", Toast.LENGTH_SHORT).show();
            }
        }

    private void updateBarChart(long currentSteps) {
        String[] allDays = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};


        Calendar calendar = Calendar.getInstance();
        int todayIndex = (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7;





        if (stepsData[todayIndex] != currentSteps) {
            stepsData[todayIndex] = currentSteps;
        }


        if (todayIndex == 0) {

            if (stepsData[6] > 0) {

                for (int i = 0; i < 7; i++) {
                    stepsData[i] = 0;
                }
            }
        }


        BarChart.clearChart();
        for (int i = 0; i < 7; i++) {
            String label = (i == todayIndex) ? "Tod" : allDays[i];
            int steps = (i <= todayIndex) ? (int) stepsData[i] : 0;
            int color = (i == todayIndex) ? Color.parseColor("#ff9500") : Color.parseColor("#F2D09E");

            BarChart.addBar(new BarModel(label, steps, color));
        }

        BarChart.startAnimation();
    }




}
