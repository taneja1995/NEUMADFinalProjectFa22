package com.example.foodorderingapp;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class ProgressBarActivity extends AppCompatActivity {

    int progress=0;
    String orderStatus=null;
    private ProgressBar progressBar;
    private Button btn,mapsBtn;
    private TextView status;
    FirebaseStorage firebaseStorage;
    DatabaseReference reference2;

    public static String latitude = null;
    public   static String longitude=null;
    public static String restaurant=null;


    private DatabaseReference mapsReference;
    private List<FoodItems> foodItemsList = new ArrayList<FoodItems>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);
        progressBar= findViewById(R.id.progressBar);
        btn= findViewById(R.id.checkStatus_btn);
        status=findViewById(R.id.status_tv);
        mapsBtn=findViewById(R.id.mapsBtn);
        reference2=FirebaseDatabase.getInstance().getReference().child("Order");
        firebaseStorage = FirebaseStorage.getInstance();

        mapsReference = FirebaseDatabase.getInstance().getReference().child("Restaurant");
        firebaseStorage = FirebaseStorage.getInstance();
        mapsReference.keepSynced(true);

        Bundle extras= getIntent().getExtras();
        if(extras!=null){
            orderStatus = extras.getString("orderStatus");
        }

        if(orderStatus.equals("Completed")){
            status.setText("Your order is complete");
        }else{
            status.setText("Your order is in progress");
        }

        //setProgress();
        // to show the progress bar status for the order based on the status.
       btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(orderStatus.equals("Completed")){
                    progress=100;
                    progressBar.setProgress(progress);
                    //status.setText("Yipeee!! Your order is prepared! Delicious food is on the way!");

                }else{
                    progress=50;
                    progressBar.setProgress(progress);
                    //status.setText("Your order is still in progress! Thanks for your patience!");

                }
            }
        });
        addDataItem();
        mapsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMaps();

            }
        });

    }

    private void showMaps() {
        ((MyApplication) this.getApplication()).setRestaurantLatitude(latitude);
        ((MyApplication) this.getApplication()).setRestaurantLongitude(longitude);
        ((MyApplication) this.getApplication()).setRestaurantName(restaurant);

        Intent intent= new Intent(ProgressBarActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    private void addDataItem() {
        mapsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foodItemsList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DataSnapshot menuList = snapshot.child("MenuList");
                    String resName = snapshot.getKey();

                    if (resName.equals(MyApplication.RestaurantName)) {
                        for (DataSnapshot menu : menuList.getChildren()) {
                            FoodItems foodItems = new FoodItems();
                            foodItems.setLatitude(snapshot.child("Latitude").getValue().toString());
                            foodItems.setLongitude(snapshot.child("Longitude").getValue().toString());
                            foodItemsList.add(foodItems);
                            latitude = snapshot.child("Latitude").getValue().toString();
                            longitude = snapshot.child("Longitude").getValue().toString();
                            restaurant = resName;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void goToChatScreen(View view){
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("origin","users");
        startActivity(intent);
    }
}
