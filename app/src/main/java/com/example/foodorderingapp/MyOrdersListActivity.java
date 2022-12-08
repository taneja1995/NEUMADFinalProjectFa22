package com.example.foodorderingapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersListActivity extends AppCompatActivity {

    private RecyclerView myOrdersRV;
    private MyOrdersListAdapter ordersListAdapter;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    FirebaseStorage firebaseStorage;

    List<Order> orderList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorders);
        ordersListAdapter = new MyOrdersListAdapter( orderList,this);
        displayRecyclerView();
    }

    private void displayRecyclerView() {
        myOrdersRV = findViewById(R.id.myOrdersRV);
        myOrdersRV.setLayoutManager(new LinearLayoutManager(this));
        myOrdersRV.setAdapter(ordersListAdapter);
    }



}
