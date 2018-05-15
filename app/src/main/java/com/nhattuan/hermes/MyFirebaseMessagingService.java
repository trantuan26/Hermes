package com.nhattuan.hermes;

import android.app.Service;
import android.content.Intent;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import org.json.JSONObject;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private SessionManager sessionManager;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        // Log.d(TAG, "From: " + remoteMessage.getFrom());
        sessionManager = new SessionManager(getApplicationContext());
        if (remoteMessage.getData().size() > 0) {
            JSONObject jsonObject = new JSONObject(remoteMessage.getData());
            ObjectMessage message = new Gson().fromJson(jsonObject.toString(), ObjectMessage.class);
            if (message != null){
                message.setIsOpen(1);
                sessionManager.setMESSAGE(message);
                saveMessage(message);
            }
        }
    }


    private void saveMessage(ObjectMessage message) {
            Intent intent = new Intent(this, NotificationActivity.class);
            intent.putExtra("FullMessage", message.getFullMessage());
            intent.putExtra("linkURL", message.getLinkURL());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
    }
}
