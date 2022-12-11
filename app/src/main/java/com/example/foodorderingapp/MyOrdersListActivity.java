package com.example.foodorderingapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
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

public class MyOrdersListActivity extends AppCompatActivity {

    private RecyclerView myOrdersRV;
    private MyOrdersListAdapter ordersListAdapter;
    com.example.foodorderingapp.MyApplication myApplication;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    FirebaseStorage firebaseStorage;
    List<Order> orderList= new ArrayList<>();
    String loggedInUser;
    String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorders);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("Order");
        firebaseStorage = FirebaseStorage.getInstance();
        loggedInUser =  ((MyApplication) this.getApplication()).getUserName();
        orderId = ((MyApplication) this.getApplication()).getCurrentOrderId();
        reference.keepSynced(true);
        ordersListAdapter = new MyOrdersListAdapter( orderList,this);
        addDataItem();
        displayRecyclerView();
        createNotificationChannel();
    }

    private void displayRecyclerView() {
        myOrdersRV = findViewById(R.id.myOrdersRV);
        myOrdersRV.setLayoutManager(new LinearLayoutManager(this));
        myOrdersRV.setAdapter(ordersListAdapter);
    }

    private void addDataItem() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Order order= null;
                orderList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    order= new Order();
                    String orderName=snapshot.getKey();
                    String resName= String.valueOf(snapshot.child("hotelId").getValue());
                    String cost= String.valueOf(snapshot.child("orderedItems").child("price").getValue());
                    String timestamp= String.valueOf(snapshot.child("orderedOn").getValue());
                    String orderedBy= String.valueOf(snapshot.child("orderedBy").getValue());
                    String status= String.valueOf(snapshot.child("completionStatus").getValue());
                    order.setTotalCost(cost);
                    order.setHotelId(resName);
                    order.setOrderedOn(timestamp);
                    order.setOrderNo(snapshot.getKey());
                    order.setOrderedBy(orderedBy);
                    order.setCompletionStatus(status);
                    orderList.add(order);
                    ordersListAdapter.notifyDataSetChanged();

                    System.out.println(loggedInUser);
                    System.out.println("xoxo"+orderedBy);

                    if(orderedBy.equals(loggedInUser)){
                    //&& orderName.equals(orderId)// {
                        sendNotification();
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(getString(R.string.channel_id), name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void sendNotification(){

        Intent intent = new Intent(this, ReceiveNotificationActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_IMMUTABLE);

        Intent intentPresent = new Intent(this, MyOrdersListActivity.class);
        PendingIntent pPresenetIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intentPresent, PendingIntent.FLAG_IMMUTABLE);

        String channelId = getString(R.string.channel_id);


        Notification noti = new NotificationCompat.Builder(this,channelId)

                .setContentTitle("Order Status")
                .setContentText("Hey " +loggedInUser +" Your order is in progess")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pIntent)
                .addAction(R.drawable.gender_checked_background, "And more", pPresenetIntent).build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        noti.flags |= Notification.FLAG_AUTO_CANCEL ;

        notificationManager.notify(0, noti);

    }







}
