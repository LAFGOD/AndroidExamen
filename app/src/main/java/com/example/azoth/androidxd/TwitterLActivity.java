package com.example.azoth.androidxd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.azoth.androidxd.sqliteadmin.Contact;
import com.example.azoth.androidxd.sqliteadmin.DatabaseHandler;
import com.google.firebase.crash.FirebaseCrash;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class TwitterLActivity extends AppCompatActivity {

    TwitterLoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        setContentView(R.layout.activity_twitter_l);


        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                Log.v("TwitterLActivity","ME HE LOGEADO");
                FirebaseCrash.log("Error 2 LOGEADO XD");
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                Log.v("TwitterLActivity","ERROR");
                FirebaseCrash.log("Error 3 ERROR LOG XD");
            }
        });

        DatabaseHandler databaseHandler= new DatabaseHandler(this);
        FirebaseCrash.log("Error 5 tengo sueño");
        Contact contact=new Contact(3,"Yony333","692428756");
        databaseHandler.addContact(contact);
        //Contact contact1 = databaseHandler.getContact(4);
        FirebaseCrash.report(new Exception("My first Android non-fatal error"));
        FirebaseCrash.log("Error 1");
        Log.v("DDBB","--------------------->>>>>>>>>>>>>>>" + databaseHandler.getAllContacts());
        /*TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig("2Z9GhzCdPv8gYreqEQ5TDZDIn", "hOpkr8LfOThAB4Ml6LYfKKiKFEGi0DyAiVBdBenmapYhDR1rK3"))
                .debug(true)
                .build();
        Twitter.initialize(config);*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
        FirebaseCrash.log("Error 4 XDDDDDDDDD");
    }
}
