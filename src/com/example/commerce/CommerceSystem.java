package com.example.commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    // 객체 생성
    Category categories = new Category();
    Scanner sc = new Scanner(System.in);
    ShoppingBasket shoppingBasket = new ShoppingBasket();


    public void start(){
        while(true){
            // 카테고리 목록 출력
            displayCategories();

            if(!shoppingBasket.isEmpty()) {
                // 주문 관리 목록 출력
                displayOrderManagement();
            }

            // 카테고리 선택
            int choiceCategory = sc.nextInt();

            // 카테고리 선택에 따른 분기 결정
            if(choiceCategory == 0){
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if(choiceCategory == 4 && !shoppingBasket.isEmpty()) {

                int totalPrice = 0;
                System.out.println("아래와 같이 주문 하시겠습니까?\n");
                System.out.println("[ 장바구니 내역 ]");
                List<Product> basketDetails = shoppingBasket.basketDetails();
                for(Product product : basketDetails){
                    System.out.println(product.getName() + " | " +  product.getPrice() + " | 수량: " + product.getQuantity());
                    totalPrice += (product.getPrice() * product.getQuantity());
                }
                System.out.println("\n[ 총 주문 금액 ]");
                System.out.println(totalPrice + "원\n");
                System.out.println(1 + ". 주문 확정");
                System.out.println(2 + ". 메인으로 돌아가기");

                int order = sc.nextInt();

                // 2차 재고 검증
                for(Product product : basketDetails){
                    for (Product category : categories.getProducts()) {
                        if (category.getName().equals(product.getName())) {
                            if(category.getQuantity() < product.getQuantity()){
                                System.out.println(product.getName() + "의 재고가 부족하여 주문을 할 수 없습니다.");
                                continue;
                            }
                        }
                    }
                }
                // 주문 및 재고 업데이트
                if(order == 1){
                    System.out.println("주문이 완료되었습니다! 총 금액: " + totalPrice + "원");
                    for(Product product : basketDetails){
                        for (Product category : categories.getProducts()) {
                            if(category.getName().equals(product.getName())){
                                int originalQuantity = category.getQuantity();
                                int newQuantity = (category.getQuantity() - product.getQuantity());
                                categories.updateQuantity(product.getName(), product.getQuantity());
                                System.out.println(product.getName() + "재고가 " + originalQuantity + " → " + newQuantity + "개로 업데이트되었습니다.");
                            }
                        }
                    }
                    // 장바구니 초기화
                    shoppingBasket.basketClear();
                    continue;
                } else if(order == 2){
                    continue;
                } else {
                    System.out.println("올바른 요청이 아닙니다.");
                    continue;
                }
            } else if(choiceCategory == 5 && !shoppingBasket.isEmpty()) {
                System.out.println("주문 취소!");
                shoppingBasket.basketClear();
                continue;
            } else if(choiceCategory < 0 || choiceCategory > 3){
                System.out.println("올바른 카테고리 값이 아닙니다.\n");
                continue;
            }
            // 선택한 카테고리 번호에 맞는 카테고리 값 저장
            String selectedCategory = getChoiceCategory(choiceCategory);
            // 선택된 카테고리의 상품 목록 리스트로 관리
            List<Product> filteredProducts = filterProducts(selectedCategory);

            if(filteredProducts.isEmpty()){
                System.out.println("해당 카테고리에는 상품이 존재하지 않습니다.\n");
                continue;
            }

            // 선택된 카테고리의 상품 목록 출력
            displayProducts(filteredProducts);

            // 상품 선택
            int choiceProduct = sc.nextInt();

            // 상품 선택에 따른 분기 결정
            if(choiceProduct == 0){
                System.out.println("메인 화면으로 돌아갑니다.\n");
                continue;
            } else if(choiceProduct > filteredProducts.size() || choiceProduct < 0) {
                System.out.println("입력하신 숫자에 해당하는 상품이 존재하지 않습니다.\n");
                continue;
            }
            // 선택된 상품 저장
            Product selectProduct = selectProduct(filteredProducts, choiceProduct);
            confirmAddToBasket(selectProduct);

            int addShoppingBasket = sc.nextInt();

            if(addShoppingBasket == 2){
                System.out.println("장바구니 추가를 취소합니다.\n");
                continue;
            } else if(addShoppingBasket < 1 || addShoppingBasket > 2){
                System.out.println("입력하신 숫자는 유효한 요청이 아닙니다.\n");
                continue;
            } else {
                if(selectProduct.getQuantity() <= 0) {
                    System.out.println("재고가 부족합니다.");
                    continue;
                }
                shoppingBasket.addProduct(selectProduct);
                System.out.println(selectProduct.getName() + "을 장바구니에 추가하였습니다.\n");
            }
        }
        sc.close();
    }

    // 카테고리 목록 출력
    public void displayCategories(){
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
        System.out.println(1 + ". 전자제품");
        System.out.println(2 + ". 식품");
        System.out.println(3 + ". 의류");
        System.out.println(0 + ". " + "종료 | 프로그램 종료");
    }

    // 주문 관리 목록 출력
    public void displayOrderManagement() {
        System.out.println("\n[ 주문 관리 ]");
        System.out.println(4 + ". 장바구니 확인");
        System.out.println(5 + ". 주문 취소");
    }

    // 선택한 카테고리 값 반환
    public String getChoiceCategory(int choice){
        switch(choice){
            case 1: return "전자제품";
            case 2: return "식품";
            case 3: return "의류";
            default: return null;
        }
    }

    // 선택된 카테고리 값을 가진 리스트 정리
    public List<Product> filterProducts(String category){
        List<Product> filtered = new ArrayList<>();
        for(Product product : categories.getProducts()){
            if(product.getCategory().equals(category)){
                filtered.add(product);
            }
        }
        return filtered;
    }

    // 정리된 리스트 출력
    public void displayProducts(List<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            System.out.print((i + 1) + ". " + products.get(i).getName() + " | ");
            System.out.print(products.get(i).getPrice() + " | ");
            System.out.print(products.get(i).getDescription() + " | ");
            System.out.println("재고: " + products.get(i).getQuantity() + "개");
        }
        System.out.println(0 + ". 뒤로가기");
    }

    // 선택된 상품 저장
    public Product selectProduct(List<Product> products, int  choiceProduct){
        Product selectedProduct = products.get(choiceProduct - 1);
        Product selected =  new Product(selectedProduct.getName(), selectedProduct.getPrice(), selectedProduct.getDescription(), selectedProduct.getCategory(), selectedProduct.getQuantity());
        System.out.println("선택된 상품: " + selectedProduct.getName() + " | " + selectedProduct.getPrice() + " | " + selectedProduct.getDescription() + " | 재고: " + selectedProduct.getQuantity() + "개\n");
        return selected;

    }

    // 상품 선택 추가 확인
    public void confirmAddToBasket(Product product) {
        System.out.println('"' + product.getName() + " | " + product.getPrice() + " | " + product.getDescription() + '"');
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.println(1 + ". 확인");
        System.out.println(2 + ". 취소");
    }
}


