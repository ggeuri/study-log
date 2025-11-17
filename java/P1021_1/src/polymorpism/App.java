package polymorpism;

public class App {
    public static void main(String[] args) {
        BeansContainer beansContainer = new BeansContainer(); 
        //상속 - 다형성 - 오버라이딩 = 인터페이스 
        // 표준을 정의하고 조립식으로 만들자
        //DI, Ioc, Container(Factory)

        User user = beansContainer.getUser();
        user.watchTv();
    }

}
