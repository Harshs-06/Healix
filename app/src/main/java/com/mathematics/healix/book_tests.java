package com.mathematics.healix;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class book_tests extends AppCompatActivity {

    BottomNavigationView bottomNav;
    LinearLayout cat_individual, cat_couple, cat_family, if_family_choosen;
    TextView datepicker, timepicker, totalPricePayable, type, pre_prepare, userprice;
    Calendar selectedCalendar = Calendar.getInstance();
    Spinner familyMemberSpinner;
    LinearLayout priceLayout;
    int basePrice;
    EditText homeaddress, pincode;
    private String selectedCategory = "";
    MaterialButton bookTest;
    ProgressBar progressBar;
    String btntxt = "Book";
    private static final String CHANNEL_ID = "booking_notification_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_tests);
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.blue));

        createNotificationChannel();

        homeaddress = findViewById(R.id.homeaddress);
        pincode = findViewById(R.id.pincode);
        bookTest = findViewById(R.id.bookTest);
        progressBar = findViewById(R.id.progressBar);

        bookTest.setOnClickListener(v -> {
            if (validateInputs()) {
                push_data();
            }
        });

        ImageButton backButton7 = findViewById(R.id.backButton7);
        backButton7.setOnClickListener(v -> finish());

        bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setSelectedItemId(R.id.nav_lab);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, home_page.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                return true;
            } else if (id == R.id.nav_lab) {
                return true;
            } else if (id == R.id.nav_community) {
                startActivity(new Intent(this, CommunityForum.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                return true;
            } else if (id == R.id.nav_diet) {
                startActivity(new Intent(this, diet_planning_page.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                return true;
            }
            return false;
        });

        type = findViewById(R.id.type);
        pre_prepare = findViewById(R.id.pre_prepare);
        userprice = findViewById(R.id.userprice);
        type.setText(getIntent().getStringExtra("title"));
        pre_prepare.setText(getIntent().getStringExtra("desc"));
        userprice.setText(getIntent().getStringExtra("price"));
        String priceString = getIntent().getStringExtra("price").replace("₹", "").trim();
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
            selectedCategory = "Individual";
            updatePrice(1);
        });
        cat_couple.setOnClickListener(v -> {
            updateCategorySelection(cat_couple);
            if_family_choosen.setVisibility(View.GONE);
            selectedCategory = "Couple";
            updatePrice(2);
        });
        cat_family.setOnClickListener(v -> {
            updateCategorySelection(cat_family);
            if_family_choosen.setVisibility(View.VISIBLE);
            int memberCount = Integer.parseInt(familyMemberSpinner.getSelectedItem().toString());
            selectedCategory = "Family (" + memberCount + ")";
            updatePrice(memberCount);
        });

        List<Integer> members = new ArrayList<>();
        for (int i = 3; i <= 9; i++) members.add(i);

        ArrayAdapter<Integer> membersadapter = new ArrayAdapter<>(this, R.layout.member_spinner_layout, members);
        membersadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        familyMemberSpinner.setAdapter(membersadapter);

        familyMemberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (cat_family.isSelected()) {
                    int memberCount = Integer.parseInt(parent.getItemAtPosition(position).toString());
                    updatePrice(memberCount);
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
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
            datepicker.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
            timepicker.setText("Select Time");
        }, today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(today.getTimeInMillis());
        datePickerDialog.show();
    }

    private void showTimePicker() {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY), minute = now.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, selectedHour, selectedMinute) -> {
            timepicker.setText(String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute));
        }, hour, minute, true);
        timePickerDialog.show();
    }

    private void updatePrice(int multiplier) {
        int total = basePrice * multiplier;
        totalPricePayable.setText("₹" + total);
        priceLayout.setVisibility(View.VISIBLE);
    }

    private void push_data() {
        progressBar.setVisibility(View.VISIBLE);
        bookTest.setText("");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            bookTest.setText(btntxt);
            progressBar.setVisibility(View.GONE);
            return;
        }
        String uid = user.getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> book_detail = new HashMap<>();
        book_detail.put("title", type.getText().toString());
        book_detail.put("description", pre_prepare.getText().toString());
        book_detail.put("category", selectedCategory);
        book_detail.put("date", datepicker.getText().toString());
        book_detail.put("time", timepicker.getText().toString());
        book_detail.put("address", homeaddress.getText().toString());
        book_detail.put("pincode", pincode.getText().toString());
        book_detail.put("amount", totalPricePayable.getText().toString());

        db.collection("book_tests").document(uid)
                .set(book_detail)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Booking successful", Toast.LENGTH_SHORT).show();
                    showNotification();
                    addEventToCalendarAutomatically();
                    finish();
                })
                .addOnFailureListener(e -> {
                    progressBar.setVisibility(View.GONE);
                    bookTest.setText(btntxt);
                    Toast.makeText(this, "Booking failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private boolean validateInputs() {
        return !datepicker.getText().toString().equals("Select Date")
                && !timepicker.getText().toString().equals("Select Time")
                && !homeaddress.getText().toString().trim().isEmpty()
                && !pincode.getText().toString().trim().isEmpty();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Booking Notification";
            String description = "Channel for booking success";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Healix")
                .setContentText("Test booked successfully. Check calendar.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        NotificationManagerCompat.from(this).notify(1, builder.build());
    }

    private void addEventToCalendarAutomatically() {
        if (type.getText() == null || timepicker.getText() == null || datepicker.getText() == null) return;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR}, 100);
            return;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

            Date eventDate = dateFormat.parse(datepicker.getText().toString());
            Date eventTime = timeFormat.parse(timepicker.getText().toString());

            Calendar calendar = Calendar.getInstance();
            assert eventDate != null;
            calendar.setTime(eventDate);

            Calendar timeCalendar = Calendar.getInstance();
            assert eventTime != null;
            timeCalendar.setTime(eventTime);

            calendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));

            long startMillis = calendar.getTimeInMillis();
            calendar.add(Calendar.HOUR_OF_DAY, 2);
            long endMillis = calendar.getTimeInMillis();

            ContentResolver cr = getContentResolver();
            ContentValues values = new ContentValues();
            values.put(CalendarContract.Events.DTSTART, startMillis);
            values.put(CalendarContract.Events.DTEND, endMillis);
            values.put(CalendarContract.Events.TITLE, type.getText().toString());
            values.put(CalendarContract.Events.DESCRIPTION, pre_prepare.getText().toString());
            values.put(CalendarContract.Events.CALENDAR_ID, 1);
            values.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());

            Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
            if (uri != null) {
                Toast.makeText(this, "Event added to calendar", Toast.LENGTH_SHORT).show();
            }

        } catch (ParseException e) {
            Toast.makeText(this, "Failed to parse date or time", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
