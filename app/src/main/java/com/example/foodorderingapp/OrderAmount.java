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
import java.util.Map;

public class OrderAmount extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    DatabaseReference reference2;
    FirebaseStorage firebaseStorage;
    DatabaseReference firebaseDbRef;
    Order order=new Order();
    com.example.foodorderingapp.MyApplication myApplication;


    private void addToFirebase(){
        firebaseDbRef = FirebaseDatabase.getInstance().getReference().child("Order");
        Date currentTime = Calendar.getInstance().getTime();
        String id = firebaseDbRef.push().getKey();
        order.setOrderedOn(currentTime.toString());

        order.setOrderedBy( ((MyApplication) this.getApplication()).getUserName());
        order.setHotelId(((MyApplication) this.getApplication()).getRestaurantName());
        order.setCompletionStatus("In Progress");
        order.setTotalCost("$30");
        StringBuilder orderedItems=new StringBuilder();
        Map<String,String> map = ((MyApplication) this.getApplication()).OrderD;
        for(String key :map.keySet()){
            orderedItems.append(key);
            orderedItems.append(";");
            orderedItems.append(map.get(key));
            orderedItems.append("/");
        }

        orderedItems.deleteCharAt(orderedItems.length()-1);
        order.setOrderedItems(orderedItems.toString());
        firebaseDbRef.child(id).setValue(order);
        System.out.println("order NG"+order.getOrderedBy());
        System.out.println("order NG"+order.getOrderNo());
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
}
