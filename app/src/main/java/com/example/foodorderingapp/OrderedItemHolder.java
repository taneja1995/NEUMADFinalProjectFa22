package com.example.foodorderingapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderedItemHolder extends RecyclerView.ViewHolder{

    public TextView orderedItemName;
    public TextView orderedItemQuantity;

    public OrderedItemHolder(@NonNull View itemView) {
        super(itemView);
        this.orderedItemName=itemView.findViewById(R.id.orderedItemName);
        this.orderedItemQuantity=itemView.findViewById(R.id.orderedItemQuantity);
    }
}
