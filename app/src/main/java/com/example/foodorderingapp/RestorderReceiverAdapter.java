package com.example.foodorderingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class RestorderReceiverAdapter extends RecyclerView.Adapter<RestorderReceiverAdapter.RestOrderReceiverHolder>{


    private final List<Order> restaurantOrdersList;

    private final Context context;


    public RestorderReceiverAdapter(List<Order> restaurantOrdersList, Context context) {
        this.restaurantOrdersList = restaurantOrdersList;
        this.context = context;
    }


    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public RestOrderReceiverHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RestorderReceiverAdapter.RestOrderReceiverHolder(LayoutInflater.from(context).inflate(R.layout.singleorderreceiver, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RestorderReceiverAdapter.RestOrderReceiverHolder holder, @SuppressLint("RecyclerView") int position) {
        String orderNumber = restaurantOrdersList.get(position).getOrderNo();
        holder.order_no.setText(orderNumber);
       holder.ordered_by_name.setText(restaurantOrdersList.get(position).getOrderedBy());
       holder.date_of_order.setText(restaurantOrdersList.get(position).getOrderedOn());
        holder.itemView.findViewById(R.id.routeIt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), OrderedItemsActivity.class);
                intent.putExtra("orderNumber",orderNumber);
                //System.out.println("the status in holder is "+ order);
                System.out.println("------oops----------"+orderNumber);

                context.startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return  restaurantOrdersList.size();
    }


    public class RestOrderReceiverHolder extends RecyclerView.ViewHolder {

        TextView time_of_order;
        TextView date_of_order;
        TextView order_no;
        TextView ordered_by_name;

        public RestOrderReceiverHolder(@NonNull View itemView) {
            super(itemView);
            this.time_of_order = itemView.findViewById(R.id.time_of_order);
            this.date_of_order = itemView.findViewById(R.id.date_of_order);
            this.order_no = itemView.findViewById(R.id.order_no);
            this.ordered_by_name = itemView.findViewById(R.id.ordered_by_name);
        }
    }
}
