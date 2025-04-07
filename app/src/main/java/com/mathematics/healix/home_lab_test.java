package com.mathematics.healix;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home_lab_test extends AppCompatActivity {
    BottomNavigationView bottomNav;
    private LocationHelper locationHelper;
    private EditText userlocation;
    ActivityResultLauncher<String> locationPermissionLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_lab_test);
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
            }
            return false;
        });

        userlocation = findViewById(R.id.location);
        locationPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        setupLocationViewModel();
                    } else {
                        userlocation.setText("Permission Denied");
                    }
                });

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            locationPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION);
        } else {
            setupLocationViewModel();
        }


    }

    

    @Override
    protected void onResume() {
        super.onResume();

        bottomNav.setSelectedItemId(R.id.nav_lab);
    }

    private void setupLocationViewModel() {
        locationHelper = new ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
        ).get(LocationHelper.class);

        locationHelper.getLocationLiveData().observe(this, location -> {
            userlocation.setText(location);
        });

        locationHelper.fetchLocation();
    }

}