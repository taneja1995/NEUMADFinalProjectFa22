package com.example.foodorderingapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.MenuListHolder>{
    private final List<OrderedItem> orderedItems;
    private final Context context;
    int count=0;



    public MenuListAdapter(List<OrderedItem> orderedItems, Context context) {
        this.orderedItems = orderedItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MenuListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // View view = LayoutInflater.from(context).inflate(R.layout.single_menu, parent, false);
       // return new MenuListHolder(view);

        return new MenuListHolder(LayoutInflater.from(context).inflate(R.layout.single_menu, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MenuListHolder holder, @SuppressLint("RecyclerView") int position) {
        OrderedItem orderedItem = orderedItems.get(position);
        System.out.println("//////////"+orderedItems.get(position).getOrderedItemName());
        holder.orderedItemName.setText(orderedItems.get(position).getOrderedItemName());
        holder.price.setText(orderedItems.get(position).getPrice());
        holder.orderedItemQuantity.setText("0");
        String imageUrl = null;

        imageUrl = orderedItem.getMenuImage();
        Picasso.get().load(imageUrl).into(holder.foodImage);
        holder.addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ItemCount=0;
                count=0;
                ItemCount=Integer.parseInt(holder.orderedItemQuantity.getText().toString());
                count=ItemCount+1;
                holder.orderedItemQuantity.setText(""+count);
            }
        });

        holder.removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ItemCount=0;
                count=0;
                ItemCount=Integer.parseInt(holder.orderedItemQuantity.getText().toString());
                if(count<0){count=0;}
                else {count=ItemCount-1;}
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
        public TextView orderedItemQuantity,price;
        public Button addItem,removeItem;
        public ImageView foodImage;

        public MenuListHolder(@NonNull View itemView) {
            super(itemView);
            this.orderedItemName=itemView.findViewById(R.id.menuName);
            this.orderedItemQuantity=itemView.findViewById(R.id.noOfItem);
            this.addItem=itemView.findViewById(R.id.addItem);
            this.removeItem=itemView.findViewById(R.id.removeItem);
            this.foodImage=itemView.findViewById(R.id.foodImage);
            this.price=itemView.findViewById(R.id.menuPrice);
        }
    }
}
