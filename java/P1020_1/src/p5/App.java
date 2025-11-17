package p5;

public class App {

    public static void main(String[] args) {
        // 상속 - 다형성 - 오버라이딩 = 3개의 대표 표현은 다형성.. 
        // 문법 의도 : 결합도를 낮춘다 => 컴포넌트를 조립식으로 하자 // 결합도를 낮춰야 여기저기 끼워넣을수있음 
        // 결합도가 낮으면 자동차 타이어 갈아끼우듯이 가능. 결합도 높으면 자동차 재조립해야 타이어를 갈 수 있다..
        // 상속 + 다형성 + 오버라이딩 => 인터페이스 
        // 자바의 꽃은 인터페이스 = 자바의 차별화 

        BBB ref2 = new BBB();
        ref2.b1 = 10; 
        ref2.a1 = 10; 
        ref2.testA();// 메서드도 됨 

        CCC ref3 = new CCC(); 
        DDD ref4 = new DDD(); 
        ref4.testA();
    

    

    }

}
//관계없던 AAA - BBB 
// BBB를 AAA에서 확장돼서(상속받아서) 정의하고 싶어 extends(BBB를 정의할 때, AAA를 확장해서 정의하겠다. = BBB클래스는 AAA를 확장해서 선언했기때문에 AAA안에 있는 멤버들이 BBB에 포함된다) 
// = (즉 이경우 int 메모리 4개 생성됨) 
// BBB는 AAA의 자식 

class AAA {
    int a1;
    int a2;

    void testA(){
        System.out.println("냐냐");
    }
}

// class BBB {
//     int b1;
//     int b2;
// }

class BBB extends AAA {
    int b1;
    int b2;
}

class CCC extends BBB {
    int c1; 
    int c2; 

}
class DDD extends BBB {
    int d1; 
    int d2; 

}
