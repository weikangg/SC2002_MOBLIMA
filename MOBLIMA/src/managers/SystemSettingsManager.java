package managers;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import entities.Holidays;
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

    public void staffMenu(int choice){
        int option = 0;
        
        try{
            if(choice == 0){
                System.out.println("==================== SYSTEM SETTINGS STAFF APP ====================\n" +
            					" 1. Configure Ticket Prices                                           \n" +
                                " 2. Configure Top 5 Rankings                                          \n" + 
			            		" 3. Configure Holidays                                                \n" +
			                    " 4. Back to MOBLIMA Staff App                                         \n" +
                                "=======================================================================");
                System.out.println("Enter choice: ");
                option = sc.nextInt();
                if(!(option >= 1 && option <=4)){
                    System.out.println("Please only enter a number from 1-4.");
                    staffMenu(0);
                }
            }
        }
        catch(InputMismatchException e){
            System.out.println("Invalid Input.");
            sc.nextLine();
            staffMenu(0);
        }
        switch (option) {
            case 1:
                break;
            case 2:
                configureTop5();
                break;
            case 3:
                configureHolidays();
                break;

            case 4:
                System.out.println("Back to Staff App......");
                sc.nextLine();
                adminApp a = adminApp.getInstance();
                try{
                    Method m = adminApp.class.getDeclaredMethod("displayLoggedInMenu");
                    m.setAccessible(true);
                    m.invoke(a);
                }catch(NoSuchMethodException e){
                    System.out.println("No such menu!");
                }catch(InvocationTargetException e){
                    System.out.println("Invocation error!");
                }catch(Exception e){
                    System.out.println("Error!");
                }
                break;

            default:
                System.out.println("Invalid choice. Please choose between 1-4.");
                break;
        }
    }

    public void configureTop5(){
        System.out.println("###########################################################");
		System.out.println("#################### CONFIGURING TOP 5 ####################");
		System.out.println("###########################################################");
		System.out.println("");
        Top5Movies top5MoviesObj = Top5Movies.getInstance();
        while (true){
            System.out.println("Please choose an option:");
            System.out.println("1. Configure Top 5 Movies Settings");
            System.out.println("2. Back to SystemSettingsManager");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    sc.nextLine();
                    if(confirm("Confirm To Configure Top 5 Movies Settings")){
                        while(true){
                            System.out.println("Should customers be able to see Top 5 Movies by Sales? (Y/N)");
                            String input = sc.nextLine();
                            if(input.equalsIgnoreCase("y")){
                                top5MoviesObj.setTop5SalesPermission("Y");
                                break;
                            }
                            else if(input.equalsIgnoreCase("n")){
                                top5MoviesObj.setTop5SalesPermission("N");
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
                                top5MoviesObj.setTop5OverallRatingPermission("Y");
                                break;
                            }
                            else if(input.equalsIgnoreCase("n")){
                                top5MoviesObj.setTop5OverallRatingPermission("N");
                                break;
                            }
                            else{
                                System.out.println("Please enter 'Y' for Yes or 'N' for No only!");
                            }
                        }
                        updateSystemSettingsCSV(top5MoviesObj);
                        System.out.println("System Settings Updated!");
                    }
                    else{
                        continue;
                    }
                    break;
                case 2:
                    sc.nextLine();
                    System.out.println("Back to System Settings......");
                    this.staffMenu(0);
                default:
                    System.out.println("Invalid choice. Please choose between 1 or 2 only.");
                    continue;
            }
            break;
        }

    }


    public void configureHolidays(){
        
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
            switch (option) {
                case 1:
                    System.out.println("Current Holidays: ");
                    Holidays.listHolidays();
                    break;
                case 2:
                    if (StaffAddHolidayToList.staffAddHoliday(HolidayListManager.getInstance().getHolidayList())) {
                        System.out.println("Holiday Added!");
                    } 
                    else {
                        System.out.println("Failed to add holiday!");
                    }
                    break;
                case 3:
                    if(StaffRemoveHolidayManager.removeHolidayFromDatabase(HolidayListManager.getInstance().getHolidayList())){
                        System.out.println("Holiday successfully removed!");
                    }
                    else{
                        System.out.println("Failed to remove holiday!");
                    }
                    break;
                case 4:
                    System.out.println("Back to Staff App......");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose between 1-5.");
                    continue;
            }
            break;
        }
        staffMenu(0);
    }

    public static boolean updateSystemSettingsCSV(Top5Movies top5MoviesObj){
        FileWriter csvWriter;
        try {
			csvWriter = new FileWriter(path,false);
			csvWriter.append("top5SalesPermission");
			csvWriter.append(separator);
			csvWriter.append("top5OverallRatingPermission");
			csvWriter.append("\n");

            StringBuilder sb = new StringBuilder();
            sb.append(top5MoviesObj.getTop5SalesPermission());
            sb.append(separator);
            sb.append(top5MoviesObj.getTop5OverallRatingPermission());
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
