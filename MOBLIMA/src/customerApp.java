
import java.util.Scanner;

import entities.Movie;
import managers.CustomerMovieManager;
import managers.MovieListManager;

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
        
        new MovieListManager();
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
            "0. Back to main menu\n"+
            "==============================================================\n"+
            "Please Enter Your Choice:");

            //scanner
            input = scan.nextInt();
            scan.nextLine();

            //cases for each input
            switch(input){
                case 1:
                counter = 0;
                //check login information, once passed go to logined menu
                this.logInMenu();

                case 2:
                counter = 0;
                //ask user for information to create account

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
                counter = 0;
                //show top 5 movies
                
                case 0:
                System.out.println("Exiting customer interface...");
                exit = true;
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
            "0. Exit\n"+
            "========================================================\n"+
            "Please Enter Your Choice:");

            input = scan.nextInt();
            scan.nextLine();

            switch(input){
                case 1:
                //book ticket
                int cineplex;
                String name;
                List<Movie> movie;
                movie = new MovieListManager().getMovieList();

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

                case 0:
                System.out.println("Exiting Customer App...");
                exit = true;
                break;

                default:
                System.out.println("Please enter a valid option");
            }
        }while(exit == false);
    }
}
