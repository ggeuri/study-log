package p11;

public class App {
    public static void main(String[] args) {
        //interface - 추상화의 끝
        //상속 - 다형성 - 오버라이딩 다 쓴 케이스 
        AAA aaa = new QWER(); // 다형성 
        aaa.foo(); //오버라이딩 
    }

}

interface AAA{// 1. 인스턴스 변수 선언 불가 new AAA(); 불가 ! 
    public void test();//메서드인데 중괄호 없다 ? 그리고 interface는 추상메서드만선언가능함.  public abstract void test();라는 뜻임  
    // void test();  =  public void test(); = public abstract void test(); 무조건 추상화 메서드다 
    void foo(); //얘는 중괄호 열면 오류임 


    default void yyyy(){//반추상.부분추상화 원래는 abstract가 ...기본 
        System.out.println("FFFF"); 
    }

    int VALUE = 10; // 무조건 public static final 이라서 생략해도 public static final a = 10; 똑같음. public static final 은 보통 대문자로 씀
    public static void qqqqq() {
        System.out.println("EEEEEE");
    }

    

}

class QWER implements AAA { // implements 
    public void test() {

    }
    public void foo() {

    }


}
