package managers;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
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

        int updated = 0 ;
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
        System.out.println("13. Exit");
        int option = sc.nextInt();
        while (true){
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
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
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
                                continue;
                        }    
                        sc.nextLine();
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                m.setShowingStatus(status);
                            }
                        }
                    }
                    break;
                case 3:
                    if(confirm("Confirm Update Synopsis")){
                        String synopsisTmp = read("New Synopsis:");
                        String synopsis = synopsisTmp.replaceAll(csv_Separator, splitter);
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                m.setSynopsis(synopsis);
                            }
                        }
                        sc.nextLine();
                    }
                    break;
                case 4:
                    if(confirm("Confirm Update Movie Director")){
                        String movieDirector = read("New Movie Director:");
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                m.setMovieDirector(movieDirector);
                            }
                        }
                        sc.nextLine();
                    }
                    break;
                case 5:
                    if(confirm("Confirm Update Casts")){
                        String castTmp = read("New Casts:");
                        String casts = castTmp.replaceAll(csv_Separator, splitter);
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                m.setCast(casts);
                            }
                        }
                        sc.nextLine();
                    }
                    break;
                case 6:
                    if(confirm("Confirm Update Genres")){
                        String genreTmp = read("New Genres:");
                        String genres = genreTmp.replaceAll(csv_Separator, splitter);
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
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
                            continue;
                        }
                        sc.nextLine();
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                m.setMovieRating(movieRating);
                            }
                        }
                    }
                    break;
                case 8:
                    if(confirm("Confirm Update Movie Duration")){
                        try{
                            print("New Movie Duration: ");
                            int movieDuration = sc.nextInt();
                            for(Movie m:movieList){
                                if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                    m.setMovieDuration(movieDuration);
                                }
                            }
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            print("Please input a valid Integer number.");
                            sc.nextLine();
                            continue;
                        }

                    }
                    break;
                case 9:
                    if(confirm("Confirm Update Profit Earned")){
                        try{
                            print("New Profit Earned: ");
                            double profitEarned = sc.nextDouble();
                            for(Movie m:movieList){
                                if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                    m.setProfitEarned(profitEarned);
                                }
                            }
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            print("Please input a valid Double number");
                            sc.nextLine();
                            continue;
                        }
                    }
                    break;
                case 10:
                    if(confirm("Confirm Update Overall Rating Score")){
                        try{
                            print("New Overall Rating Score: ");
                            double overallRatingScore = sc.nextDouble();
                            for(Movie m:movieList){
                                if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                    m.setProfitEarned(overallRatingScore);
                                }
                            }
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            print("Please input a valid Double number");
                            sc.nextLine();
                            continue;
                        }
                    }
                    break;
                case 11:
                    if(confirm("Confirm Update Release Date")){
                        String releaseDateStr = read("New Overall Release Date (YYYY-MM-DD): ");
                            try{
                                LocalDate releaseDate = LocalDate.parse(releaseDateStr);
                                for(Movie m:movieList){
                                    if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                        m.setReleaseDate(releaseDate);
                                    }
                                }
                                sc.nextLine();
                                break;
                            }catch(DateTimeParseException e){
                                System.out.println("Wrong format, enter again!");
                                continue;
                            }
                    }

                case 12:
                    MovieType movieType = null;
                    if(confirm("Confirm Update Movie Type")){
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
                            continue;
                        }
                        sc.nextLine();
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                m.setMovieType(movieType);
                            }
                        }
                    }
            }
            break;
        }
        
        updateMovieListCSV(movieList);
        return true;
    }
}
