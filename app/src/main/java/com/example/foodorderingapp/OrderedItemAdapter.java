package com.example.foodorderingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderedItemAdapter extends RecyclerView.Adapter<OrderedItemHolder>{
    private final List<OrderedItem> linksList;
    private final Context context;

    public OrderedItemAdapter(List<OrderedItem> linksList, Context context) {
        this.linksList = linksList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderedItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderedItemHolder(LayoutInflater.from(context).inflate(R.layout.ordered_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderedItemHolder holder, int position) {
        holder.orderedItemName.setText(linksList.get(position).getOrderedItemName());
        holder.orderedItemQuantity.setText(String.valueOf(linksList.get(position).getOrderedItemQuantity()));
    }

    @Override
    public int getItemCount() {
        return linksList.size();
    }
}
