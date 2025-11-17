package p5;

public class App {
    public static void main(String[] args) {
        Test t1 = new Test(); 
        // t1.test1();
        
    }

}

class Test{
    private static int a; 
    private int b ;

    public void test1(AAA a){}
    private static void test2(){}

}

class AAA{
    BBB b = new BBB();//멤버임 선이 다름.둘이 라이프사이클똑같아서 아예 Composition으로 그림 
    CCC c; // AAA가 죽는다고해서 죽지는 않음 물건소유느낌

    public void aaa(){
        BBB b = new BBB(); // 의존한다 Dependency선  
    }

}

class BBB {

    
}

class CCC {

}