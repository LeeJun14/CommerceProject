package com.example.commerce;

import java.util.List;

public class AdminMode {
    private String password = "qwe123";
    Category categories = new Category();

    public void setPassword(String password) {
        this.password = password;
    }

    // 관리자 모드 화면 출력
    public void displayAdminMode(){
        System.out.println("\n[ 관리자 모드 ]");
        System.out.println(1 + ". 상품 추가");
        System.out.println(2 + ". 상품 수정");
        System.out.println(3 + ". 상품 삭제");
        System.out.println(4 + ". 전체 상품 현황");
        System.out.println(0 + ". 메인으로 돌아가기");
    }

    // 카테고리 목록 화면 출력
    public void displayAdminCategory(){
        System.out.println(1 + ". 전자제품");
        System.out.println(2 + ". 의류");
        System.out.println(3 + ". 식품");
    }

    public String getPassword() {
        return password;
    }
}
