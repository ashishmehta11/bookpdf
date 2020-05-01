package com.projects.bookpdf.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.projects.bookpdf.R;
import com.projects.bookpdf.data.ObjectCollection;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectCollection.setHomePageBook(SplashScreen.this);
                Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        },1000);
    }
}
