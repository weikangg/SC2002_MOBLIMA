package view;

import java.util.Scanner;

import entities.*;
import managers.CustomerMovieManager;
import managers.MovieListManager;
import managers.Top5Movies;
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

    Scanner scan = new Scanner(System.in);

    public void customerMenu(){
        
        List<Movie> movieList = MovieListManager.getInstance().getMovieList();
        List<Review> reviewList = ReviewListManager.getInstance().getReviewList();
        boolean exit = false;
        String strinput;
        int input;
        int counter = 0;

        do{
            //main menu
            System.out.print("================= MOBLIMA CUSTOMER INTERFACE =================\n"+
                                "1. Log In\n"+
                                "2. Create account\n"+
                                "3. Show all movies\n"+
                                "4. Search Movie by Name\n"+
                                "5. Top 5 Movies\n"+
                                "6. Back to main menu\n"+
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
                    if(CustomerAccManager.checkLogin(username, password))
                    {
                        User user = new User(username);
                        this.logInMenu(user);
                    }
                    else{
                        System.out.println("Wrong username/password");
                    }
                    break;

                case 2:
                    //ask user for information to create account
                    CustomerAccManager.createAcc();
                    break;

                case 3:
                    CustomerMovieManager.printMovieList(movieList,reviewList);
                    break;

                case 4:
                //search
                    System.out.println("Please enter the movie name:");
                    strinput = scan.nextLine();
                    if(CustomerMovieManager.searchMovie(movieList, reviewList, strinput, true) == 0){
                        System.out.println("Movie not found!");
                    }
                    break;

                case 5:
                    int displaySetting;
                    Top5Movies top5Movies = Top5Movies.getInstance();
                    
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
                            Top5Movies.getInstance().top5Movies(MovieListManager.getInstance().getMovieList());
                            break;
                        
                        // Only view top 5 sales
                        case 1:
                            Top5Movies.getInstance().top5SalesOnly(MovieListManager.getInstance().getMovieList());
                            break;
                        
                        // Only view top 5 overall Rating score
                        case 2:
                            Top5Movies.getInstance().top5RatingsOnly(MovieListManager.getInstance().getMovieList());
                            break;
                        // Else both cannot display (if the movie has very bad sales & ratings)
                        case 3:
                            Top5Movies.getInstance().viewNone();
                            break;
                    }
                    
                    break;
                case 6:
                    System.out.println("Exiting customer interface...");
                    mainApp.main(null);
                    break;
                
                default:
                    System.out.println("Please enter a valid option");
                    customerMenu();
            }


        }while(exit == false);
    }

    public void logInMenu(User user){
        int input = 5;
        boolean exit = false;
       
        do{
            //menu
            System.out.print("================= MOBLIMA CUSTOMER APP =================\n"+
            "1. Book and Purchase Ticket\n"+
            "2. Booking History\n"+
            "3. Seat Availability\n"+
            "4. Review Movie\n"+
            "5. Exit\n"+
            "========================================================\n");
            System.out.println("Please Enter Your Choice:\n");

            try{
                input = scan.nextInt();
                scan.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Please enter numbers only!");
                scan.nextLine();
                logInMenu(user);
            }

            switch(input){
                case 1:
                //book ticket
                int cineplex;
                int idx = 1;
                Showtime movst;
                String name;
                List<Movie> movieList = MovieListManager.getInstance().getMovieList();
                List<Review> reviewList = ReviewListManager.getInstance().getReviewList();
                List<Showtime> showtime;

                //ask user for cineplex
                System.out.println("Please choose a Cineplex:");
                cineplex = scan.nextInt();
                
                //ask user for movie
                CustomerMovieManager.printMovieList(movieList, reviewList);
                System.out.println("Please choose a movie:");
                name = scan.nextLine();
                

                //link the movie to showtime
                showtime = CustomerShowtime.searchMovieID(name);
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
                BookingManager.newBM().bookingMenu(movst);
                break;

                case 2:
                //show booking history

                case 3:
                //show seat availability
                    
                case 4:
                //review movie
                    ReviewManager.getInstance().reviewMenu(0, user);
                    break;

                case 5:
                    System.out.println("Exiting Customer App...");
                    customerApp.getInstance().customerMenu();
                    break;

                default:
                System.out.println("Please enter a valid option (1-5) only.");
            }
        }while(exit == false);
    }
}
