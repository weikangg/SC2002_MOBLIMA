package managers;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import static utils.IOUtils.*;
import static managers.MovieListManager.*;
import entities.*;

public class StaffUpdateMovieManager {
    private static Scanner sc = new Scanner(System.in);
    static String csv_Separator = ",";
	static String splitter = ";";
	static String converter = ":";


    
    public static boolean updateMovie(List<Movie> movieList){
        System.out.println("#########################################################");
        System.out.println("#################### UPDATING MOVIES ####################");
        System.out.println("#########################################################");
        System.out.println("");
        System.out.println("Please enter Movie Title to update:");
        String newTitle = sc.nextLine();
        
        // Search if movie exists first

        Movie temp = null;
        for(Movie m: movieList){
            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                 temp = m;
            }
        }
        if(!movieList.contains(temp)){
            System.out.println("Movie does not exist!");
            return false;
        }

        // Then do the updating if it exists

        System.out.println("Please choose an option:");
        System.out.println("1. Update Movie Title");
        System.out.println("2. Update Showing Status");
        System.out.println("3. Update Synopsis");
        System.out.println("4. Update Movie Director");
        System.out.println("5. Update Casts");
        System.out.println("6. Update Genres");
        System.out.println("7. Update Movie Rating");
        System.out.println("8. Update Movie Duration");
        System.out.println("9. Update Profit Earned");
        System.out.println("10. Update Overall Rating Score");
        System.out.println("11. Update Release Date");
        System.out.println("12. Update Movie Type");
        int option = sc.nextInt();
        switch(option){
            case 1:
                if(confirm("Confirm Update Title")){
                    String title = read("New Title:");
                    if (movieList.stream().filter(o -> o.getMovieTitle().equalsIgnoreCase(title)).findFirst().isPresent()) {
                        System.out.println("Movie Already Exists!");
                        sc.nextLine();
                        return false;
                    }
                    for(Movie m:movieList){
                        if(m.getMovieTitle().equals(newTitle)){
                            m.setMovieTitle(title);
                        }
                    }
                    sc.nextLine();
                }
                break;
            case 2:
                ShowingStatus status = null;
                if(confirm("Confirm Update Showing Status")){
                    System.out.println("Choose Movie Status");
                    System.out.println("1: COMING_SOON");
                    System.out.println("2: PREVIEW");
                    System.out.println("3: NOW_SHOWING");
                    System.out.println("4: FINISHED_SHOWING");
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1: {
                            status = ShowingStatus.COMING_SOON;
                            break;
                        }
                        case 2: {
                            status = ShowingStatus.PREVIEW;
                            break;
                        }
                        case 3: {
                            status = ShowingStatus.NOW_SHOWING;
                            break;
                        }
                        case 4: {
                            status = ShowingStatus.FINISHED_SHOWING;
                            break;
                        }
                        default:
                            System.out.println("Error Input! Please only input values from 1-4.\n");
                            return false;
                    }    
                    sc.nextLine();
                    for(Movie m:movieList){
                        if(m.getMovieTitle().equals(newTitle)){
                            m.setShowingStatus(status);
                        }
                    }
                }
                break;
            case 3:
                if(confirm("Update Synopsis")){
                    String synopsisTmp = read("New Synopsis:");
                    String synopsis = synopsisTmp.replaceAll(csv_Separator, splitter);
                    for(Movie m:movieList){
                        if(m.getMovieTitle().equals(newTitle)){
                            m.setSynopsis(synopsis);
                        }
                    }
                    sc.nextLine();
                }
                break;
            case 4:
                if(confirm("Update Movie Director")){
                    String movieDirector = read("New Movie Director:");
                    for(Movie m:movieList){
                        if(m.getMovieTitle().equals(newTitle)){
                            m.setMovieDirector(movieDirector);
                        }
                    }
                    sc.nextLine();
                }
                break;
            case 5:
                if(confirm("Update Casts")){
                    String castTmp = read("New Casts:");
                    String casts = castTmp.replaceAll(csv_Separator, splitter);
                    for(Movie m:movieList){
                        if(m.getMovieTitle().equals(newTitle)){
                            m.setCast(casts);
                        }
                    }
                    sc.nextLine();
                }
                break;
            case 6:
                if(confirm("Update Genres")){
                    String genreTmp = read("New Genres:");
                    String genres = genreTmp.replaceAll(csv_Separator, splitter);
                    for(Movie m:movieList){
                        if(m.getMovieTitle().equals(newTitle)){
                            m.setGenres(genres);
                        }
                    }
                    sc.nextLine();
                }
                break;
            case 7:
                MovieRating movieRating = null;
                if(confirm("Confirm Update Movie Rating")){
                    System.out.println("Choose Movie Rating");
                    System.out.println("1: G");
                    System.out.println("2: PG");
                    System.out.println("3: PG-13");
                    System.out.println("4: NC-16");
                    System.out.println("5: M-18");
                    System.out.println("6: R-21");
                    int choice = sc.nextInt();
                    switch (choice) {
                    case 1: {
                        movieRating = MovieRating.G;
                        break;
                    }
                    case 2: {
                        movieRating = MovieRating.PG;
                        break;
                    }
                    case 3: {
                        movieRating = MovieRating.PG13;
                        break;
                    }
                    case 4: {
                        movieRating = MovieRating.NC16;
                        break;
                    }
                    case 5: {
                        movieRating = MovieRating.M18;
                        break;
                    }
                    case 6: {
                        movieRating = MovieRating.R21;
                        break;
                    }
                    default:
                        System.out.println("Error Input! Please only input values from 1-6.\n");
                        return false;
                    }
                    sc.nextLine();
                    for(Movie m:movieList){
                        if(m.getMovieTitle().equals(newTitle)){
                            m.setMovieRating(movieRating);
                        }
                    }
                }
                break;
            case 8:
                if(confirm("Update Movie Duration")){
                    int movieDuration = readInt("New Movie Duration:");
                    for(Movie m:movieList){
                        if(m.getMovieTitle().equals(newTitle)){
                            m.setMovieDuration(movieDuration);
                        }
                    }
                    sc.nextLine();
                }
                break;
            case 9:
                if(confirm("Update Profit Earned")){
                    double profitEarned = readDouble("New Profit Earned:");
                    for(Movie m:movieList){
                        if(m.getMovieTitle().equals(newTitle)){
                            m.setProfitEarned(profitEarned);
                        }
                    }
                    sc.nextLine();
                }
                break;
            case 10:
                if(confirm("Update Overall Rating Score")){
                    double ratingScore = readDouble("New Overall Rating Score:");
                    for(Movie m:movieList){
                        if(m.getMovieTitle().equals(newTitle)){
                            m.setOverallRatingScore(ratingScore);
                        }
                    }
                    sc.nextLine();
                }
                break;
            case 11:
                if(confirm("Update Release Date")){
                    String releaseDateStr = read("New Overall Release Date (YYYY-MM-DD): ");
                    while(true){
                        try{
                            LocalDate releaseDate = LocalDate.parse(releaseDateStr);
                            for(Movie m:movieList){
                                if(m.getMovieTitle().equals(newTitle)){
                                    m.setReleaseDate(releaseDate);
                                }
                            }
                            sc.nextLine();
                            break;
                        }catch(DateTimeParseException e){
                            System.out.println("Wrong format, enter again!");
                            releaseDateStr = read("New Overall Release Date (YYYY-MM-DD): ");
                        }
                    }
                }
                break;
            case 12:
                MovieType movieType = null;
                if(confirm("Update Movie Type")){
                    System.out.println("Choose Movie Type");
                    System.out.println("1: TWOD");
                    System.out.println("2: THREED");
                    System.out.println("3: IMAX");
                    System.out.println("4: BLOCKBUSTER");
                    int choice = sc.nextInt();
                    switch (choice) {
                    case 1: {
                        movieType = MovieType.TWOD;
                        break;
                    }
                    case 2: {
                        movieType = MovieType.THREED;
                        break;
                    }
                    case 3: {
                        movieType = MovieType.IMAX;
                        break;
                    }
                    case 4: {
                        movieType = MovieType.BLOCKBUSTER;
                        break;
                    }
                    default:
                        System.out.println("Error Input! Please only input values from 1-4.\n");
                    }
                    sc.nextLine();
                    for(Movie m:movieList){
                        if(m.getMovieTitle().equals(newTitle)){
                            m.setMovieType(movieType);
                        }
                    }
                }
        }
        updateMovieListCSV(movieList);
        return true;
    }
}
