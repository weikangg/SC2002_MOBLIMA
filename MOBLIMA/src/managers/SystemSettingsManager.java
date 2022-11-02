package managers;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
            staffMenu(0);
        }
        switch (option) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                configureHolidays();
                break;

            case 4:
                System.out.println("Back to Staff App......");
                break;
            default:
                System.out.println("Invalid choice. Please choose between 1-5.");
                break;
        }
    }

    private void configureHolidays(){
        
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
