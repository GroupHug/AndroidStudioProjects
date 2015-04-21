package com.example.samuel.layout;

/**
 * Created by Samuel on 4/15/2015.
 */
public class User {
    private String name;
    private int age;
    private int photoRes;
    private int photos;
    private int messages;
    private int friends;

    public User(String name, int age, int photoRes, int photos, int messages, int friends) {
        this.name = name;
        this.age = age;
        this.photoRes = photoRes;
        this.photos = photos;
        this.messages = messages;
        this.friends = friends;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getPhotoRes() {
        return photoRes;
    }

    public int getPhotos() {
        return photos;
    }

    public int getMessages() {
        return messages;
    }

    public int getFriends() {
        return friends;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhotoRes(int photoRes) {
        this.photoRes = photoRes;
    }

    public void setPhotos(int photos) {
        this.photos = photos;
    }

    public void setMessages(int messages) {
        this.messages = messages;
    }

    public void setFriends(int friends) {
        this.friends = friends;
    }
}
