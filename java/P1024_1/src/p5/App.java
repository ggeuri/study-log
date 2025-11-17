package p5;

public class App {
    //정규표현식 - 대부분의 언어, 데이터베이스에서 지원함.
    //문자를 특수한 형태로 판별하는 문법 (올바른이메일인지 구분하기 뭐 이런거)
    public static void main(String[] args) {
        String accountName = "asds1234"; // id
        
        String test = "[a-z]+[0-9]*"; 
        if(accountName.matches(test)){
            System.out.println("패턴에 부합");
        } else{
            System.out.println("패턴안맞아옹");
        }
     
        String password = "";

        test = "[a-z]+[0-9]*"; 
        if(accountName.matches(test)){
            System.out.println("패턴에 부합");
        } else{
            System.out.println("패턴안맞아옹");
        }
    }

}
