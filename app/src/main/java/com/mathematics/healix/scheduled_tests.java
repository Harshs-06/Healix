package com.mathematics.healix;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class scheduled_tests extends AppCompatActivity {

    BottomNavigationView bottomNav;
    private RecyclerView recyclerView;
    private LinearLayout emptyDataLayout;
    private List<ScheduledTestModel> testList;
    private ScheduledTestAdapter adapter;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_scheduled_tests);


        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.blue));

        // UI elements
        recyclerView = findViewById(R.id.scheduledTestsRecyclerView);
        emptyDataLayout = findViewById(R.id.emptydata1);
        ImageButton backButton4 = findViewById(R.id.backButton4);

        // Set up RecyclerView
        testList = new ArrayList<>();
        adapter = new ScheduledTestAdapter(this, testList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
        Log.d("test", "Check");
        recyclerView.setVisibility(View.GONE);
        emptyDataLayout.setVisibility(View.GONE);

        // Firebase
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();


        currentUserId = Objects.requireNonNull(auth.getCurrentUser()).getUid();


        backButton4.setOnClickListener(v -> {
            finish();
        });

        // Bottom Navigation
        bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setSelectedItemId(R.id.nav_lab);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, home_page.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                return true;
            } else if (id == R.id.nav_lab) {
                return true;
            } else if (id == R.id.nav_community) {
                startActivity(new Intent(this, CommunityForum.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                return true;
            } else if (id == R.id.nav_diet) {
                startActivity(new Intent(this, diet_planning_page.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                return true;
            }
            return false;
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        fetchScheduledTests();
    }

    private void fetchScheduledTests() {
        Log.d("test", "fetching data");
        db.collection("book_tests")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    testList.clear();
                    if (!queryDocumentSnapshots.isEmpty()) {
                        for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                            Log.w("data", snapshot.getString("title") + snapshot.getString("description") + snapshot.getString("category") + snapshot.getString("date") + snapshot.getString("time") + snapshot.getString("address") + snapshot.getString("pincode") + snapshot.getString("amount"));
                            String title = snapshot.getString("title");

                            String description = snapshot.getString("description");
                            String category = snapshot.getString("category");
                            String date = snapshot.getString("date");
                            String time = snapshot.getString("time");
                            String address = snapshot.getString("address");
                            String pincode = snapshot.getString("pincode");
                            String amount = snapshot.getString("amount");

                            ScheduledTestModel model = new ScheduledTestModel(title, description, category, date, time, address, pincode, amount);

                            testList.add(model);
                            Log.d("test", String.valueOf(testList.size()));
                        }
                        adapter.notifyDataSetChanged();

                        recyclerView.setVisibility(View.VISIBLE);
                        emptyDataLayout.setVisibility(View.GONE);
                    } else {
                        emptyDataLayout.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                })
                .addOnFailureListener(e -> {
                    emptyDataLayout.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    Log.e("Firestore", "Failed to fetch booking: " + e.getMessage());
                });
    }


    @Override
    protected void onResume() {
        super.onResume();
        bottomNav.setSelectedItemId(R.id.nav_lab);
        Log.d("test", "resuming st");
    }
}
