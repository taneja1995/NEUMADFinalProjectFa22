package com.example.foodorderingapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MenuListActivity extends AppCompatActivity {

    TextView noOfItem;
    private List<OrderedItem> menuList = new ArrayList<OrderedItem>();

    OrderedItem orderedItem;
    int count=0;
    RecyclerView menuListRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodmenu);

        noOfItem= (TextView) findViewById(R.id.noOfItem);
        this.menuList.add(new OrderedItem("rice",""));
        this.menuList.add(new OrderedItem("roti",""));
        this.menuList.add(new OrderedItem("bread",""));
        menuListRV = findViewById(R.id.menuListRV);

        menuListRV.setLayoutManager(new LinearLayoutManager(this));

        menuListRV.setAdapter(new MenuListAdapter(menuList, this));
    }

   /* public void increment(View v){
        count++;
        noOfItem.setText(""+count);
        orderedItem.setOrderedItemQuantity(String.valueOf(count));

    }

    public void decrement(View v){
        if(count<0){ count=0;}
        else{ count--;}
        noOfItem.setText(""+count);
        orderedItem.setOrderedItemQuantity(String.valueOf(count));
    }*/
}