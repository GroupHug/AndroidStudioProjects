package com.example.samuel.hug;

import com.parse.ParseObject;
import com.parse.ParseFile;
import com.parse.ParseClassName;

/**
 * Created by Samuel on 4/13/2015.
 */
@ParseClassName("User")
public class User extends ParseObject {
    private String hugger;
    private ParseFile image;
    private String message;
    private int hugs;

    public User(String hugger, ParseFile image, int hugs) {
        this.hugger = hugger;
        this.image = image;
        this.hugs = hugs;
    }

    public User() {
    }

    public String getHugger() {
        return hugger;
    }

    public ParseFile getImageUrl() {
        return image;
    }

    public int getHugs() {
        return hugs;
    }

    public void setHugger(String hugger) {
        this.hugger = hugger;
    }

    public void setImageUrl(ParseFile image) {
        this.image = image;
    }

    public void setHugs(int hugs) {
        this.hugs = hugs;
    }
}
