package UserInterface;

import java.util.Scanner;

public class InputCorrection {
    private final Scanner scanner = new Scanner(System.in);

    public int getInt( String msg, int min, int max) {
        while (true) {
            try {
                System.out.print(msg);
                int val = Integer.parseInt(scanner.nextLine());
                if (val >= min && val <= max) return val;
            } catch (Exception e) { /* erro de parse */ }
            System.out.println("Invalid input. Try again.");
        }
    }
    public String getString(String msg) {
        System.out.print(msg + " ");
        return scanner.nextLine();
    }
}
