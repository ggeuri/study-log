package p5;

public class App {
    public static void main(String[] args) {
        BBB bbb = System.out::println;
        bbb.test(3);
        bbb.test(5);
        bbb.test(7);
        
    }

}

interface BBB {
    public void test(int a); 
}