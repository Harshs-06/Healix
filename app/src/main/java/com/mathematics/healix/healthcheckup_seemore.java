package com.mathematics.healix;



import android.content.Intent;
import android.os.Bundle;

import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

public class healthcheckup_seemore extends AppCompatActivity{
    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_healthcheckup);
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
        TextView title,subtitle;
        title = findViewById(R.id.title);
        subtitle = findViewById(R.id.subtitle);
        TextView price = findViewById(R.id.price);
        MaterialButton btnBook = findViewById(R.id.btnBook);
        btnBook.setOnClickListener(v -> {
            Intent intent = new Intent(healthcheckup_seemore.this, book_tests.class);
            intent.putExtra("title",title.getText().toString());
            intent.putExtra("desc",subtitle.getText().toString());
            intent.putExtra("price",price.getText().toString());
            startActivity(intent);
        });


        ImageButton backButton1 = findViewById(R.id.backButton1);
        backButton1.setOnClickListener(v -> {
           finish();
        });




        title.setText(getIntent().getStringExtra("title"));
        subtitle.setText(getIntent().getStringExtra("desc"));

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
