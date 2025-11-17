package p5;

public class App {
    public static void main(String[] args) {
        // Wrapper 클래스 - 기본타입을 보조하는 클래스
        // 편하지만 사용시 주의를 요함 
        // 연산만 하지 말자.
        // 장점 : null을 가질 수 있다. 

        int a = 10; 
        Integer b = 20; //문법을 벗어나는 형태의 Class - String, Wrapper //Wrapper 는 박싱임. 기본타입을 박스에 담아서 힙메모리에 넣고 그 참조수를 주겠다. 

        System.out.println(b);

        int c = b;  // 언박싱 

        Integer qq = 10; 
        qq = qq + 230; // 이런 행동만 안하면 됨. 연산만 하지마라. 왜 ? 힙메모리 다 생성하나..?근데 ++이나 for 쓰면 하나하나 다 생성하겄네..  

        System.out.println(qq);


        /////////////////////////////////////////////////
        /// 기본 타입을 보조하는 Wrapper Class(다 대문자)
        /// null값을 담을 수 있는게 최고 
        /// 값이 변활할때마다 메모리생성된다는걸 생각해야함 오버헤드  
        /////////////////////////////////////////////////

        Byte byte1 = 20; 
        Short short1 = 30; 
        Integer integer1 = 32; 
        Long long1 = 40L;
        Float float1 = 3.14f;
        Double double1 = 3.14;
        Character character1 = 'a';
        Boolean boolean1 = true; 

        ///Wrapper Class는 기본타입처럼 맘대로 쓸 수 있으면서 Class처럼도 사용 됨

        Object aaa = 3; // 얘가 다형성으로 Integer한거네 ... 


    }

}
