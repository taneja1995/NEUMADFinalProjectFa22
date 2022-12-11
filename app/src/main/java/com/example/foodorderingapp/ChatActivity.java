package com.example.foodorderingapp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView chatsRV;
    private ChatActivityAdapter chatActivityAdapter;
    List<Message> messageList= new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    FirebaseStorage firebaseStorage;
    Message message= new Message();
    Button sendMessBtn;
    EditText inputMess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("Message");
        firebaseStorage = FirebaseStorage.getInstance();
        reference.keepSynced(true);
        chatActivityAdapter = new ChatActivityAdapter( messageList,this);
        sendMessBtn= findViewById(R.id.send_btn);
        inputMess= findViewById(R.id.input_message);

        sendMessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFirebase();
            }
        });
        addDataItem();
        displayRecyclerView();
    }

    private void displayRecyclerView() {
        chatsRV = findViewById(R.id.chatScreenRV);
        chatsRV.setLayoutManager(new LinearLayoutManager(this));
        chatsRV.setAdapter(chatActivityAdapter);
    }

    private void addToFirebase(){
        reference = FirebaseDatabase.getInstance().getReference().child("Message");
        Date currentTime = Calendar.getInstance().getTime();
        String id = reference.push().getKey();
        message.setSentOn(currentTime.toString());
        message.setMessage(inputMess.getText().toString());
        message.setUserType("Sender");
        System.out.println(" the message after adding to firebase is "+message.getUserType());
        // get ordered items from menu list selected.
        reference.child(id).setValue(message);
    }

    private void addDataItem() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Message message= null;
                messageList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    message= new Message();
                    //String orderName=snapshot.getKey();
                    String messg= String.valueOf(snapshot.child("message").getValue());
                    String userType= String.valueOf(snapshot.child("userType").getValue());
                    message.setMessage(messg);
                    message.setUserType(userType);
                    messageList.add(message);
                    System.out.println(" the message type is display firebase "+ message.getUserType());
                    chatActivityAdapter.notifyDataSetChanged();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
