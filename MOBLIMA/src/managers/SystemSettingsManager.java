package managers;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

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
                    }
                    else{
                        System.out.println("Returning Back..");
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
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    //displayHolidayList();
                    break;
                case 2:
                    //addHoliday();
                    break;
                case 3:
                    //removeHoliday();
                    break;
                case 4:
                    System.out.println("Back to Staff App......");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose between 1-5.");
                    break;
            }
            break;
        }
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
    // private void displayHolidayList() {
    //     printHeader("Holiday list");
    //     HashMap<String, Holiday> holidayList = getHolidayList();
    //     HashMap<Integer, Holiday> searchIndex = new HashMap<>();
    //     if (holidayList.isEmpty()) {
    //         printMenu("No holiday exists", "");
    //         readString("Press ENTER to go back");
    //         configureHolidays();
    //     }
    //     else {
    //         int index  = 0;
    //         for (String date : holidayList.keySet()) {
    //             System.out.println(++index + ". " + holidayList.get(date));
    //             searchIndex.put(index, holidayList.get(date));
    //         }
    //         System.out.println(++index + ". Go back");
    //         System.out.println();

    //         int choice = readChoice(1, index);
    //         if (choice == index) configureHolidays();
    //         else displayHolidayDetail(searchIndex.get(choice));
    //     }
    // }

    // /**
    //  * This method is to display the detail of the holiday and ask user whether
    //  * to remove the holiday.
    //  * @param holiday the holiday whose detail to be displayed
    //  */
    // private void displayHolidayDetail(Holiday holiday) {
    //     printHeader(holiday.getName());
    //     printMenu(holiday.printDetail(), "");
    //     if (askConfirm("Enter Y if you want to delete the holiday",
    //             "Enter N to go back:")) {
    //         getHolidayList().remove(formatTimeMMdd(holiday.getDate()));
    //         try {
    //             updateHolidayList();
    //             System.out.println("Successfully deleted the holiday.");
    //         } catch (IOException e) {
    //             System.out.println("Failed to delete the holiday.");
    //         }
    //     }
    //     displayHolidayList();
    // }

    /**
     * This method is to add a holiday.
     */
    // private void addHoliday() {
    //     String holName="";
    //     String holDate="";
    //     double discount;

    //     System.out.println("Enter the name of the holiday:");
    //     holName = sc.nextLine();
    //     System.out.println("Enter the date of the holiday (MM-DD)");
    //     discount = readDouble("Enter the price rate on that day:",
    //             "e.g. 0.7 stands for ticket price * 0.7");

    //     Holiday holiday = new Holiday(name, date, discount);

    //     try {
    //         CineplexManager.addHoliday(formatTimeMMdd(date), holiday);
    //         System.out.println("Successfully added the holiday.");
    //     } catch (IOException ex) {
    //         System.out.println("Failed to add the holiday.");
    //     }

    //     displayHolidayList();
    // }
}
