package com.example.foodorderingapp;

import java.util.Date;
import java.util.List;

public class Order {

    private String completionStatus;
    private String hotelId;
    private List<OrderedItem> orderedItems;
    private Date orderedOn;
    private String orderedBy;

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
    public Date getOrderedOn() {
        return orderedOn;
    }
    public void setOrderedOn(Date orderedOn) {
        this.orderedOn = orderedOn;
    }

}
