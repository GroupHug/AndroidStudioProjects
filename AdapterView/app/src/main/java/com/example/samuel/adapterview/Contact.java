package com.example.samuel.adapterview;

/**
 * Created by Samuel on 4/20/2015.
 */
public class Contact {
    private String name;
    private String imageUrl;

    public Contact(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
