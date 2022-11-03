package managers;
import java.util.*;


import entities.Cineplex;
import entities.Cinema;
import entities.Showtime;

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
        int showtimeID;
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

                //Prompt Cineplexes
                Cineplex[] cineplexes = CineplexManager.configCineplexes(); 
                for(int i = 0; i < cineplexes.length; i++){
                    System.out.println(i + ": " + cineplexes[i].getName());
                }

                System.out.println("Enter Cineplex ID:");
                int cineplexID = sc.nextInt();

                if(cineplexID<0||cineplexID>cineplexes.length){
                    System.out.println("No such Cineplex");
                    break;
                }

                //Prompt Cinemas
                Cinema[] cinemas = cineplexes[cineplexID].getCinemas();
                for(int i = 0; i < cinemas.length; i++){
                    System.out.println(i + ": Hall " + cinemas[i].getCinemaID());
                }

                System.out.println("Enter Hall ID:");
                int cinemaID = sc.nextInt();

                if(cinemaID<0||cinemaID>cinemas.length){
                    System.out.println("No such Cinema");
                    break;
                }

                //Prompt Showtimes
                Showtime[] showtimes = cinemas[cinemaID].getShowtimes();
                for(int i = 0; i < showtimes.length; i++){
                    System.out.println(showtimes[i].getShowtimeID() + ": " + showtimes[i].getMovieTitle()
                                        + ", " + showtimes[i].getDateTime() + ", " + showtimes[i].getMovieType());
                }
                
                System.out.println("Enter ShowTime ID: ");
                showtimeID = sc.nextInt();

                if(cinemaID<0||cinemaID>showtimes.length){
                    System.out.println("No such Cinema");
                    break;
                }

                showtimes[showtimeID].showInfo();
                showtimes[showtimeID].showSeats();

                sc.nextLine();

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
