package com.example.canyou;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.canyou.pojo.CurrentUser;

public class PreferenceManager {
    private static final String PREF_NAME = "MyPreferences";
    private static final String KEY_USER = "user";
    private static final String KEY_TOKEN = "token";

    private SharedPreferences sharedPreferences;

    public PreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveUser(CurrentUser currentUser) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER, currentUser.toJsonString());
        editor.apply();
    }

    public CurrentUser getUser() {
        String userJson = sharedPreferences.getString(KEY_USER, null);
        if (userJson != null) {
            return CurrentUser.fromJsonString(userJson);
        }
        return null;
    }

    public void saveToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_TOKEN, token);
        editor.apply();
    }

    public String getToken() {
        return sharedPreferences.getString(KEY_TOKEN, null);
    }


    public void clearUserAndToken() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_USER);
        editor.remove(KEY_TOKEN);
        editor.apply();
    }
}
