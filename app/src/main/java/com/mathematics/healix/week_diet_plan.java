//package com.mathematics.healix;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.view.Window;
//import android.widget.ImageButton;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//
//public class week_diet_plan extends AppCompatActivity implements View.OnClickListener {
//    BottomNavigationView bottomNav;
//    private TextView calorieText;
//    private TextView carbsText;
//    private TextView fatText;
//    private TextView proteinText;
//
//    // UI elements for breakfast details
//    private TextView breakfastTitle;
//    private TextView breakfastCalories;
//    private TextView breakfastTime;
//    private TextView breakfastServings;
//
//    // UI elements for lunch details
//    private TextView lunchTitle;
//    private TextView lunchCalories;
//    private TextView lunchTime;
//    private TextView lunchServings;
//
//    // UI elements for dinner details
//    private TextView dinnerTitle;
//    private TextView dinnerCalories;
//    private TextView dinnerTime;
//    private TextView dinnerServings;
//
//    // Progress bar for calorie visualization
//    private ProgressBar calorieProgress;
//
//    // Day tabs
//    private TextView[] dayTabs = new TextView[7];
//
//    // Day indicator dots
//    private View[] dayIndicators = new View[7];
//
//    // Currently selected day (1-7)
//    private int currentDay = 1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_week_diet_plan);
//        Window window = getWindow();
//        window.setStatusBarColor(getResources().getColor(R.color.blue));
//        TextView food_title, food_subtitle;
//
////        food_title = findViewById(R.id.food_title);
////        food_subtitle = findViewById(R.id.food_subtitle);
//
//        food_title.setText(getIntent().getStringExtra("title"));
//        food_subtitle.setText(getIntent().getStringExtra("subtitle"));
//
//        bottomNav = findViewById(R.id.bottomNavigationView);
//        bottomNav.setSelectedItemId(R.id.nav_home);
//        bottomNav.setOnItemSelectedListener(item -> {
//            int id = item.getItemId();
//            if (id == R.id.nav_home) {
//                return true;
//            } else if (id == R.id.nav_lab) {
//                startActivity(new Intent(this, home_lab_test.class));
//                return true;
//            } else if (id == R.id.nav_community) {
//                startActivity(new Intent(this, CommunityForum.class));
//                return true;
//            } else if (id == R.id.nav_diet) {
//                startActivity(new Intent(this, diet_planning_page.class));
//                return true;
//            }
//            return false;
//        });
//
//        ImageButton backButton4 = findViewById(R.id.backButton4);
//        backButton4.setOnClickListener(v -> {
//            finish();
//        });
//
//        initializeViews();
//        setupDayTabs();
//        updateDayContent(1);
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//    }
//
//    private void initializeViews() {
//        calorieText = findViewById(R.id.calorieText);
//        carbsText = findViewById(R.id.carbsText);
//        fatText = findViewById(R.id.fatText);
//        proteinText = findViewById(R.id.proteinText);
//        calorieProgress = findViewById(R.id.calorieProgress);
//
//        breakfastTitle = findViewById(R.id.breakfastTitle);
//        breakfastCalories = findViewById(R.id.breakfastCalories);
//        breakfastTime = findViewById(R.id.breakfastTime);
//        breakfastServings = findViewById(R.id.breakfastServings);
//
//        lunchTitle = findViewById(R.id.lunchTitle);
//        lunchCalories = findViewById(R.id.lunchCalories);
//        lunchTime = findViewById(R.id.lunchTime);
//        lunchServings = findViewById(R.id.lunchServings);
//
//        dinnerTitle = findViewById(R.id.dinnerTitle);
//        dinnerCalories = findViewById(R.id.dinnerCalories);
//        dinnerTime = findViewById(R.id.dinnerTime);
//        dinnerServings = findViewById(R.id.dinnerServings);
//
//        dayTabs[0] = findViewById(R.id.tab_day1);
//        dayTabs[1] = findViewById(R.id.tab_day2);
//        dayTabs[2] = findViewById(R.id.tab_day3);
//        dayTabs[3] = findViewById(R.id.tab_day4);
//        dayTabs[4] = findViewById(R.id.tab_day5);
//        dayTabs[5] = findViewById(R.id.tab_day6);
//        dayTabs[6] = findViewById(R.id.tab_day7);
//
//        dayIndicators[0] = findViewById(R.id.indicator_day1);
//        dayIndicators[1] = findViewById(R.id.indicator_day2);
//        dayIndicators[2] = findViewById(R.id.indicator_day3);
//        dayIndicators[3] = findViewById(R.id.indicator_day4);
//        dayIndicators[4] = findViewById(R.id.indicator_day5);
//        dayIndicators[5] = findViewById(R.id.indicator_day6);
//        dayIndicators[6] = findViewById(R.id.indicator_day7);
//    }
//
//    private void setupDayTabs() {
//        for (int i = 0; i < dayTabs.length; i++) {
//            dayTabs[i].setOnClickListener(this);
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        for (int i = 0; i < dayTabs.length; i++) {
//            if (v.getId() == dayTabs[i].getId()) {
//                updateDayContent(i + 1);
//                break;
//            }
//        }
//    }
//
//    private void updateDayContent(int day) {
//        currentDay = day;
//        updateTabAppearance();
//        updateDayIndicators();
//
//        TextView dayTitle = findViewById(R.id.textDayTitle);
//        dayTitle.setText("Day " + day);
//
//        switch (day) {
//            case 1:
//                displayDay1Data();
//                break;
//            case 2:
//                displayDay2Data();
//                break;
//            case 3:
//                displayDay3Data();
//                break;
//            case 4:
//                displayDay4Data();
//                break;
//            case 5:
//                displayDay5Data();
//                break;
//            case 6:
//                displayDay6Data();
//                break;
//            case 7:
//                displayDay7Data();
//                break;
//        }
//    }
//
//    private void updateTabAppearance() {
//        for (int i = 0; i < dayTabs.length; i++) {
//            if (i == currentDay - 1) {
//                dayTabs[i].setBackgroundResource(R.drawable.tab_selected_bg);
//                dayTabs[i].setTextColor(getResources().getColor(R.color.white));
//            } else {
//                dayTabs[i].setBackgroundResource(R.drawable.tab_unselected_bg);
//                dayTabs[i].setTextColor(getResources().getColor(R.color.text_primary));
//            }
//        }
//    }
//
//    private void updateDayIndicators() {
//        for (int i = 0; i < dayIndicators.length; i++) {
//            dayIndicators[i].setBackgroundResource(i == currentDay - 1 ? R.color.indicator_active : R.color.indicator_inactive);
//        }
//    }
//
//    private void displayDay1Data() {
//        calorieText.setText("1000");
//        calorieProgress.setProgress(75);
//        carbsText.setText("Carbs 95 g");
//        fatText.setText("Fat 45 g");
//        proteinText.setText("Protein 104 g");
//
//        breakfastTitle.setText("1 cup of Yogurt Oatmeal with Raisins");
//        breakfastCalories.setText("300kcal");
//        breakfastTime.setText("5min");
//        breakfastServings.setText("2 servings");
//
//        lunchTitle.setText("Pan-fried Chicken Patties");
//        lunchCalories.setText("500kcal");
//        lunchTime.setText("25min");
//        lunchServings.setText("5 servings");
//
//        dinnerTitle.setText("Grilled Salmon with Vegetables");
//        dinnerCalories.setText("400kcal");
//        dinnerTime.setText("30min");
//        dinnerServings.setText("3 servings");
//    }
//
//    private void displayDay2Data() {
//        calorieText.setText("1100");
//        calorieProgress.setProgress(80);
//        carbsText.setText("Carbs 100 g");
//        fatText.setText("Fat 40 g");
//        proteinText.setText("Protein 110 g");
//
//        breakfastTitle.setText("Greek Yogurt with Berries");
//        breakfastCalories.setText("250kcal");
//        breakfastTime.setText("5min");
//        breakfastServings.setText("1 serving");
//
//        lunchTitle.setText("Quinoa Vegetable Bowl");
//        lunchCalories.setText("450kcal");
//        lunchTime.setText("20min");
//        lunchServings.setText("4 servings");
//
//        dinnerTitle.setText("Grilled Chicken with Sweet Potatoes");
//        dinnerCalories.setText("500kcal");
//        dinnerTime.setText("35min");
//        dinnerServings.setText("3 servings");
//    }
//
//    private void displayDay3Data() {
//        calorieText.setText("950");
//        calorieProgress.setProgress(70);
//        carbsText.setText("Carbs 85 g");
//        fatText.setText("Fat 40 g");
//        proteinText.setText("Protein 95 g");
//
//        breakfastTitle.setText("Avocado Toast with Egg");
//        breakfastCalories.setText("320kcal");
//        breakfastTime.setText("10min");
//        breakfastServings.setText("1 serving");
//
//        lunchTitle.setText("Mediterranean Salad");
//        lunchCalories.setText("380kcal");
//        lunchTime.setText("15min");
//        lunchServings.setText("2 servings");
//
//        dinnerTitle.setText("Vegetable Stir Fry with Tofu");
//        dinnerCalories.setText("350kcal");
//        dinnerTime.setText("25min");
//        dinnerServings.setText("3 servings");
//    }
//
//    private void displayDay4Data() {
//        calorieText.setText("1050");
//        calorieProgress.setProgress(78);
//        carbsText.setText("Carbs 110 g");
//        fatText.setText("Fat 35 g");
//        proteinText.setText("Protein 90 g");
//
//        breakfastTitle.setText("Smoothie Bowl with Granola");
//        breakfastCalories.setText("280kcal");
//        breakfastTime.setText("8min");
//        breakfastServings.setText("1 serving");
//
//        lunchTitle.setText("Turkey and Avocado Wrap");
//        lunchCalories.setText("420kcal");
//        lunchTime.setText("12min");
//        lunchServings.setText("1 serving");
//
//        dinnerTitle.setText("Baked Cod with Roasted Vegetables");
//        dinnerCalories.setText("380kcal");
//        dinnerTime.setText("40min");
//        dinnerServings.setText("2 servings");
//    }
//
//    /**
//     * Display sample data for Day 5
//     */
//    private void displayDay5Data() {
//        // Set calorie data
//        calorieText.setText("980");
//        calorieProgress.setProgress(74);
//
//        // Set macronutrient information
//        carbsText.setText("Carbs 92 g");
//        fatText.setText("Fat 38 g");
//        proteinText.setText("Protein 98 g");
//
//        // Set breakfast meal details
//        breakfastTitle.setText("Peanut Butter Banana Smoothie");
//        breakfastCalories.setText("300kcal");
//        breakfastTime.setText("5min");
//        breakfastServings.setText("2 servings");
//
//        // Set lunch meal details
//        lunchTitle.setText("Grilled Chicken Caesar Salad");
//        lunchCalories.setText("450kcal");
//        lunchTime.setText("20min");
//        lunchServings.setText("2 servings");
//
//        // Set dinner meal details
//        dinnerTitle.setText("Lentil Soup with Whole Wheat Bread");
//        dinnerCalories.setText("350kcal");
//        dinnerTime.setText("30min");
//        dinnerServings.setText("3 servings");
//    }
//
//    /**
//     * Display sample data for Day 6
//     */
//    private void displayDay6Data() {
//        // Set calorie data
//        calorieText.setText("1020");
//        calorieProgress.setProgress(77);
//
//        // Set macronutrient information
//        carbsText.setText("Carbs 90 g");
//        fatText.setText("Fat 42 g");
//        proteinText.setText("Protein 100 g");
//
//        // Set breakfast meal details
//        breakfastTitle.setText("Boiled Eggs with Toast");
//        breakfastCalories.setText("280kcal");
//        breakfastTime.setText("7min");
//        breakfastServings.setText("2 servings");
//
//        // Set lunch meal details
//        lunchTitle.setText("Brown Rice with Grilled Veggies");
//        lunchCalories.setText("460kcal");
//        lunchTime.setText("25min");
//        lunchServings.setText("3 servings");
//
//        // Set dinner meal details
//        dinnerTitle.setText("Baked Chicken with Quinoa");
//        dinnerCalories.setText("380kcal");
//        dinnerTime.setText("35min");
//        dinnerServings.setText("2 servings");
//    }
//
//    /**
//     * Display sample data for Day 7
//     */
//    private void displayDay7Data() {
//        // Set calorie data
//        calorieText.setText("990");
//        calorieProgress.setProgress(75);
//
//        // Set macronutrient information
//        carbsText.setText("Carbs 88 g");
//        fatText.setText("Fat 39 g");
//        proteinText.setText("Protein 102 g");
//
//        // Set breakfast meal details
//        breakfastTitle.setText("Fruit and Nut Yogurt Parfait");
//        breakfastCalories.setText("310kcal");
//        breakfastTime.setText("6min");
//        breakfastServings.setText("1 serving");
//
//        // Set lunch meal details
//        lunchTitle.setText("Chickpea and Spinach Curry");
//        lunchCalories.setText("440kcal");
//        lunchTime.setText("30min");
//        lunchServings.setText("3 servings");
//
//        // Set dinner meal details
//        dinnerTitle.setText("Stuffed Bell Peppers");
//        dinnerCalories.setText("380kcal");
//        dinnerTime.setText("40min");
//        dinnerServings.setText("2 servings");
//    }
//
//
//}
