package com.example.foodorderingapp;

public class FoodItems {
    public String foodItemName;
    public static String foodItemQuantity;
    public String foodImage;
    public String foodPrice;
    public String latitude,longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public FoodItems() {
    }

    public String getFoodItemName() {
        return foodItemName;
    }

    public void setFoodItemName(String foodItemName) {
        this.foodItemName = foodItemName;
    }

    public String getFoodItemQuantity() {
        return foodItemQuantity;
    }

    public void setFoodItemQuantity(String foodItemQuantity) {
        this.foodItemQuantity = foodItemQuantity;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }
}
