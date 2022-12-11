package com.example.foodorderingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderedItemsActivity extends AppCompatActivity {

    private List<OrderedItem> orderedItemList = new ArrayList<OrderedItem>();
    private String orderId="-NIv92ZRvFti6AYRZQ96";

    RecyclerView orderedItemsRecyclerView;
    Order order = null;

    FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    OrderedItemAdapter orderedItemAdapter;
    Button completePreparingOrder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_items);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("Order").child(orderId);
        reference.keepSynced(true);
        completePreparingOrder = findViewById(R.id.orderCompletedByRestaurant);
        completePreparingOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("are you reaching");
                order.setCompletionStatus("Completed");
                HashMap orderUpdate = new HashMap();
                orderUpdate.put("completionStatus","Completed");
                reference.updateChildren(orderUpdate).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        System.out.println("Order updated successfully");
                    }
                });
            }
        });

        orderedItemAdapter = new OrderedItemAdapter(orderedItemList, this);

        orderedItemsRecyclerView = findViewById(R.id.orderedItemsRecyclerView);

        //This defines the way in which the RecyclerView is oriented
        orderedItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Associates the adapter with the RecyclerView

        orderedItemsRecyclerView.setAdapter(orderedItemAdapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orderedItemList.clear();

                order = new Order();
                //String user=String.valueOf(dataSnapshot.child(userName).getValue());
                String hotelId = String.valueOf(dataSnapshot.child("hotelId").getValue());
                String completionStatus
                        = String.valueOf(dataSnapshot.child("completionStatus").getValue());
                //DataSnapshot orderDetails = dataSnapshot.child("orderDetails");

                order.setHotelId(hotelId);
                order.setCompletionStatus(completionStatus);
//                for(DataSnapshot od:orderDetails.getChildren()){
//                    OrderedItem orderedItem=new OrderedItem();
//                    orderedItem.setOrderedItemName(String.valueOf(od.getKey()));
//                    orderedItem.setOrderedItemQuantity(String.valueOf(od.getValue()));
//                    orderedItemList.add(orderedItem);
//                }
//                orderedItemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}