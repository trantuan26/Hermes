package com.nhattuan.hermes;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {
    private TextView FullMessage;
    private TextView linkURL;
    private Button btnOK;
    private SessionManager sessionManager;

    private String fullmesage;
    private String linkurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        sessionManager = new SessionManager(this);
        FullMessage = findViewById(R.id.FullMessage);
        linkURL = findViewById(R.id.linkURL);
        btnOK = findViewById(R.id.loveit);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getIntent() != null) {
            fullmesage = getIntent().getStringExtra("FullMessage") != null ? getIntent().getStringExtra("FullMessage") : "";
            linkurl = getIntent().getStringExtra("linkURL") != null ? getIntent().getStringExtra("linkURL") : "";
            FullMessage.setText(fullmesage);
            final String htmlString="<u><b>"+linkurl+"</b></u>";
            linkURL.setText(Html.fromHtml(htmlString));
            if (htmlString.toLowerCase().contains("http://") || htmlString.toLowerCase().contains("https://") ) {
                linkURL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkurl));
                        startActivity(browserIntent);
                    }
                });
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        ObjectMessage objectMessage = new SessionManager(this).getMESSAGE();
        objectMessage.setIsOpen(0);
        new SessionManager(this).setMESSAGE(objectMessage);
        super.onDestroy();
    }
}
