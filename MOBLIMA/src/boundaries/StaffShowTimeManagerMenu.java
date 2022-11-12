package boundaries;
import java.time.LocalDateTime;
import java.util.*;
import entities.*;
import managers.*;
/**
 * The class for the menu for the staff to manage showtimes
 * @author Wei Kang
 * @version 3.0
 * @since 11-11-2022
 */

public class StaffShowTimeManagerMenu implements BaseMenuWithAccount{
     /**
	 * The scanner for reading input of user
	 */
    private Scanner sc = new Scanner(System.in);    
    /**
	 * For singleton pattern adherence. This MovieManager instance persists throughout runtime.
	 */
    private static StaffShowTimeManagerMenu single_instance = null;
	/**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
    public static StaffShowTimeManagerMenu getInstance()
    {
        if (single_instance == null)
            single_instance = new StaffShowTimeManagerMenu();
        return single_instance;
    }
     /**
     * Function to select and choose Showtime Options
     * @param account Account of User, Staff or Customer
     */
    public void display(Account account){
        
        int option = 0;
        try{
                System.out.println("==================== SHOWTIME STAFF APP ====================\n" +
            					" 1. View Showtime Details                                      \n" +
                                " 2. Add a Showtime                                             \n" + 
			            		" 3. Update a Showtime                                          \n" +
			                    " 4. Remove a Showtime                                          \n" +
			                    " 5. Go Back                                                    \n"+
                                "==============================================================");
                System.out.print("Enter choice: ");
                option = sc.nextInt();
                if(!(option >= 1 && option <=5)){
                    System.out.println("Please only enter a number from 1-5.");
                    display(account);
                }

        }
        catch(InputMismatchException e){
            System.out.println("Invalid Input. Please enter a valid option from 1-5 only!");
            sc.nextLine();
            display(account);
        }

        Cinema cinema;

        Showtime showtime;
        int[] inputs;
        int movieID;
        LocalDateTime dateTime;
        MovieType movieType;
        sc.nextLine();

        switch (option) {
            case 1:
                inputs = ShowtimeManager.getInstance().promptShowtimeSelection();
                if(inputs==null) break;
                showtime = CineplexManager.getInstance().configCineplexes()[inputs[0]].getCinemas()[inputs[1]].getShowtimes()[inputs[2]];
                showtime.showInfo();
                showtime.showSeats();
                break;
            case 2:
                inputs = ShowtimeManager.getInstance().promptCinemaSelection();
                if(inputs==null) break;
                cinema = CineplexManager.getInstance().configCineplexes()[inputs[0]].getCinemas()[inputs[1]];
                movieID = ShowtimeManager.getInstance().promptMovieID();
                if(movieID == -1) break;
                dateTime = ShowtimeManager.getInstance().promptDateTime(movieID);
                if(dateTime == null) break;
                // movieType = ShowtimeManager.getInstance().promptMovieType();
                // if(movieType == null) break;
                movieType = MovieListManager.getInstance().getMovie(movieID).getMovieType();
                cinema.addShowtime(movieID, dateTime, movieType);
                break;
            case 3:
                inputs = ShowtimeManager.getInstance().promptShowtimeSelection();
                if(inputs==null) break;
                showtime = CineplexManager.getInstance().configCineplexes()[inputs[0]].getCinemas()[inputs[1]].getShowtimes()[inputs[2]];
                movieID = ShowtimeManager.getInstance().promptMovieID();
                if(movieID == -1) break;
                dateTime = ShowtimeManager.getInstance().promptDateTime(movieID);
                if(dateTime == null) break;
                // movieType = ShowtimeManager.getInstance().promptMovieType();
                // if(movieType == null) break;
                movieType = MovieListManager.getInstance().getMovie(movieID).getMovieType();
                showtime.setMovieID(movieID);
                showtime.setDateTime(dateTime);
                showtime.setMovieType(movieType);

                System.out.println("Updated!");

                break;
            case 4:
                
                inputs = ShowtimeManager.getInstance().promptShowtimeSelection();

                if(inputs==null) break;

                cinema = CineplexManager.getInstance().configCineplexes()[inputs[0]].getCinemas()[inputs[1]];

                cinema.deleteShowtime(inputs[2]);

                break;
            case 5:
                System.out.println("Back to Staff App......");
                AdminMenu.getInstance().display(account);
                break;
            default:
                System.out.println("Invalid choice. Please choose between 1-5.");
                break;
            }
    }
}
