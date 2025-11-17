package p5;

import java.util.concurrent.CompletableFuture;

public class App {
    public static void main(String[] args)throws Exception {
        
        // 자바 1.8 이후부터 나온 
        CompletableFuture.runAsync(SomeClass::test)
            .thenRun(()-> System.out.println("비동기 완료")) ; //Async면 비동기 Sync면 동기 
            //thenRun은 이 스레드가 끝나면 시행하겠다 

        CompletableFuture.supplyAsync(() -> { //얘도 비동기 시행. [supply : 파라미터는 없고 리턴타입은 있는] 
            int sum = 0 ; 
            for(int i = 0 ; i < 100 ; i ++ ){
                sum += i; 
            }
            return sum;
        }).thenAccept(result -> {
            System.out.println("결과 : " + result);
        }); 

        // SomeClass.test();

        Thread.sleep(3000); // 메인스레드 종료안시키려고 걸었음 

        System.out.println("프로그램종료");

 
    }

}

class SomeClass{
    public static void test(){
        for(int i = 0 ; i < 100 ; i++){
            System.out.println("야호"+i);
        }
    }
}
