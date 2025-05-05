package com.mathematics.healix;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class book_tests extends AppCompatActivity {
    BottomNavigationView bottomNav;
    LinearLayout cat_individual,cat_couple,cat_family,if_family_choosen;
    TextView datepicker,timepicker;
    Calendar selectedCalendar = Calendar.getInstance();
    Spinner familyMemberSpinner;
    LinearLayout priceLayout;
    TextView totalPricePayable;
    int basePrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_tests);
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.blue));



        ImageButton backButton7 = findViewById(R.id.backButton7);
        backButton7.setOnClickListener(v -> finish());


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
            }else if (id==R.id.nav_community) {
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

        TextView type,pre_prepare,userprice;
        type = findViewById(R.id.type);
        pre_prepare = findViewById(R.id.pre_prepare);
        userprice = findViewById(R.id.userprice);

        type.setText(getIntent().getStringExtra("title"));
        pre_prepare.setText(getIntent().getStringExtra("desc"));
        userprice.setText(getIntent().getStringExtra("price"));

        String priceString = getIntent().getStringExtra("price");
        priceString = priceString.replace("₹", "").trim();
        basePrice = Integer.parseInt(priceString);



        cat_individual = findViewById(R.id.cat_individual);
        cat_couple = findViewById(R.id.cat_couple);
        cat_family = findViewById(R.id.cat_family);
        if_family_choosen = findViewById(R.id.if_family_choosen);
        priceLayout = findViewById(R.id.priceLayout);
        totalPricePayable = findViewById(R.id.totalprice_payable);
        familyMemberSpinner = findViewById(R.id.familyMemberSpinner);
        datepicker = findViewById(R.id.datepicker);
        timepicker = findViewById(R.id.timepicker);

        datepicker.setOnClickListener(v -> showDatePicker());
        timepicker.setOnClickListener(v -> showTimePicker());


        cat_individual.setOnClickListener(v -> {
            updateCategorySelection(cat_individual);
            if_family_choosen.setVisibility(View.GONE);
            updatePrice(1);
        });

        cat_couple.setOnClickListener(v -> {
            updateCategorySelection(cat_couple);
            if_family_choosen.setVisibility(View.GONE);
            updatePrice(2);
        });

        cat_family.setOnClickListener(v -> {
            updateCategorySelection(cat_family);
            if_family_choosen.setVisibility(View.VISIBLE);

            int memberCount = Integer.parseInt(familyMemberSpinner.getSelectedItem().toString());
            updatePrice(memberCount);
        });



        List<Integer> members = new ArrayList<>();
        for (int i = 3; i <= 9; i++) {
            members.add(i);
        }
        ArrayAdapter<Integer> membersadapter = new ArrayAdapter<>(
                this,
                R.layout.member_spinner_layout,
                members
        );
        membersadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        familyMemberSpinner.setAdapter(membersadapter);

        familyMemberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Only update price if family is currently selected
                if (cat_family.isSelected()) {
                    int memberCount = Integer.parseInt(parent.getItemAtPosition(position).toString());
                    updatePrice(memberCount);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void updateCategorySelection(LinearLayout selectedLayout) {
        cat_individual.setSelected(false);
        cat_couple.setSelected(false);
        cat_family.setSelected(false);

        selectedLayout.setSelected(true);
    }
    @Override
    protected void onResume() {
        super.onResume();

        bottomNav.setSelectedItemId(R.id.nav_lab);
    }

    private void showDatePicker() {
        Calendar today = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            selectedCalendar.set(Calendar.YEAR, year);
            selectedCalendar.set(Calendar.MONTH, month);
            selectedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String date = dayOfMonth + "/" + (month + 1) + "/" + year;
            datepicker.setText(date);
            timepicker.setText("Select Time"); // Reset time
        }, today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH));

        // Disable all past dates
        datePickerDialog.getDatePicker().setMinDate(today.getTimeInMillis());
        datePickerDialog.show();
    }

    private void showTimePicker() {
        Calendar now = Calendar.getInstance();

        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, selectedHour, selectedMinute) -> {
            Calendar selectedTime = (Calendar) selectedCalendar.clone();
            selectedTime.set(Calendar.HOUR_OF_DAY, selectedHour);
            selectedTime.set(Calendar.MINUTE, selectedMinute);

            Calendar current = Calendar.getInstance();

            boolean isSameDay =
                    selectedCalendar.get(Calendar.YEAR) == current.get(Calendar.YEAR) &&
                            selectedCalendar.get(Calendar.DAY_OF_YEAR) == current.get(Calendar.DAY_OF_YEAR);

            if (isSameDay && selectedTime.before(current)) {
                Toast.makeText(this, "Please select a future time", Toast.LENGTH_SHORT).show();
            } else {
                String time = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute);
                timepicker.setText(time);
            }
        }, hour, minute, true);

        timePickerDialog.show();
    }
    private void updatePrice(int multiplier) {
        int total = basePrice * multiplier;
        totalPricePayable.setText("₹" + total);
        priceLayout.setVisibility(View.VISIBLE);
    }
}