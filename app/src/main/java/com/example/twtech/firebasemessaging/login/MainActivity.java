package com.example.twtech.firebasemessaging.login;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.twtech.firebasemessaging.R;
import com.example.twtech.firebasemessaging.sharedpreference.SharedPreference;
import com.example.twtech.firebasemessaging.url.Url;
import com.example.twtech.firebasemessaging.volleysingleton.VolleySingleton;
import com.facebook.CallbackManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;


import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String validation="";
    private boolean isSigninScreen = true;
    private TextView tvSignupInvoker,tvSigninInvoker;
    private LinearLayout llSignup,llSignin;
    private Button btnSignup,register_submit,btnSignin;
    CallbackManager callbackManager;

    LinearLayout llsignin,llsignup;


    TextInputEditText register_email,register_password,register_phone;
    String tok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);




        FirebaseMessaging.getInstance().subscribeToTopic("Test");
        FirebaseInstanceId.getInstance().getToken();
        // String tok=SharedPreference.getInstance(getApplicationContext()).getdata(SharedPreference.TOKEN);
        //FirebaseInstanceIDService.sharedpreferences1 = getSharedPreferences(FirebaseInstanceIDService.myToken, Context.MODE_PRIVATE);
        tok= SharedPreference.getInstance(getApplicationContext()).getdata(SharedPreference.TOKEN);


        register_email=findViewById(R.id.register_email);
        register_password=findViewById(R.id.register_password);
        register_phone=findViewById(R.id.register_phone);

        register_submit=(Button) findViewById(R.id.register_submit);

        llSignin = (LinearLayout) findViewById(R.id.llSignin);
        llSignin.setOnClickListener(this);
        //LinearLayout singnin =(LinearLayout)findViewById(R.id.signin);
        llsignup =(LinearLayout)findViewById(R.id.llSignup);
        llsignup.setOnClickListener(this);
        tvSignupInvoker = (TextView) findViewById(R.id.tvSignupInvoker);
        tvSigninInvoker = (TextView) findViewById(R.id.tvSigninInvoker);

    //  btnSignup= (Button) findViewById(R.id.btnSignup);
        btnSignin= (Button) findViewById(R.id.btnSignin);

        llSignup = (LinearLayout) findViewById(R.id.llSignup);
        llSignin = (LinearLayout) findViewById(R.id.llSignin);


        register_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validation="";
                validation();

                if(validation().equals("invalid"))
                {

                    setSnackBar(view,"Please check the error");

                    }
                    else {
                    register();
                }


            }
        });

        tvSignupInvoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSigninScreen = false;
                showSignupForm();
            }
        });

        tvSigninInvoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSigninScreen = true;
                showSigninForm();
            }
        });
        showSigninForm();



      /*  btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_right_to_left);
                if(isSigninScreen)
                    btnSignup.startAnimation(clockwise);
            }
        });*/
    }

    private void showSignupForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) llSignin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.15f;
        llSignin.requestLayout();


        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) llSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.85f;
        llSignup.requestLayout();

        tvSignupInvoker.setVisibility(View.GONE);
        tvSigninInvoker.setVisibility(View.VISIBLE);
        Animation translate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_right_to_left);
        llSignup.startAnimation(translate);

        Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_right_to_left);
        register_submit.startAnimation(clockwise);

    }

    private void showSigninForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) llSignin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.85f;
        llSignin.requestLayout();


        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) llSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.15f;
        llSignup.requestLayout();

        Animation translate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_left_to_right);
        llSignin.startAnimation(translate);

        tvSignupInvoker.setVisibility(View.VISIBLE);
        tvSigninInvoker.setVisibility(View.GONE);
        Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_left_to_right);
        btnSignin.startAnimation(clockwise);
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.llSignin || v.getId() ==R.id.llSignup){
            // Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
            InputMethodManager methodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            methodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

        }

    }

    public String validation()
    {

        if(register_email.getText().toString().trim().equals(""))
        {
            register_email.setError("Please Enter your Email");
            validation="invalid";
        }
        if(register_password.getText().toString().trim().equals(""))
        {
            register_password.setError("Please Enter Password");
            validation="invalid";

        }
        if(register_phone.getText().toString().trim().equals(""))
        {
            register_phone.setError("Please Enter your phone number");
            validation="invalid";

        }


        return validation;

    }

    public static void setSnackBar(View root, String snackTitle) {
        Snackbar snackbar = Snackbar.make(root, snackTitle, Snackbar.LENGTH_SHORT);
        snackbar.show();
        View view = snackbar.getView();
        TextView txtv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        txtv.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    public void register()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("email", "samir.softians@gmail.com");
                params.put("phone", "902833644");
                params.put("password", "123");
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


}
