package p7;

public class App {
    public static void main(String[] args) {
        BBB b1 = new BBB(); 

        System.out.println(b1.a2);

        b1.testB(); // AAA생성하지 않았어도 생성자 호출됨. 


    }

}

class AAA{
    private int a1 = 10;
    protected int a2 = 20;

    AAA(){
        System.out.println("AAA 생성자 호출됨! a1과 a2 초기화하는 중요한 코드 ");
    }
    AAA(int a1){
        System.out.println("AAA 생성자 2번째 생성");
    }
    
}

class BBB extends AAA{
    int a1 = 30;  // 이렇게되면 변수명 똑같은데 메모리는 5개가 됨 이런거하면안된당 ㅠ 
    int b1 = 40;
    int b2 = 50;

    BBB(){
        super(1);//AAA의 생성자가 오버로딩되어있을때 생성자 선택할 수 있음.
        System.out.println("BBB 생성자 호출됨. b1,b2 초기화하는 중요한 코드"); // 초기화한다는건 뭔말이징
    }

    void testB(){
        System.out.println(this.a1);
        System.out.println(super.a2);//상속 위에 있는걸 접근할 수 있는 문법 

    }
}