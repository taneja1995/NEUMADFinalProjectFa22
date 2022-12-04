package com.example.foodorderingapp;

public class OrderedItem {
    private String orderedItemName;
    private String orderedItemQuantity;

    public OrderedItem(String orderedItemName,String orderedItemQuantity){
        this.orderedItemName=orderedItemName;
        this.orderedItemQuantity=orderedItemQuantity;
    }

    public OrderedItem() {

    }

    public void setOrderedItemName(String orderedItemName) {
        this.orderedItemName = orderedItemName;
    }

    public void setOrderedItemQuantity(String orderedItemQuantity) {
        this.orderedItemQuantity = orderedItemQuantity;
    }

    public String getOrderedItemName(){
        return orderedItemName;
    }
    public String getOrderedItemQuantity(){
        return orderedItemQuantity;
    }

    public String getOrderedItemName() {
        return orderedItemName;
    }

    public void setOrderedItemName(String orderedItemName) {
        this.orderedItemName = orderedItemName;
    }

    public String getOrderedItemQuantity() {
        return orderedItemQuantity;
    }

    public void setOrderedItemQuantity(String orderedItemQuantity) {
        this.orderedItemQuantity = orderedItemQuantity;
    }
}
