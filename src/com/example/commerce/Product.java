package com.example.commerce;

public class Product {
    // 필드 캡슐화
    private String name;
    private double price;
    private int quantity;
    private String description;

    // 생성자
    public Product(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
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
}
