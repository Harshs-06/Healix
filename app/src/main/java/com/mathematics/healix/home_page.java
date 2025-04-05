package com.mathematics.healix;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class home_page extends AppCompatActivity {
    private EditText searchBar;
    private final String[] hints = {"Search for doctors", "Find lab tests", "Make your diet plan"};

    private int hintIndex = 0;
    private int charIndex = 0;
    private boolean deleting = false;
    private Handler handler = new Handler();
    private long delay = 100; // Typing speed
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.blue));

        searchBar = findViewById(R.id.searchBar);
        animateHint();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void animateHint() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!deleting) {
                    if (charIndex <= hints[hintIndex].length()) {
                        searchBar.setHint(hints[hintIndex].substring(0, charIndex++));
                        handler.postDelayed(this, delay);
                    } else {
                        deleting = true;
                        handler.postDelayed(this, 1000); // Wait before deleting
                    }
                } else {
                    if (charIndex > 0) {
                        searchBar.setHint(hints[hintIndex].substring(0, --charIndex));
                        handler.postDelayed(this, delay);
                    } else {
                        deleting = false;
                        hintIndex = (hintIndex + 1) % hints.length;
                        handler.postDelayed(this, delay);
                    }
                }
            }
        }, delay);
    }
}