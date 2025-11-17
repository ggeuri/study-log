package p13;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        SumAndIO sumAndIO = new SumAndIO();  // 스캐너 생성 

        int first = sumAndIO.inputValue("첫 번째 수 입력 > ");
        int second = sumAndIO.inputValue("두 번째 수 입력 > ");

        sumAndIO.getResult(first,second);
        sumAndIO.getAverage(first,second);
        
    }

}

class SumAndIO {
    Scanner scanner = new Scanner(System.in);

    int inputValue(String text) {
        System.out.print(text);
        int a = scanner.nextInt(); 

        return a;
    }

    void getAverage(int a, int b) {
        double average = (a+b) / 2.0;
        
        System.out.println("두 수의 평균은 " + average + "입니다.") ; 
    }

    void getResult(int a, int b){
        int sum = a + b ;
        System.out.println("두 수의 합은 " + sum + "입니다.") ;
    }
   
}
