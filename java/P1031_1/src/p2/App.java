package p2;

public class App {
    public static void main(String[] args) {
        Test1 t1 = new Test1();
        t1.setPriority(Thread.MIN_PRIORITY); // 0~10까지 우선순위 주지만 우선순위가 보장되지는 않음 (직접 0,10넣으면 안돈다) 
        t1.start();
        
        Test2 t2 = new Test2();
        t2.setPriority(Thread.MAX_PRIORITY); // 0~10까지 우선순위
        t2.start();
    }

}

class Test1 extends Thread {
    public void run(){
          for(int i = 0 ; i < 100; i++){
                System.out.println("test 1: "+i);
        }        
    }
}

class Test2 extends Thread {
    public void run(){
          for(int i = 0 ; i < 100; i++){
                System.out.println("test2 : "+i);
        }        
     }
 }


