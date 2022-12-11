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
    MyApplication application= new MyApplication();


    private void addToFirebase(){
        firebaseDbRef = FirebaseDatabase.getInstance().getReference().child("Order");
        Date currentTime = Calendar.getInstance().getTime();
        String id = firebaseDbRef.push().getKey();
        order.setOrderedOn(currentTime.toString());
        order.setOrderedBy(application.getUserName());
        order.setHotelId(application.getRestaurantName());
        order.setCompletionStatus("In Progress");
        order.setTotalCost("$30");
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
