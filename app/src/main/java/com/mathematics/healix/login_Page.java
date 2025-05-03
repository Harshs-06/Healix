package com.mathematics.healix;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class login_Page extends AppCompatActivity {

    private TextView textView2;
    private TextView signup_toggle;
    private Button login_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);

        textView2 = findViewById(R.id.textView2);
        signup_toggle = findViewById(R.id.signup_toggle);
        login_btn = findViewById(R.id.login_btn);


        login_btn.setOnClickListener(view -> {
            Intent intent = new Intent(login_Page.this, home_page.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);


        });

        signup_toggle.setOnClickListener(view -> {
            Intent intent = new Intent(login_Page.this, signup_Page.class);
            startActivity(intent);
            finish();


        });



    }
}