package p3;

public class App {
    public static void main(String[] args) throws Exception {
        // 비동기 코드 작성 시 주의사항
        // 상당수는 데이터를 공유하지 않음 -> 편하게 비동기 코드 작성해도됨
        // 여러 쓰레드(비동기 코드가) 메모리를 공유하면 많이 힘들어짐. 동기화 필수 

        Test t1 = new Test();
        t1.start();
        
        Test t2 = new Test();
        t2.start();

        t1.join(); // join 동기화 메서드 - t1쓰레드 끝날때까지 기다림 
        t2.join(); // join 동기화 메서드 - t2쓰레드 끝날때까지 기다림 


        System.out.println(Data.count); // 왜 0,200나옴 !? -> join추가하면 ->  왔다갔다함..  특정값을 나오게 할 수 없음 
        //스레드마다 두개의 i임 내부변수니까 
        //어.. 혹시 AML시스템 저장 두번되고 그러던거 .. 
    }
    
}

class Test extends Thread {
    static int i = 0 ; 
    @Override
    public void run(){
        for(int i = 0 ; i < 100 ; i ++ ){
            Data.increaseCount();
        }
    }

}


class Data {
    public static int count = 0 ;
    //아 쪼개서 수행하면..
    public synchronized static void increaseCount(){ //synchronized 붙여주면 동기화됨 중간에안멈춤 
        //시간오래걸리는코드
        
        int temp = count;
        // System.out.println("어떤어떤 코드");//일부러오버헤드발생시킬목적
        int a = 10; 
        a++;
        a++;//a스레드가 이거하다말고 멈추고 b스레드가 다시 처음부터 할 수도 있음. 중간에 안멈췄으면 운좋게 200 뜨는거고 멈췄으면 수 깎이는거임 
        a++;
        temp++;
        count = temp;
        //시간오래걸리는코드 -- 이런게 있으면 얘만 따로 빼서 메서드. 싱크로나이즈드해야함 
    }
}