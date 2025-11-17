package p6;

public class App {
    public static void main(String[] args) {
        
    }

}

class Person {
    String name;
    int age; 
    
}

class Student extends Person{
    // String name; 생략가능이네 
    // int age; 
    int score;
}

class FireMan extends Person{
    int height;
}

class Police extends Person{
    int weight; 
}

//이걸 일반화 할 수 있자나  - Person


