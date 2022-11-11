package boundaries;
import java.util.Scanner;


import entities.Account;

import java.util.InputMismatchException;
import java.util.List;

import managers.AccountManager;
import managers.StaffAccManager;

/**
 * The class for our adminApp 
 * @author Wei Kang
 * @version 2.5
 * @since 01-11-2022
 */

public class AdminMenu implements BaseMenuWithAccount {
	/**
	 * For singleton pattern adherence. This MovieListManager instance persists throughout runtime.
	 */
    private static AdminMenu newInstance = null;
	/**
	 * The scanner for reading input of user
	 */
    private static Scanner sc = new Scanner(System.in);
	/**
	 * The default constructor for the adminApp class
	 */
    private AdminMenu(){}

    /**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
    public static AdminMenu getInstance(){
        if (newInstance == null){
            newInstance = new AdminMenu();
        }
        return newInstance;
    }

    /**
     * Function to display logged in menu after staff logs in
     * @param account Staff account
	 */
    public void display(Account account){

        int choice;
            do {
                System.out.println(	"==================== MOBLIMA STAFF APP ====================\n" +
                                    " 1. Manage Movie Listings                                  \n" +
                                    " 2. Manage Movie Reviews                                   \n" +
                                    " 3. Manage Cinema Showtimes                                \n" +
                                    " 4. Configure System Settings                              \n" +
                                    " 5. Create New Staff Account                               \n" +
                                    " 6. Delete Staff Account                                   \n" +
                                    " 7. Logout from StaffApp                                   \n" +
                                    "===========================================================");
                System.out.println("Enter choice: ");
    
                while(true){
                    try{
                        choice = sc.nextInt();
                        sc.nextLine();
                        break;
                    }catch(InputMismatchException e){
                        System.out.println("Invalid Input! Enter 1-7 only!");
                        sc.nextLine();
                        display(account);
                    }
                }
                switch(choice){
                    case 1:
                        MovieManagerMenu.getInstance().display(account);
                        break;
                    case 2:
                        StaffReviewMenu.getInstance().display(account);
                        break;
                    case 3:
                        StaffShowTimeManagerMenu.getInstance().display(account);
                        break;
                    case 4:
                        SystemSettingsMenu.getInstance().display(account);
                        break;
                    case 5:
                        if(account.getAccessLevel().equals("SA")){
                            List<Account>accountList = AccountManager.getInstance().getAccountList();
                            if(StaffAccManager.getInstance().createStaffAccount(accountList)){
                                System.out.println("Account created!");
                            }
                            else{
                                System.out.println("Failed to create account!");
                            }
                        }
                        else{
                            System.out.println("No access!");
                        }
                        continue;
                    case 6:
                        if(account.getAccessLevel().equals("SA")){
                            List<Account>accountList = AccountManager.getInstance().getAccountList();
                            int result = StaffAccManager.getInstance().removeStaffAccount(accountList);              
                            if(result == 1){
                                System.out.println("Account removed!");
                            }
                            else if(result == 2){
                                ;
                            }
                            else{
                                System.out.println("Failed to remove account!");
                            }
                        }
                        else{
                            System.out.println("No access!");
                        }
                        continue;
                    case 7: 
                        System.out.println("Logging out from StaffApp, have a nice day!");
                        MainMenu.getInstance().display();
                        break;
                    
                    default:
                        System.out.println("Error input, please only choose 1-7.");
                        break;
                }
            }while(choice != 7);
    }

}
