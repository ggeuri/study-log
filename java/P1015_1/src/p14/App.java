package p14;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        sumUpAndReturn sumUp = new sumUpAndReturn();

        Scanner scanner = new Scanner(System.in);
        System.out.print("입력값 > ");
        int endNum = scanner.nextInt();

        int result1 = sumUp.inputValue(endNum);
        System.out.println("1부터 " + endNum + "까지의 합은 " + result1 + "입니다.");

    }

}
// 🔹 문제 2

// 1부터 N까지의 합을 구해 리턴하는 메서드를 만들어라.
// 	•	입력: 정수 N
// 	•	출력: “1부터 N까지의 합은 X입니다.”

// 👉 조건: 합계 계산은 return, 출력은 main에서

class sumUpAndReturn {

    int inputValue(int endNum) {
        int sum = 0;
        for(int i = 1; i <= endNum; i++){
            sum += i ; 
        }
        return sum;
    }

}