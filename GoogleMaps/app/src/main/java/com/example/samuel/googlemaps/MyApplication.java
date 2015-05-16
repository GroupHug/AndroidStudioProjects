package com.example.samuel.googlemaps;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Samuel on 5/13/2015.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "BGhMRCkei2aLRnrA6rS8m5hnNj7kR1JVZzqykQrY", "jIv66n0nvRVdCQUtG11vWJ4YXnw5sDHvSKijwYlL");
    }
}
