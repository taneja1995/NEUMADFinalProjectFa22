package com.example.foodorderingapp;

import java.util.Date;
import java.util.List;

public class Order {

    private String completionStatus;
    private String hotelId;
    private List<OrderedItem> orderedItems;
    private String orderedOn;
    private String orderedBy;

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    private String totalCost;

    public String getOrderedBy() {
        return orderedBy;
    }
    public void setOrderedBy(String orderedBy) {
        this.orderedBy = orderedBy;
    }
    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public List<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }
    public String getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(String completionStatus) {
        this.completionStatus = completionStatus;
    }
    public String getOrderedOn() {
        return orderedOn;
    }
    public void setOrderedOn(String orderedOn) {
        this.orderedOn = orderedOn;
    }

}
