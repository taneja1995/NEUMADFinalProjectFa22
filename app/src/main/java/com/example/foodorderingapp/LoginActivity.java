package com.example.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    DatabaseReference userRef;
   // MyApplication myApplication;
    DatabaseReference firebaseDbRef;
    EditText userName;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName=findViewById(R.id.email);
        loginBtn=findViewById(R.id.signIn);
        userRef= FirebaseDatabase.getInstance().getReference().child("Customer").child("customerId");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  insertUserData();
                Intent intent= new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

   /* private void insertUserData() {
        String user=userName.getText().toString();
        UserData userData=new UserData(user);
        userData.setUserName(user);
        ((com.example.foodorderingapp.MyApplication) this.getApplication()).setUserName(user);
        userRef.child(user).setValue(userData);
    }*/


    public void signup(View view){
        Intent intent=new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
}
