
import java.util.Scanner;
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
        
        boolean exit = false;
        int input;
        int counter = 0;

        do{
            //main menu
            System.out.print("================= MOBLIMA CUSTOMER INTERFACE =================\n"+
            "1. Log In"+
            "2. Create account"+
            "2. Search\n"+
            "3. Top 5 Movies\n"+
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
                counter = 0;
                //search for movie
                
                case 4:
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
                this.booking();
                //after showing user the seats for a movie, call bookingmenu in booking manager

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
    
    public void booking(){
        int cineplex;
        int count = 0;
        List<Movie> movie;

        

        System.out.println("Please choose a Cineplex:");
        cineplex = scan.nextInt();
        
        movie = new MovieListManager().getMovieList();
        System.out.println("Please choose a movie:");
        for(Movie m : movie) {
            String cast, genre;
            String casttmp = m.getCast();
            String genretmp = m.getGenres();
            String[] rating;

            System.out.printf("----------------- MOVIE %d -----------------\n", count);
            System.out.println("Movie Title: "+ m.getMovieTitle());
            /*System.out.println("Showing Status: "+ m.getShowingStatus());
            System.out.println("Synopsis: " + m.getSynopsis());
            System.out.println("Movie Director: "+ m.getMovieDirector());
            cast = casttmp.replaceAll(SplitBy, cvsSplitBy);
            System.out.println("Casts: "+ cast);
            cast = casttmp.replaceAll(SplitBy, cvsSplitBy);
            genre = genretmp.replaceAll(SplitBy, cvsSplitBy);
            System.out.println("Genres: "+ genre);
            System.out.println("Movie Rating: " + m.getMovieRating());
            System.out.println("Movie Duration: " + m.getMovieDuration());
            double profitEarned = m.getProfitEarned();
            BigDecimal bd = new BigDecimal(profitEarned);
            System.out.println("Profit Earned: " + bd.toPlainString());
            System.out.println("Overall Rating Score: " + m.getOverallRatingScore());
            System.out.println("Release Date: " + m.getReleaseDate().toString());
            System.out.println("Movie Type: " + m.getMovieType());
            count++;*/
            System.out.println("");
        }
        
        //link the movie to showtime

        //show user movie timing

        //ask user to choose a movie timing
        //call bookingmanager
    }
}
