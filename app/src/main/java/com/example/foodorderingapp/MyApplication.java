package com.example.foodorderingapp;

import android.app.Application;

import java.util.HashMap;

public class MyApplication extends Application {
        public static String UserName;
        public String UserId;
        public String RestaurantLatitude,RestaurantLongitude;

        public static String RestaurantName;

    public String getMostFrequentRestaurantName() {
        return mostFrequentRestaurantName;
    }

    public void setMostFrequentRestaurantName(String mostFrequentRestaurantName) {
        this.mostFrequentRestaurantName = mostFrequentRestaurantName;
    }

    public String getCurrentLoggedInRestaurantName() {
        return CurrentLoggedInRestaurantName;
    }

    public void setCurrentLoggedInRestaurantName(String currentLoggedInRestaurantName) {
        CurrentLoggedInRestaurantName = currentLoggedInRestaurantName;
    }

    public static String CurrentLoggedInRestaurantName;

    // to find which restaurant has logged in - not the one you are
    //selecting via restaurant list.




        public static String OrderDetails;
        public static HashMap<String,String> OrderD;
        public  static double subTotal;

    public String getCurrentOrderId() {
        return currentOrderId;
    }

    public void setCurrentOrderId(String currentOrderId) {
        this.currentOrderId = currentOrderId;
    }

    public String currentOrderId;

    public String mostFrequentRestaurantName;

    public void setmostFrequentRestaurantName(String name) {
        mostFrequentRestaurantName = name;
    }

    public String setmostFrequentRestaurantName() {
        return mostFrequentRestaurantName;
    }

    public HashMap<String, String> getOrderD() {
        return OrderD;
    }

    public void setOrderD(HashMap<String, String> orderD) {
        OrderD = orderD;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public static void setSubTotal(double subTotal) {
        subTotal = subTotal;
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
