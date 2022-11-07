package managers;

import java.util.*;

import entities.*;
import view.adminApp;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Function Class that handles Showtime Management Options
 * @author Andrew Leung
 * @version 2.0
 * @since 06-11-2022
 */
public class ShowtimeManager {
    private static Scanner sc = new Scanner(System.in);

    private static ShowtimeManager single_instance = null;
    public static ShowtimeManager getInstance()
    {
        if (single_instance == null)
            single_instance = new ShowtimeManager();
        return single_instance;
    }

    private ShowtimeManager() {}


    /**
     * Function to select and choose Showtime Options
     * @param choice input choice by user
     * @param account Account of User, Staff or Customer
     */
    public void staffMenu(int choice,Account account){
        
        int option = 0;
        try{
            if(choice == 0){
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
                    staffMenu(0,account);
                }
            }
        }
        catch(InputMismatchException e){
            System.out.println("Invalid Input. Please enter a valid option from 1-5 only!");
            sc.nextLine();
            staffMenu(0,account);
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
                inputs = promptShowtimeSelection();
                if(inputs==null) break;
                showtime = CineplexManager.getInstance().configCineplexes()[inputs[0]].getCinemas()[inputs[1]].getShowtimes()[inputs[2]];
                showtime.showInfo();
                showtime.showSeats();
                break;
            case 2:
                inputs = promptCinemaSelection();
                if(inputs==null) break;
                cinema = CineplexManager.getInstance().configCineplexes()[inputs[0]].getCinemas()[inputs[1]];
                movieID = promptMovieID();
                if(movieID == -1) break;
                dateTime = promptDateTime();
                if(dateTime == null) break;
                movieType = promptMovieType();
                if(movieType == null) break;
                cinema.addShowtime(movieID, dateTime, movieType);
                break;
            case 3:
                inputs = promptShowtimeSelection();
                if(inputs==null) break;
                showtime = CineplexManager.getInstance().configCineplexes()[inputs[0]].getCinemas()[inputs[1]].getShowtimes()[inputs[2]];
                movieID = promptMovieID();
                if(movieID == -1) break;
                dateTime = promptDateTime();
                if(dateTime == null) break;
                movieType = promptMovieType();
                if(movieType == null) break;
                showtime.setMovieID(movieID);
                showtime.setDateTime(dateTime);
                showtime.setMovieType(movieType);

                System.out.println("Updated!");

                break;
            case 4:
                
                inputs = promptShowtimeSelection();

                if(inputs==null) break;

                cinema = CineplexManager.getInstance().configCineplexes()[inputs[0]].getCinemas()[inputs[1]];

                cinema.deleteShowtime(inputs[2]);

                break;
            case 5:
                System.out.println("Back to Staff App......");
                adminApp.getInstance().displayLoggedInMenu(account);
                break;
            default:
                System.out.println("Invalid choice. Please choose between 1-5.");
                break;
            }
    }

    /**
     * Function to prompt user for Selection of Cinema
     * @return IDs of Cinema and Cineplex
     */
    public int[] promptCinemaSelection(){
        int[] inputs = new int[2];

        try {

            //Prompt Cineplexes
            Cineplex[] cineplexes = CineplexManager.getInstance().configCineplexes(); 
            for(int i = 0; i < cineplexes.length; i++){
                System.out.println((i+1) + ": " + cineplexes[i].getName());
            }

            System.out.println("");
            System.out.print("Enter Cineplex ID: ");
            int cineplexID = sc.nextInt();
            cineplexID--;
            System.out.println("");

            if(cineplexID<0||cineplexID>=cineplexes.length){
                System.out.println("No such Cineplex");
                return null;
            }

            inputs[0] = cineplexID;

            //Prompt Cinemas
            Cinema[] cinemas = cineplexes[cineplexID].getCinemas();
            for(int i = 0; i < cinemas.length; i++){
                System.out.println((i+1) + ": Hall " + (cinemas[i].getCinemaID()+1)+", Class: "+cinemas[i].getCinemaClass());
            }

            System.out.println("");
            System.out.print("Enter Hall ID: ");
            int cinemaID = sc.nextInt();
            cinemaID--;
            System.out.println("");

            if(cinemaID<0||cinemaID>=cinemas.length){
                System.out.println("No such Cinema");
                return null;
            }
            
            inputs[1] = cinemaID;

            
        } catch (InputMismatchException e) {
            // TODO: handle exception
            System.out.println("Input invalid");
            return null;
        }

        return inputs;
    }
    
    /**
     * Function to prompt user for Selection of Showtime
     * @return IDs of Showtime, Cinema and Cineplex
     */
    public int[] promptShowtimeSelection(){
        int[] inputs = new int[3];

        try {
            //Prompt Cineplexes
            Cineplex[] cineplexes = CineplexManager.getInstance().configCineplexes(); 
            for(int i = 0; i < cineplexes.length; i++){
                System.out.println((i+1) + ": " + cineplexes[i].getName());
            }

            System.out.println("");
            System.out.print("Enter Cineplex ID: ");
            int cineplexID = sc.nextInt();
            cineplexID--;

            if(cineplexID<0||cineplexID>=cineplexes.length){
                System.out.println("No such Cineplex");
                return null;
            }

            inputs[0] = (cineplexID);

            //Prompt Cinemas
            Cinema[] cinemas = cineplexes[cineplexID].getCinemas();

            for(int i = 0; i < cinemas.length; i++){
                System.out.println((i+1) + ": Hall " + (cinemas[i].getCinemaID()+1)+", Class: "+cinemas[i].getCinemaClass());
            }

            System.out.println("");
            System.out.print("Enter Hall ID: ");
            int cinemaID = sc.nextInt();
            cinemaID--;

            if(cinemaID<0||cinemaID>=cinemas.length){
                
                System.out.println("No such Cinema");
                return null;
            }
            
            inputs[1] = cinemaID;

            //Prompt Showtimes
            Showtime[] showtimes = cinemas[cinemaID].getShowtimes();

            if (showtimes.length == 0){
                System.out.println("No showtimes in cinema");
                return null;
            }

            for(int i = 0; i < showtimes.length; i++){
                System.out.println(i + ": " + showtimes[i].getMovieTitle()
                                    + ", " + showtimes[i].getDateTime() + ", " + showtimes[i].getMovieType());
            }
            
            System.out.println("");
            System.out.print("Enter ShowTime ID: ");
            int showtimeID = sc.nextInt();

            if(showtimeID<0||showtimeID>=showtimes.length){
                System.out.println("No such Showtime");
                return null;
            }

            inputs[2] = showtimeID;
            
        } catch (InputMismatchException e) {
            // TODO: handle exception
            System.out.println("Input invalid");
            return null;
        }

        return inputs;
    }

    /**
     * Function to prompt user for MovieID
     * @return MovieID
     */
    public int promptMovieID(){
        int movieID = -1;
        try {
            System.out.print("Enter Movie ID: ");
            movieID = sc.nextInt();
            System.out.println("");

            //check if movie id exists in movie database
            if(MovieListManager.getInstance().getMovie(movieID) == null){
                System.out.println("No such Movie");
                return -1;
            }

            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Input invalid");
            return -1;
        }
  
        return movieID;
    }

    /**
     * Function to prompt user for Date and Time
     * @return DateTime 
     */
    public LocalDateTime promptDateTime(){
        LocalDateTime dateTime;
        
        try {
            System.out.print("Enter Date and Time (yyyy-MM-dd HH:mm): ");
            sc.nextLine();
            String str = sc.nextLine();

            System.out.println("");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            dateTime = LocalDateTime.parse(str, formatter);
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Date invalid");
            return null;
        }
        
        
        
        return dateTime;
        
    }

    /**
     * Function to prompt user for Type of Movie
     * @return MovieType
     */
    public MovieType promptMovieType(){
        MovieType movieType; 

        System.out.print("Enter Movie Type (TWOD/THREED/IMAX/BLOCKBUSTER): ");
        String str = sc.nextLine();
        System.out.println("");

        if(str.equals("TWOD")){
            movieType = MovieType.TWOD;
        }else if(str.equals("THREED")){
            movieType = MovieType.THREED;
        }else if(str.equals("IMAX")){
            movieType = MovieType.IMAX;
        }else if(str.equals("BLOCKBUSTER")){
            movieType = MovieType.BLOCKBUSTER;
        }else{
            System.out.println("Input invalid");
            return null;
        }
        
        return movieType;

    }


    
}
