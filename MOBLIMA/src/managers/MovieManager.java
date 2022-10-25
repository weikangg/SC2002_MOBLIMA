package managers;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import com.opencsv.*;
import movie_entities.Movie;
import movie_entities.Genre;
import movie_entities.MovieRating;
import movie_entities.ShowingStatus;
public class MovieManager {    
    private Scanner sc = new Scanner(System.in);
    private Map <String,Movie>movies;

    private static MovieManager single_instance = null;
    public static MovieManager getInstance()
    {
        if (single_instance == null)
            single_instance = new MovieManager();
        return single_instance;
    }


    private MovieManager(){
        this.movies  = new HashMap<>();
    }

    public void staffMenu(){
        int choice;
        do {
            System.out.println("=================== MOVIE MENU (STAFF) ==================\n" +
                               " 1. View/ Edit Movies 						    		\n" +
                               " 2. Add Movies		                                 	\n" +
                               " 3. Search Movies (By Title)	                        \n" +
                               " 4. Back to StaffApp......                             \n"+
                               "==========================================================");

            System.out.println("Enter choice: ");
            while(!sc.hasNextInt()) {
                System.out.println("Please only enter a number from 1-3.");
                sc.next();
            }
            choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    this.viewMovies("Staff");
                    break;
                case 2:
                    this.addMovies();
                    break;
                case 4:
                	System.out.println("Back to StaffApp......");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 0-3.");
                    break;
            }
        } while (choice != 4);
    }
    public void viewMovies(String accessType){
        if(accessType.equals("Staff")){

        }
        else if(accessType.equals("Customer")){

        }
    }
    private void addMovies(){
        Movie newMovie = new Movie();
        ArrayList<Genre> genreList = new ArrayList<>();
        ArrayList<String> castList = new ArrayList<>();

        System.out.println("Enter movie title: ");
        sc.nextLine();
        String title = sc.nextLine();
        newMovie.setMovieTitle(title);

        System.out.println("List of Genres:");
        for(int i=0;i<Genre.values().length;i++){
            System.out.println(i+1 +". " + Genre.values()[i].toString());
        }
        System.out.println("Enter number of genres: ");
        while (!sc.hasNextInt()) {
        	System.out.println("Please only input integers");
    		sc.next(); // Flush input buffer
        }
        int numGenres = sc.nextInt();
        for (int i=0;i<numGenres;i++)
        {
            System.out.println("Pick genre: ");
            
            while (!sc.hasNextInt()) {
            	System.out.println("Invalid input type. Please enter an integer.");
        		sc.next(); // Remove newline character
            }
            
            int choice = sc.nextInt()-1;
            System.out.println("You picked: "+Genre.values()[choice].toString());
            genreList.add(Genre.values()[choice]);
        }
        newMovie.setGenres(genreList);

        System.out.println("Enter director name: ");
        sc.nextLine();
        newMovie.setMovieDirector(sc.nextLine());

        System.out.println("Enter number of cast members: ");
        
        while (!sc.hasNextInt()) {
        	System.out.println("Invalid input type. Please enter an integer value.");
    		sc.next(); // Remove newline character
        }
        
        int castLength = sc.nextInt();
        while(castLength < 2){
            System.out.println("Must have at least 2 cast members!");
            System.out.println("Re-Enter number of cast members: ");
            castLength = sc.nextInt();
        }
        sc.nextLine();
        for (int i=0;i<castLength;i++)
        {
            System.out.println("Enter cast member: ");
            String castName = sc.nextLine();
            castList.add(castName);
        }
        newMovie.setCast(castList);

        System.out.println("Enter synopsis: ");
        newMovie.setSynopsis(sc.nextLine());

        System.out.println("Pick movie rating: ");
        for(int i=0;i<MovieRating.values().length;i++){
            System.out.println(i+1 + ". " +MovieRating.values()[i].toString());
        }
        
        while (!sc.hasNextInt()) {
        	System.out.println("Invalid input type. Please enter an integer value.");
    		sc.next(); // Remove newline character
        }
        
        int movieRating = sc.nextInt()-1;
        System.out.println("You picked "+MovieRating.values()[movieRating].toString());
        newMovie.setMovieRating(MovieRating.values()[movieRating]);

        System.out.println("Enter movie duration: ");
        
        while (!sc.hasNextInt()) {
        	System.out.println("Invalid input type. Please enter an integer value in minutes.");
    		sc.next(); // Remove newline character
        }
        
        newMovie.setMovieDuration(sc.nextInt());

        System.out.println("Pick showing status: ");
        for(int i=0;i<ShowingStatus.values().length;i++){
            System.out.println(i+1 + ". " +ShowingStatus.values()[i].toString());
        }
        
        while (!sc.hasNextInt()) {
        	System.out.println("Invalid input type. Please enter an integer value.");
    		sc.next(); // Remove newline character
        }
        
        int showStatus = sc.nextInt()-1;
        System.out.println("You picked "+ShowingStatus.values()[showStatus]);
        newMovie.setShowingStatus(ShowingStatus.values()[showStatus]);

        System.out.println("Enter release date (format YYYY-MM-DD HH:MM): ");
        String releaseDateTime = "1986-04-08 12:30";
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(releaseDateTime, dateTimeFormat);
        newMovie.setReleaseDateTime(dateTime);
        this.save(newMovie);
        this.movies.put(newMovie.getMovieID(),newMovie);
        System.out.println("Movie added successfully!");
    }
    private void save (Movie movie){
        try{
            // String path = System.getProperty("user.dir") +"\\MOBLIMA\\rsc\\movies\\movies.csv"; //FilePath for movies.csv
            // System.out.println(path);
            // FileWriter filewriter = new FileWriter(path); //CSVReader Instantiation
            // CSVWriter csvWriter = new CSVWriter(filewriter); 
            // csvWriter.writeAll((Iterable<String[]>) movie);
            // csvWriter.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
