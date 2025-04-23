package com.mathematics.healix;

import android.content.Intent;
import android.os.Bundle;
import android.os.RecoverySystem;
import android.view.Window;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class healthCheckups extends AppCompatActivity {
    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_health_checkups);
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.blue));

        bottomNav =findViewById(R.id.bottomNavigationView);
        bottomNav.setSelectedItemId(R.id.nav_lab);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                Intent intent = new Intent(this, home_page.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

                return true;
            }else if (id == R.id.nav_lab) {

                return true;
            }
            return false;
        });


        RecyclerView healthcheckups_recyclerview = findViewById(R.id.healthcheckups_recyclerview);
        List<HealthCheckupItem> healthCheckupItems = new ArrayList<>();
        healthCheckupItems.add(new HealthCheckupItem("Basic Wellness", "No preparation needed", "81", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        healthCheckupItems.add(new HealthCheckupItem("Basic Wellness", "No preparation needed", "81", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        healthCheckupItems.add(new HealthCheckupItem("Basic Wellness", "No preparation needed", "81", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        healthCheckupItems.add(new HealthCheckupItem("Basic Wellness", "No preparation needed", "81", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        healthCheckupItems.add(new HealthCheckupItem("Basic Wellness", "No preparation needed", "81", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        healthCheckupItems.add(new HealthCheckupItem("Basic Wellness", "No preparation needed", "81", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        healthCheckupItems.add(new HealthCheckupItem("Basic Wellness", "No preparation needed", "81", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        healthCheckupItems.add(new HealthCheckupItem("Basic Wellness", "No preparation needed", "81", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        healthCheckupItems.add(new HealthCheckupItem("Basic Wellness", "No preparation needed", "81", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        healthCheckupItems.add(new HealthCheckupItem("Basic Wellness", "No preparation needed", "81", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        healthCheckupItems.add(new HealthCheckupItem("Basic Wellness", "No preparation needed", "81", "8 Hours", "₹1999", "₹2999", "You saved 33%"));

        HealthCheckupAdapter healthCheckupAdapter = new HealthCheckupAdapter(this,healthCheckupItems);
        LinearLayoutManager healthlayoutmanager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        healthcheckups_recyclerview.setLayoutManager(healthlayoutmanager);
        healthcheckups_recyclerview.setAdapter(healthCheckupAdapter);


        ImageButton backButton1 = findViewById(R.id.backButton1);
        backButton1.setOnClickListener(v -> {
            Intent intent = new Intent(this, home_lab_test.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        bottomNav.setSelectedItemId(R.id.nav_lab);
    }
}