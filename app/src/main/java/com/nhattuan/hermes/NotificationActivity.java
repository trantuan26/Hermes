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
      private String fullmesage;
    private String linkurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        FullMessage = findViewById(R.id.FullMessage);
        linkURL = findViewById(R.id.linkURL);
        btnOK = findViewById(R.id.loveit);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if (getIntent().getExtras() != null) {
            fullmesage = getIntent().getStringExtra("FullMessage");
            linkurl = getIntent().getStringExtra("linkURL");
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
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
