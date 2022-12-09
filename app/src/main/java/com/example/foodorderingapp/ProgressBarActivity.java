package com.example.foodorderingapp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class ProgressBarActivity extends AppCompatActivity {

    int progress=0;
    String orderStatus=null;
    private ProgressBar progressBar;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);
        progressBar= findViewById(R.id.progressBar);
        btn= findViewById(R.id.checkStatus_btn);
        Bundle extras= getIntent().getExtras();
        if(extras!=null){
            orderStatus = extras.getString("status");
            System.out.println("the order status is"+orderStatus);
        }
        // to show the progress bar status for the order based on the status.
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("inside the progress bar method");

                if(orderStatus.equals("Completed")){
                    progress=100;
                    progressBar.setProgress(progress);
                }else{
                    progress=50;
                    progressBar.setProgress(progress);
                }
            }
        });
    }
}
