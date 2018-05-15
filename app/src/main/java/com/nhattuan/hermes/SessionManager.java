package com.nhattuan.hermes;

import android.content.Context;
import android.content.SharedPreferences;


import com.google.gson.Gson;

public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_PICK = "pick";
    private static final String MESSAGE = "message";


    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_PICK, Context.MODE_PRIVATE);
        editor = pref.edit();
    }



    public void setMESSAGE(ObjectMessage message) {
        editor.putString(MESSAGE, new Gson().toJson(message));
        // commit changes
        editor.commit();
    }


    public ObjectMessage getMESSAGE() {
        String json = pref.getString(MESSAGE, "");
        ObjectMessage obj =  new Gson().fromJson(json, ObjectMessage.class);
        return obj;
    }
}
