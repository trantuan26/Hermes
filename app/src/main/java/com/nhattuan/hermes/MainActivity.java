package com.nhattuan.hermes;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (getIntent() != null) {
            String  fullname = getIntent().getStringExtra("FullMessage");
            String urllink = getIntent().getStringExtra("linkURL");
            if (!TextUtils.isEmpty(fullname) && !TextUtils.isEmpty(urllink)) {
                final String finalFullname = fullname;
                final String finalUrllink = urllink;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                        intent.putExtra("FullMessage", finalFullname);
                        intent.putExtra("linkURL", finalUrllink);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }, 1000);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
