package main;

import java.util.Scanner;

import staff.AdminApp;

/* 
 * Main app to run to choose either Customer or Staff App
 */

public class App {

    public static void main(String[] args){
        
        Scanner scan = new Scanner(System.in); //Scanner Object Instantiation
        int input = -1; //User Input
        
        System.out.println("======================= MOBLIMA APP =======================\n" + 
                                        "Please select an option:\n" +        
                                        "(1) Staff App           \n" +
                                        "(2) Customer App        \n" + 
                                        "(0) Exit \n " + 
                                        "===========================================================");
        System.out.print("Enter your choice: ");


        try { //Attempt User Input
            input = scan.nextInt(); 
        } catch (Exception e) { //Catch bad inputs
            scan.nextLine(); //Flush input
        }

        while(input < 1 || input > 2){ //Attempt User Input Again
            
            System.out.println("Please enter a valid selection"); //Error Msg
            System.out.println("");
            System.out.print("Enter your choice: ");

            try {
                input = scan.nextInt(); 
            } catch (Exception e) {
                scan.nextLine();
            }

        }

        switch(input){ //Switch User Input
            case 1: 
                AdminApp.getInstance().displayLoginMenu(); //Start Admin Module
                break;
            case 2:
                User.start(); //Start User Module
                break;
            default:
        }

        System.out.println("Thank you for using MOBLIMA!");
        System.out.println("Have a nice day.");

        scan.close();

    }

}
