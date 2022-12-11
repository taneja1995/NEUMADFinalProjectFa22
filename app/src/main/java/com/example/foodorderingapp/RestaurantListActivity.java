package com.example.foodorderingapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class RestaurantListActivity  extends AppCompatActivity {


    private List<RestaurantDetail> restaurantList = new ArrayList<>();

    RecyclerView restaurantListRV;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    FirebaseStorage firebaseStorage;
    RestaurantListAdapter restaurantListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantlist);


        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("Restaurant");
        firebaseStorage = FirebaseStorage.getInstance();
        reference.keepSynced(true);
        restaurantListAdapter = new RestaurantListAdapter(restaurantList,this);

        addDataItem();
        DisplayRecyclerView();


    }

    private void addDataItem() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RestaurantDetail restaurantDetail = null;
                restaurantList.clear();


                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String ResName = snapshot.getKey();
                    restaurantDetail = new RestaurantDetail();
                    restaurantDetail.setName(ResName);
                    restaurantDetail.setImage(snapshot.child("Image").getValue().toString());
                    restaurantList.add(restaurantDetail);
                    restaurantListAdapter.notifyDataSetChanged();
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void DisplayRecyclerView() {
        restaurantListRV = findViewById(R.id.restaurantRecyclerView);
        restaurantListRV.setLayoutManager(new LinearLayoutManager(RestaurantListActivity.this));
        restaurantListRV.setAdapter(restaurantListAdapter);
    }


}
