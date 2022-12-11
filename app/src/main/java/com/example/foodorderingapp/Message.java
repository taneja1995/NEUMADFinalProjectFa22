package com.example.foodorderingapp;

public class Message {

    private String message;
    private String sentOn;
    private String sender;
    private String receiver;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentOn() {
        return sentOn;
    }

    public void setSentOn(String sentOn) {
        this.sentOn = sentOn;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String username) {
        this.sender = sender;
    }
}
