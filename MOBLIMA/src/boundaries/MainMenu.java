package boundaries;

import java.util.*;
import entities.*;
import managers.*;

/**
 * The class for the main menu
 * @author Wei Kang
 * @version 3.0
 * @since 11-11-2022
 */

public class MainMenu implements BaseMenu {
	/**
	 * The scanner for reading input of user
	 */
    private static Scanner sc = new Scanner(System.in); //Scanner Object Instantiation
    	/**
	 * For singleton pattern adherence. This mainApp instance persists throughout runtime.
	 */
    private static MainMenu newInstance = null;
    /**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
    public static MainMenu getInstance(){
        if (newInstance == null){
            newInstance = new MainMenu();
        }
        return newInstance;
    }
    /**
     * Main Menu
     * Login to go to either Staff or Customer Menu based on account's access level
     * Or simply continue as Guest
     */
    @Override
    public void display() {

        int input;
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
                            CustomerLoggedInMenu.getInstance().display(account);
                        }
                        else{
                            AdminMenu.getInstance().display(account);
                        }
                    }
                    else{
                        System.out.println("Incorrect Username/Password! Try again!");
                        System.out.println("Create a new Customer Account by going into Customer Guest Menu?");
                        continue;
                    }
            case 2:
                CustomerGuestMenu.getInstance().display();
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