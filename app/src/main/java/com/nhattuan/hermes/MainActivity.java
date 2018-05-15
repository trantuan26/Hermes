package com.nhattuan.hermes;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionManager = new SessionManager(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectMessage message = sessionManager.getMESSAGE();
                if( message != null){
                    if(message.getIsOpen() == 1){
                        Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                        intent.putExtra("FullMessage", message.getFullMessage());
                        intent.putExtra("linkURL", message.getLinkURL());
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }
            }
        }, 1000);
    }
}
