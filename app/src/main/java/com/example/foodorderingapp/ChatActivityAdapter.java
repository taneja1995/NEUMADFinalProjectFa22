package com.example.foodorderingapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatActivityAdapter extends RecyclerView.Adapter{

    private Context context;
    private List<Message> messageList;


    public ChatActivityAdapter(Context context, List<Message> messageList){

        this.context=context;
        this.messageList=messageList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class SentMessageHolder extends RecyclerView.ViewHolder {

        public TextView sentMessage;

        public SentMessageHolder(@NonNull View itemView) {
            super(itemView);
            this.sentMessage = itemView.findViewById(R.id.send_message_tv);
        }
    }

    public class ReceivedMessageHolder extends RecyclerView.ViewHolder {

        public TextView receivedMessage;


        public ReceivedMessageHolder(@NonNull View itemView) {
            super(itemView);
            this.receivedMessage = itemView.findViewById(R.id.receive_message_tv);

        }
    }
}
