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

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.MenuListHolder>{
    private final List<FoodItems> foodItems;
    private final Context context;
    int count=0;
    String orderDetails;
   // MyApplication myApplication=new MyApplication();
    Double totalPrice=0.0;
    HashMap<String, String> orderDe=new HashMap<>();

    public MenuListAdapter(List<FoodItems> foodItems, Context context) {
        this.foodItems = foodItems;
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
        FoodItems orderedItem = foodItems.get(position);

        holder.orderedItemName.setText(foodItems.get(position).getFoodItemName());
        holder.price.setText(foodItems.get(position).getFoodPrice());
        holder.orderedItemQuantity.setText("0");
        String imageUrl = null;
        imageUrl = orderedItem.getFoodImage();
        Picasso.get().load(imageUrl).into(holder.foodImage);

        holder.addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ItemCount=0;
                count=0;
                ItemCount=Integer.parseInt(holder.orderedItemQuantity.getText().toString());
                count=ItemCount+1;
                holder.orderedItemQuantity.setText(""+count);
                orderedItem.setFoodItemQuantity(String.valueOf(count));
                System.out.println("///"+count);
                System.out.println("........."+orderedItem.getFoodItemQuantity());
                if(orderDetails==null)
                {
                    orderDetails=orderedItem.getFoodItemName()+";"
                            +orderedItem.getFoodItemQuantity()+";"+
                            orderedItem.getFoodPrice();
                }
                else {
                    orderDetails=orderDetails.concat(";"+orderedItem.getFoodItemName()+";"
                            +orderedItem.getFoodItemQuantity()+";"+
                            orderedItem.getFoodPrice());
                }

                orderDe.put(orderedItem.getFoodItemName(),orderedItem.getFoodItemQuantity()+";"+orderedItem.getFoodPrice());
                totalPrice= totalPrice+Double.parseDouble(orderedItem.getFoodPrice());

                MyApplication.OrderD=orderDe;
                MyApplication.OrderDetails=orderDetails;
                MyApplication.TotalPrice=totalPrice;


            }

        });

        holder.removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ItemCount=0;

                count=0;
                ItemCount=Integer.parseInt(holder.orderedItemQuantity.getText().toString());
                count=ItemCount;
                if(count<=0){
                    holder.orderedItemQuantity.setText("0");
                    orderedItem.setFoodItemQuantity(""+count);
                    System.out.println("........."+orderedItem.getFoodItemQuantity());

                }
                else {count=ItemCount-1;
                    holder.orderedItemQuantity.setText(""+count);
                    orderedItem.setFoodItemQuantity(""+count);
                    if(orderDetails==null)
                    {
                        orderDetails=orderedItem.getFoodItemName()+";"
                                +orderedItem.getFoodItemQuantity()+";"+
                                orderedItem.getFoodPrice();
                    }
                    else {
                        orderDetails=orderDetails.concat(";"+orderedItem.getFoodItemName()+";"
                                +orderedItem.getFoodItemQuantity()+";"+
                                orderedItem.getFoodPrice());
                    }

                }
                orderDe.put(orderedItem.getFoodItemName(),orderedItem.getFoodItemQuantity()+";"+orderedItem.getFoodPrice());
                totalPrice= totalPrice-Double.parseDouble(orderedItem.getFoodPrice());
                MyApplication.OrderD=orderDe;
                MyApplication.TotalPrice=totalPrice;
                //myApplication.setOrderDetails(orderDetails);
                //myApplication.setTotalPrice(totalPrice);
            }
        });
        orderedItem.setFoodItemName(holder.orderedItemName.getText().toString());
        orderedItem.setFoodItemQuantity(holder.orderedItemQuantity.getText().toString());
        orderedItem.setFoodPrice(holder.price.getText().toString());


    }

    @Override
    public int getItemCount() {
        return foodItems.size();
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
