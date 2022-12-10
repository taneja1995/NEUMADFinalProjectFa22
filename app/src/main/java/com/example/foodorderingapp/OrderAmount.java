package com.example.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Calendar;
import java.util.Date;

public class OrderAmount extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    DatabaseReference reference2;
    FirebaseStorage firebaseStorage;
    DatabaseReference firebaseDbRef;
    Order order=new Order();


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderamount);
    }

    public void confirmCheckoutBtn(View view){
        addToFirebase();
        Intent intent = new Intent(this, MyOrdersListActivity.class);
        startActivity(intent);
    }
    // create the order dynamically

}
