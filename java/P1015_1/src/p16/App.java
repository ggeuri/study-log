package p16;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        IOManager ioManager = new IOManager();
        Calc calc = new Calc();

        int startNum = ioManager.inputStart("시작 값 > ");
        int endNum = ioManager.inputEnd("끝 값 > ");

        System.out.println(calc.SumCalc(startNum, endNum));
        
    }

}

// 🔹 문제 5

// 입출력 전용 클래스(IoManager)와
// 계산 전용 클래스(Calc)를 만들어 협업시키기.

// // 예시 출력:
// 시작값: 1  
// 끝값: 10  
// 합계는 55입니다.

class IOManager {
    Scanner scanner = new Scanner(System.in);
    int inputStart(String text){
        System.out.print(text);
        int startNum = Integer.parseInt(scanner.nextLine());
        
        return startNum;
    }

    int inputEnd(String text){
        System.out.print(text);
        int endNum = Integer.parseInt(scanner.nextLine());
        
        return endNum;
    }
}

class Calc {

    String SumCalc(int startNum, int endNum){
        int sum = 0; 
        for(int i = startNum; i <= endNum; i++){
            sum += i;
        }
        
        return "합계는" + sum + "입니다.";
    }

}