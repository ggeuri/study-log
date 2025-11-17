package polymorpism;

//인스턴스 생성 관리, 조립 - 다른 기능 없음 
//설정에 해당하는 클래스 = 나중에 프레임워크 따라 xml, json, 어노테이션 설정
public class BeansContainer {
    private User user; 
    
    public BeansContainer() {
     // 생성 및 조립 설정
     Speaker speaker = new AppleSpeaker();
     Tv tv = new LgTv(speaker);
     user = new User(tv);    //조립중 ~ tv - user연결해주기  

    }
    public User getUser(){
        return user;
    }

    Speaker speaker = new AppleSpeaker();
    


    //팩토리 
    // public Tv getTv(String name){
    //     if(name.equals("samsung")){
    //         return new SamsungTv();
    //     }
    //     else if(name.equals("LG")){
    //         return new LgTv();
    //     }
    //     return null;
    // }
}
