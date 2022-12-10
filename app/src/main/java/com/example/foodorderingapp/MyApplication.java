package com.example.foodorderingapp;

import android.app.Application;

public class MyApplication extends Application {

        public String UserName;
        public String UserId;

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

