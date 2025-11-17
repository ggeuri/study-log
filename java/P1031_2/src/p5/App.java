package p5;

public class App {
    public static void main(String[] args) {
        Person p = new Teacher();
        p.talk();
        
    }

}

class Person{

    public void talk(){

    }

}


class Teacher extends Person{

    public void talk(){
        System.out.println("학생들을 가르칩니다.");
    }

}