package com.example.commerce;

public class Customer {
    private String name;
    private String email;
    private String rating;

    public enum Rating {
        BRONZE(0), SILVER(0.05), GOLD(0.1), PLATINUM(0.15);

        private final double discount;

        Rating(double discount) {
            this.discount = discount;
        }

        public double getDiscount() {
            return discount;
        }
    }

    public int getDiscount(int choice, int price) {

        switch (choice) {
            case 1:
                price = (int) (price * Rating.BRONZE.getDiscount());
                break;

            case 2:
                price = (int) (price * Rating.SILVER.getDiscount());
                break;

            case 3:
                price = (int) (price * Rating.GOLD.getDiscount());
                break;

            case 4:
                price = (int) (price * Rating.PLATINUM.getDiscount());
                break;

            default:
                throw new IllegalArgumentException("잘못된 회원 등급 선택입니다. 1-4 사이의 숫자를 입력하여 주세요.");
        }
        return price;
    }

    public void printOrderPrice(int choice, int price) {
        int discountPrice = price - getDiscount(choice, price);
        System.out.println("주문이 완료되었습니다!");
        System.out.println("할인 전 금액: " + price);
        switch (choice) {
            case 1:
                System.out.println("BRONZE 등급 할인(0%): -" + getDiscount(choice, price) + "원");
                System.out.println("최종 결제 금액: " + discountPrice + "원");
                break;

            case 2:
                System.out.println("SILVER 등급 할인(0%): -" + getDiscount(choice, price) + "원");
                System.out.println("최종 결제 금액: " + discountPrice + "원");
                break;

            case 3:
                System.out.println("GOLD 등급 할인(0%): -" + getDiscount(choice, price) + "원");
                System.out.println("최종 결제 금액: " + discountPrice + "원");
                break;

            case 4:
                System.out.println("PLATINUM 등급 할인(0%): -" + getDiscount(choice, price) + "원");
                System.out.println("최종 결제 금액: " + discountPrice + "원");
                break;
        }
    }
}


