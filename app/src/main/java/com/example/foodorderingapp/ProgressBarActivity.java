package com.example.foodorderingapp;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProgressBarActivity extends AppCompatActivity {

    int progress=0;
    String orderStatus=null;
    private ProgressBar progressBar;
    private Button btn;
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);
        progressBar= findViewById(R.id.progressBar);
        btn= findViewById(R.id.checkStatus_btn);
        status=findViewById(R.id.status_tv);
        Bundle extras= getIntent().getExtras();
        if(extras!=null){
            orderStatus = extras.getString("status");
        }
        // to show the progress bar status for the order based on the status.
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(orderStatus.equals("Completed")){
                    progress=100;
                    progressBar.setProgress(progress);
                    status.setText("Yipeee!! Your order is prepared! Delicious food is on the way!");
                    status.setTextColor(Color.BLACK);
                }else{
                    progress=50;
                    progressBar.setProgress(progress);
                    status.setText("Your order is still in progress! Thanks for your patience!");
                    status.setTextColor(Color.BLACK);
                }
            }
        });
    }
}
