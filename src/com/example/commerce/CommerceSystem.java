package com.example.commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    // 객체 생성
    private List<Product> products =  new ArrayList<Product>();

    // 객체 데이터 생성자로 생성
    public CommerceSystem() {
        products.add(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰"));
        products.add(new Product("Iphone", 1300000, "Apple의 최신 스마트폰"));
        products.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북"));
    }

    // 상품 목록 출력 (products에 값이 존재하지 않을 때를 대비한 예외처리 필요!)
    public void displayProducts() {
        for (int i = 0; i < products.size(); i++) {
            System.out.print((i + 1) + ". " + products.get(i).getName() + " | ");
            System.out.print(products.get(i).getPrice() + " | ");
            System.out.println(products.get(i).getDescription());
        }
        System.out.println(0 + ". " + "종료 | 프로그램 종료");
    }

    // 상품 선택 (get 메서드에 choice -1 반복 사용. 재사용 가능한 변수 선언으로 코드 간결화 필요!)
    public void choice() {
        Scanner sc = new Scanner(System.in);

        // 입력 받은 숫자에 따라 로직 실행
        System.out.print("숫자를 입력하여 상품을 선택하여주세요: ");
        int choice = sc.nextInt();

        // 입력 받은 숫자에 따른 분기 조건
        if (choice == 0) {
            System.out.println("프로그램을 종료합니다.");
        } else if(choice > products.size() || choice < 0) {
            System.out.println("입력하신 숫자에 해당하는 상품이 존재하지 않습니다.");
        } else {
            System.out.println(products.get(choice - 1).getName() + " | " + products.get(choice - 1).getPrice() + " | " + products.get(choice - 1).getDescription());
        }
        sc.close();
    }
}
