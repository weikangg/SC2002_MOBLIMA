package boundaries;
import java.util.*;
import entities.*;
import managers.*;

public class SystemSettingsMenu implements BaseMenuWithAccount{
        /**
	 * The scanner for reading input of user
	 */
    private Scanner sc = new Scanner(System.in);    
    /**
	 * For singleton pattern adherence. This MovieManager instance persists throughout runtime.
	 */
    private static SystemSettingsMenu single_instance = null;
	/**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
    public static SystemSettingsMenu getInstance()
    {
        if (single_instance == null)
            single_instance = new SystemSettingsMenu();
        return single_instance;
    }
/**
	 * Staff's Menu to manage system settings
     * Choose options to configure ticket prices, customer's access to view top 5 movies, holidays, rating score limit for movies
     * @param account User's account
	 */
    public void display(Account account){
        int option = 0;
        
        try{
                System.out.println("==================== SYSTEM SETTINGS STAFF APP ====================\n" +
            					" 1. Configure Ticket Prices                                           \n" +
                                " 2. Configure Top 5 Movies Permissions                                \n" + 
			            		" 3. Configure Holidays                                                \n" +
                                " 4. Configure Rating Score Limit for Movies                           \n" +
			                    " 5. Back to Staff App                                                 \n" +
                                "=======================================================================");
                System.out.println("Enter choice: ");
                option = sc.nextInt();
                if(!(option >= 1 && option <=5)){
                    System.out.println("Please only enter a number from 1-5.");
                    display( account);
                }
        }
        catch(InputMismatchException e){
            System.out.println("Invalid Input.");
            sc.nextLine();
            display(account);
        }
        switch (option) {
            case 1: 
                SystemSettingsManager.getInstance().configureTicketPriceMenu(account);
                break;
            case 2:
                SystemSettingsManager.getInstance().configureTop5(account);
                break;
            case 3:
                SystemSettingsManager.getInstance().configureHolidays(account);
                break;
            case 4:
                SystemSettingsManager.getInstance().configureRatingScoreLimit(account);
                break;
            case 5:
                System.out.println("Back to Staff App......");
                sc.nextLine();
                AdminMenu.getInstance().display(account);

            default:
                System.out.println("Invalid choice. Please choose between 1-4.");
                display(account);
        }
    }

}
