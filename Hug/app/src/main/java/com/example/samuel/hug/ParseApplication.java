package com.example.samuel.hug;

import android.app.Application;
import android.util.Log;
import com.parse.Parse;
import com.parse.ParseObject;


/**
 * Created by Samuel on 4/27/2015.
 */
public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Parse and enable local datastore
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "VItHboPGcuvLhM1fIGNfSYeAmpaI8gZ8kapZzA5e", "pysSJFYVoD9Nqze879M8RPNTj2808uhatinCINNM");
        ParseObject.registerSubclass(User.class);
        Log.i("Application", "Initialized!");
    }
}
