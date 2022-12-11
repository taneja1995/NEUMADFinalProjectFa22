package com.example.foodorderingapp;

import android.app.Application;

public class MyApplication extends Application {
        public String UserName;
        public String UserId;
        public String RestaurantLatitude,RestaurantLongitude;
        public String RestaurantName;

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
