package p8;

public class App {
    public static void main(String[] args) {
        // Exception(Checked 예외), RuntimeException(unChecked 예외) 
        new Test().test();
    }

}

class Test {
    public void test() {
        new SumCalculator().sum(1, 5);
    }
}


class SumCalculator {
    public int sum(int a, int b) throws SumCalculatorException{
        if( b < a ) {
                throw new SumCalculatorException();//throw가 가능하려면 Exception을 상속받은 클래스여야함 
        }int sum = 0; 
        for(int i = a; i <= b ; i ++){
            sum+=i;
        }
        return sum;
    }
}

class SumCalculatorException extends RuntimeException {} //Checked예외 exception 클래스임 