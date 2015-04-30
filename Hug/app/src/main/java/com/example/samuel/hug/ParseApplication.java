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

        // Enable local datastore
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "VItHboPGcuvLhM1fIGNfSYeAmpaI8gZ8kapZzA5e", "pysSJFYVoD9Nqze879M8RPNTj2808uhatinCINNM");

/*        // You need to register the subclass if you want to define your own data class like Professor
        ParseObject.registerSubclass(Professor.class);
        Log.i("Application", "Initialized!");*/

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }
}
