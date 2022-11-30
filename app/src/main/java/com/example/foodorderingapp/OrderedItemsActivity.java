package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class OrderedItemsActivity extends AppCompatActivity {

    private List<OrderedItem> linkList = new ArrayList<OrderedItem>();

    RecyclerView orderedItemsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_items);

        this.linkList.add(new OrderedItem("rice","2"));
        this.linkList.add(new OrderedItem("roti","5"));
        this.linkList.add(new OrderedItem("bread","7"));
        orderedItemsRecyclerView = findViewById(R.id.orderedItemsRecyclerView);

        //This defines the way in which the RecyclerView is oriented
        orderedItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Associates the adapter with the RecyclerView
        orderedItemsRecyclerView.setAdapter(new OrderedItemAdapter(linkList, this));
    }
}