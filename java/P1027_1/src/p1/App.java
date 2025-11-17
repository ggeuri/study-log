package p1;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        //제너릭 
        StudentDto<String,Integer> s1 = new StudentDto<>(); //명확하게 정의. String으로 할거야 //컴파일될때 String됨 
        s1.age = 30;
        s1.name = "한조";


        StudentDto<Integer, Scanner> s2 = new StudentDto<>();//제너릭에서 기본타입은 정의못함 = int -> Integer 
        s2.name = 30;
        s2.age = new Scanner(System.in);

        StudentDto<?,?> qqq ; // 와일드 카드, 지금으로서는 넘어가도 됨.

    }

}


//제너릭 문법의 의도: StudentDto의 score가 int일수도 double일수도있다..? 개발자가 타입을 모르겠을 경우... (프레임워크에서 존재할 수 있음) 
//여러개의 클래스를 만들어야해..? 타입을 모르겠을 경우 제너릭을 쓰나봄 

class StudentDto <N,T>{ //제너릭 정의문법 <> 뭔지 모르는 타입을 정의할 수 있음  
    N name;
    T age; 
    T score; 

    public T test(T value){//메서드로도 사용가능하고 
        T result = null; 
        return result;
    }
}