package p6;

public class App {
    public static void main(String[] args) {
        Test.c = 10; //인스턴스와 상관없이 존재하는 메모리이기 때문에 클래스명으로 접근 = 그래서 클래스변수임. 실질적 static에 접근하기 위해서는 클래스명 활용 (참조변수활용X)
        Test t1 = new Test();

        t1.a = 10; 

        new Ex().qqqq();

        System.out.println(t1.c);
        // System.out.println(t2.c); // static이 붙으면 처음부터 메모리 생성되고 소멸되지 않는다. 인스턴스 생성과 관계없음. 초기화는 안됨. 아무값도 안생김.   

    }

}

class Ex{
    void qqqq() { 
        System.out.println(Test.c);

    }
}


class Test{
    int a; //인스턴스변수(속성, 필드, 멤버)
    int b;
    static int c; // 클래스 변수 _처음부터 메모리 생성되고 소멸되지 않는다. 인스턴스 생성과 관계없음. 

}
