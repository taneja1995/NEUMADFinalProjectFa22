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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView chatsRV;
    private ChatActivityAdapter chatActivityAdapter;
    List<Message> messageList= new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);
        chatActivityAdapter = new ChatActivityAdapter( messageList,this);
        addDataItem();
        displayRecyclerView();
    }

    private void displayRecyclerView() {
        chatsRV = findViewById(R.id.chatScreenRV);
        chatsRV.setLayoutManager(new LinearLayoutManager(this));
        chatsRV.setAdapter(chatActivityAdapter);
    }


    private void addToFirebase(){
        firebaseDbRef = FirebaseDatabase.getInstance().getReference().child("Order");
        Date currentTime = Calendar.getInstance().getTime();
        String id = firebaseDbRef.push().getKey();
        order.setOrderedOn(currentTime.toString());
        order.setOrderedBy("ananth");
        // get the hotel Id from the restaurant selected in rest page.
        order.setHotelId("Curry & Spice");
        order.setCompletionStatus("In Progress");
        // calculate the total cost from the order amount activity.
        order.setTotalCost("$30");
        // get ordered items from menu list selected.
        firebaseDbRef.child(id).setValue(order);
    }

    private void addDataItem() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Message message= null;
                messageList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    message= new Message();
                    String orderName=snapshot.getKey();
                    String resName= String.valueOf(snapshot.child("hotelId").getValue());
                    String cost= String.valueOf(snapshot.child("orderedItems").child("price").getValue());
                    String timestamp= String.valueOf(snapshot.child("orderedOn").getValue());
                    String orderedBy= String.valueOf(snapshot.child("orderedBy").getValue());
                    String status= String.valueOf(snapshot.child("completionStatus").getValue());
                    order.setTotalCost(cost);
                    order.setHotelId(resName);
                    order.setOrderedOn(timestamp);
                    order.setOrderedBy(orderedBy);
                    order.setCompletionStatus(status);
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
