package com.example.foodorderingapp;

import android.app.Application;

import java.util.HashMap;

public class MyApplication extends Application {
        public String UserName;
        public String UserId;
        public String RestaurantLatitude,RestaurantLongitude;
        public String RestaurantName;
        public static String OrderDetails;
        public HashMap<String,Integer> OrderD;
        public  static double TotalPrice;

    public HashMap<String, Integer> getOrderD() {
        return OrderD;
    }

    public void setOrderD(HashMap<String, Integer> orderD) {
        OrderD = orderD;
    }

    public  double getTotalPrice() {
        return TotalPrice;
    }

    public static void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getOrderDetails() {
        return OrderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        OrderDetails = orderDetails;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }

    public String getRestaurantLatitude() {
        return RestaurantLatitude;
    }

    public void setRestaurantLatitude(String restaurantLatitude) {
        RestaurantLatitude = restaurantLatitude;
    }

    public String getRestaurantLongitude() {
        return RestaurantLongitude;
    }

    public void setRestaurantLongitude(String restaurantLongitude) {
        RestaurantLongitude = restaurantLongitude;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

}
