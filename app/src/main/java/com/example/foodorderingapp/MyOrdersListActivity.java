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
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("Order");
        firebaseStorage = FirebaseStorage.getInstance();
        reference.keepSynced(true);
        ordersListAdapter = new MyOrdersListAdapter( orderList,this);
        addDataItem();
        displayRecyclerView();
    }

    private void displayRecyclerView() {
        myOrdersRV = findViewById(R.id.myOrdersRV);
        myOrdersRV.setLayoutManager(new LinearLayoutManager(this));
        myOrdersRV.setAdapter(ordersListAdapter);
    }

    private void addDataItem() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Order order= null;
                orderList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    order= new Order();
                    String orderName=snapshot.getKey();
                    String resName= String.valueOf(snapshot.child("hotelId").getValue());
                    String cost= String.valueOf(snapshot.child("orderedItems").child("price").getValue());
                    String timestamp= String.valueOf(snapshot.child("orderedOn").getValue());
                    String orderedBy= String.valueOf(snapshot.child("orderedBy").getValue());
                    order.setTotalCost(cost);
                    order.setHotelId(resName);
                    order.setOrderedOn(timestamp);
                    order.setOrderedBy(orderedBy);
                    orderList.add(order);
                    ordersListAdapter.notifyDataSetChanged();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




}
