package com.example.commerce;

import java.util.ArrayList;
import java.util.List;

public class Category {
    // 객체 생성
    private List<Product> products =  new ArrayList<Product>();

    // 생성자 생성
    public Category() {
        initializeProducts();
    }

    // 객체 값 초기화
    public void initializeProducts() {
        products.add(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", "전자제품", 25));
        products.add(new Product("iPhone", 1300000, "Apple의 최신 스마트폰", "전자제품", 30));
        products.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", "전자제품", 15));
        products.add(new Product("비빔밥", 10000, "야채와 고추장이 들어간 비빔밥", "식품", 10));
        products.add(new Product("가디건", 50000, "가볍게 입기 좋은 가디건", "의류", 40));
    }

    // List는 가변형 객체이기 때문에 복사본으로 반환
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // 상품 추가
    public void addProduct(Product product) {
        products.add(product);
    }

    // List 재고 업데이트
    public void updateQuantity(String productName, int quantity) {
        for (Product p : products) {
            if (p.getName().equals(productName)) {
                int newQuantity = p.getQuantity() - quantity;
                p.setQuantity(newQuantity);
                break;
            }
        }
    }
}
