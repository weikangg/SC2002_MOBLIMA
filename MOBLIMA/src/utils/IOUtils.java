package utils;

import java.util.Scanner;

/**
 * A utility class allowing easy access to functions which are repeatedly used
 * @author Wei Kang
 * @version 1.0
 * @since 01-11-2022
 */
public class IOUtils {
	/**
	 * The scanner for reading input of user
	 */
    private static Scanner sc = new Scanner(System.in);
    /**
     * Function to allow user to confirm his choice
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
            if (in.equalsIgnoreCase("y")){
                return true;
            }
            else if (in.equalsIgnoreCase("n")){
                System.out.println("Returning Back....");
                return false;
            }
            else{
                System.out.println("Invalid Input! Please only enter 'Y' for Yes or 'N' for No!");
                continue;
            }
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
        System.out.println(message);
    }
	
    /**
     * Asks for user input within a specified range
     * @param from starting range
     * @param to ending range
     * @param sc scanner class
     * @return the user's choice
     */
    public static int check(int from, int to, Scanner sc)
    {
        int choice;
        while (!sc.hasNextInt()) { // Not a string
            sc.next(); // Remove newline character
            System.out.println("Invalid input! Enter value ("+from+"-"+to+"):");
        }
        choice = sc.nextInt();
        sc.nextLine();
        while(choice < from || choice > to)
        {
            //sc.next(); // Remove newline character
            System.out.println("Invalid input! Enter value ("+from+"-"+to+"):");
            while (!sc.hasNextInt()) { // Not a string
                sc.next(); // Remove newline character
                System.out.println("Invalid input! Enter value ("+from+"-"+to+"):");
            }
            choice = sc.nextInt();
            sc.nextLine();
        }
        return choice;
    }

}
