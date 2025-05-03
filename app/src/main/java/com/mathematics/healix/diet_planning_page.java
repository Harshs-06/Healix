package com.mathematics.healix;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

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

public class diet_planning_page extends AppCompatActivity {
    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_diet_planning_page);

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.blue));
        RecyclerView dietplans_recyclerview = findViewById(R.id.dietplans_recyclerview);
        dietplans_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        List<PlanItem> dietplanList= new ArrayList<>();
        dietplanList.add(new PlanItem("Trending",R.drawable.summerweightloss_img,"Summer Weight Loss","Light, refreshing meals for hot weather"));
        dietplanList.add(new PlanItem("Lose Weight",R.drawable.loseweight_img,"Low Calorie","Control intake, help weight loss"));
        dietplanList.add(new PlanItem("Burn Fat",R.drawable.burnfat_img,"Fat Burning","Boost metabolism and burn fat efficiently"));
        dietplanList.add(new PlanItem("Stay Health",R.drawable.stayhealth_img,"High Fiber","Promote gut health, lose weight"));
        dietplanList.add(new PlanItem("Heart Health",R.drawable.hearthealth_img,"Low Fat","Good for cardiovascular health"));
        dietplanList.add(new PlanItem("Vegan",R.drawable.vegan_img,"Vegan","Health, environmental protection"));

        PlanAdapter dietplanAdapter = new PlanAdapter(dietplanList);
        dietplans_recyclerview.setAdapter(dietplanAdapter);

        bottomNav =findViewById(R.id.bottomNavigationView);
        bottomNav.setSelectedItemId(R.id.nav_diet);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                Intent intent = new Intent(this, home_page.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

                return true;
            }else if (id == R.id.nav_lab) {
                Intent intent = new Intent(this, home_lab_test.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                return true;
            }
            else if (id == R.id.nav_community) {
                Intent intent = new Intent(this, CommunityForum.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                return true;
            }
            else if (id == R.id.nav_diet) {

                return true;
            }

            return false;
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

        bottomNav.setSelectedItemId(R.id.nav_diet);
    }
}