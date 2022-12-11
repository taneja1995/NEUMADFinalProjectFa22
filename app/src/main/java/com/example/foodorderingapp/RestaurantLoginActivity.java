package com.example.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RestaurantLoginActivity extends AppCompatActivity {
    DatabaseReference userRef;
    com.example.foodorderingapp.MyApplication myApplication;
    DatabaseReference firebaseDbRef;
    EditText restaurantUserName;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantlogin);
        this.restaurantUserName=findViewById(R.id.restaurantUserName);
        loginBtn=findViewById(R.id.signIn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertRestaurantData();
                Intent intent= new Intent(RestaurantLoginActivity.this, RestorderReceiverActivity.class);
                startActivity(intent);
            }
        });
    }

    private void insertRestaurantData() {
        String restaurantName= restaurantUserName.getText().toString();
        ((MyApplication) this.getApplication()).setCurrentLoggedInRestaurantName(restaurantName);
    }
}
