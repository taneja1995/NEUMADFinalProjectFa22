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
import java.util.Objects;

public class RestorderReceiverActivity extends AppCompatActivity {

    private List<Order> restaurantOrdersList = new ArrayList<Order>();

    Order order;
    RecyclerView restaurantReceiverRecyclerView;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    FirebaseStorage firebaseStorage;
    RestorderReceiverAdapter restorderReceiverAdapter;
    com.example.foodorderingapp.MyApplication myApplication;
    String restaurantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_restaurantlist);



        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("Order");
        firebaseStorage = FirebaseStorage.getInstance();
        reference.keepSynced(true);
        restorderReceiverAdapter = new RestorderReceiverAdapter( restaurantOrdersList,this);
        restaurantName =   ((MyApplication) this.getApplication()).getCurrentLoggedInRestaurantName();

        System.out.println(restaurantName);

        addDataItem();
        DisplayRecyclerView();
    }

    private void DisplayRecyclerView() {
        restaurantReceiverRecyclerView = findViewById(R.id.restaurantReceiverRecyclerView);
        restaurantReceiverRecyclerView.setLayoutManager(new LinearLayoutManager(RestorderReceiverActivity.this));
        restaurantReceiverRecyclerView.setAdapter(restorderReceiverAdapter);
    }

    private void addDataItem() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Order order;
                restaurantOrdersList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String orderNo =snapshot.getKey();
                    if(snapshot.child("hotelId").getValue() == null)
                        continue;

                    String hotelId = Objects.requireNonNull(snapshot.child("hotelId").getValue()).toString();
                    String completionStatus = Objects.requireNonNull(snapshot.child("completionStatus").getValue()).toString();

                    if(hotelId.equals(restaurantName) && completionStatus.equals("In Progress")){

                        order = new Order();
                        order.setOrderNo(orderNo);
                        order.setOrderedOn(Objects.requireNonNull(snapshot.child("orderedOn").getValue()).toString());
                        order.setOrderedBy(Objects.requireNonNull(snapshot.child("orderedBy").getValue()).toString());
                        order.setCompletionStatus(Objects.requireNonNull(snapshot.child("completionStatus").getValue()).toString());


                        restaurantOrdersList.add(order);
                        restorderReceiverAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }


}
