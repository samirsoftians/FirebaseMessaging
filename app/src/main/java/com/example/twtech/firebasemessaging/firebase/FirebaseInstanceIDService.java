package com.example.twtech.firebasemessaging.firebase;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.twtech.firebasemessaging.MainActivity;
import com.example.twtech.firebasemessaging.sharedpreference.SharedPreference;
import com.example.twtech.firebasemessaging.url.Url;
import com.example.twtech.firebasemessaging.volleysingleton.VolleySingleton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

import static android.content.ContentValues.TAG;

/**
 * Created by Lenovo on 7/21/2017.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    public static SharedPreferences sharedpreferences1;

    String t;

    //*****************************SHARRED PREFERENCES TO STORE DATA****
    public static final String myToken = "mytoken";
    public static final String userToken = "token";
    //****************************END HERE*************************z


    //*********************Code to check if token did receive or not *****************************************
//    private static final String TAG = "MyFirebaseIIDService";
//
//    @Override
//    public void onTokenRefresh() {
//
//        //Getting registration token
//        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//
//        //Displaying token on logcat
//        Log.d(TAG, "Refreshed token: " + refreshedToken);
//        Toast.makeText(this, refreshedToken, Toast.LENGTH_SHORT).show();
//
//    }

//    private void sendRegistrationToServer(String token) {
//        //You can implement this method to store the token on your server
//        //Not required for current project
//    }
//**************************************************Ends Here*************************************************


    @Override
    public void onTokenRefresh() {
       // sharedpreferences1 = getSharedPreferences(SharedPreference.TOKEN, Context.MODE_PRIVATE);

        t = FirebaseInstanceId.getInstance().getToken();

       /*  Variables.token = FirebaseInstanceId.getInstance().getToken();
        System.out.println("Token :-"+Variables.token);*/

        Log.e("Token", t);

        SharedPreference.getInstance(getApplicationContext()).update(SharedPreference.TOKEN,t);

        register();



//ffr8DsRJkhk:APA91bF05hr1aejl4mFkYg2F4JnLJZjDKwadjPAn_vCwTVZhhrZZf5QGqdFhOkbu97Me72FhXUc1VfLK-mMbJwhwaxlgJUZEOL5MTwqL5QmkdQZmPyitRE1bl5OuCMbTeZHi5Lf7cAqo





    }
    public void register()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.registration,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        Log.e("RESPONCE",response);
                        //  progressBar.setVisibility(View.GONE);

                       /* try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("user");

                                //creating a new user object
                                User user = new User(
                                        userJson.getInt("id"),
                                        userJson.getString("username"),
                                        userJson.getString("email"),
                                        userJson.getString("gender")
                                );

                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                                //starting the profile activity
                                finish();
                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();


                params.put("email", "samir");
                params.put("phone", "902833644");
                params.put("password", "123");
                params.put("user_token",t);

                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


}
