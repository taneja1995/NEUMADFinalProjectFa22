package com.example.foodorderingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {

    private static final int PERMISSIONS_FINE_LOCATION = 99;
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;
    TextView locationTxt;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    FirebaseStorage firebaseStorage;
    private List<FoodItems> foodItemsList = new ArrayList<FoodItems>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        locationTxt=findViewById(R.id.locationTxt);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("Restaurant");
        firebaseStorage = FirebaseStorage.getInstance();
        reference.keepSynced(true);

        locationRequest=new LocationRequest();
        locationRequest.setInterval(30000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(locationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        updateGPS();

    }

    private void getRestLocation(Location location) {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foodItemsList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //OrderedItem = new OrderedItem();
                    DataSnapshot menuList = snapshot.child("MenuList");
                    String resName=snapshot.getKey();
                    //restaurantName.setText("IndianSpices");
                    if(resName.equals("Chipotle")){
                            FoodItems foodItems=new FoodItems();
                            location.setLatitude(Double.parseDouble(snapshot.child("Latitude").getValue().toString()));
                            location.setLongitude(Double.parseDouble(snapshot.child("Longitude").getValue().toString()));
                            foodItems.setLatitude(snapshot.child("Latitude").getValue().toString());
                            foodItems.setLongitude(snapshot.child("Longitude").getValue().toString());
                            System.out.println("____________"+snapshot.child("Latitude").getValue().toString());
                            System.out.println("Lati____________"+location.getLatitude());
                            foodItemsList.add(foodItems);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSIONS_FINE_LOCATION:
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                updateGPS();
            }
            else{
                Toast.makeText(this,"This app requires permission for location",Toast.LENGTH_SHORT).show();
                finish();
            }
            break;
        }
    }

    private void updateGPS() {
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
        {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    System.out.println("origin location is that//////////"+location);
                    updateUI(location);
                }
            });
        }
        else{
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSIONS_FINE_LOCATION);
            }

        }
    }

    private void updateUI(Location location1) {

        getRestLocation(location1);

        Geocoder geocoder=new Geocoder(LocationActivity.this);
        try {
            List<Address> addresses=geocoder.getFromLocation(location1.getLatitude(),location1.getLongitude(),1);
            System.out.println("******"+addresses.get(0).getLatitude()+"@@@@"+location1.getLatitude());
            locationTxt.setText(addresses.get(0).getAddressLine(0));
        }
        catch (Exception e)
        {

        }
    }
}