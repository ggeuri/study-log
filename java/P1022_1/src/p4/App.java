package p4;

public class App {
    public static void main(String[] args) {
        //람다식 문법 - 현재 함수형 프로그래밍 기법의 주요 문법
        //가독성 높이고 간결하게 사용하자
        // 사용조건: "하나"의 추상 메서드가 있는 interface 

        AAA a1 = (c,d) -> c+d;
  // ()우선 매개변수명, 인터페이스 클래스랑 변수명 동일하지않아도됨.어차피 타입은 똑같다.  
 
 
        int result = a1.test(10,20);
        System.out.println(result);
    }

}

interface AAA{ // 첫번째 조건 인터페이스 존재
    public int test(int a, int b);

}

class AAAImpl implements AAA{// 인터페이스가 있으면 상속받을 클래스가 필요 
    public int test(int a, int b){

    return a+b;
    }
}
