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
}
