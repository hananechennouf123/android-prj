package com.example.oujdashop;

public class Product {

    private long id;
    private String name;
    private double price;
    private String description;
    private String category;
    private int imageResource;

    public Product(long id, String name, double price, String description, String category, int imageResource) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.imageResource = imageResource;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public int getImageResource() {
        return imageResource;
    }
}

