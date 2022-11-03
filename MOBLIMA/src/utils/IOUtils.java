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
     * Method to check the validate input of integer
     * @param Message message that need to be printed
     * @return validate integer otherwise NumberFormatException
     */
    // public static int readInt(String Message) {
    //     print(Message);
    //     String s;
    //     int value = -1;
    //     while (true) {
    //         try {
    //             s = sc.next();
    //             value = Integer.parseInt(s);
    //             if(value!= -1){
    //                 break;
    //             }
    //         } catch (NumberFormatException e) {
    //             print("Please input a valid Integer number.");
    //             print(Message);
    //             s = sc.next();
    //             value = Integer.parseInt(s);
    //             if(value != -1){
    //                 break;
    //             }
    //         }
    //     }
    //     return Integer.parseInt(s);
    // }
    // /**
    //  * Method to check the validate input of double
    //  * @param Message message that need to be printed
    //  * @return validate double otherwise NumberFormatException
    //  */
    // public static double readDouble(String Message) {
    //     print(Message);
    //     String s;
    //     double value = -1;
    //     while (true) {
    //         try {
    //             s = sc.next();
    //             value = Double.parseDouble(s);
    //             if(value != -1){
    //                 break;
    //             }

    //         } catch (NumberFormatException e) {
    //             print("Please input a valid number.");
    //             print(Message);
    //             s = sc.next();
    //             value = Double.parseDouble(s);
    //             if(value != -1){
    //                 break;
    //             }
    //         }
    //     }
    //     return Double.parseDouble(s);
    // }
    /**
     * Print Message
     * @param message message to be display
     */
    public static void print(String message) {
        System.out.println(message);
    }

}
