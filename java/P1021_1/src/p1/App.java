package p1;

public class App {
    public static void main(String[] args) {
        //상속 -  다형성 - 오버라이딩 = > 인터페이스 

        Walkable aa = new Person(); 
        aa.walk(); // 인터페이스 기준으로 코드를 짜지만 실행만 후자가(조립식입 뒤에만 Person,Duck 끼워넣으면 밑 코드가 죄다 실행이 바뀌는거임) 되는거임 

        Walkable bb = new Duck(); 
        Flyable cc = new Duck(); 

        bb.walk();
        cc.fly();

        Walkable[] walkList = new Walkable[2]; 

        walkList[0] = new Duck();
        walkList[1] = new Person();

        for(Walkable walkable: walkList){
            walkable.walk();

            //덕은 이중상속이니까 둘다쓰고싶으면..fly,walk..

            Duck duck = new Duck(); 
            duck.fly();
        }

    }

}



interface Flyable{
    public void fly(); //인터페이스는 abstract 안써도 abstract임. 당연한거임. 


}

interface Walkable{
    public void walk(); 
}

class Person implements Walkable{
    public void walk() {
        System.out.println("사람의 로직으로 걷습니다.");
    }
}

class Duck implements Flyable,Walkable { //원래 다중상속안되는데 interface만 ! 다중상속이 됨. 확장
    public void fly() {
        System.out.println("오리의 로직으로 날아갑니다");
    }
    public void walk() {
        System.out.println("오리의 로직으로 걷습니다.");
    }
}

//인터페이스끼리 상속도 가능하나 중요치않다 