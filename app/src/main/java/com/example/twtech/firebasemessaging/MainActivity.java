package com.example.twtech.firebasemessaging;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.twtech.firebasemessaging.firebase.FirebaseInstanceIDService;
import com.example.twtech.firebasemessaging.sharedpreference.SharedPreference;
import com.example.twtech.firebasemessaging.url.Url;
import com.example.twtech.firebasemessaging.volleysingleton.VolleySingleton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    String tok="samir";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("Test");
        FirebaseInstanceId.getInstance().getToken();
       // String tok=SharedPreference.getInstance(getApplicationContext()).getdata(SharedPreference.TOKEN);
        //FirebaseInstanceIDService.sharedpreferences1 = getSharedPreferences(FirebaseInstanceIDService.myToken, Context.MODE_PRIVATE);
         tok=SharedPreference.getInstance(getApplicationContext()).getdata(SharedPreference.TOKEN);


    }


}
