package com.example.foodorderingapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createNotificationChannel();
        startTracking();
    }

    public void showRestaurantList(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void signup(View view){
        Intent intent=new Intent(this,SignUpActivity.class);
        startActivity(intent);
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

    private boolean checkTime(String time){
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("EEE MM dd HH:mm:ss z yyyy");
        Date ordered = null;
        try {
            ordered = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff = currentTime.getTime() - ordered.getTime();
        long diffSeconds = diff / 1000 % 60;
        if(diffSeconds>100){
            System.out.println("aaaaa");
            return true;
        }
        else{
            System.out.println("bbbbbb");
            return false;
        }
    }

    public void getDataFirebase(){
        String username="ananth";
        FirebaseDatabase firebaseDatabase;
        DatabaseReference reference;
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("Order");
        reference.keepSynced(true);
        Map<String,Integer> orderedItemList = new HashMap<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Order order = null;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    order = new Order();
                    String user=String.valueOf(snapshot.child("orderedBy").getValue());
                    String hotelId = String.valueOf(snapshot.child("hotelId").getValue());
                    String completionStatus
                            = String.valueOf(snapshot.child("completionStatus").getValue());
                    String time = String.valueOf(snapshot.child("orderedOn").getValue());
                    DataSnapshot orderDetails = snapshot.child("orderedItems");

                    if (user.equals(username) && checkTime(time)) {
                        order.setHotelId(hotelId);
                        order.setCompletionStatus(completionStatus);
                        for(DataSnapshot od:orderDetails.getChildren()){
                            orderedItemList.put(String.valueOf(od.getKey()),orderedItemList.getOrDefault(String.valueOf(od.getKey()),0)+1);
                        }
                        Map.Entry<String, Integer> maxEntry = null;
                        for (Map.Entry<String, Integer> entry : orderedItemList.entrySet())
                        {
                            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
                            {
                                maxEntry = entry;
                            }
                        }
                        System.out.println(maxEntry.getKey());
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void sendNotification(){

        Intent intentPresent = new Intent(this, MenuListActivity.class);
        intentPresent.putExtra("my_string_data", "Hello, this is my string data!");
        PendingIntent pPresenetIntent = PendingIntent.getActivity(this, 0, intentPresent, PendingIntent.FLAG_UPDATE_CURRENT);

        String channelId = getString(R.string.channel_id);


        Notification noti = new NotificationCompat.Builder(this,channelId)

                .setContentTitle("Sticker")
                .setContentText(" sent a sticker.")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .addAction(R.drawable.gender_checked_background, "And more", pPresenetIntent).build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        noti.flags |= Notification.FLAG_AUTO_CANCEL ;
        Random r = new Random();
        notificationManager.notify(r.nextInt(100) + 1, noti);

    }

    public void startTracking(){
        Handler handler = new Handler(Looper.getMainLooper());

        final int[] count = {0};
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Check if the condition is met
                count[0]++;
                if (count[0] >5) {
                    // If the condition is met, remove the Runnable from the MessageQueue
                    handler.removeCallbacks(this);
                    return;
                }

                System.out.println("aaaaaaa");
                getDataFirebase();
                // Generate the notification here
//                sendNotification();
                // Post the Runnable again after 10 seconds
                handler.postDelayed(this, 1000);
            }
        };

// Post the Runnable for the first time
        handler.post(runnable);

    }
}
