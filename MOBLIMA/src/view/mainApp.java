package view;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import entities.*;
import managers.AccountManager;

import java.io.File;

/* 
 * Main app to run to choose either Customer or Staff App 
 * Allows user to either run as Guest or Login to make bookings
 */

public class mainApp {

    private static mainApp newInstance = null;
    private static Scanner sc = new Scanner(System.in); //Scanner Object Instantiation
    public static mainApp getInstance(){
        if (newInstance == null){
            newInstance = new mainApp();
        }
        return newInstance;
    }
    public static void main(String[] args){

        File directory = new File("SC2002_OOP\\MOBLIMA").getAbsoluteFile();
        if (directory.exists()) System.setProperty("user.dir", directory.getAbsolutePath());
        directory = new File("MOBLIMA").getAbsoluteFile();
        if (directory.exists()) System.setProperty("user.dir", directory.getAbsolutePath());
        
        // System.out.println(System.getProperty("user.dir"));
        

        int input = -1; //User Input
        while (true){
            System.out.println("======================= MOBLIMA APP =======================\n" + 
                                "Please select an option: \n" +        
                                "(1) Login                \n" +
                                "(2) Continue As Guest    \n" +
                                "(3) Exit                 \n" +
                                "===========================================================");
            System.out.print("Enter your choice: ");


            try { //Attempt User Input
                input = sc.nextInt(); 
                sc.nextLine();
            } catch (InputMismatchException e) { //Catch bad inputs
                sc.nextLine(); //Flush input
                System.out.println("Please enter a valid selection (1-3) only.");
                continue;
            }

            while(input < 1 || input > 3){ //Attempt User Input Again

                System.out.println("Please enter a valid selection (1-3) only."); //Error Msg
                System.out.println("");
                System.out.print("Enter your choice: ");

                try {
                    input = sc.nextInt(); 
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    sc.nextLine();
                    continue;
                }

            }

            switch(input){ //Switch User Input
            // login
                case 1: 
                    AccountManager accountManager = AccountManager.getInstance();
                    List<Account>accountList = accountManager.getAccountList();
                    System.out.println("Enter Username: ");
                    while(!sc.hasNext()){
                        System.out.println("Error, please try again!");
                        sc.next();
                    }

                    String username = sc.nextLine();
                    System.out.println("Enter password: ");
                    while(!sc.hasNext()){
                        System.out.println("Error, please try again!");
                        sc.next();
                    }

                    String password = sc.nextLine();
                    boolean approved = accountManager.checkLogin(accountList,username,password);
                    if(approved){
                        Account account = accountManager.getAccount(accountList, username, password);
                        String accessLevel = account.getAccessLevel();
                        if(accessLevel.equals("C")){
                            customerApp.getInstance().customerLoggedInMenu(account);
                        }
                        else{
                            adminApp.getInstance().displayLoggedInMenu(account);
                        }
                    }
                    else{
                        System.out.println("Incorrect Username/Password! Try again!");
                        System.out.println("Create a new Customer Account by going into Customer Guest Menu?");
                        continue;
                    }
            case 2:
                customerApp.getInstance().customerGuestMenu();
                break;
            case 3:
                sc.close();
                System.out.println("Thank you for using MOBLIMA!");
                System.out.println("Have a nice day.");
                break;
            default:
                sc.close();
                System.out.println("Thank you for using MOBLIMA!");
                System.out.println("Have a nice day.");
                break;
            }
            break;
        }
        System.exit(0);
    }
}
