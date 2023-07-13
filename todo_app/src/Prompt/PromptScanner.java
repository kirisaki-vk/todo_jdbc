package Prompt;

import java.util.Scanner;

public class PromptScanner {
     private static Scanner scanner = new Scanner(System.in);
    public static Scanner get() {
        return scanner;
    }
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
