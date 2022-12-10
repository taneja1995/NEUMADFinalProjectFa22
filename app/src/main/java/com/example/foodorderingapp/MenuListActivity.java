package com.example.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

public class MenuListActivity extends AppCompatActivity {

    @Override
    protected void onNewIntent (Intent intent) {
        super.onNewIntent(intent) ;
        Bundle extras = intent.getExtras() ;
        if (extras != null ) {
            if (extras.containsKey( "my_string_data" )) {
                String msg = extras.getString( "my_string_data" ) ;
                System.out.println(msg);
            }
        }
    }

    TextView restaurantName;
    private List<FoodItems> foodItemsList = new ArrayList<FoodItems>();

    FoodItems foodItem;
    int count=0;
    RecyclerView menuListRV;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    FirebaseStorage firebaseStorage;
    MenuListAdapter menuListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodmenu);
        restaurantName=findViewById(R.id.restaurantName);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("Restaurant");
        firebaseStorage = FirebaseStorage.getInstance();
        reference.keepSynced(true);
        menuListAdapter = new MenuListAdapter( foodItemsList,this);

        addDataItem();
        DisplayRecyclerView();
    }

    private void DisplayRecyclerView() {
        menuListRV = findViewById(R.id.menuListRV);
        menuListRV.setLayoutManager(new LinearLayoutManager(this));
        menuListRV.setAdapter(menuListAdapter);
    }

    private void addDataItem() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foodItemsList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //OrderedItem = new OrderedItem();
                    DataSnapshot menuList = snapshot.child("MenuList");
                    String resName=snapshot.getKey();
                    System.out.println("----------------"+resName);
                    restaurantName.setText("IndianSpices");
                    if(resName.equals("IndianSpices")){

                    for(DataSnapshot menu:menuList.getChildren()) {
                        FoodItems foodItems=new FoodItems();
                        foodItems.setFoodImage(menu.child("Image").getValue().toString());
                        foodItems.setFoodItemName(menu.getKey());
                        foodItems.setFoodPrice(menu.child("Price").getValue().toString());
                        System.out.println("----------------" + menu.getKey() + "--" + menu.child("Price").getValue().toString());
                        foodItemsList.add(foodItems);
                        menuListAdapter.notifyDataSetChanged();
                    }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }
}