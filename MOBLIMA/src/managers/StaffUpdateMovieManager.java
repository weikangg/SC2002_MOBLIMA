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

    public static int updateMovie(List<Movie> movieList){
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
            return 0;
        }

        // Then do the updating if it exists
        while (true){
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
            System.out.println("12. Update End Of Showing Date");
            System.out.println("13. Update Movie Type");
            System.out.println("14. Exit");
            int option;
            try{
                option = sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Please only enter a number from 1-14 only!");
                sc.nextLine();
                continue;
            }

            switch(option){
                case 1:
                    if(confirm("Confirm Update Title")){
                        String title = read("New Title:");
                        if (movieList.stream().filter(o -> o.getMovieTitle().equalsIgnoreCase(title)).findFirst().isPresent()) {
                            System.out.println("Movie Already Exists!");
                            sc.nextLine();
                            return 0;
                        }
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                m.setMovieTitle(title);
                                break;
                            }
                        }

                    }
                    else{
                        return 2;
                    }
                    break;
                case 2:
                    ShowingStatus status = null;
                    if(confirm("Confirm Update Showing Status")){
                        int choice;
                        while(true){
                            System.out.println("Choose Movie Status");
                            System.out.println("1: COMING_SOON");
                            System.out.println("2: PREVIEW");
                            System.out.println("3: NOW_SHOWING");
                            System.out.println("4: FINISHED_SHOWING");
                            try{
                                choice = sc.nextInt();
                                sc.nextLine();
                                if(choice < 1 || choice > 4){
                                    System.out.println("Please only input values from 1-4 only.");
                                    continue;
                                }
                                break;
                            }catch(InputMismatchException e){
                                System.out.println("Please only input values from 1-4 only");
                                sc.nextLine();
                                continue;
                            }
                        }
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
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                m.setShowingStatus(status);
                                break;
                            }
                        }
                    }
                    else{
                        return 2;
                    }
                    break;
                case 3:
                    if(confirm("Confirm Update Synopsis")){
                        String synopsisTmp = read("New Synopsis:");
                        String synopsis = synopsisTmp.replaceAll(csv_Separator, splitter);
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                m.setSynopsis(synopsis);
                                break;
                            }
                        }
                    }
                    else{
                        return 2;
                    }
                    break;
                case 4:
                    if(confirm("Confirm Update Movie Director")){
                        String movieDirector = read("New Movie Director:");
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                m.setMovieDirector(movieDirector);
                                break;
                            }
                        }
                    }
                    else{
                        return 2;
                    }
                    break;
                case 5:
                    if(confirm("Confirm Update Casts")){
                        String castTmp = read("New Casts:");
                        String casts = castTmp.replaceAll(csv_Separator, splitter);
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                m.setCast(casts);
                                break;
                            }
                        }
                    }
                    else{
                        return 2;
                    }
                    break;
                case 6:
                    if(confirm("Confirm Update Genres")){
                        String genreTmp = read("New Genres:");
                        String genres = genreTmp.replaceAll(csv_Separator, splitter);
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                m.setGenres(genres);
                                break;
                            }
                        }
                    }
                    else{
                        return 2;
                    }
                    break;
                case 7:
                    MovieRating movieRating = null;
                    if(confirm("Confirm Update Movie Rating")){
                        int choice;
                        while(true){
                            System.out.println("Choose Movie Rating");
                            System.out.println("1: G");
                            System.out.println("2: PG");
                            System.out.println("3: PG-13");
                            System.out.println("4: NC-16");
                            System.out.println("5: M-18");
                            System.out.println("6: R-21");
                            try{
                                choice = sc.nextInt();
                                sc.nextLine();
                                if (choice < 1 || choice > 6){
                                    System.out.println("Please only input values from 1-6 only.");
                                    continue;
                                }
                                break;
                            }catch(InputMismatchException e){
                                System.out.println("Please only input values from 1-6 only.");
                                sc.nextLine();
                                continue;
                            }
                        }
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
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                m.setMovieRating(movieRating);
                                break;
                            }
                        }
                    }
                    else{
                        return 2;
                    }
                    break;
                case 8:
                    if(confirm("Confirm Update Movie Duration")){
                        while(true){
                            try{
                                print("New Movie Duration: ");
                                int movieDuration = sc.nextInt();
                                if(movieDuration < 0 || movieDuration > 1440){ // 1 day
                                    System.out.println("Please input a valid movie duration!");
                                    sc.nextLine();
                                    continue;
                                }
                                for(Movie m:movieList){
                                    if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                        m.setMovieDuration(movieDuration);
                                        break;
                                    }
                                }
                                sc.nextLine();
                                break;
                            }catch(InputMismatchException e){
                                print("Please input a valid Movie Duration.");
                                sc.nextLine();
                                continue;
                            }
                        }

                    }
                    else{
                        return 2;
                    }
                    break;
                case 9:
                    if(confirm("Confirm Update Profit Earned")){
                        while(true){
                            try{
                                print("New Profit Earned: ");
                                double profitEarned = sc.nextDouble();
                                if(profitEarned < 0){
                                    System.out.println("Please enter a valid amount of profit earned!");
                                    sc.nextLine();
                                    continue;
                                }
                                for(Movie m:movieList){
                                    if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                        m.setProfitEarned(profitEarned);
                                        break;
                                    }
                                }
                                sc.nextLine();
                                break;
                            }catch(InputMismatchException e){
                                print("Please input a valid amount of profit earned!");
                                sc.nextLine();
                                continue;
                            }
                        }

                    }
                    else{
                        return 2;
                    }
                    break;
                case 10:
                    if(confirm("Confirm Update Overall Rating Score")){
                        while(true){
                            try{
                                print("New Overall Rating Score: ");
                                double overallRatingScore = sc.nextDouble();
                                if(overallRatingScore < 0 || overallRatingScore > 5){
                                    print("Overall Rating can only be between 0 and 5!");
                                    sc.nextLine();
                                    continue;
                                }
                                for(Movie m:movieList){
                                    if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                        m.setOverallRatingScore(overallRatingScore);
                                        break;
                                    }
                                }
                                sc.nextLine();
                                break;
                            }catch(InputMismatchException e){
                                print("Overall Rating can only be between 0 and 5!");
                                sc.nextLine();
                                continue;
                            }
                        }

                    }
                    else{
                        return 2;
                    }
                    break;
                case 11:
                    int valid = 0;
                    if(confirm("Confirm Update Release Date")){
                        while(true){
                            String releaseDateStr = read("New Overall Release Date (YYYY-MM-DD): ");
                            try{
                                LocalDate releaseDate = LocalDate.parse(releaseDateStr);
                                for(Movie m:movieList){
                                    if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                        if(releaseDate.isAfter(m.getEndOfShowingDate())){
                                            System.out.println("Invalid! Release Date should only be before or same as end of showing date!");
                                            break;
                                        }
                                        else{
                                            m.setReleaseDate(releaseDate);
                                            valid = 1;
                                            break;
                                        }
                                    }
                                }
                                if(valid == 1){
                                    break;
                                }else{
                                    continue;
                                }
                            }catch(DateTimeParseException e){
                                System.out.println("Wrong format, enter again!");
                                continue;
                            }
                        }
                    }
                    else{
                        return 2;
                    }
                    break;
                case 12:
                    int valid2 = 0;
                    if(confirm("Confirm Update End Of Showing Date")){
                        while(true){
                            String endShowingStr = read("New Overall End Of Showing Date (YYYY-MM-DD): ");
                            try{
                                LocalDate endOfShowingDate = LocalDate.parse(endShowingStr);
                                for(Movie m:movieList){
                                    if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                        if(endOfShowingDate.isAfter(m.getReleaseDate()) || endOfShowingDate.isEqual(m.getReleaseDate())){
                                            m.setEndOfShowingDate(endOfShowingDate);
                                            valid2 = 1;
                                            break;
                                        }
                                        else{
                                            System.out.println("Invalid Input! End of Showing Date must be after Release Date!");
                                            break;
                                        }
                                    }
                                }
                                if(valid2 == 1){
                                    break;
                                }else{
                                    continue;
                                }
                            }catch(DateTimeParseException e){
                                System.out.println("Wrong format, enter again!");
                                continue;
                            }
                        }
                    }
                    else{
                        return 2;
                    }
                    break;
                case 13:
                    MovieType movieType = null;
                    if(confirm("Confirm Update Movie Type")){
                        int choice;
                        while(true){
                            System.out.println("Choose Movie Type");
                            System.out.println("1: TWOD");
                            System.out.println("2: THREED");
                            System.out.println("3: IMAX");
                            System.out.println("4: BLOCKBUSTER");
                            try{
                                choice = sc.nextInt();
                                sc.nextLine();
                                if(choice < 1 || choice > 4){
                                    System.out.println("Please only input values from 1-4 only.");
                                    continue;
                                }
                                break;
                            }catch(InputMismatchException e){
                                System.out.println("Please only input values from 1-4 only.");
                                sc.nextLine();
                                continue;
                            }
                        }
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
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                m.setMovieType(movieType);
                                break;
                            }
                        }
                    }
                    else{
                        return 2;
                    }
                    break;
                case 14:
                    print("Returning to Movie Menu....");
                    sc.nextLine();
                    return 2;
                default:
                    print("Please enter a number from 1-14 only.");
                    continue;
            }
            break;
        }
        
        if(updateMovieListCSV(movieList)){
            return 1;
        }
        return 0;
    }
}
