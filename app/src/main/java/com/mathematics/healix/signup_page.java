package com.mathematics.healix;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class signup_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_page);
        TextView appTitle = findViewById(R.id.app_title);



        appTitle.post(() -> {
            float centerX = appTitle.getWidth() / 2f;
            float centerY = appTitle.getHeight() / 2f;

            ValueAnimator animator = ValueAnimator.ofFloat(0, 360);
            animator.setDuration(3000); // 3 seconds animation
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setInterpolator(new LinearInterpolator());

            animator.addUpdateListener(animation -> {
                float angle = (float) animation.getAnimatedValue();
                Matrix matrix = new Matrix();
                matrix.setRotate(angle, centerX, centerY);

                Shader textShader = new SweepGradient(
                        centerX, centerY,
                        new int[]{
                                Color.parseColor("#E5CC1F"),
                                Color.parseColor("#4F513C"),
                                Color.parseColor("#92C745"),
                                Color.parseColor("#E5CC1F") // Repeat for smooth looping
                        },
                        null);

                textShader.setLocalMatrix(matrix);
                appTitle.getPaint().setShader(textShader);
                appTitle.invalidate();
            });

            animator.start();
        });





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}