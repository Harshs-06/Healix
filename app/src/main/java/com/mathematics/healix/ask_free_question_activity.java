package com.mathematics.healix;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

public class ask_free_question_activity extends AppCompatActivity {
    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ask_free_question);

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.blue));
        bottomNav =findViewById(R.id.bottomNavigationView);
        bottomNav.setSelectedItemId(R.id.nav_home);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {


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

            return false;
        });
        Spinner forprofile = findViewById(R.id.forprofile);
        Spinner problemtype = findViewById(R.id.problemtype);


        String[] choice_forprofile = {"Select a profile","Asked for Male","Asked for Female"};
        ArrayAdapter<String> profileAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item_layout,choice_forprofile){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.parseColor("#818080"));
                } else {
                    tv.setTextColor(Color.parseColor("#000000"));
                }
                return view;
            }
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

        };
        profileAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        forprofile.setAdapter(profileAdapter);
        forprofile.setSelection(0);

        String[] healthProblems = {
                "Select a health issue",
                "Fever",
                "Cough & Cold",
                "Headache",
                "Stomach Pain",
                "Body Pain",
                "Skin Allergy",
                "High Blood Pressure",
                "Diabetes",
                "Injury",
                "Fatigue",
                "Depression",
                "Anxiety",
                "Other"
        };
        ArrayAdapter<String> problemAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item_layout,healthProblems){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.parseColor("#818080"));
                } else {
                    tv.setTextColor(Color.parseColor("#000000"));
                }
                return view;
            }
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }


        };
        problemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        problemtype.setAdapter(problemAdapter);
        problemtype.setSelection(0);

        EditText title = findViewById(R.id.title);
        EditText description = findViewById(R.id.description);


        MaterialButton askquestion = findViewById(R.id.askquestion);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        askquestion.setOnClickListener(v -> {
            String titleText = title.getText().toString().trim();
            String descriptionText = description.getText().toString().trim();
            String selectedProfile = forprofile.getSelectedItem().toString();
            String selectedHealth = problemtype.getSelectedItem().toString();
            if (titleText.isEmpty() || descriptionText.isEmpty() ||
                    selectedProfile.equals("Select a profile") || selectedHealth.equals("Select a health issue")) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }else {
                askquestion.setText("");
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(() -> {
                    progressBar.setVisibility(View.GONE);
                    askquestion.setText("Ask");
                }, 5000);
                Toast.makeText(this, "Your question posted successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, home_page.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        ImageButton backButton7 = findViewById(R.id.backButton7);
        backButton7.setOnClickListener(v -> {
            Intent intent = new Intent(this, home_page.class);
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

        bottomNav.setSelectedItemId(R.id.nav_home);
    }
}