import java.util.Scanner;

public class IoManager {
    public static Scanner scanner = new Scanner(System.in);
    
    public static void print(String text){
        System.out.println(text);
    }

    public static String input(String text){
        System.out.print(text);
        String value = scanner.nextLine().trim().toUpperCase();
        return value;
    }

    public static void pause(){
        scanner.nextLine();
    }

}
