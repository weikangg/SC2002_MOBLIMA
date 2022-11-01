package managers;
import java.util.*;

public class ShowtimeManager {
    private Scanner sc = new Scanner(System.in);

    private static ShowtimeManager single_instance = null;
    public static ShowtimeManager getInstance()
    {
        if (single_instance == null)
            single_instance = new ShowtimeManager();
        return single_instance;
    }

    private ShowtimeManager() {}

    public void staffMenu(int choice){
        int option = 0;
        int showtimeID, hallNo;
        String cinemaName;
        try{
            if(choice == 0){
                System.out.println("==================== SHOWTIME STAFF APP ====================\n" +
            					" 1. View Showtime Details                                      \n" +
                                " 2. Add a Showtime                                             \n" + 
			            		" 3. Update a Showtime                                          \n" +
			                    " 4. Remove a Showtime                                          \n" +
			                    " 5. Back to ShowtimeManager                                    \n"+
                                "==============================================================");
                System.out.println("Enter choice: ");
                option = sc.nextInt();
                if(!(option >= 1 && option <=5)){
                    System.out.println("Please only enter a number from 1-5.");
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
                sc.nextLine();
                System.out.println("Enter Cinema Name:");
                cinemaName = sc.nextLine();
                System.out.println("Enter Hall No:");
                hallNo = sc.nextInt();
                System.out.println("Enter ShowTime ID: ");
                showtimeID = sc.nextInt();
                sc.nextLine();
                StaffViewShowTime.viewShowTime(cinemaName, hallNo, showtimeID);
                //this.viewShowtime(showtimeID);
                break;
        case 2:
            System.out.println("Enter showtimeID: ");
            showtimeID = sc.nextInt();
            break;
        case 3:
            System.out.println("Enter showtimeID: ");
            showtimeID = sc.nextInt();
            StaffUpdateShowtime.updateShowtime(showtimeID);
            break;
        case 4:
            System.out.println("Enter showtimeID: ");
            showtimeID = sc.nextInt();
            //this.deleteShowtime(showtimeID);
            break;
        case 5:
            System.out.println("Back to Staff App......");
            break;
        default:
            System.out.println("Invalid choice. Please choose between 1-5.");
            break;
        }
        // MovieListManager movListManager = new MovieListManager();
        // ReviewListManager reviewListManager = new ReviewListManager();

        // clear garbage
        // movListManager = null;
        // reviewListManager = null;
        // staffMenu(0);
    }

    
}
