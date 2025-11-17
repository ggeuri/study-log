package p1;

public class App {
    public static void main(String[] args) {
        //이너클래스 [4개중 두개는알아둬야]

        AAA.CCC ccc = new AAA.CCC();//얜 static이라 이렇게쓸수있음. 안드로이드 프레임워크에서 
    }

}


class AAA{


    static class CCC{ //주구장창 사용되는 케이스..? 코드를 볼 일은 없음
    
    }
    public void testA(){
        int a = 10;
        int b = 10;


        System.out.println("ffff");
    }
}