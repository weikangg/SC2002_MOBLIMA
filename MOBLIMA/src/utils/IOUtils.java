package utils;

import java.util.Scanner;
public class IOUtils {

    private static Scanner sc = new Scanner(System.in);
    /**
     * Confirmation Message
     * --------------------
     * This method only accept 'y' or 'n' as input
     * return 	 true  if input is 'y' or
     * return 	 false if input is 'n'
     * Otherwise it will repeatedly prompt user for input
     * @param message string value that need to be printed out
     * @return boolean value of the choice selected by the user
     */
    public static boolean confirm(String message) {
        while (true) {
            String in = read(message + " (Y/N): ");
            if (in.equalsIgnoreCase("y"))
                return true;
            else if (in.equalsIgnoreCase("n"))
                return false;
        }
    }
    /**
     * Method to notify user about the input String
     * @param message info about input
     * @return validate String otherwise Exception
     */
    public static String read(String message) {
        String input = "";
        print(message);

        do {
            input = sc.nextLine();
        } while (input.trim().equals(""));

        return input;
    }
    /**
     * Print Message
     * @param message message to be display
     */
    public static void print(String message) {
        System.out.print(message);
    }

}
