package p8;

public class App {
    public static void main(String[] args) {
        //다형성 _ 중요 
        
        AAA a1 = new AAA();
        BBB b1 = new BBB();
        AAA a2 = new BBB(); // 원래는 당연히 안됨 그런데 상속하면? 되네? 근데 부모에만 대입이 되는건가? 
        // 특정 타입(AAA)의 변수는 특정타입(AAA)을 상속받는 모든 형태의 타입을 변수로 받을 수 있다. 아항 부모만 할 수 있는 스킬(자기자신&내 자식&자손(CCC 등 더 아래도)들을 변수로) 
        // 중요한 개념! 상속 관계에서만 다형성이 발생한다. 
        a2.a1 = 10; 
        // a2.b2 = 20; 

        AAA a3 = new CCC(); 

        // BBB tempB = (BBB)a2; // 변수는 BBB, a2는 타입이 AAA 자식이 부모를 대입받는건 안됨. 근데 캐스팅이된다. 
        // 다형성 AAA a2 = new BBB(); 의 경우에는 문법적으로는 AAA고 런타임은 BBB니까 문제 없음. 자식이 부모 대입받을 수 있음.
        //일반적으로는 하면 안됨.  왜? AAA a2 = new AAA();일수도 있는데 강제캐스팅때려버림. 런타임오류  ClassCastException
        // a2에 런타임이 BBB라고 있을거라고확신할 수 있는 경우에만 
        
        if(a2 instanceof BBB){ // 런타임이 BBB 인지 확인하고 캐스팅
        BBB tempB = (BBB) a2; 
        tempB.testB();
        }
        System.out.println("프로그램끝");

    }


class AAA {
    int a1;
    int a2;
}
class BBB extends AAA {
    int b1;
    int b2;


    void testB(){


    }
}

class CCC extends BBB{
    int c1;
    int c2;


    void testC(){}
    }
}
 
// 근데 서로 상속도되나? 