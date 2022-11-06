package view;

import java.util.Scanner;

import entities.*;
import managers.CustomerMovieManager;
import managers.MovieListManager;
import managers.SystemSettings;
import managers.*;
import java.util.InputMismatchException;
import java.util.List;

public class customerApp {
    private static customerApp newInstance = null;
    public static customerApp getInstance(){
        if (newInstance == null){
            newInstance = new customerApp();
        }
        return newInstance;
    }
    static AccountManager accountManager = AccountManager.getInstance();
    Scanner scan = new Scanner(System.in);

    public void customerGuestMenu(){

        List<Movie> movieList = MovieListManager.getInstance().getMovieList();
        List<Review> reviewList = ReviewListManager.getInstance().getReviewList();
        boolean exit = false;
        String strinput;
        int input;
        do{
            //main menu
            System.out.print("================= MOBLIMA CUSTOMER INTERFACE =================\n"+
                                "1. Log In\n"+
                                "2. Create account\n"+
                                "3. Seat availability\n"+
                                "4. Show all movies\n"+
                                "5. Search Movie by Name\n"+
                                "6. Top 5 Movies\n"+
                                "7. Review Movie\n" +
                                "8. Exit\n"+
                            "==============================================================\n");
            //scanner
            while(true){
                try{
                    System.out.println("Please Enter Your Choice: ");
                    input = scan.nextInt();
                    scan.nextLine();
                    break;
                }catch(InputMismatchException e){
                    scan.nextLine();
                    System.out.println("Please enter numbers only!");
                    continue;
                }
            }

            //cases for each input
            switch(input){
                case 1:
                    String username;
                    String password;
                    //check login information, once passed go to logined menu
                    System.out.println("Please enter your username:");
                    username = scan.nextLine();
                    System.out.println("Please enter your password:");
                    password = scan.nextLine();
                    if(accountManager.checkLogin(accountManager.getAccountList(),username, password))
                    {
                        Account user = accountManager.getAccount(accountManager.getAccountList(), username, password);
                        this.customerLoggedInMenu(user);
                    }
                    else{
                        System.out.println("Wrong username/password");
                    }
                    break;

                case 2:
                    //ask user for information to create account
                    CustomerAccManager.createAcc(accountManager.getAccountList());
                    break;
                case 3:
                    int idx = 1;
                    Showtime movst;
                    List<Showtime> showtime;

                    showtime = CustomerShowtime.searchMovieShowtime(movieList, reviewList);
                    if(showtime.isEmpty())
                    {
                        break;
                    }
                
                    //show user movie timing
                    for(Showtime st: showtime)
                    {
                        System.out.println(idx + ". " + st.getDateTime());
                        idx++;
                    }
                    //ask user to choose a movie timing
                    System.out.println("Please choose a timing:");
                    input = scan.nextInt();
                    movst = showtime.get(input - 1);
    
                    //call bookingmenu in booking manager
                    movst.showSeats();

                    break;
                case 4:
                    CustomerMovieManager.printMovieList(movieList,reviewList);
                    break;

                case 5:
                //search
                    System.out.println("Please enter the movie name:");
                    strinput = scan.nextLine();
                    if(CustomerMovieManager.searchMovie(movieList, reviewList, strinput) == 0){
                        System.out.println("Movie not found!");
                    }
                    break;

                case 6:
                    int displaySetting;
                    SystemSettings top5Movies = SystemSettings.getInstance();
                    
                    // Getting permissions
                    top5Movies.updatePermissions();

                    // Setting display setting
                    if(top5Movies.getTop5OverallRatingPermission().equalsIgnoreCase("Y")  && top5Movies.getTop5SalesPermission().equalsIgnoreCase("Y") ){
                        displaySetting = 0;
                    }
                    else if(top5Movies.getTop5OverallRatingPermission().equalsIgnoreCase("N")  && top5Movies.getTop5SalesPermission().equalsIgnoreCase("Y")){
                        displaySetting = 1;
                    }
                    else if(top5Movies.getTop5OverallRatingPermission().equalsIgnoreCase("Y")  && top5Movies.getTop5SalesPermission().equalsIgnoreCase("N")){
                        displaySetting = 2;
                    }
                    else{
                        displaySetting = 3;
                    }
                    switch(displaySetting){
                        
                        // Normal setting
                        case 0:
                            SystemSettings.getInstance().top5Movies(MovieListManager.getInstance().getMovieList());
                            break;
                        
                        // Only view top 5 sales
                        case 1:
                            SystemSettings.getInstance().top5SalesOnly(MovieListManager.getInstance().getMovieList());
                            break;
                        
                        // Only view top 5 overall Rating score
                        case 2:
                            SystemSettings.getInstance().top5RatingsOnly(MovieListManager.getInstance().getMovieList());
                            break;
                        // Else both cannot display (if the movie has very bad sales & ratings)
                        case 3:
                            SystemSettings.getInstance().viewNone();
                            break;
                    }
                    
                    break;
                case 7:
                    // Generate random username and temporary account for guest to review movie
                    // Temporary Account will not be saved within database.
                    String userName = AccountManager.getInstance().randomNameGenerator();
                    Account guestAccount = new CustomerAcc(userName,"Guest Account", 0, 0, "Guest", "G");
                    ReviewManager.getInstance().reviewMenuCustomer(0, guestAccount);
                    break;
                case 8:
                    System.out.println("Exiting customer interface...");
                    mainApp.main(null);
                    break;
                
                default:
                    System.out.println("Please enter a valid option");
                    customerGuestMenu();
            }

        }while(exit == false);
    }

    public void customerLoggedInMenu(Account account){
        List<Movie> movieList = MovieListManager.getInstance().getMovieList();
        List<Review> reviewList = ReviewListManager.getInstance().getReviewList();
        boolean exit = false;
        String strinput;
        int input;
        do{
            //main menu
            System.out.print("================= MOBLIMA CUSTOMER INTERFACE =================\n"+
                                "1. Make Bookings for movies\n"+
                                "2. Show all movies\n"+
                                "3. Search Movie by Name\n"+
                                "4. Top 5 Movies\n"+
                                "5. Give Review\n" + 
                                "6. Log out\n"+
                            "==============================================================\n");
            //scanner
            while(true){
                try{
                    System.out.println("Please Enter Your Choice: ");
                    input = scan.nextInt();
                    scan.nextLine();
                    break;
                }catch(InputMismatchException e){
                    scan.nextLine();
                    System.out.println("Please enter a valid option from 1-6 only!");
                    continue;
                }
            }

            //cases for each input
            switch(input){
                case 1:
                    this.bookingAndReviewMenu(account);
                    break;

                case 2:
                    CustomerMovieManager.printMovieList(movieList,reviewList);
                    break;

                case 3:
                //search
                    System.out.println("Please enter the movie name:");
                    strinput = scan.nextLine();
                    if(CustomerMovieManager.searchMovie(movieList, reviewList, strinput) == 0){
                        System.out.println("Movie not found!");
                    }
                    break;

                case 4:
                    int displaySetting;
                    SystemSettings top5Movies = SystemSettings.getInstance();
                    
                    // Getting permissions
                    top5Movies.updatePermissions();

                    // Setting display setting
                    if(top5Movies.getTop5OverallRatingPermission().equalsIgnoreCase("Y")  && top5Movies.getTop5SalesPermission().equalsIgnoreCase("Y") ){
                        displaySetting = 0;
                    }
                    else if(top5Movies.getTop5OverallRatingPermission().equalsIgnoreCase("N")  && top5Movies.getTop5SalesPermission().equalsIgnoreCase("Y")){
                        displaySetting = 1;
                    }
                    else if(top5Movies.getTop5OverallRatingPermission().equalsIgnoreCase("Y")  && top5Movies.getTop5SalesPermission().equalsIgnoreCase("N")){
                        displaySetting = 2;
                    }
                    else{
                        displaySetting = 3;
                    }
                    switch(displaySetting){
                        
                        // Normal setting
                        case 0:
                            SystemSettings.getInstance().top5Movies(MovieListManager.getInstance().getMovieList());
                            break;
                        
                        // Only view top 5 sales
                        case 1:
                            SystemSettings.getInstance().top5SalesOnly(MovieListManager.getInstance().getMovieList());
                            break;
                        
                        // Only view top 5 overall Rating score
                        case 2:
                            SystemSettings.getInstance().top5RatingsOnly(MovieListManager.getInstance().getMovieList());
                            break;
                        // Else both cannot display (if the movie has very bad sales & ratings)
                        case 3:
                            SystemSettings.getInstance().viewNone();
                            break;
                    }
                    
                    break;
                case 5:
                    //review movie
                    ReviewManager.getInstance().reviewMenuCustomer(0, account);
                    break;
                case 6:
                    System.out.println("Logging out...");
                    mainApp.main(null);
                    break;
                
                default:
                    System.out.println("Please enter a valid option from 1-6 only!");
                    customerLoggedInMenu(account);
            }


        }while(exit == false);
    }

    public void bookingAndReviewMenu(Account user){
        int input = 5;
        boolean exit = false;
        List<Movie> movieList = MovieListManager.getInstance().getMovieList();
        List<Review> reviewList = ReviewListManager.getInstance().getReviewList();
       
        do{
            //menu
            System.out.print("================= MOBLIMA CUSTOMER APP =================\n"+
                            "1. Book and Purchase Ticket\n"+
                            "2. Booking History\n"+
                            "3. Exit\n"+
                            "========================================================\n");
            System.out.println("Please Enter Your Choice:");

            try{
                input = scan.nextInt();
                scan.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Please enter a valid option from 1-3 only!");
                scan.nextLine();
                bookingAndReviewMenu(user);
            }

            switch(input){
                case 1:
                //book ticket
                int idx = 1;
                Showtime movst;
                List<Showtime> showtime;

                //link the movie to showtime
                showtime = CustomerShowtime.searchMovieShowtime(movieList, reviewList);
                if(showtime.isEmpty())
                {
                    break;
                }
                
                //show user movie timing
                for(Showtime st: showtime)
                {
                    System.out.println(idx + ". " + st.getDateTime());
                    idx++;
                }
                //ask user to choose a movie timing
                System.out.println("Please choose a timing:");
                input = scan.nextInt();
                movst = showtime.get(input - 1);

                //debug
                System.out.println(movst.getMovieID());
                System.out.println(movst.getMovieTitle());
    
                //call bookingmenu in booking manager
                BookingManager.newBM().bookingMenu(movst, user);
                break;

                case 2:
                //show booking history
                    break;
                    
                case 3:
                    System.out.println("Exiting Customer App...");
                    customerApp.getInstance().customerGuestMenu();
                    break;

                default:
                System.out.println("Please enter a valid option (1-3) only.");
            }
        }while(exit == false);
    }
}
