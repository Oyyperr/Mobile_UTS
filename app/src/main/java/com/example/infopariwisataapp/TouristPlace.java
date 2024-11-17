package com.example.infopariwisataapp;

public class TouristPlace {
    private String name;
    private String location;
    private String description;
    private int image;

    public TouristPlace(String name, String location, String description, int image) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }
}

