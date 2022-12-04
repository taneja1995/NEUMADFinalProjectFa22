package com.example.foodorderingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderedItemsActivity extends AppCompatActivity {

    private List<OrderedItem> orderedItemList = new ArrayList<OrderedItem>();

    RecyclerView orderedItemsRecyclerView;

    FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    OrderedItemAdapter orderedItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_items);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("Order");
        reference.keepSynced(true);

        orderedItemAdapter = new OrderedItemAdapter(orderedItemList, this);
        this.orderedItemList.add(new OrderedItem("rice","2"));
        this.orderedItemList.add(new OrderedItem("roti","5"));
        this.orderedItemList.add(new OrderedItem("bread","7"));

        orderedItemsRecyclerView = findViewById(R.id.orderedItemsRecyclerView);

        //This defines the way in which the RecyclerView is oriented
        orderedItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Associates the adapter with the RecyclerView

        orderedItemsRecyclerView.setAdapter(orderedItemAdapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Order order = null;
                orderedItemList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    order = new Order();
                    //String user=String.valueOf(dataSnapshot.child(userName).getValue());
                    String hotelId = String.valueOf(snapshot.child("hotelId").getValue());
                    String completionStatus
                            = String.valueOf(snapshot.child("completionStatus").getValue());
                    DataSnapshot orderDetails = snapshot.child("orderDetails");

                    if (hotelId.equals("1")) {
                        order.setHotelId(hotelId);
                        order.setCompletionStatus(completionStatus);
                        for(DataSnapshot od:orderDetails.getChildren()){
                            OrderedItem orderedItem=new OrderedItem();
                            orderedItem.setOrderedItemName(String.valueOf(od.getKey()));
                            orderedItem.setOrderedItemQuantity(String.valueOf(od.getValue()));
                            orderedItemList.add(orderedItem);
                        }
                        orderedItemAdapter.notifyDataSetChanged();
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}