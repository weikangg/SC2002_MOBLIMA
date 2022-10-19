package main;

import java.util.Scanner;

public class App {

    public static void main(String[] args){
        
        Scanner scan = new Scanner(System.in); //Scanner Object Instantiation
        int input = -1; //User Input
        
        System.out.println("");
        System.out.println("Welcome to __ Cinemas!");
        System.out.println("");
        System.out.println("Please select an option:");
        System.out.println("");       
        System.out.println("(1) Admin Module");
        System.out.println("(2) User Module");
        System.out.println("(0) Exit");
        System.out.print("Enter your choice: ");


        try { //Attempt User Input
            input = scan.nextInt(); 
        } catch (Exception e) { //Catch bad inputs
            scan.nextLine(); //Flush input
        }

        while(input < 0 || input > 2){ //Attempt User Input Again
            
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
                Admin.start(); //Start Admin Module
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
