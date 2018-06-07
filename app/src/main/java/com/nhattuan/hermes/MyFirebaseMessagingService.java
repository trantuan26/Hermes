package com.nhattuan.hermes;

import android.app.Service;
import android.content.Intent;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        // Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            JSONObject jsonObject = new JSONObject(remoteMessage.getData());
            // ObjectMessage message = new Gson().fromJson(jsonObject.toString(), ObjectMessage.class);
            Intent intent = new Intent(this, NotificationActivity.class);
            try {
                intent.putExtra("FullMessage", jsonObject.getString("FullMessage"));
                intent.putExtra("linkURL", jsonObject.getString("linkURL"));
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //sendNotification("Goal Card", remoteMessage.getNotification().getBody(), "NOTIFICATION");
        }
    }

}
