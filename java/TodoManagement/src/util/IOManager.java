package util;

import java.util.Scanner;

public class IOManager {
    private static Scanner scanner = new Scanner(System.in);

    private IOManager(){}

    public static String input(String text){
        System.out.print(text);
        return scanner.nextLine();
    }

    public static void print(String text){
        System.out.println(text);
    }

    public static void pause(){
        System.out.println("계속 진행하시려면 enter를 입력하세요.");
        scanner.nextLine();
    }
    }

