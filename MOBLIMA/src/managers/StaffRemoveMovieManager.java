package managers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import entities.*;
import static utils.IOUtils.*;
import java.util.Scanner;
import static managers.MovieListManager.*;

public class StaffRemoveMovieManager {
    private static Scanner sc = new Scanner(System.in);
    static String path = System.getProperty("user.dir") +"\\MOBLIMA\\data\\movies\\movies.csv";

    public static boolean removeMovie(List<Movie> mList){
        System.out.println("#########################################################");
		System.out.println("#################### REMOVING MOVIES ####################");
		System.out.println("#########################################################");
		System.out.println("");

        String title;
        List<Movie>newList = new ArrayList<Movie>();
        System.out.println("Enter Movie Title: ");
        title = sc.next();
        if(confirm("Remove Title:")){
            for(Movie m : mList){
                if(!m.getMovieTitle().equals(title)){
                    String movieTitle = m.getMovieTitle();
                    ShowingStatus showingStatus =  m.getShowingStatus();
                    String synopsis = m.getSynopsis();
                    String movieDirector =  m.getMovieDirector();
                    String casts = m.getCast();
                    String genres = m.getGenres();
                    MovieRating movieRating = m.getMovieRating();
                    int movieDuration =  m.getMovieDuration();
                    double profitEarned =  m.getProfitEarned();
                    double overallRatingScore =  m.getOverallRatingScore();
                    LocalDate releaseDate =  m.getReleaseDate();
                    MovieType movieType = m.getMovieType();
                    Movie newMovie = new Movie(movieTitle,showingStatus, synopsis, movieDirector, casts, genres, movieRating, movieDuration, profitEarned,  overallRatingScore, releaseDate, movieType);
                    newList.add(newMovie);
                }
            }
            return updateMovieListCSV(newList,1);
        }
        else{
            System.out.println("Removing Movie Cancelled, Returning to Original Menu...");
            return true;
        }
    }
}