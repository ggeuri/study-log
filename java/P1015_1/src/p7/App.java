package p7;

public class App {
    public static void main(String[] args) { // 클래스 안에 있는 메서드였던것... JVM은 main을 찾아서 실행함 
        Test.a = 10;
        Test.b = 30;
        Test.qqqq();
        
    }

}

class Test {
    int c ; //인스턴스변수 
    static int a ; 
    static int b ; 
    static void qqqq() { // static 메서드는 제한이 있다. 인스턴스 생성과 상관없이 사용 가능 => 인스턴스 변수 사용 불가. "c" 존재안할수도있어서 사용불가임
    // static 붙어있으면 static만 쓸 수 있따 . 
        System.out.println("qqqq"); 
    } 
}

