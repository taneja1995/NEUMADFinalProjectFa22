package com.example.foodorderingapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class MenuListActivity extends AppCompatActivity {

    TextView noOfItem;
    private List<OrderedItem> menuLists = new ArrayList<OrderedItem>();

    OrderedItem orderedItem;
    int count=0;
    RecyclerView menuListRV;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    FirebaseStorage firebaseStorage;
    MenuListAdapter menuListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodmenu);

        noOfItem= (TextView) findViewById(R.id.noOfItem);
        //this.menuLists.add(new OrderedItem("rice",""));
       // this.menuList.add(new OrderedItem("roti",""));
        //this.menuList.add(new OrderedItem("bread",""));


        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("Restaurant");
        firebaseStorage = FirebaseStorage.getInstance();
        reference.keepSynced(true);
        menuListAdapter = new MenuListAdapter( menuLists,this);

        addDataItem();
        DisplayRecyclerView();
    }

    private void DisplayRecyclerView() {
        menuListRV = findViewById(R.id.menuListRV);
        menuListRV.setLayoutManager(new LinearLayoutManager(this));
        menuListRV.setAdapter(menuListAdapter);
    }

    private void addDataItem() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                menuLists.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //OrderedItem = new OrderedItem();
                    DataSnapshot menuList = snapshot.child("MenuList");
                    String ResName=snapshot.getKey();
                    System.out.println("----------------"+ResName);
                    if(ResName.equals("Chipotle")){

                    for(DataSnapshot menu:menuList.getChildren()) {
                        OrderedItem orderedItem=new OrderedItem();
                        orderedItem.setMenuImage(menu.child("Image").getValue().toString());
                        orderedItem.setOrderedItemName(menu.getKey());
                        orderedItem.setPrice(menu.child("Price").getValue().toString());
                        System.out.println("----------------" + menu.getKey() + "--" + menu.child("Price").getValue().toString());
                        menuLists.add(orderedItem);
                        menuListAdapter.notifyDataSetChanged();
                    }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
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