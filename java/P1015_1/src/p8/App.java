package p8;

public class App {
    public static void main(String[] args) {
        Test t1 = new Test();
        t1.a = 10; 

        t1.qqqq(); // private 사용불가 . public 사용가능

    }


}


//접근 제한자 - 캡슐화, 정보의 은닉
// private가 붙은 녀석은 그 클래스 내에서만 사용할 수 있음. [문법적으로]  
class Test { 
    public int a; // 중요 
    private int d; // 중요. 내부에서만 쓸 수 있나벼? 

    public void qqqq() { // private면 API아님. 공개안되니까
        a = 10; 
        d = 10;  // 내부이용~ 
    }

}