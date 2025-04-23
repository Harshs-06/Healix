package com.mathematics.healix;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class home_lab_test extends AppCompatActivity {
    BottomNavigationView bottomNav;
    private LocationHelper locationHelper;
    private EditText userlocation;
    ActivityResultLauncher<String> locationPermissionLauncher;
    ActivityResultLauncher<Intent> filePickerLauncher;
    MaterialCardView uploadPrescriptionButton;
    TextView prescriptiontitle;


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
            }else if (id==R.id.nav_community) {
                Intent intent = new Intent(this,CommunityForum.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
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

        RecyclerView labchoices_recyclerview = findViewById(R.id.labchoices_recyclerview);
        List<LabChoice_Model> choiceList = new ArrayList<>();
        choiceList.add(new LabChoice_Model(R.drawable.healthcheckup_img,"Health Checkups"));
        choiceList.add(new LabChoice_Model(R.drawable.labtest_img,"Lab Tests"));
        choiceList.add(new LabChoice_Model(R.drawable.downloadreport_img,"Download Report"));
        choiceList.add(new LabChoice_Model(R.drawable.tracksample_img,"Track Sample"));

        LabChoiceModel_Adapter labChoiceModel_adapter = new LabChoiceModel_Adapter(choiceList);

        labChoiceModel_adapter.setOnItemClickListener(item -> {
            switch (item.getTitle()) {
                case "Health Checkups":
                    Intent intent = new Intent(this, healthCheckups.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    break;
                case "Lab Tests":
                    Intent intent2 = new Intent(this, lab_tests.class);
                    intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent2);
                    break;
                case "Download Report":
//                    startActivity(new Intent(this, DownloadReportActivity.class));
                    break;
                case "Track Sample":
//                    startActivity(new Intent(this, TrackSampleActivity.class));
                    break;
            }
        });
        labchoices_recyclerview.setAdapter(labChoiceModel_adapter);

        LinearLayoutManager labchoice_layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        labchoices_recyclerview.setLayoutManager(labchoice_layoutManager);


        RecyclerView packages_recyclerview = findViewById(R.id.packages_recyclerview);
        packages_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<PackageItem> packageList = new ArrayList<>();
        packageList.add(new PackageItem("Basic Wellness", "No preparation needed", "81", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        packageList.add(new PackageItem("Advanced Health", "Includes blood tests", "100", "12 Hours", "₹2499", "₹3499", "You saved 28%"));
        packageList.add(new PackageItem("Basic Wellness", "No preparation needed", "81", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        packageList.add(new PackageItem("Advanced Health", "Includes blood tests", "100", "12 Hours", "₹2499", "₹3499", "You saved 28%"));
        packageList.add(new PackageItem("Basic Wellness", "No preparation needed", "81", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        packageList.add(new PackageItem("Advanced Health", "Includes blood tests", "100", "12 Hours", "₹2499", "₹3499", "You saved 28%"));
        packageList.add(new PackageItem("Basic Wellness", "No preparation needed", "81", "8 Hours", "₹1999", "₹2999", "You saved 33%"));
        packageList.add(new PackageItem("Advanced Health", "Includes blood tests", "100", "12 Hours", "₹2499", "₹3499", "You saved 28%"));

        PackageAdapter adapter = new PackageAdapter(this, packageList);
        packages_recyclerview.setAdapter(adapter);

         uploadPrescriptionButton = findViewById(R.id.uploadPrescriptionButton);
        prescriptiontitle = findViewById(R.id.prescriptiontitle);
        filePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri fileUri = result.getData().getData();
                        String fileName = getFileName(fileUri);
                        prescriptiontitle.setText(fileName);
                    }
                });

        uploadPrescriptionButton.setOnClickListener(v -> openFilePicker());



    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        String[] mimeTypes = {"application/pdf", "image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        filePickerLauncher.launch(intent);
    }

    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME));
                }
            }
        }
        if (result == null) {
            result = uri.getLastPathSegment();
        }
        return result;
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