package com.example.foodorderingapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.MenuListHolder>{
    private final List<OrderedItem> orderedItems;
    private final Context context;
    int count=0;
    Activity activity;

    public MenuListAdapter(List<OrderedItem> orderedItems, Context context, Activity activity) {
        this.orderedItems = orderedItems;
        this.context = context;
        this.activity=activity;
    }


    public MenuListAdapter(List<OrderedItem> orderedItems, Context context) {
        this.orderedItems = orderedItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MenuListAdapter.MenuListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_menu, parent, false);
        return new MenuListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuListAdapter.MenuListHolder holder, int position) {
        holder.orderedItemName.setText(orderedItems.get(position).getLinkText());
       // holder.orderedItemQuantity.setText(String.valueOf(orderedItems.get(position).getOrderedItemQuantity()));
        holder.addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                holder.orderedItemQuantity.setText(""+count);
            }
        });

        holder.removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count<0){count=0;}
                else {count--;}
                holder.orderedItemQuantity.setText(""+count);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderedItems.size();
    }

    public class MenuListHolder extends RecyclerView.ViewHolder {

        public TextView orderedItemName;
        public TextView orderedItemQuantity;
        public Button addItem,removeItem;

        public MenuListHolder(@NonNull View itemView) {
            super(itemView);
            this.orderedItemName=itemView.findViewById(R.id.menuName);
            this.orderedItemQuantity=itemView.findViewById(R.id.noOfItem);
            this.addItem=itemView.findViewById(R.id.addItem);
            this.removeItem=itemView.findViewById(R.id.removeItem);
        }
    }
}
