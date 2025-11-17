package p6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args)throws Exception {
        Thread.ofVirtual().start(()->{
            System.out.println("헬로");
        });

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.submit(()->{
            System.out.println("실행코드");
        });

        Thread.sleep(3000);
        System.out.println("메인쓰레드종료");


    }

}
