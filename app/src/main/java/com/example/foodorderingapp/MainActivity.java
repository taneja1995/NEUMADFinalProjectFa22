package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void BtnOrderedItems(View view){
        Intent intent = new Intent(this, OrderedItemsActivity.class);
        startActivity(intent);
    }

    public void BtnMenuList(View view){
        Intent intent = new Intent(this, MenuListActivity.class);
        startActivity(intent);
    }

    public void BtnMaps(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void checkoutBtn(View view){
        Intent intent = new Intent(this, OrderAmount.class);
        startActivity(intent);
    }

    public void RestaurantListButton(View view){
        Intent intent = new Intent(this, RestaurantListActivity.class);
        startActivity(intent);
    }

    public void RestaurantOrdersButton(View view){
        Intent intent = new Intent(this, RestorderReceiverActivity.class);
        startActivity(intent);
    }
    public void RestaurantLoginButton(View view){
        Intent intent = new Intent(this, RestaurantLoginActivity.class);
        startActivity(intent);
    }

    public void UserLoginButton(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}

