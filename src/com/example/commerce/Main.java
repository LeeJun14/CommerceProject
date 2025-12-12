package com.example.commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 객체 생성
        List<Product> products = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        products.add(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰"));
        products.add(new Product("Iphone", 1300000, "Apple의 최신 스마트폰"));
        products.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북"));

        // 상품 목록 출력
        for (int i = 0; i < products.size(); i++) {
            System.out.print((i + 1) + ". " + products.get(i).getName() + " | ");
            System.out.print(products.get(i).getPrice() + " | ");
            System.out.println(products.get(i).getDescription());
        }
        System.out.println(0 + ". " + "종료 | 프로그램 종료");

        // 입력 받은 숫자에 따라 로직 실행
        System.out.print("숫자를 입력하여 상품을 선택하여주세요: ");
        int choice = sc.nextInt();

        // 입력 받은 숫자에 따른 분기 조건
        if (choice == 0) {
            System.out.println("프로그램을 종료합니다.");
            sc.close();
        } else if(choice > products.size() || choice < 0) {
            System.out.println("입력하신 숫자에 해당하는 상품이 존재하지 않습니다.");
            sc.nextInt();
        } else {
            System.out.println(products.get(choice - 1).getName() + " | " + products.get(choice - 1).getPrice() + " | " + products.get(choice - 1).getDescription());
        }
        sc.close();
    }
}
