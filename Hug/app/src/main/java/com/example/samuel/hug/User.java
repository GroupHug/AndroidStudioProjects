package com.example.samuel.hug;

/**
 * Created by Samuel on 4/13/2015.
 */
public class User {
    private String hugger;
    private int photoRes;
    private int hugs;

    public User(String hugger, int photoRes, int hugs) {
        this.hugger = hugger;
        this.photoRes = photoRes;
        this.hugs = hugs;
    }

    public String getHugger() {
        return hugger;
    }

    public int getPhotoRes() {
        return photoRes;
    }

    public int getHugs() {
        return hugs;
    }

    public void setHugger(String hugger) {
        this.hugger = hugger;
    }

    public void setPhotoRes(int photoRes) {
        this.photoRes = photoRes;
    }

    public void setHugs(int hugs) {
        this.hugs = hugs;
    }
}
