public class P1 {
    public static void main(String[] args) { //프로그램은 main 안쪽이 실행되고 끝이다 . 
        System.out.println("안녕하세요");
        //요기는 지역변수 
        int a = 10; 
        Repository qqqqq = new Repository() ; 
        // Repository qqqqq; 중요질문 : 변수인가요? Y . Stack에 쌓이나요? Y(qqqq가 stack에 쌓임). 참조타입인가요 기본타입인가요 ? 참조 
        // qqqqq = new Repository() ; 메모리 생성하나요? Y(new 이후가 생성문법임). 결과값 반환해주나요? (메모리주소값)Y.

        qqqqq.name = "어머";
        qqqqq.a = 10;

        System.out.println(qqqqq.a);
        System.out.println(qqqqq.name);
    }

}


// 클래스는 대문자로 시작함. 구분하기엔 대문자 Math, System, Scanner 등.. 
// 문법규칙 말고는 논리 찾지 마라 
//1. 클래스 정의 문법. (이러한 객체가 있어. 이런 역할을 할거야가 목표)
//2. 안에는 속성정의, 생성자 정의, 기능정의가 있음

class Repository { 
    // 속성정의(변수 선언) _ 메모리를 묶은 것 
    // 클래스에 대해 정의만 한 것이기때문에 메모리는 생성되지 않는다.  그러니까...위에도 a를 만들수있네?
    //묶여있는 메모리를 생성/활용하려면?  main 안에서 Repository qqqq;를 만들 수 있다. (모든 클래스는 변수 선언이 가능하다)
    //new! (만약 new하면 int, int, int, String 메모리가 생성된다. : 인스턴스 생성 문법)
    // 속성 >= 멤버변수 >= 인스턴스 변수(추상화 레벨에 따라 살짝 다른 표현/추상화레벨 >=)
    // 지역변수는 stack에 쌓이지만 인스턴스 변수는 heap에 
    int a = 70 ;  // 초기값 지정해주고 싶으면 
    int b = (int)Math.random(); 
    int score; 
    String name;

    // 생성자정의

    // 기능정의

}

//시행은 main안쪽만 된다고 생각하기
//class정의는 정의만 한 것이고 묶는 용도. 활용을 위해 미리 정리하는거네... 인덱스는 없지만 자료구조다(뭉쳐있다!)


class Tttt{

}