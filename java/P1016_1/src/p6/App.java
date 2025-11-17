package p6;

public class App {
    public static void main(String[] args) {
        Car car1 = new Car(); // 외부에서 생성
        Person person1 = new Person();

        person1.setCar(car1);

        Person person2 = new Person();

        person2.setCar(car1); // 둘이 소유할 수도 이찌 
    }

}


class Person {

    Eye left = new Eye();
    Eye right = new Eye();
    Car car; 

    // DI = 의존 주입 코드(생성자 주입), 의존주입=적어도 Person이 car를 생성하지는 않는다.Person외부에서 Car 생성된 걸 주입받음.
    Person( ) {
        this.car = car; 
    }
    // DI = 의존 주입 코드(setter 주입)
    void setCar(Car car){
        this.car = car; 

    }

}

class Eye {

}

class Car {

}

