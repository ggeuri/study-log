package p1;

public class App {
    public static void main(String[] args) {
        new Test1().start();        
        new Test2().start();        

        //인터페이스 정석
        new Thread(new Test3()).start(); 

        //람다식
        new Thread(() -> {
            for(int i = 0 ; i < 100; i++){
                System.out.println("인터 첫번째"+i);
          }
        }).start();       
        
        //람다식 버전 2 
        new Thread(App::print).start();

        System.out.println("프로그램 종료 X 메인스레드 종료");
        
    }
    public static void print(){
        for(int i = 0 ; i < 100; i++){
              System.out.println("인터 세번째"+i);
          }        
        
    }
}




//1. 스레드를 상속받기 
class Test1 extends Thread {
    //어노테이션오버라이딩 꼭 쓰기
    //Thread에 오버라이딩 할 메서드 없으면 컴파일 에러 일으킴(문법오류 뜸)
    @Override 
    public void run(){
          for(int i = 0 ; i < 100; i++){
                System.out.println("야호"+i);
            }        
         }
    }
class Test2 extends Thread {
    //어노테이션오버라이딩 꼭 쓰기
    //Thread에 오버라이딩 할 메서드 없으면 컴파일 에러 일으킴(문법오류 뜸)
    @Override 
    public void run(){
          for(int i = 0 ; i < 100; i++){
                System.out.println("으아"+i);
            } 
        }
    }   
    
    // 인터페이스 구현 방법 
class Test3 implements Runnable {
    @Override
    public void run() {
        for(int i = 0 ; i < 100; i++){
              System.out.println("인터 인터"+i);
          } 
      }
    }

