package com.example.kocsmap;

public class Review {
    private String key,description,address;
    private float rating;

    public Review(String key, String descriptin, String address, float rating) {
        this.key = key;
        this.description = descriptin;
        this.address = address;
        this.rating = rating;
    }

    public Review() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descriptin) {
        this.description = descriptin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
