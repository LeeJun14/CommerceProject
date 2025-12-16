package com.example.commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    // 객체 생성
    Category categories = new Category();
    Scanner sc = new Scanner(System.in);


    public void start(){
        while(true){
            displayCategories();
            int choiceCategory = sc.nextInt();

            if(choiceCategory == 0){
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if(choiceCategory < 0 || choiceCategory > 3){
                System.out.println("올바른 카테고리 값이 아닙니다.");
                continue;
            }

            String selectedCategory = getChoiceCategory(choiceCategory);
            List<Product> filteredProducts = filterProducts(selectedCategory);

            if(filteredProducts.isEmpty()){
                System.out.println("해당 카테고리에는 상품이 존재하지 않습니다.");
                continue;
            }
            displayProducts(filteredProducts);
            selectProduct(filteredProducts);
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
            System.out.println(products.get(i).getDescription());
        }
        System.out.println(0 + ". 뒤로가기");
    }

    // 상품 선택
    public void selectProduct(List<Product> products) {
        int choiceProduct = sc.nextInt();
        Product selectedProduct = products.get(choiceProduct - 1);

        // 입력 받은 숫자에 따른 분기 조건
        if (choiceProduct == 0) {
            System.out.println("메인 화면으로 돌아갑니다.");
        } else if(choiceProduct > products.size() || choiceProduct < 0) {
            System.out.println("입력하신 숫자에 해당하는 상품이 존재하지 않습니다.");
        } else {
            System.out.println(selectedProduct.getName() + " | " + selectedProduct.getPrice() + " | " + selectedProduct.getDescription());
        }
    }
}
