package com.mathematics.healix;

import android.app.Application;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationHelper extends AndroidViewModel {
    private final MutableLiveData<String> locationLiveData = new MutableLiveData<>();
    private final FusedLocationProviderClient fusedClient;

    public LocationHelper(@NonNull Application application) {
        super(application);
        fusedClient = LocationServices.getFusedLocationProviderClient(application);
    }

    public LiveData<String> getLocationLiveData() {
        return locationLiveData;
    }

    public void fetchLocation() {
        if (ContextCompat.checkSelfPermission(getApplication(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != android.content.pm.PackageManager.PERMISSION_GRANTED) {
            locationLiveData.setValue("Permission not granted");
            return;
        }

        fusedClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                updateLocationName(location);
            } else {
                locationLiveData.setValue("Location not available");
            }
        });
    }

    private void updateLocationName(Location location) {
        Geocoder geocoder = new Geocoder(getApplication(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (!addresses.isEmpty()) {
                Address address = addresses.get(0);
                String city = address.getLocality();
                String area = address.getSubLocality();
                String state = address.getAdminArea();

                if (area != null && !area.isEmpty()) {
                    locationLiveData.setValue(area + ", " + city+ ", " + state);
                } else {
                    locationLiveData.setValue(city+ ", " + state);
                }

            } else {
                locationLiveData.setValue("Address not found");
            }
        } catch (IOException e) {
            locationLiveData.setValue("Geocoder error");
        }
    }
}
