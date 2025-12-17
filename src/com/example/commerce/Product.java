package com.example.commerce;

public class Product {
    // 필드 캡슐화
    private String name;
    private double price;
    private int quantity;
    private String description;
    private String category;

    // 생성자
    public Product(String name, double price, String description, String category, int quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
    }

    // 게터
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

    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) {this.quantity = quantity;}
}
