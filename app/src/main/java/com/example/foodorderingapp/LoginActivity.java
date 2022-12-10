package com.example.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = findViewById(R.id.email);
    }

    public void showRestaurantList(View view){
        insertUserData();
        Log.i("LoginActivity", String.valueOf(userName));
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void signup(View view){
        Intent intent=new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    private void insertUserData() {
        String user=userName.getText().toString();

        ((MyApplication) this.getApplication()).setUserName(user);
         // globally set
    }
}
