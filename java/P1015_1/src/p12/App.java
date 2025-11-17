package p12;

public class App {
    public static void main(String[] args) {
        final int a = 10; 
        // a = 20; // final이 붙으면 그 후에 초기화 안됨. 변수가 아니라 상수가 됨 
        final String databaseIP = "211.222.111.222"; // 

        System.out.println(Math.PI); // 파이는 안바뀌니까 final static. 대문자명명

        System.out.println(a);
    }
}


//final 
class Test {
    //절대불변:이런경우엔 모두 대문자로 명명+_로 단어구분 . 유일한 하나 
    public final static String STUDENT_NAME = "ddd";  

}
