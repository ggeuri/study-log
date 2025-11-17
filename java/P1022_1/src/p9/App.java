package p9;

public class App {
    public static void main(String[] args) {
        new Some().test(()-> {
            System.out.println("사용자의 알고리즘");//qqqqq
        });
    
        new Some().test(()-> {
            System.out.println("사용자의 또다른 알고리즘");//qqqqq
        });
    }

}

//콜백함수 
interface Consumer{
    public void qqqqq();
}

class Some{
    public void test(Consumer aaaa) { // 파라미터로 알고리즘을 넘길 수 있음 
        System.out.println("테스트 시작 알고리즘");


        //여기 알고리즘은 호출한 사람이.(컨슈머)에서 짜줘 
        aaaa.qqqqq();    
        
        
        
        System.out.println("테스트 마무리 알고리즘");
    }
}







