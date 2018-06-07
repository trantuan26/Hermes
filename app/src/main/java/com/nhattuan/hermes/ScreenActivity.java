package com.nhattuan.hermes;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessaging;

public class ScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        final Intent mainIntent = new Intent(ScreenActivity.this, MainActivity.class);
        if (getIntent().getExtras() != null) {
            mainIntent.putExtra("FullMessage", getIntent().getExtras().getString("FullMessage"));
            mainIntent.putExtra("linkURL", getIntent().getExtras().getString("linkURL"));
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(mainIntent);
                ScreenActivity.this.finish();
                //overridePendingTransition(0,0);
            }
        }, 2000);
    }
}
