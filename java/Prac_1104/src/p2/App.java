package p2;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        System.out.print("회원 등급을 입력하세요");
        String inputgrade = scanner.nextLine().trim().toUpperCase();
        System.out.print("상품 가격을 입력하세요 > ");
        int price = Integer.parseInt(scanner.nextLine());

        try {
            MemberGrade grade = MemberGrade.valueOf(inputgrade);
            double discountAmount = price * grade.getDiscountRate();
            System.out.println("할인 가격은 " + (price - discountAmount) + "원입니다.");
        } catch (Exception e) {
            System.out.println("잘못된 등급입니다.");
        }


        // System.out.println(MemberGrade.valueOf(inputgrade)); 


        
    }

}

enum MemberGrade {
    BRONZE(0.02) 
    , SILVER(0.05)
    , GOLD(0.10)
    , PLATINUM(0.15);

    private final double discountRate; 

    MemberGrade(double discountRate){
        this.discountRate = discountRate;
    }

    public double getDiscountRate(){
        return discountRate;
    }


}
