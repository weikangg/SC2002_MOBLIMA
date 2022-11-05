package managers;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import entities.*;
import view.adminApp;
import static utils.IOUtils.*;

public class SystemSettingsManager {
    private Scanner sc = new Scanner(System.in);

    private static SystemSettingsManager single_instance = null;
    public static SystemSettingsManager getInstance()
    {
        if (single_instance == null)
            single_instance = new SystemSettingsManager();
        return single_instance;
    }

    private SystemSettingsManager(){}
    static String path = System.getProperty("user.dir") +"\\data\\staffs\\staffsSettings.csv";
    static String separator = ",";

    public void staffMenu(int choice,Account account){
        int option = 0;
        
        try{
            if(choice == 0){
                System.out.println("==================== SYSTEM SETTINGS STAFF APP ====================\n" +
            					" 1. Configure Ticket Prices                                           \n" +
                                " 2. Configure Top 5 Rankings                                          \n" + 
			            		" 3. Configure Holidays                                                \n" +
                                " 4. Configure Rating Score Limit for Movies                           \n" +
			                    " 5. Back to Staff App                                                 \n" +
                                "=======================================================================");
                System.out.println("Enter choice: ");
                option = sc.nextInt();
                if(!(option >= 1 && option <=5)){
                    System.out.println("Please only enter a number from 1-5.");
                    staffMenu(0, account);
                }
            }
        }
        catch(InputMismatchException e){
            System.out.println("Invalid Input.");
            sc.nextLine();
            staffMenu(0, account);
        }
        switch (option) {
            case 1:
                break;
            case 2:
                configureTop5(account);
                break;
            case 3:
                configureHolidays(account);
                break;
            case 4:
                configureRatingScoreLimit(account);
                break;
            case 5:
                System.out.println("Back to Staff App......");
                sc.nextLine();
                adminApp.getInstance().displayLoggedInMenu(account);

            default:
                System.out.println("Invalid choice. Please choose between 1-4.");
                staffMenu(0,account);
        }
    }

    public void configureRatingScoreLimit(Account account){
        System.out.println("###########################################################");
		System.out.println("############## CONFIGURING DISPLAY OF RATINGS #############");
		System.out.println("###########################################################");
		System.out.println("");
        SystemSettings systemSettings = SystemSettings.getInstance();
        systemSettings.updatePermissions();
        while (true){
            System.out.println("Please choose an option:");
            System.out.println("1. Configure Rating Score Limit for Customers");
            System.out.println("2. Back to SystemSettingsManager");
            int option;
            try{
                option= sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Enter a number from 1-2 only!");
                sc.nextLine();
                continue;
            }
            switch (option) {
                case 1:

                    if(confirm("Confirm To Configure Rating Score Limit for Customers")){
                        while(true){
                           try{
                                System.out.println("Enter Rating Score Limit for Customers:");
                                double limit = sc.nextDouble();
                                sc.nextLine();
                                if(limit < 1 || limit > 5){
                                    System.out.println("Enter a valid rating score from 1-5 only!");
                                    continue;
                                }
                                systemSettings.setRatingScoreLimit(limit);
                                break;
                            }catch(InputMismatchException e){
                                System.out.println("Enter a valid rating score from 1-5 only!");
                                sc.nextLine();
                                continue;
                            }
                        }
                        updateSystemSettingsCSV(systemSettings);
                        System.out.println("System Settings Updated!");
                    }
                    else{
                        continue;
                    }
                    break;
                case 2:
                    System.out.println("Back to System Settings......");
                    this.staffMenu(0, account);
                default:
                    System.out.println("Invalid choice. Please choose between 1 or 2 only.");
                    continue;
            }
            break;
        }
    }

    public void configureTop5(Account account){
        System.out.println("###########################################################");
		System.out.println("#################### CONFIGURING TOP 5 ####################");
		System.out.println("###########################################################");
		System.out.println("");
        SystemSettings systemSettings = SystemSettings.getInstance();
        systemSettings.updatePermissions();
        while (true){
            System.out.println("Please choose an option:");
            System.out.println("1. Configure Top 5 Movies Settings");
            System.out.println("2. Back to SystemSettingsManager");
            int option;
            try{
                option = sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Enter a number from 1-2 only!");
                sc.nextLine();
                continue;
            }
            switch (option) {
                case 1:
                    if(confirm("Confirm To Configure Top 5 Movies Settings")){
                        while(true){
                            System.out.println("Should customers be able to see Top 5 Movies by Sales? (Y/N)");
                            String input = sc.nextLine();
                            if(input.equalsIgnoreCase("y")){
                                systemSettings.setTop5SalesPermission("Y");
                                break;
                            }
                            else if(input.equalsIgnoreCase("n")){
                                systemSettings.setTop5SalesPermission("N");
                                break;
                            }
                            else{
                                System.out.println("Please enter 'Y' for Yes or 'N' for No only!");
                            }
                        }
                        while(true){
                            System.out.println("Should customers be able to see Top 5 Movies by Overall Rating Score? (Y/N)");
                            String input = sc.nextLine();
                            if(input.equalsIgnoreCase("y")){
                                systemSettings.setTop5OverallRatingPermission("Y");
                                break;
                            }
                            else if(input.equalsIgnoreCase("n")){
                                systemSettings.setTop5OverallRatingPermission("N");
                                break;
                            }
                            else{
                                System.out.println("Please enter 'Y' for Yes or 'N' for No only!");
                            }
                        }
                        updateSystemSettingsCSV(systemSettings);
                        System.out.println("System Settings Updated!");
                    }
                    else{
                        continue;
                    }
                    break;
                case 2:
                    System.out.println("Back to System Settings......");
                    this.staffMenu(0, account);
                default:
                    System.out.println("Invalid choice. Please choose between 1 or 2 only.");
                    continue;
            }
            break;
        }

    }


    public void configureHolidays(Account account){
        
        System.out.println("###########################################################");
		System.out.println("################## CONFIGURING HOLIDAYS ###################");
		System.out.println("###########################################################");
		System.out.println("");

        while (true){
            System.out.println("Please choose an option:");
            System.out.println("1. List Holidays");
            System.out.println("2. Add a Holiday");
            System.out.println("3. Remove a Holiday");
            System.out.println("4. Back to SystemSettingsManager");
            int option; 
            try{
                option = sc.nextInt();
                sc.nextLine();
            }
            catch(InputMismatchException e){
                System.out.println("Please enter integers only.");
                sc.nextLine();
                continue;
            }
            List<Holidays>holidayList = HolidayListManager.getInstance().getHolidayList();
            switch (option) {
                case 1:
                    System.out.println("Current Holidays: ");
                    StaffHolidayCRUDManager.listHolidays();
                    break;
                case 2:
                    if (StaffHolidayCRUDManager.staffAddHoliday(holidayList)) {
                        System.out.println("Holiday Added!");
                    } 
                    else {
                        System.out.println("Failed to add holiday!");
                    }
                    break;
                case 3:
                    if(StaffHolidayCRUDManager.removeHolidayFromDatabase(holidayList)){
                        System.out.println("Holiday successfully removed!");
                    }
                    else{
                        System.out.println("Failed to remove holiday!");
                    }
                    break;
                case 4:
                    System.out.println("Back to Systems Settings......");
                    this.staffMenu(0,account);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose between 1-5.");
                    continue;
            }
            break;
        }
        staffMenu(0,account);
    }

    public static boolean updateSystemSettingsCSV(SystemSettings systemSettings){
        FileWriter csvWriter;
        try {
			csvWriter = new FileWriter(path,false);
			csvWriter.append("top5SalesPermission");
			csvWriter.append(separator);
			csvWriter.append("top5OverallRatingPermission");
            csvWriter.append(separator);
			csvWriter.append("RatingScoreLimit");
			csvWriter.append("\n");

            StringBuilder sb = new StringBuilder();
            sb.append(systemSettings.getTop5SalesPermission());
            sb.append(separator);
            sb.append(systemSettings.getTop5OverallRatingPermission());
            sb.append(separator);
            sb.append(Double.toString(systemSettings.getRatingScoreLimit()));
            sb.append('\n');
            csvWriter.append(sb.toString());
			csvWriter.flush();
			csvWriter.close();
			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }
}
