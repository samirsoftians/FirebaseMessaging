package com.example.twtech.firebasemessaging.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {

    public static final String SHARED_PREF_NAME = "sell";
    public static final String Password = "password";
    public static final String Email = "email";
    public static final String MOBILE = "contact";
    public static final String OTP = "otp";
    public static final String TOKEN = "token";


    private static SharedPreference mInstance;
    private static Context mCtx;

    public SharedPreference(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPreference getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPreference(context);
        }
        return mInstance;
    }


    public void update(String KEY,String VALUE) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY, VALUE);


        editor.apply();
    }

    public boolean isLoggedIn(String key) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null) != null;
    }


    public String getdata(String key) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return  sharedPreferences.getString(key, null);
    }


    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
/*
Preferences.getInstance(getApplicationContext()).update(Preferences.Email,email);
*/
    }

}
