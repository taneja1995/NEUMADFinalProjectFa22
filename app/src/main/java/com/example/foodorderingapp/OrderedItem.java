package com.example.foodorderingapp;

public class OrderedItem {
    private String orderedItemName;
    private String orderedItemQuantity;

    public OrderedItem(String orderedItemName,String orderedItemQuantity){
        this.orderedItemName=orderedItemName;
        this.orderedItemQuantity=orderedItemQuantity;
    }
    public String getLinkText(){
        return orderedItemName;
    }
    public String getLinkURL(){
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
