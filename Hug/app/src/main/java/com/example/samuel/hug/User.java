package com.example.samuel.hug;

/**
 * Created by Samuel on 4/13/2015.
 */
public class User {
    private String hugger;
    private int photoRes;
    private int gender; // 0 female 1 male
    private int age;
    private int hugs;

    public User(String hugger, int photoRes, int gender, int age, int hugs) {
        this.hugger = hugger;
        this.photoRes = photoRes;
        this.gender = gender;
        this.age = age;
        this.hugs = hugs;
    }

    public String getHugger() {
        return hugger;
    }

    public int getPhotoRes() {
        return photoRes;
    }

    public int getGender() {
        return gender;
    }

    public int getAge() {
        return age;
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

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHugs(int hugs) {
        this.hugs = hugs;
    }
}
