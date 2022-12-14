package com.example.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MenuListActivity extends AppCompatActivity {

    TextView restaurantName;
    Button confirmOrder;
    private List<FoodItems> foodItemsList = new ArrayList<FoodItems>();

    FoodItems foodItem;
    int count=0;
    RecyclerView menuListRV;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    FirebaseStorage firebaseStorage;
    MenuListAdapter menuListAdapter;
    public static String latitude = null;
    public   static String longitude=null;
    public static String restaurant=null;
    public String temp="";
    MyApplication myApplication=new MyApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodmenu);
        temp=getIntent().getStringExtra("my_string_data");
        if (temp!=null){
            MyApplication.RestaurantName = temp;
        }
        restaurantName=findViewById(R.id.restaurantName);
        confirmOrder=findViewById(R.id.confirmOrder);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("Restaurant");
        firebaseStorage = FirebaseStorage.getInstance();
        reference.keepSynced(true);
        menuListAdapter = new MenuListAdapter( foodItemsList,this);

        addDataItem();
        DisplayRecyclerView();

        confirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkout();

            }
        });
    }

    private void checkout() {
        Intent intent= new Intent(MenuListActivity.this, OrderAmount.class);
        startActivity(intent);
    }

    private void DisplayRecyclerView() {

        menuListRV = findViewById(R.id.menuListRV);
        menuListRV.setLayoutManager(new LinearLayoutManager(this));
        List<FoodItems> foodIt=foodItemsList;

        for(int i=0;i<foodItemsList.size();i++)
        {

        }
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
                    restaurantName.setText(MyApplication.RestaurantName);

                    if(resName.equals(MyApplication.RestaurantName)){
                    for(DataSnapshot menu:menuList.getChildren()) {
                        FoodItems foodItems=new FoodItems();
                        foodItems.setFoodImage(menu.child("Image").getValue().toString());
                        foodItems.setFoodItemName(menu.getKey());
                        foodItems.setFoodPrice(menu.child("Price").getValue().toString());
                        foodItems.setLatitude(snapshot.child("Latitude").getValue().toString());
                        foodItems.setLongitude(snapshot.child("Longitude").getValue().toString());
                        System.out.println("Latitude--"+foodItems.getLatitude()+"--Longitude--"+foodItems.getLongitude());
                        System.out.println("----------------" + menu.getKey() + "--" + menu.child("Price").getValue().toString());
                        foodItemsList.add(foodItems);
                        latitude=snapshot.child("Latitude").getValue().toString();
                        longitude=snapshot.child("Longitude").getValue().toString();
                        restaurant=resName;
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