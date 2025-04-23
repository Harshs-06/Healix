package com.mathematics.healix;

import android.content.Intent;
import android.os.Bundle;
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

public class lab_tests extends AppCompatActivity {
    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_tests);
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.blue));

        ImageButton backButton2 = findViewById(R.id.backButton2);
        backButton2.setOnClickListener(v -> {
            Intent intent = new Intent(this, home_lab_test.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

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

        RecyclerView labtests_recyclerview = findViewById(R.id.labtests_recyclerview);
        labtests_recyclerview.setLayoutManager(new LinearLayoutManager(this));

        List<LabTestItem> labTestItems= new ArrayList<>();
        labTestItems.add(new LabTestItem("Albumin - Serum", "No preparation needed", "All", "All", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        labTestItems.add(new LabTestItem("Albumin - Serum", "No preparation needed", "All", "All", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        labTestItems.add(new LabTestItem("Albumin - Serum", "No preparation needed", "All", "All", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        labTestItems.add(new LabTestItem("Albumin - Serum", "No preparation needed", "All", "All", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        labTestItems.add(new LabTestItem("Albumin - Serum", "No preparation needed", "All", "All", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        labTestItems.add(new LabTestItem("Albumin - Serum", "No preparation needed", "All", "All", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        labTestItems.add(new LabTestItem("Albumin - Serum", "No preparation needed", "All", "All", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        labTestItems.add(new LabTestItem("Albumin - Serum", "No preparation needed", "All", "All", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        labTestItems.add(new LabTestItem("Albumin - Serum", "No preparation needed", "All", "All", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        labTestItems.add(new LabTestItem("Albumin - Serum", "No preparation needed", "All", "All", "8 Hours", "₹1999", "₹2999", "You saved 33%"));



        LabTestAdapter labTestAdapter = new LabTestAdapter(this,labTestItems);
        labtests_recyclerview.setAdapter(labTestAdapter);

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