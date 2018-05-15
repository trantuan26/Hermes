package com.nhattuan.hermes;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        if (getIntent().getExtras() != null) {
            new SessionManager(this)
                    .setMESSAGE(
                            new ObjectMessage(getIntent().getExtras().getString("FullMessage"),
                                    getIntent().getExtras().getString("linkURL"),
                                    1)
                    );
        }

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(ScreenActivity.this,MainActivity.class);
                startActivity(mainIntent);
                ScreenActivity.this.finish();
                //overridePendingTransition(0,0);
            }
        }, 2000);
    }
}
