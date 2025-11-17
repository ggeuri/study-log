package p4;

public class App {
    public static void main(String[] args) {
        SumCalc1 sc1 = new SumCalc1();
        int result1 = sc1.sum(1,10);

        System.out.println(result1);

        SumCalc2 sc2 = new SumCalc2(1, 10);
        int result2 = sc2.getResult(); // 보통은 다회용 
        
        new SumCalc2(1,10); // 변수안받아도 할 수는 있는데 생성되자마자 참조변수가 없어서 GC가 수거함 
        int result3 = new SumCalc2(1,10).getResult(); // 할 수 있는 이유? 참조변수타입이니까 접근연산자인 .을 통해 바로 연결가능 . 결과물은 인트임 
        //이런케이스는 보통 일회용.. 
        
        System.out.println(result2);
        System.out.println(result3);

    }
}

class SumCalc1 {
    int sum(int s, int e) { //메서드로 하기 
        int sum = 0; 
        for(int i = s; i <= e ; i++) {
            sum += i; 
        }
        return sum; 
    }
}

class SumCalc2 {
    int sum = 0 ;

    SumCalc2(int startNum, int endNum){ //생성자로 하기 
        for(int i = startNum; i <= endNum; i++ ){
            sum += i ; 
        }
    }

    int getResult () {
        return sum; 
    }

}