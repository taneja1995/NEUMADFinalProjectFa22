package com.example.foodorderingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatActivityAdapter extends RecyclerView.Adapter{

    private Context context;
    private List<Message> messageList;
    private int sender_view= 1;
    private int receiver_view=0;

    public ChatActivityAdapter(List<Message> messageList, Context context){

        this.context=context;
        this.messageList=messageList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == sender_view){
            return new
                    ChatActivityAdapter.SentMessageHolder(LayoutInflater.from(context).inflate(R.layout.activity_sender_item, null));

        }
        else{
            return new
                    ChatActivityAdapter.ReceivedMessageHolder(LayoutInflater.from(context).inflate(R.layout.activity_receiver_item, null));

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
         Message message= messageList.get(position);
        String name= ((MyApplication) context.getApplicationContext()).getUserName();
         if(message.getUsername().equals(name)){
             ((SentMessageHolder)holder).sentMessage.setText(message.getMessage());
         }
         else{
             ((ReceivedMessageHolder)holder).receivedMessage.setText(message.getMessage());
         }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public int getItemViewType(int position){

        Message message= messageList.get(position);
        String name= ((MyApplication) context.getApplicationContext()).getUserName();
        if(message.getUsername().equals(name)) {
            return sender_view;
        }
        else{
            return receiver_view;
        }
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
