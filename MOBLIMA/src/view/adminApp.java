package view;
import java.util.Scanner;

import entities.Account;

import java.util.InputMismatchException;
import java.util.List;

import managers.SystemSettingsManager;
import managers.AccountManager;
import managers.MovieManager;
import managers.ShowtimeManager;
import managers.StaffAccManager;

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

    // public void displayLoginMenu(){
    //     int choice;
    //     boolean adminLoggedIn = false;
    //     boolean toQuit = false;

    //     do{
    //         System.out.println(	"================= MOBLIMA STAFF INTERFACE =================\n"+
    //                             " 1. Staff Login                                            \n"+
    //                             " 0. Back to Main Menu                                      \n"+
    //                             "===========================================================");
    //         System.out.println("Enter option: ");

    //         // If invalid input
    //         while(!sc.hasNextInt()){
    //             System.out.println("Please enter numbers only!");
    //             sc.next(); // Flush input
    //         }
            
    //         choice = sc.nextInt();
    //         sc.nextLine();

    //         switch(choice){
    //             case 1:
    //                 System.out.println("Enter Username: ");
    //                 while(!sc.hasNext()){
    //                     System.out.println("Error, please try again!");
    //                     sc.next();
    //                 }

    //                 String userName = sc.nextLine();
    //                 System.out.println("Enter password: ");
    //                 while(!sc.hasNext()){
    //                     System.out.println("Error, please try again!");
    //                     sc.next();
    //                 }

    //                 String passWord = sc.nextLine();
    //                 boolean approved = StaffLogin.getInstance().checkStaffLogin(userName,passWord);

    //                 if(approved){
    //                     toQuit = true;
    //                     adminLoggedIn = true;
    //                     this.displayLoggedInMenu();
    //                 }
    //                 else{
    //                     System.out.print("Wrong Username/Password.");
    //                     System.out.println(" Try again!");
    //                 }
    //                 break;
    //             case 2:
    //                 break;
    //             case 0:
    //                 System.out.println("Heading back to Main Menu....");
    //                 mainApp2.main(null);
    //                 break;
    //         }
            
    //     } while(toQuit == false && adminLoggedIn == false);
    // }

// PRIVATE METHODS

/*
 ***********************************************
 * DISPLAY MENU FOR STAFF TO EDIT AND DO STUFF *
 ***********************************************
 */
    public void displayLoggedInMenu(Account account){

        // System.out.println("");

        // Cineplex[] cineplexes = CineplexManager.configCineplexes(); //Function to get cineplexes object

        // // ArrayList<Showtime> list = cineplexes[0].searchMovie(5);

        // // for(int i = 0; i < list.size(); i++){
        // //     list.get(i).showInfo();
        // //     System.out.println("");
        // // }

        // Cinema[] cinemas = cineplexes[0].getCinemas(); //Function to get cinemas object of cineplexes[0]

        // // Showtime[] showtimes = cinemas[0].getShowtimes(); //Function to get movies object of cinema[0]


        // String str = "2023-01-23 20:00";
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        // LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        // MovieType movieType = MovieType.THREED;

        // cinemas[2].addShowtime(4, dateTime, movieType);

        // cinemas[1].showShowtimes();
        
        // cinemas[1].deleteShowtime(2);

        // cinemas[1].showShowtimes();

        // cinemas[1].showShowtimes();
        
        // showtimes[0].reserveSeat(0, 0);

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
                                    " 5. Create New Staff Account                               \n" +
                                    " 6. Delete Staff Account                                   \n" +
                                    " 7. Logout from StaffApp                                   \n" +
                                    "===========================================================");
                System.out.println("Enter choice: ");
    
                while (!sc.hasNextInt()) {
                    System.out.println("Error Input. Enter 1-7 only!!");
                    sc.next(); // Remove newline character
                }
                while(true){
                    try{
                        choice = sc.nextInt();
                        sc.nextLine();
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
                    case 2:
                        // Hide from viewer?
                        break;
                    case 3:
                        ShowtimeManager.getInstance().staffMenu(0);
                        break;
                    case 4:
                        SystemSettingsManager.getInstance().staffMenu(0);
                        break;
                    case 5:
                        if(account.getAccessLevel().equals("SA")){
                            List<Account>accountList = AccountManager.getInstance().getAccountList();
                            if(StaffAccManager.getInstance().createStaffAccount(accountList)){
                                System.out.println("Account created!");
                            }
                            else{
                                System.out.println("Failed to create account!");
                            }
                        }
                        else{
                            System.out.println("No access!");
                        }
                        break;
                    case 7: 
                        System.out.println("Logging out from StaffApp, have a nice day!");
                        mainApp2.main(null);
                        break;
                    
                    default:
                        System.out.println("Error input, please only choose 1-7.");
                        break;
                }
            }while(choice != 7);
    }

    public void createAccount(){

    }
}
