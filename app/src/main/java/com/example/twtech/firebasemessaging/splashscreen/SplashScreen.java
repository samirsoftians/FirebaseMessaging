package com.example.twtech.firebasemessaging.splashscreen;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.twtech.firebasemessaging.MainActivity;
import com.example.twtech.firebasemessaging.R;
import com.example.twtech.firebasemessaging.login.Login;
import com.example.twtech.firebasemessaging.variables.Variables;
import com.facebook.CallbackManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.example.twtech.firebasemessaging.url.Url.login;

public class SplashScreen extends AppCompatActivity {
//https://developers.facebook.com/docs/facebook-login/android
    TextView splashTag;
    Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sparrow);

        final View view = findViewById(R.id.bird);
        splashTag=(TextView) findViewById(R.id.splash_tag);


        getSha();//zj9Dd9jcthjCnNjnKK8y3zu/MtM=


       /* FirebaseMessaging.getInstance().subscribeToTopic("Test");
        FirebaseInstanceId.getInstance().getToken();
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        FirebaseInstanceIDService.sharedpreferences1 = getSharedPreferences(FirebaseInstanceIDService.myToken,getApplicationContext().MODE_PRIVATE);*/

        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale);


        view.startAnimation(anim);



        splashTag.postDelayed(new Runnable() {
            public void run() {
                splashTag.setVisibility(View.VISIBLE);
                /* splashTag.startAnimation(anim);*/

                splashTag.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
            }
        }, 1000);



        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                //  new Timer().schedule(splashTag.V, 2000);


Intent intent=new Intent(SplashScreen.this, com.example.twtech.firebasemessaging.login.MainActivity.class);
startActivity(intent);



              /*  Intent intent=new Intent(SplashScreen.this, com.example.twtech.firebasemessaging.login.MainActivity.class);
                startActivity(intent);
                finish();*/

            }
        }, Variables.SPLASH_TIME_OUT);





    }


    public void getSha()
    {
        PackageInfo info;
        try {

            info = getPackageManager().getPackageInfo(
                    "com.example.twtech.firebasemessaging", PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("Hashkey", something);
                System.out.println("Hash key" + something);
            }

        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }



  /*  <com.facebook.login.widget.LoginButton
    android:id="@+id/login_button"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="center_horizontal"
    android:layout_marginTop="30dp"
    android:layout_marginBottom="30dp" />*/

}
