package p2;

public class App {
    public static void main(String[] args) {
        //자바문법의 interface = 클래스 간의 상호작용하기 위한 표준 정의 : 다른 클래스가 사용 

        //입출력 담당.. 코드 
        int valueA = 40;
        int valueB = 70;

        Calculator calculator = new CalculatorMock(); // Mock -> CalculatorImpl로 갈아끼우기만 하면 된다. 
        int result = calculator.sum(valueA, valueB);

        System.out.println(result);

    }

}

//여러사람이 할 수록 인터페이스 설계가 먼저임. 표준 먼저 정의해야 동시에 같이 할 수 있음. 
//미리 input값이랑 output값만 정해놓고 Mock으로 가짜 하나 만들어놓음. 나중에 연결만 바꾸면 됨.
interface Calculator {
    public int sum(int a, int b); 
}

class CalculatorMock implements Calculator { //가짜 클래스 먼저 만들고 
    public int sum(int a, int b){ // 내용 안만들어용 
    return 70;
    }
}

class CalculatorImpl implements Calculator{ // 클래스명에 Impl = 특정인터페이스(Calculator)를 구현한 클래스 
    public int sum(int a, int b){
        int sum = 0;
        for(int i = a; i <= b ; i++){
            sum += i;
        }
        return sum;
    } 
    

}

