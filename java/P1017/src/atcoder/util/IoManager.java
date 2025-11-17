package atcoder.util;

import java.util.Scanner;

public class IoManager {
    private static Scanner scanner = new Scanner(System.in);

    private IoManager() {} 

    public static int inputInt(String text) {
        System.out.print(text);
        String line = scanner.nextLine();
        int value = Integer.parseInt(line);
        return value;
    }

    public static int[] inputInts(int count, String prompt) {
        int[] arr = new int[count];
        System.out.println(prompt);
        for (int i = 0; i < count; i++) {
            System.out.print((i + 1) + "번째 정수 > ");
            String line = scanner.nextLine();
            arr[i] = Integer.parseInt(line);
        }
        return arr;
    }

    public static void print(String text) {
        System.out.print(text);
    }

    public static void println(String text) {
        System.out.println(text);
    }

    public static void close() {
        scanner.close();
    }
}