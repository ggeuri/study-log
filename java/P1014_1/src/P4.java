import java.util.Scanner;

public class P4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //남들이 만들어놓은 클래스를 사용하는거다 이런걸API라고 부름.
        // Scanner scanner = new Scanner(); //이게 오류라는 것 = 매개변수(파라미터)에서 요구하는 바가 있다는 것 

        Worker w1 = new Worker("철수"); // 인스턴스 생성 - 메모리 생성 - 생성자 실행 (생성자는 '자동' 1회'만' 실행) 
        Worker w2 = new Worker("영희");

        w1.test1(); // 메서드 호출 문법. 메서드의 경우 참조변수를 사용해서 실행할 수 있음. 수동호출 (메서드는 '수동', '원할때마다' 실행)
        w2.test1(); // w1이든 w2든 상관없이 동일한 메서드 호출 

        w1.test2(15,23);   
        int aaa = w1.test2(9, 5);  // 리턴타입 int였으니까 int로 하고 


        System.out.println(aaa);


        System.out.println("프로그램 종료");

    }

}

class Worker {
    //속성
    String name;
    int money;

    //생성자 
    Worker(String name) {
        this.name = name; 
    }
    //기능-매서드 (정의)
    void test1(){ // "void - 리턴타입" "test1 - 메서드명/보통은 동사. 소문자" "() - 매개변수를 쓸 수 있음" "{} - 실행될 코드 작성"
        System.out.println("test1 호출됨");
    }
    int test2(int a, int b) { //매개변수(파라미터) 넘겨주기 , 리턴타입이 void면 리턴타입이 없다는 뜻임 ! 
        
        System.out.println("test2 호출됨");

        if(a<0 || b<0) {
            System.out.println("음수는 허용되지 않습니다.");
            return -1; 
        } 
        System.out.println("오호?");
        return a+b; // 리턴타입이 int기 때문에 리턴코드가 꼭 필요함. 당연히 int가 리턴되어야함 타입미스매치안됨! 
        // System.out.println();
    }
    void test3(String text) {
        if(text.contains("바보")) {
            System.out.println("비속어는 사용 불가능합니다.");
            return;   
        }
        
        System.out.println(text); //println 메서드 호출하는 문법이었다... 
    }
    void test3(String test,int a){

    }
}


//메서드도 오버로딩 가능합니다. 
//단 이름만 똑같고 파라미터(매개변수)는 달라야함

