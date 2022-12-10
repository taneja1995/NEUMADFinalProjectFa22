package com.example.foodorderingapp;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class ProgressBarActivity extends AppCompatActivity {

    int progress=0;
    String orderId=null;
    private ProgressBar progressBar;
    private Button btn;
    private TextView status;
    FirebaseStorage firebaseStorage;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);
        progressBar= findViewById(R.id.progressBar);
        btn= findViewById(R.id.checkStatus_btn);
        status=findViewById(R.id.status_tv);
        reference2=FirebaseDatabase.getInstance().getReference().child("Order");
        firebaseStorage = FirebaseStorage.getInstance();
        Bundle extras= getIntent().getExtras();
        if(extras!=null){
            orderId = extras.getString("orderId");
        }
        System.out.println(" the order id from the intent is "+orderId);
        setProgress();
        // to show the progress bar status for the order based on the status.
       /* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(orderStatus.equals("Completed")){
                    progress=100;
                    progressBar.setProgress(progress);
                    status.setText("Yipeee!! Your order is prepared! Delicious food is on the way!");
                    status.setTextColor(Color.BLACK);
                }else{
                    progress=50;
                    progressBar.setProgress(progress);
                    status.setText("Your order is still in progress! Thanks for your patience!");
                    status.setTextColor(Color.BLACK);
                }
            }
        });*/
    }

    private void setProgress(){
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Order order = null;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    order = new Order();
                    String id =reference2.child("Order").getKey();
                    String orderStatus = String.valueOf(snapshot.child("completionStatus").getValue());
                    System.out.println("The order status is "+ orderStatus);
                    System.out.println(" the order id from database is "+ id);
                    System.out.println("The order ID is from intent is "+ orderId);
                    if (id == orderId) {
                        if (orderStatus.equals("Completed")) {
                            progress = 100;
                            progressBar.setProgress(progress);
                            status.setText("Yipeee!! Your order is prepared! Delicious food is on the way!");
                            status.setTextColor(Color.BLACK);
                        } else {
                            progress = 50;
                            progressBar.setProgress(progress);
                            status.setText("Your order is still in progress! Thanks for your patience!");
                            status.setTextColor(Color.BLACK);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void goToChatScreen(View view){
        Intent intent = new Intent(this, OrderAmount.class);
        startActivity(intent);
    }
}
