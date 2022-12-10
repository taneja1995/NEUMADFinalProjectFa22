package com.example.foodorderingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantListHolder>{
    private final List<RestaurantDetail> restaurantDetailList;

    private final Context context;



    public RestaurantListAdapter(List<RestaurantDetail> restaurantDetailList, Context context) {
        this.restaurantDetailList = restaurantDetailList;
        this.context = context;
    }

    @NonNull
    @Override
    public RestaurantListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new RestaurantListHolder(LayoutInflater.from(context).inflate(R.layout.restaurantdetail, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantListHolder holder, @SuppressLint("RecyclerView") int position) {

        RestaurantDetail restaurantDetail =  restaurantDetailList.get(position);
        System.out.println("//////////"+restaurantDetailList.get(position).getName());
        holder.restaurantName.setText(restaurantDetailList.get(position).getName());

        String imageUrl = null;

        imageUrl = restaurantDetail.getImage();
        Picasso.get().load(imageUrl).into(holder.thumbImage);

    }

    @Override
    public int getItemCount() {
        return  restaurantDetailList.size();
    }


    public class RestaurantListHolder extends RecyclerView.ViewHolder {

        TextView  restaurantName;
        ImageView thumbImage;

        public RestaurantListHolder(View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            thumbImage = itemView.findViewById(R.id.RestaurantImage);

        }
    }
}
