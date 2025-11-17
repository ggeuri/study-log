package p4;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("점수 입력 > ");

        
        try{
            int score = Integer.parseInt(scanner.nextLine());
            System.out.println("score: " + score); 
        } catch(NumberFormatException e) {
            System.out.println("숫자를 입력하세요");
        }

        System.out.println("프로그램 종료");
    }

}
