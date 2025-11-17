package p3;

public class App {
    public static void main(String[] args) {
        // 예외 처리 - 사실상 정말 중요한 문법 - 프로그램 안정성 확보 
        // 기능이 구현 가능한 상태(실력)에서 그 다음으로 가장 중요한 

        // 런타임 에러 (문법 에러 아님) : 실행 과정 중 에러 

        System.out.println("프로그램 시작");
        
        int a = 10;
        int b = 0;

        try{
            System.out.println("로직 1"); //실행할 코드 1 : 여긴 시도했을때 OK니까 실행함
            System.out.println(a/b);//실행할 코드 2 : 여긴 0인 경우 exception뜨니까 catch문으로 
        } catch(Exception e){
            System.out.println("에러가 났어요. 다만 프로그램이 뻗지 않습니다.");
            //try 다 정상이면 catch안함.
            //실력있는 개발자의 차이 catch를 얼마나 잘 고민해서 다루는가 Exception handling 
            //catch문에는 1. 에러를 회복하기 위한 코드를 짜거나(복잡한 케이스) 2. 사용자에게 알려준다(쉬운 케이스)
        }

        System.out.println("프로그램 끝");

        
    }

}
