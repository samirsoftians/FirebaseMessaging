package com.example.twtech.firebasemessaging.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.twtech.firebasemessaging.R;



public class Login extends AppCompatActivity {

//*************

    /*implementation fileTree(dir: 'libs', include: ['*.jar'])
    //noinspection GradleCompatible
    implementation 'com.android.support:appcompat-v7:28.0.0'
    implementation 'com.android.support.constraint:constraint-layout:1.1.3'
    implementation 'com.google.firebase:firebase-messaging:11.8.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
    compile 'com.android.support:percent:28.0.0'
    compile 'com.android.support:design:28.0.0'
    compile 'com.mcxiaoke.volley:library:1.0.19'*/

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        sharedpreferences = getApplication().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
*/



    }


    public void setDataSharedPreference()
    {

       /* SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("Password", "shivi");
        editor.putString("Email", "shivi");
        editor.commit();*/
    }

public void getDataSharedPreference()
{
    /*sharedpreferences.contains(Email);
    String email = sharedpreferences.getString(Email, "");*/
}

}
