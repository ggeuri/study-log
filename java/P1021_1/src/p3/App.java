package p3;

public class App {
    public static void main(String[] args) {
        // SOLID 원칙
        // 클래스 설계를 위한 5가지 원칙

        //SRP: 단일 책임 원칙 
    //---------------------위는 클래스 | 아래는 상속, 다형성, 오버라이딩 관련 원칙--------------------------------
        //OCP: 개방 페쇄 원칙  (= 기능 추가할 때 코드를 수정하는 것은 지양해야돼..? 1,2,3기능 만들고 4기능만들때 elseif안대? 클래스랑 인터페이스 다 따로따로만든대..) 
        //LSP: 리스코프 치환 원칙 (= (상위클래스 - 하위클래스 바뀌어도(주입클래스가 바뀌어도)) 기대하는 동작(값)은 그대로여야한다.)_turnOn이면 전원켜지는.. 알고리즘은 달라도 결과는 같아야지 
        //OSP: 인터페이스 분리 원칙 (= 거대한 인터페이스 만들지 말고 잘 쪼개라)
        //DIP: 의존 역전 원칙 (= 인터페이스 만들어서 A가 B 직접 생성하지마라. 주입받아서 실행해라 )


    }

}


interface Moveable{ // 너무 거대한 인터페이스니까 분리해서 flyable, runable, walkable 로 쪼개서 다중상속을 해라 implements flyable, runable
    public void fly();
    public void walk();
    public void run();
}

class Duck implements Moveable{
    public void fly(){}
    public void walk(){}
    public void run(){}
}

