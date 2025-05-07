package com.mathematics.healix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class signup_Page extends AppCompatActivity {

    private EditText signupEmail, signupPassword, signupUsername;
    private Button signup_btn;
    private ProgressBar signupProgress;
    private TextView login_toggle;
    private FirebaseAuth mAuth;
    private String btntxt = "Sign Up";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_page);

        // Firebase Auth initialization
        mAuth = FirebaseAuth.getInstance();

        // UI references
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupUsername = findViewById(R.id.signup_username);
        signup_btn = findViewById(R.id.signup_btn);
        signupProgress = findViewById(R.id.login_progress);
        login_toggle = findViewById(R.id.login_toggle);

        // Sign up button logic
        signup_btn.setOnClickListener(view -> {
            signup_btn.setText("");

            String email = signupEmail.getText().toString().trim();
            String password = signupPassword.getText().toString().trim();
            String username = signupUsername.getText().toString().trim();



            if (email.isEmpty() || password.isEmpty() || username.isEmpty()) {
                Toast.makeText(signup_Page.this, "All fields are required", Toast.LENGTH_SHORT).show();
                signup_btn.setText(btntxt);
                return;
            }

            signupProgress.setVisibility(View.VISIBLE);
            signup_btn.setEnabled(false);

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {


                        if (task.isSuccessful()) {
                            Toast.makeText(signup_Page.this, "Sign-up successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(signup_Page.this, genderSelection.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        } else {
                            signupProgress.setVisibility(View.GONE);
                            signup_btn.setText(btntxt);
                            signup_btn.setEnabled(true);
                            Toast.makeText(signup_Page.this, "Sign-up failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });
        });

        // Redirect to login page
        login_toggle.setOnClickListener(v -> {
            Intent intent = new Intent(signup_Page.this, login_Page.class);
            startActivity(intent);
            finish();
        });
    }
}
