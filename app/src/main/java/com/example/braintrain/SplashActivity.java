package com.example.braintrain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread thread=new Thread(){
            public void run(){
                try{
                    sleep(3000);

                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch(Exception e){
                    Toast.makeText(SplashActivity.this, "Problem in Thread", Toast.LENGTH_SHORT).show();
                }
            }
        };
        thread.start();
    }
}
