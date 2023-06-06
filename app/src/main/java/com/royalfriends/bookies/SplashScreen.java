package com.royalfriends.bookies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_SCREEN_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashScreen.this,
                    LoginActivity.class);
            //Intent is used to switch from one activity to another.
            startActivity(i);
            //invoke the SecondActivity.
            finish();
            //the current activity will get finished.
        }, SPLASH_SCREEN_TIME_OUT);
    }
}