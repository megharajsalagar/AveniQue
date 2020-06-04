package com.example.forms.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.forms.R;

public class Splash_screen extends AppCompatActivity {
    private int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SaveSharedPreference sharedPreference = new SaveSharedPreference();

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SaveSharedPreference.getUserName(Splash_screen.this).length() == 0) {
                    startActivity(new Intent(getApplicationContext(), Login_form.class));
                    finish();
                } else {
                    Intent splashIntent = new Intent(Splash_screen.this, entry.class);
                    startActivity(splashIntent);
                    finish();
                }


            }
        }, SPLASH_TIME_OUT);


    }

}
