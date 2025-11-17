package p10;

public class App {
    public static void main(String[] args) {
        //유닛은 탱크(강력하고 비싼), scv(전투에 능하지 않음), 마린(싸지만 빠름)
        //모든 유닛은 이동, 공격, 멈춤 기능  -- 이게 부모네 
        //각 유닛은 자신만의 이동, 공격 알고리즘이 있음  // 여기가 추상해도되겠구나의 포인트 
        // 유닛의 종류와 상관없이 12 유닛을 부대지정해서 한번에 이동, 공격, 멈춤 가능 // 이게 다형성 필요 이유 
        //다형성은 결국.....묶고나서 개별화하기위함이다 

        Unit[] unitList = new Unit[12];

        unitList[0] = new Marine(); // 다형성 
        unitList[1] = new Tank();
        unitList[2] = new Scv();
        unitList[3] = new Marine();
        unitList[4] = new Tank();
        unitList[5] = new Marine();
        unitList[6] = new Tank();
        unitList[7] = new Marine();
        unitList[8] = new Scv();
        unitList[9] = new Marine();
        unitList[10] = new Tank();
        unitList[11] = new Scv();

        for(Unit unit : unitList){
            unit.move(); // 문법적으로는 unit의 move지만 메모리적으로는 오버라이딩한 녀석으로 감. if나 다름이 없다 if보다 더 좋은거임 이게 
            unit.attack();
        }
    }

}

abstract class Unit{// 개념. 추상클래스. 상속을 위한 클래스. 생성불가. private과는 다름.문법적으로 생성 불가해짐.얘를 썼다는건 메서드도 abstract썼을거임. 근데 배열은 되네? 
    int hp;
    int ap;
    int speed;

    void move(){ 
        //구현부
    }
//메서드도 추상으로 바꿀수 있음 
    abstract void attack(); // 구현부 사라진당 존재는 하는데 실체는 없음. 규칙 = 얘를 상속받는 놈은 무조건 오버라이딩 되어있어야 함 (나는 구현하지 않을테니 자식이 구현해)
    
    final void stop(){ // final 붙이면 오버라이딩 불가함 
        System.out.println("유닛의 로직으로 멈춥니다"); // 실제구현됐당 
    }
    
}

class Tank extends Unit {
    Tank(){
        this.hp = 100; // 생성자로 초기값 지정 
        this.ap = 20;
        this.speed = 5;
        
    }
    void move(){
        System.out.println("Tank의 로직으로 " + speed+"만큼 이동합니다");
    }
    
    void attack(){
        System.out.println("Tank의 로직으로 " + ap +"만큼 공격합니다");
    
    }
    
    // void stop(){
    //     System.out.println("Tank의 로직으로 멈춥니다");
    // }
}


class Marine extends Unit {
    Marine(){
        this.hp = 50;
        this.ap = 5;
        this.speed = 3;
    }
    void move(){
        System.out.println("Marine의 로직으로 " + speed+"만큼 이동합니다");
    }
    
    void attack(){
        System.out.println("Marine의 로직으로 " + ap +"만큼 공격합니다");
    
    }
    
    // void stop(){
    //     System.out.println("Marine의 로직으로 멈춥니다");
    // }
}

class Scv extends Unit {
    Scv(){
        this.hp = 30;
        this.ap = 1;
        this.speed = 1;
    }
    void move(){
        System.out.println("Scv의 로직으로 " + speed+"만큼 이동합니다");
    }
    
    void attack(){
        System.out.println("Scv의 로직으로 " + ap +"만큼 공격합니다");
    
    }
    
    // void stop(){
    //     System.out.println("Scv의 로직으로 멈춥니다");
    // }
}
