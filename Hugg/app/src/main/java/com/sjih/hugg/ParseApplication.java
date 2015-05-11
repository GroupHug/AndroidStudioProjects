package com.sjih.hugg;

import android.app.Application;
import android.util.Log;
import com.parse.Parse;
import com.parse.ParseObject;


/**
 * Created by Samuel on 5/9/2015.
 */
public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "nYTUxeORZPuh8lsv8SX4aj2caY1xgH0j1MQhqHmo", "U74NH5FKoDSTGddUVM203eYyTz5PohvwQPQrvRlS");
    }
}
