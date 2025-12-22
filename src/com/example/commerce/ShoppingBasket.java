package com.example.commerce;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {
    private List<Product> shoppingBasket = new ArrayList<Product>();

    public List<Product> getShoppingBasket(){
        return new ArrayList<>(shoppingBasket);
    }

    // 리스트에 Product 추가
    public void addProduct(Product product){
        shoppingBasket.add(product);
    }

    public List<Product> basketDetails() {
        List<Product> processedProducts = new ArrayList<>();
        int totalPrice = 0;

        for (Product product : getShoppingBasket()) {
            if(isProductProcessed(processedProducts,  product)) {
                continue;
            }
            // 같은 이름의 상품 개수에 따라 수량 입력
            int quantity = 0;
            for(Product p : getShoppingBasket()) {
                if(p.getName().equals(product.getName())) {
                    quantity++;
                }
            }
            product.setQuantity(quantity);
            processedProducts.add(product);
        }
        return processedProducts;
    }

    // 장바구니에 추가한 적 있는 상품인지 확인
    private boolean isProductProcessed(List<Product> processedProducts, Product product) {
        for (Product p : processedProducts) {
            if (p.getName().equals(product.getName())) {
                return true;
            }
        }
        return false;
    }

    // 리스트 값 검증
    public boolean isEmpty(){
        return shoppingBasket.isEmpty();
    }

    // 리스트 초기화
    public void basketClear(){
        shoppingBasket.clear();
    }

    public void removeProduct(String productName){
        shoppingBasket.removeIf(product -> product.getName().equals(productName));
    }
}
