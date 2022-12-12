package com.example.foodorderingapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class OrderAmount extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    DatabaseReference reference2;
    FirebaseStorage firebaseStorage;
    DatabaseReference firebaseDbRef;
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    Order order=new Order();
    com.example.foodorderingapp.MyApplication myApplication;
    Double totalCost=0.0;
    TextView itemSubtotal;
    TextView orderTotal, deliveryFee, tax;
    private String loggedInUser;

    private void addToFirebase(){
        firebaseDbRef = FirebaseDatabase.getInstance().getReference().child("Order");
        Date currentTime = Calendar.getInstance().getTime();
        String id = firebaseDbRef.push().getKey();
        loggedInUser = ((MyApplication) this.getApplication()).getUserName();
        createNotificationChannel();

        order.setOrderedOn(currentTime.toString());
        order.setOrderedBy( ((MyApplication) this.getApplication()).getUserName());
        order.setHotelId(((MyApplication) this.getApplication()).getRestaurantName());
        order.setCompletionStatus("In Progress");
        totalCost= (((MyApplication) this.getApplication()).getSubTotal()+10+2);
        order.setTotalCost(decimalFormat.format(totalCost));
        StringBuilder orderedItems=new StringBuilder();
        Map<String,String> map = ((MyApplication) this.getApplication()).getOrderD();
        for(String key :map.keySet()){
            orderedItems.append(key);
            orderedItems.append(";");
            orderedItems.append(map.get(key));
            orderedItems.append("/");
        }

        orderedItems.deleteCharAt(orderedItems.length()-1);
        order.setOrderedItems(orderedItems.toString());
        firebaseDbRef.child(id).setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()) {
                    sendNotification();
                }
            }
        });

        System.out.println("order NG"+order.getOrderedBy());
        System.out.println("order NG"+order.getOrderNo());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderamount);
        itemSubtotal=findViewById(R.id.subtotalVal_tv);
        orderTotal=findViewById(R.id.totalVal_tv);
        deliveryFee=findViewById(R.id.delFeeVal_tv);
        tax=findViewById(R.id.taxVal_tv);
        Double subTot= ((MyApplication) this.getApplication()).getSubTotal();
        decimalFormat.format(subTot);
        System.out.println(" the subtotal is "+ subTot);

        if(subTot==0.0){
            itemSubtotal.setText(String.valueOf(subTot));
            deliveryFee.setText(String.valueOf(0));
            tax.setText(String.valueOf(0));
            orderTotal.setText(String.valueOf(0));
        }
        else{
            itemSubtotal.setText(String.valueOf(subTot));
            orderTotal.setText(String.valueOf(decimalFormat.format(subTot+10.00+2.00)));
        }
    }

    public void confirmCheckoutBtn(View view){
        addToFirebase();
        Intent intent = new Intent(this, MyOrdersListActivity.class);
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
