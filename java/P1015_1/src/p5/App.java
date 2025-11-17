package p5;

public class App {
    public static void main(String[] args) {

        System.out.println();
        System.out.append('0');
        System.out.println();
        
        int result = Math.max(1, 95930);
        System.out.println(result);

        double result3 = Math.abs(8213.3);
        System.out.println(result3); 

        String str = "안녕,하세,요";

        String[] result4 = str.split(",");
        System.out.println(result4[0]);

        String str2 = "반갑";
        str2.equals("반갑");
        "반갑".equals(str); // 두개 동일한 코드임 

        int result6 = "안녕".length();
        System.out.println(result6);

        String cggg = "sdTDfddrs".toUpperCase();
        System.out.println(cggg);

        String cffff = "sdtdrs".toUpperCase().toLowerCase(); // String[참조변수]이니까 접근연산자 계속 붙일수있네
        System.out.println(cffff);

        int cffdf = "sdtdrs".toUpperCase().toLowerCase().length(); // String[참조변수]이니까 접근연산자 계속 붙일수있네. 대신 length는 int니까 뒤에 못붙임 
        System.out.println(cffdf);

        int sub = "aaaa.jpg".substring("aaaa.jpg".indexOf(".")).length();
        System.out.println(sub);






    }

}
