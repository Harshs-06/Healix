package com.mathematics.healix;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.concurrent.TimeUnit;

public class StepTrackerActivity extends AppCompatActivity {

    private CircularProgressBar circularProgressBar;
    private TextView stepText;
    private Handler handler = new Handler();
    private Runnable updateStepsRunnable;
    private final int STEP_GOAL = 10000;
    private static final int PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_tracker);

        circularProgressBar = findViewById(R.id.circularProgressBar);
        stepText = findViewById(R.id.step_count_text);
        circularProgressBar.setProgressMax(STEP_GOAL);

        // Requesting permission for ACTIVITY_RECOGNITION
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
                long steps = StepCounterService.stepsSinceBoot;
                circularProgressBar.setProgressWithAnimation(steps, 300L);
                stepText.setText(String.valueOf(steps));
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
}
