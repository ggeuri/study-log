package p3;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        IoManager ioManager = new IoManager();
        Calculator calculator = new Calculator();

        String text = ioManager.input("값 입력 > ");
        ioManager.output("입력된 값= " + text);
        //클래스에 output만들었으면 sout쓰지말기.. 규칙을 정한거니까

        int inputStart = 77;
        int inputEnd = 1500;

        int result1 = calculator.sumAndReturnFormatText(20,50);
        int result2 = calculator.sumAndReturnFormatText(inputStart,inputEnd);
        int result3 = calculator.sumAndReturnFormatText(1,10);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);

    }
}

class IoManager {
    Scanner scanner = new Scanner(System.in); // 스캐너 중복생성을 피하기위해서 멤버로 올림 = 그럼 언제생성? = 인스턴스 생성할때 한번만 되게찌 
    String input(String text) {
        System.out.print(text);
        String inputValue = scanner.nextLine();

        return inputValue;
    }

    void output(String text){
        System.out.println(text);
    }
}

class  Calculator {
    int sum; 
    int sumAndReturnFormatText(int startNum, int endNum) {
// 1부터 10까지 합 구하는 코드 
        int sum = 0;
        for(int i = startNum ; i <= endNum; i++){
            accumulate(i);
        }
        return sum;
    }

    void accumulate(int i) {
        sum += i;
    }
}
