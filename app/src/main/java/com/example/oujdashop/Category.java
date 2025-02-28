package com.example.oujdashop;

public class Category {
    private int id;
    private String name;
    private int imageResource;

    public Category(int id, String name, int imageResource) {
        this.id = id;
        this.name = name;
        this.imageResource = imageResource;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setName(String name) {
        this.name = name;
    }
}
