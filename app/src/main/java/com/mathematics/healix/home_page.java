package com.mathematics.healix;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class home_page extends AppCompatActivity {
    private EditText searchBar;
    private MaterialCardView stepCountButton,heart_bpm,sleep_cycle,calorie_burn;
    private final String[] hints = {"Search for doctors", "Find lab tests", "Make your diet plan"};

    private int hintIndex = 0;
    private int charIndex = 0;
    private boolean deleting = false;
    private Handler handler = new Handler();
    private long delay = 100; // Typing speed
    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.blue));

        searchBar = findViewById(R.id.searchBar);
        animateHint();


        RecyclerView carouselRecycler = findViewById(R.id.carousel_recycler);
        List<home_carousel_item> items = new ArrayList<>();
        items.add(new home_carousel_item("10d ago", "Asked for Male", "28 Years, Bangalore", "Feeling rod post surgery", "I am having bp machine..."));
        items.add(new home_carousel_item("2w ago", "Asked for Male", "28 Years, Bangalore", "Feeling rod post surgery", "I am having bp machine..."));
        items.add(new home_carousel_item("10d ago", "Asked for Male", "28 Years, Bangalore", "Feeling rod post surgery", "I am having bp machine..."));
        items.add(new home_carousel_item("10d ago", "Asked for Male", "28 Years, Bangalore", "Feeling rod post surgery", "I am having bp machine..."));
        items.add(new home_carousel_item("10d ago", "Asked for Male", "28 Years, Bangalore", "Feeling rod post surgery", "I am having bp machine..."));
        home_carousel_adapter adapter = new home_carousel_adapter(items);
        carouselRecycler.setAdapter(adapter);

        MaterialButton askyourquestion = findViewById(R.id.askyourquestion);
        askyourquestion.setOnClickListener(v -> {
            Intent intent = new Intent(this, ask_free_question_activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });








        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        carouselRecycler.setLayoutManager(layoutManager);
        carouselRecycler.setPadding(40, 0, 40, 0);
        carouselRecycler.setClipToPadding(false);
        carouselRecycler.setClipChildren(false);



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
                    } else if (id==R.id.nav_community) {
                        Intent intent = new Intent(this,CommunityForum.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        return true;

                    }
                    else if (id == R.id.nav_diet) {
                        Intent intent = new Intent(this, diet_planning_page.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        return true;
                    }
            return false;
        });



        stepCountButton = findViewById(R.id.stepCountButton);

        stepCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home_page.this, StepTrackerActivity.class);
                startActivity(intent);
            }
        });

        heart_bpm = findViewById(R.id.heart_bpm);

        heart_bpm.setOnClickListener(v -> {
            Intent intent = new Intent(home_page.this, heart_rate.class);
            startActivity(intent);
        });

        sleep_cycle = findViewById(R.id.sleep_cycle);

        sleep_cycle.setOnClickListener(v -> {
            Intent intent = new Intent(home_page.this, sleep_cycle.class);
            startActivity(intent);
        });

        calorie_burn = findViewById(R.id.calorie_burn);

        calorie_burn.setOnClickListener(v -> {
            Intent intent = new Intent(home_page.this, calorie_burn.class);
            startActivity(intent);
        });














        TextView greetingText = findViewById(R.id.greetingText);
        TextView greetingDescription = findViewById(R.id.greetingDescription);
        ImageView greetingImg = findViewById(R.id.greetingImg);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour >= 5 && hour < 10) {
            greetingText.setText("It’s Time for Breakfast!");
            greetingDescription.setText("Good morning! Let’s kickstart your day with a \nhealthy breakfast.");
            greetingImg.setImageResource(R.drawable.food_img);
        } else if (hour >= 12 && hour < 15) {
            greetingText.setText("It’s Time for Lunch!");
            greetingDescription.setText("Your breakfast was a bit light today ! Let’s boost your energy with a delicious lunch.");
            greetingImg.setImageResource(R.drawable.food_img);
        } else if (hour >= 19 && hour < 22) {
            greetingText.setText("It’s Time for Dinner!");
            greetingDescription.setText("It’s dinner time! Unwind and recharge with\n a wholesome meal.");
            greetingImg.setImageResource(R.drawable.food_img);
        } else {
            greetingText.setText("Stay Hydrated!");
            greetingDescription.setText("Don’t forget to stay hydrated ! Your body will thank you for it. ");
            greetingImg.setImageResource(R.drawable.water_icon);
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //trying push
    private void animateHint() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!deleting) {
                    if (charIndex <= hints[hintIndex].length()) {
                        searchBar.setHint(hints[hintIndex].substring(0, charIndex++));
                        handler.postDelayed(this, delay);
                    } else {
                        deleting = true;
                        handler.postDelayed(this, 1000); // Wait before deleting
                    }
                } else {
                    if (charIndex > 0) {
                        searchBar.setHint(hints[hintIndex].substring(0, --charIndex));
                        handler.postDelayed(this, delay);
                    } else {
                        deleting = false;
                        hintIndex = (hintIndex + 1) % hints.length;
                        handler.postDelayed(this, delay);
                    }
                }
            }
        }, delay);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNav.setSelectedItemId(R.id.nav_home);
    }
}