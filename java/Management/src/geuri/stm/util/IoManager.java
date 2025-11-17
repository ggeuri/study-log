package geuri.stm.util;

import java.util.Scanner;

public class IoManager {
    private static Scanner scanner = new Scanner(System.in);

    public static void println(String text){
        System.out.println(text);
    }

    public static void print(String text){
        System.out.print(text);
    }

    public static String inputCommand(){
        String command = scanner.nextLine();

        return command;
    }
    public static String input(String text){
        System.out.print(text);
        String inputValue = scanner.nextLine();

        return inputValue;
    }

    public static void pause(){
        IoManager.println("계속하시려면 enter를 눌러주세요.");
        scanner.nextLine();
    }
}
