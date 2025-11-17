import java.util.Scanner;

public class IoManager {
    private static Scanner scanner = new Scanner(System.in); 

    public static void print(String text){
        System.out.println(text);
    }
    public static String input(String text){
        System.out.print(text);
        return scanner.nextLine().trim();
    }

    public static void pause(){
        System.out.println("계속하시려면 enter를 입력하세요.");
        scanner.nextLine();
    }



}
