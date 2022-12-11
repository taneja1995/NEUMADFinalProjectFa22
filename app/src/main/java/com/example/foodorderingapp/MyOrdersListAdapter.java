package com.example.foodorderingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MyOrdersListAdapter extends RecyclerView.Adapter<MyOrdersListAdapter.MyOrdersListHolder>{

    private final List<Order> ordersList;
    private final Context context;
    DatabaseReference firebaseDbRef;

    public MyOrdersListAdapter(List<Order> ordersList, Context context) {
        this.ordersList=ordersList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyOrdersListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new
                MyOrdersListHolder(LayoutInflater.from(context).inflate(R.layout.activity_single_user_order, null));

    }

    @Override
    public void onBindViewHolder(@NonNull MyOrdersListHolder holder, int position) {
        firebaseDbRef = FirebaseDatabase.getInstance().getReference().child("Order");
        String orderId = ordersList.get(position).getOrderNo();
        Order order= ordersList.get(position);
        holder.hotelName.setText(ordersList.get(position).getHotelId());
        holder.orderTime.setText(ordersList.get(position).getOrderedOn());
        holder.orderCost.setText(ordersList.get(position).getTotalCost());
        String status= ordersList.get(position).getCompletionStatus();
        holder.itemView.findViewById(R.id.statusBtn).setOnClickListener(view ->{
            Intent intent = new Intent(view.getContext(), ProgressBarActivity.class);
            intent.putExtra("orderId",orderId);
            //System.out.println("the status in holder is "+ order);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    public class MyOrdersListHolder extends RecyclerView.ViewHolder {

        public TextView hotelName;
        public TextView orderCost, orderTime;
        public Button getOrderStatus;

        public MyOrdersListHolder(@NonNull View itemView) {
            super(itemView);
            this.hotelName=itemView.findViewById(R.id.restName_tv);
            this.orderCost=itemView.findViewById(R.id.orderCost_tv);
            this.orderTime=itemView.findViewById(R.id.date_tv);
            this.getOrderStatus=itemView.findViewById(R.id.statusBtn);

        }
    }
}
