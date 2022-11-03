import java.util.Scanner;
import java.util.InputMismatchException;
import managers.StaffLogin;
import managers.SystemSettingsManager;
import managers.MovieManager;
import managers.ShowtimeManager;


public class adminApp {

    private static adminApp newInstance = null;
    private static Scanner sc = new Scanner(System.in);

    private adminApp(){}

// PUBLIC METHODS

    // If no previous instance of Admin has been created, we create one. Else use the previously
    // created one. Returns an instance of StaffApp.
    public static adminApp getInstance(){
        if (newInstance == null){
            newInstance = new adminApp();
        }
        return newInstance;
    }
/*
 *****************************************
 * DISPLAY LOGIN MENU FOR STAFF TO LOGIN *
 *****************************************
 */

    public void displayLoginMenu(){
        int choice;
        boolean adminLoggedIn = false;
        boolean toQuit = false;

        do{
            System.out.println(	"================= MOBLIMA STAFF INTERFACE =================\n"+
                                " 1. Staff Login                                            \n"+
                                " 0. Back to Main Menu                                      \n"+
                                "===========================================================");
            System.out.println("Enter option: ");

            // If invalid input
            while(!sc.hasNextInt()){
                System.out.println("Please enter numbers only!");
                sc.next(); // Flush input
            }
            
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    System.out.println("Enter Username: ");
                    while(!sc.hasNext()){
                        System.out.println("Error, please try again!");
                        sc.next();
                    }

                    String userName = sc.nextLine();
                    System.out.println("Enter password: ");
                    while(!sc.hasNext()){
                        System.out.println("Error, please try again!");
                        sc.next();
                    }

                    String passWord = sc.nextLine();

                    System.out.println("Username is " + userName + " and password is " + passWord );
                    boolean approved = StaffLogin.getInstance().checkLogin(userName,passWord);

                    if(approved){
                        toQuit = true;
                        adminLoggedIn = true;
                        this.displayLoggedInMenu();
                    }
                    else{
                        System.out.print("Wrong Username/Password.");
                        System.out.println(" Try again!");
                    }
                    break;
                case 0:
                    System.out.println("Heading back to Main Menu....");
                    mainApp.main(null);
                    break;
            }
            
        } while(toQuit == false && adminLoggedIn == false);
    }

// PRIVATE METHODS

/*
 ***********************************************
 * DISPLAY MENU FOR STAFF TO EDIT AND DO STUFF *
 ***********************************************
 */
    private void displayLoggedInMenu(){

        // System.out.println("");

        // Cineplex[] cineplexes = CineplexManager.configCineplexes(); //Function to get cineplexes object

        // Cinema[] cinemas = cineplexes[0].getCinemas(); //Function to get cinemas object of cineplexes[0]

        // Showtime[] showtimes = cinemas[0].getShowtimes(); //Function to get movies object of cinema[0]


        // showtimes[1].setMovieID(5);
        // cinemas[0].showShowtimes(); //Show Showtimes in cinemas[0] of cineplexes[0]

        // System.out.println("Information about showtimes[0]");
        // // showtimes[1].showInfo(); //Show info of showtimes[0] in cinemas[0] of cineplexes[0]
        // System.out.println("");

        // System.out.println("Seats available in showtimes[0]");
        // showtimes[2].showSeats(); //Show seats of showtimes[0] in cinemas[0] of cineplexes[0]
        // System.out.println("");

        // List<Movie> movies = MovieListManager.getMovieList();
        // for(int i = 0; i < movies.size(); i++){
            
        //     System.out.println(": "+movies.get(i).getMovieTitle());
        // }


        int choice;
            do {
                System.out.println(	"==================== MOBLIMA STAFF APP ====================\n" +
                                    " 1. Manage Movie Listings                                  \n" +
                                    " 2. Manage Movie Reviews                                   \n" +
                                    " 3. Manage Cinema Showtimes                                \n" +
                                    " 4. Configure System Settings                              \n" +
                                    " 5. Logout from StaffApp                                  \n"+
                                    "===========================================================");
                System.out.println("Enter choice: ");
    
                while (!sc.hasNextInt()) {
                    System.out.println("Error Input. Enter 1-5 only!!");
                    sc.next(); // Remove newline character
                }
                while(true){
                    try{
                        choice = sc.nextInt();
                        break;
                    }catch(InputMismatchException e){
                        System.out.println("Enter numbers only!");
                        sc.nextLine();
                        continue;
                    }
                }
                switch(choice){
                    case 1:
                        MovieManager.getInstance().staffMenu(0);
                        break;
                    case 3:
                        ShowtimeManager.getInstance().staffMenu(0);
                        break;
                    case 4:
                        SystemSettingsManager.getInstance().staffMenu(0);
                        break;
                    case 5: 
                        System.out.println("Exiting StaffApp, have a nice day!");
                        mainApp.main(null);
                        break;
                    
                    default:
                        System.out.println("Error input, please only choose 1-5.");
                        break;
                }
            }while(choice != 5);
    }

    
}
