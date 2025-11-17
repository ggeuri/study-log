package p1;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        System.out.println("코드 입력 (O/S/D/C)");
        String input = scanner.nextLine();

        try {
            Ordercode code = Ordercode.fromCode(input); 
            System.out.println(code.getDescription());
        } catch (Exception e) {
            System.out.println("잘못된 코드 입력입니다.");
        }
        
        

        
        
    }

}

enum Ordercode {
    ORDERED("주문완료","O")
    ,SHIPPED("배송중","S")
    ,DELIVERED("배송완료","D")
    ,CANCELED("취소됨","C");

    private final String description;
    private final String code;

    Ordercode(String description, String code){
        this.description = description; 
        this.code = code;
    }
    public String getDescription(){
        return description;
    }
    public String getCode(){
        return code;
    }

    public static Ordercode fromCode(String input) {
        for (Ordercode e : values()) {
            if(e.getCode().equalsIgnoreCase(input)){
                return e;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 코드");
    }

    }
