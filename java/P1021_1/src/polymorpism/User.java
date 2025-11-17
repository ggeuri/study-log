package polymorpism;

public class User { 
    private Tv tv; //방법 1. 일반적으로는 멤버로 등록해줌 - 얘만 있으면 NP
    
    //DI = 의존 주입(생성자 주입) 보통 생성자 - Setter 둘 중 하나로 선택함
    public User(Tv tv){
        this.tv = tv; 
    }
    
    public void watchTv(){

        System.out.println("사용자의 Tv 시청 시작!");

        tv.powerOn();
        tv.volumeUp();
        tv.volumeDown();
        tv.powerOff();
        
        System.out.println("사용자의 Tv 시청 끝!");
        
        
    }


    
}

