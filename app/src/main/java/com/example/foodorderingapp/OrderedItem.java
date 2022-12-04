package com.example.foodorderingapp;

public class OrderedItem {
    private String orderedItemName;
    private String orderedItemQuantity;
    public String menuImage;
    public String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }

    public OrderedItem(String orderedItemName, String orderedItemQuantity){
        this.orderedItemName=orderedItemName;
        this.orderedItemQuantity=orderedItemQuantity;
    }

    public OrderedItem() {

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
