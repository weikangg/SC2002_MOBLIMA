package managers;
import java.text.SimpleDateFormat;
import java.util.*;
import entities.Showtime;
// import entities.MovieType;
// import entities.Cinema;
// import entities.CinemaStatus;

public class StaffUpdateShowtime {
    public static void updateShowtime(int showtimeID){
        int choice;
        Scanner sc = new Scanner(System.in);
        //Showtime showtimeToUpdate = this.showtimes.get(showtimeID);
        if (showtimeID != 0) {
            do {
                System.out.println(	"================= UPDATE SHOWTIME STAFF APP ================\n" +
                                    " 1. Showtime Date Time                                    \n" +
                                    " 2. Movie ID                                              \n" +
                                    " 3. Cinema                                                \n" +
                                    " 4. Cineplex Name                                         \n" +
                                    " 5. Cinema Status                                         \n" +
                                    " 6. Movie Format                                          \n" +
                                    " 0. Back to Showtime Staff App                            \n"+
                                    "============================================================");

                System.out.println("Enter choice: ");
                while(!sc.hasNextInt()) {
                    System.out.println("Please enter a number!");
                    sc.next();
                }
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        sc.nextLine();
                        System.out.println("Enter new Showtime datetime (dd/MM/yyyy HH:mm): ");
                        String newDateTime = sc.nextLine();
                        //Showtime.setDateTime((SimpleDateFormat) newDateTime);
                        while (newDateTime == null) {
                            System.out.println("Enter new Showtime datetime (dd/MM/yyyy HH:mm): ");
                            newDateTime = sc.nextLine();
                        }
                        //showtimeToUpdate.setDateTime(this.dateTimeParser(newDateTime));
                        break;
                    case 2:
                        System.out.println("Enter new movie ID: ");
                        Integer newMovieID = sc.nextInt();
                        //showtimeToUpdate.setMovieID(newMovieID);
                        break;
                    case 3:
                        System.out.println("Enter new cinema ID: ");
                        Integer newCinemaID = sc.nextInt();
                        //Cinema newCinema = CompanyManager.getInstance().getNewCinema(newCinemaID);
                        //showtimeToUpdate.setCinema(newCinema);
                        break;
                    case 4:
                        System.out.println("Enter new cineplex name: ");
                        String cineplexName = sc.next();
                        //showtimeToUpdate.setCineplexName(cineplexName);
                        break;
                    case 5:
                        System.out.println("Enter new cinema status: ");
                        String cinemaStatus = sc.next();
                        // while (!cinemaStatusValidator(cinemaStatus)) {
                        //     System.out.println("Enter new movie format: ");
                        //     cinemaStatus = sc.next();
                        // }
                        //showtimeToUpdate.setCinemaStatus(CinemaStatus.valueOf(sc.next()));
                        break;
                    case 6:
                        System.out.println("Enter new movie format: ");
                        String movieType = sc.next();
                        // while (!movieFormatValidator(movieType)) {
                        //     System.out.println("Enter new movie format: ");
                        //     movieType = sc.next();
                        // }
                        //showtimeToUpdate.setMovieFormat(MovieType.valueOf(sc.next()));
                        break;
                    case 0:
                        System.out.println("Going back to Showtime Staff App ...");
                    default:
                        System.out.println("Please enter an option 0 - 6!");
                        break;
                }
            } while (choice != 0);
        }
        else
        {
            System.out.println("Showtime not found!");
        }
    }

}
