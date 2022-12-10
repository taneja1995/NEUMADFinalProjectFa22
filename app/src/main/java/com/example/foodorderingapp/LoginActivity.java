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

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;


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
        // Create a new Handler object that runs on the main thread
        Handler handler = new Handler(Looper.getMainLooper());

// Create a Runnable that generates the notification
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

                // Generate the notification here
                sendNotification();
                // Post the Runnable again after 10 seconds
                handler.postDelayed(this, 1000);
            }
        };

// Post the Runnable for the first time
        handler.post(runnable);

    }
}
