package p4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        //Object  = 사실 얘는 의미없는데 중요함.
        // 모든 클래스의 최상위 부모(조상)

        AAA aaa = new AAA();

        Object aaaa  = new AAA(); // Object는 부모니까 모든 타입을 받을수있다..(다형성 )
        Object a1 = new Scanner("str"); 
        Object a2 = "2313sad";
        Object a3 = 213; 

        List<Object> list = new ArrayList<>();  

        AAA ref1 = new AAA();

        System.out.println(ref1);
    }
}


class AAA { // 자바는 아무것도 상속받지않은 클래스가없다. 상속을 선언안한 클래스라면 다 뒤에 extends Object가 숨겨져있었다....
    int a; 
    int b; 

    public String toString(){
        //로그용.............오버라이딩..........
        return String.format("{a=%d, b=%d}",a,b);
    }
}