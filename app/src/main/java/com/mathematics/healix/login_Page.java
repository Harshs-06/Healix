package com.mathematics.healix;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;



import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class login_Page extends AppCompatActivity {

    private EditText login_email, login_password;
    private Button login_btn;
    private ProgressBar login_progress;
    private TextView signup_toggle;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // View bindings
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        login_btn = findViewById(R.id.login_btn);
        login_progress = findViewById(R.id.login_progress);
        signup_toggle = findViewById(R.id.signup_toggle);

        // Login button click
        login_btn.setOnClickListener(view -> {
            String email = login_email.getText().toString().trim();
            String password = login_password.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(login_Page.this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            // Hide login button and show progress bar
            login_btn.setVisibility(View.INVISIBLE);
            login_progress.setVisibility(View.VISIBLE);

            // Firebase Login
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Success - Go to home page
                            Intent intent = new Intent(login_Page.this, home_page.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            startActivity(intent);
                            finish();
                        } else {
                            // Failure - Show login button again
                            login_progress.setVisibility(View.GONE);
                            login_btn.setVisibility(View.VISIBLE);
                            Toast.makeText(login_Page.this, "Login failed. Please check credentials.", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        // Signup redirect
        signup_toggle.setOnClickListener(view -> {
            Intent intent = new Intent(login_Page.this, signup_Page.class);
            startActivity(intent);
            finish();
        });
    }
}
