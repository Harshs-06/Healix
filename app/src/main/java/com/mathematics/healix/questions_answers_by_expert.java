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

public class questions_answers_by_expert extends AppCompatActivity {
    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_questions_answers_by_expert);

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.blue));

        ImageButton backButton6 = findViewById(R.id.backButton6);
        backButton6.setOnClickListener(v -> {
            Intent intent = new Intent(this, home_page.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        bottomNav =findViewById(R.id.bottomNavigationView);
        bottomNav.setSelectedItemId(R.id.nav_home);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {


                return true;
            }else if (id == R.id.nav_lab) {
                Intent intent = new Intent(this,home_lab_test.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                return true;
            }else if (id==R.id.nav_community) {
                Intent intent = new Intent(this,CommunityForum.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                return true;

            }
            return false;
        });

        RecyclerView doctoranswers_recyclerview = findViewById(R.id.doctoranswers_recyclerview);
        doctoranswers_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        List<DoctorAnswer> doctorAnswers = new ArrayList<>();
        doctorAnswers.add(new DoctorAnswer("Dr. Marini Vanitha", "General Physician", "2 minutes ago", "Your SGPT levels are elevated. Avoid oily, fatty, and spicy foods. Completely avoid alcohol and smoking."));
        doctorAnswers.add(new DoctorAnswer("Dr. Karan Mehta", "Hepatologist", "5 minutes ago", "Include more fruits and vegetables in your diet. Stay hydrated and follow up with liver function tests."));
        doctorAnswers.add(new DoctorAnswer("Dr. Sneha Roy", "Dietitian", "10 minutes ago", "Reduce intake of red meat and processed food. Eat smaller, frequent meals. Avoid unnecessary medications."));
        DoctorAnswerAdapter doctorAnswerAdapter = new DoctorAnswerAdapter(this,doctorAnswers);
        doctoranswers_recyclerview.setAdapter(doctorAnswerAdapter);


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        bottomNav.setSelectedItemId(R.id.nav_home);
    }
}