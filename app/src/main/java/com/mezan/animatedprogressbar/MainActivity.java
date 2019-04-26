package com.mezan.animatedprogressbar;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders;

public class MainActivity extends AppCompatActivity {

    CircularFillableLoaders CFL;
    Button cBtn;
    TextView txtStatus;
    private int pStatus = 0;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CFL = (CircularFillableLoaders) findViewById(R.id.cfProgress);
        cBtn = (Button) findViewById(R.id.sCFL);
        txtStatus = (TextView)findViewById(R.id.status);
        CFL.setProgress(0);
        cBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (pStatus<100){
                            pStatus += 1;

                            try{
                                Thread.sleep(50);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    CFL.setProgress(pStatus);
                                    txtStatus.setText("Current Status : "+pStatus);
                                }
                            });
                        }
                    }
                }).start(); //start the operation
            }
        });
    }
}
