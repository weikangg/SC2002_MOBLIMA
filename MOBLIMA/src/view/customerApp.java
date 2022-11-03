package view;

import java.util.Scanner;

import entities.Movie;
import managers.CustomerMovieManager;
import managers.MovieListManager;
import managers.Top5Movies;

import managers.*;
import static managers.Top5Movies.*;

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
        
        List<Movie> movie = MovieListManager.getMovieList();
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
                                "4. Search\n"+
                                "5. Top 5 Movies\n"+
                                "6. Back to main menu\n"+
                            "==============================================================\n");
            //scanner
            while(true){
                try{
                    System.out.println("Please Enter Your Choice: ");
                    input = scan.nextInt();
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
                counter = 0;
                String username;
                String password;
                //check login information, once passed go to logined menu
                System.out.println("Please enter your username:");
                username = scan.nextLine();
                System.out.println("Please enter your password:");
                password = scan.nextLine();
                if(CustomerAccManager.checkLogin(username, password))
                {
                    this.logInMenu();
                }
                else{
                    System.out.println("Wrong username/password");
                }
                break;

                case 2:
                counter = 0;
                //ask user for information to create account
                CustomerAccManager.createAcc();
                break;

                case 3:
                CustomerMovieManager.printMovieList(movie);
                break;

                case 4:
                //search
                System.out.println("Please enter the movie name:");
                strinput = scan.nextLine();
                CustomerMovieManager.searchMovie(movie, strinput, true);
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
                            Top5Movies.getInstance().top5Movies(MovieListManager.getMovieList());
                            break;
                        
                        // Only view top 5 sales
                        case 1:
                            Top5Movies.getInstance().top5SalesOnly(MovieListManager.getMovieList());
                            break;
                        
                        // Only view top 5 overall Rating score
                        case 2:
                            Top5Movies.getInstance().top5RatingsOnly(MovieListManager.getMovieList());
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
                counter++;
                System.out.println("Please enter a valid option");
                if(counter == 4)
                {
                    System.out.println("Exiting customer interface...");
                    exit = true;
                }
            }


        }while(exit == false);
    }

    public void logInMenu(){
        int input;
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

            input = scan.nextInt();
            scan.nextLine();

            switch(input){
                case 1:
                //book ticket
                int cineplex;
                String name;
                List<Movie> movie;
                movie = MovieListManager.getMovieList();

                //ask user for cineplex
                System.out.println("Please choose a Cineplex:");
                cineplex = scan.nextInt();
                
                //ask user for movie
                System.out.println("Please choose a movie:");
                CustomerMovieManager.printMovieList(movie);
                name = scan.nextLine();

                //link the movie to showtime

                //show user movie timing

                //ask user to choose a movie timing

                //call bookingmenu in booking manager

                case 2:
                //show booking history

                case 3:
                //show seat availability
                    
                case 4:
                //review movie

                case 5:
                    System.out.println("Exiting Customer App...");
                    customerApp.getInstance().customerMenu();
                break;

                default:
                System.out.println("Please enter a valid option");
            }
        }while(exit == false);
    }
}
