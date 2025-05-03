package com.mathematics.healix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class signup_Page extends AppCompatActivity {

    private Button signup_btn;
    private TextView textView2;
    private TextView login_toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_page);

        signup_btn = findViewById(R.id.signup_btn);

        login_toggle= findViewById(R.id.login_toggle);

        signup_btn.setOnClickListener(view -> {
            Intent intent = new Intent(signup_Page.this, home_page.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        });

        login_toggle.setOnClickListener(v ->  {
            Intent intent = new Intent(signup_Page.this, login_Page.class);
            startActivity(intent);
            finish();
        });





    }
}