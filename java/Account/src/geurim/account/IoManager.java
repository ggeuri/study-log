package geurim.account;

import java.util.Scanner;

public class IoManager {
    private static Scanner scanner = new Scanner(System.in);

    private IoManager(){    }

    public static String input(String text){
        System.out.print(text);
        String inputText = scanner.nextLine();

        return inputText;
    }

    public static String print(String text){
        System.out.println(text);

        return text;
    }

    public static String pause(String text){
        System.out.println(text);
        
        return scanner.nextLine();
    }

    


    



}


// * 데이터 구조
// (데이터명(타입): 예시)
// 날짜(문자): 2025-10-22
// 유형(문자): 수입, 지출
// 카테고리(문자): 식비, 공과금, 교통비, 월급.. 등등
// 금액(숫자): 12000
// 메모(문자): 점심
