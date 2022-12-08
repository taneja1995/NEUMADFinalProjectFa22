package com.example.foodorderingapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyOrdersListAdapter extends RecyclerView.Adapter<MyOrdersListAdapter.MyOrdersListHolder>{

    private final List<Order> ordersList;
    private final Context context;

    public MyOrdersListAdapter(List<Order> ordersList, Context context) {
        this.ordersList=ordersList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyOrdersListAdapter.MyOrdersListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrdersListAdapter.MyOrdersListHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyOrdersListHolder extends RecyclerView.ViewHolder {

        public MyOrdersListHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
