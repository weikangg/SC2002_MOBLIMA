package managers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import entities.*;

public class MovieListManager {
	
    private static MovieListManager single_instance = null;
	public static MovieListManager getInstance()
    {
        if (single_instance == null)
            single_instance = new MovieListManager();
        return single_instance;
    }
	
    // Constructor
    public MovieListManager(){}

    static String path = System.getProperty("user.dir") +"\\data\\movies\\movies.csv";
    // static String path = "src/data/movies/movies.csv";
    static String separator = ",";

    public List<Movie>getMovieList(){
    	List<Movie>movieList = new ArrayList<>();
    	BufferedReader br = null;
		String line = "";
		Movie movietmp;
		try {
			br = new BufferedReader(new FileReader(path));
			while((line = br.readLine()) !=null ) {
				String[] moviecsv = line.split(separator);
				if(!moviecsv[0].equals("MOVIE_ID")) {
					String releaseDate = moviecsv[11];
					String endOfShowingDate = moviecsv[12];
					LocalDate ReleaseDate = LocalDate.parse(releaseDate,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					LocalDate EndOfShowingDate = LocalDate.parse(endOfShowingDate,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					movietmp = new Movie(Integer.parseInt(moviecsv[0]) ,moviecsv[1],ShowingStatus.valueOf(moviecsv[2]),moviecsv[3],moviecsv[4],moviecsv[5],moviecsv[6], MovieRating.valueOf(moviecsv[7]),
							Integer.parseInt(moviecsv[8]),Double.parseDouble(moviecsv[9]),Double.parseDouble(moviecsv[10]), ReleaseDate,EndOfShowingDate,MovieType.valueOf(moviecsv[13]));
					movieList.add(movietmp);		
				}
			}
			updateMovieRatingScores(movieList, ReviewListManager.getInstance().getReviewList());
			updateEndOfShowing(movieList);
			br.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File not found!");
		}
		catch(ArrayIndexOutOfBoundsException e){
			return movieList;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return movieList;
    }

	public static Movie getMovie(int id){
		List<Movie> movies = getInstance().getMovieList();

		for(int i = 0; i < movies.size(); i++){
            if(movies.get(i).getMovieID() == id){
				return movies.get(i);
			}
        }
		return null;
	}

    public static boolean addMovieList(List<Movie> movieList, int movieID,String movieTitle, String synopsis,String movieDirector, String cast, String genres, int movieDuration, 
									   ShowingStatus showingStatus, double profitEarned, MovieRating movieRating, double overallRatingScore, LocalDate releaseDateTime, LocalDate endOfShowingDate, MovieType movieType) {
        Movie newMovie = new Movie(movieID, movieTitle,showingStatus,synopsis,movieDirector,cast, genres,movieRating,movieDuration,profitEarned,overallRatingScore,releaseDateTime, endOfShowingDate,movieType);
        movieList.add(newMovie);
        return updateMovieListCSV(movieList);
    }

	// Gets all the rating scores from complete review list, calculate the average rating score & sets it as the overall rating score for the movie.

	public static boolean updateMovieRatingScores(List<Movie>movieList, List<Review>reviewList){
		for (Movie m: movieList){
			int movieExists = 0;
			double totalMovies = 0, TotalRating = 0;
			for(Review r : reviewList){
				if(r.getMovieTitle().equalsIgnoreCase(m.getMovieTitle())){
					movieExists = 1;
					totalMovies++;
					TotalRating += r.getRatingScore();
				}
			}
			if(movieExists == 1){
				m.setOverallRatingScore(TotalRating/totalMovies);
			}
			else{
				m.setOverallRatingScore(0);
			}
		}
		return updateMovieListCSV(movieList);
	}

	// TO DO
	public static boolean updateMovieSales(List<Movie>movieList){
		return true;
	}


	// If Current date is after the end of showing date, we will update it to end of showing immediately.
	public static boolean updateEndOfShowing(List<Movie>movieList){
		for(Movie m : movieList){
			if(LocalDate.now().isAfter(m.getEndOfShowingDate())){
				m.setShowingStatus(ShowingStatus.FINISHED_SHOWING);
			}
		}
		return updateMovieListCSV(movieList);
	}

    public static boolean updateMovieListCSV(List<Movie> movieList) {
		FileWriter csvWriter;
		try {
			csvWriter = new FileWriter(path,false);
			csvWriter.append("MOVIE_ID");
			csvWriter.append(separator);
			csvWriter.append("MOVIE_TITLE");
			csvWriter.append(separator);
			csvWriter.append("SHOWING_STATUS");
			csvWriter.append(separator);
			csvWriter.append("SYNOPSIS");
			csvWriter.append(separator);
			csvWriter.append("MOVIE_DIRECTOR");
			csvWriter.append(separator);
			csvWriter.append("CASTS");
			csvWriter.append(separator);
			csvWriter.append("GENRES");
			csvWriter.append(separator);
			csvWriter.append("MOVIE_RATING");
			csvWriter.append(separator);
			csvWriter.append("MOVIE_DURATION");
			csvWriter.append(separator);
			csvWriter.append("PROFIT_EARNED");
			csvWriter.append(separator);
			csvWriter.append("OVERALL_RATING_SCORE");
            csvWriter.append(separator);
			csvWriter.append("RELEASE_DATE");
			csvWriter.append(separator);
			csvWriter.append("END_OF_SHOWING_DATE");
			csvWriter.append(separator);
			csvWriter.append("MOVIE_TYPE");
			csvWriter.append("\n");

			for (Movie movie : movieList) {
				StringBuilder sb = new StringBuilder();
				sb.append(movie.getMovieID());
				sb.append(separator);
				sb.append(movie.getMovieTitle());
				sb.append(separator);
                sb.append(movie.getShowingStatus().toString());
				sb.append(separator);
				sb.append(movie.getSynopsis());
				sb.append(separator);
				sb.append(movie.getMovieDirector());
				sb.append(separator);
				sb.append(movie.getCast());
				sb.append(separator);
				sb.append(movie.getGenres());
				sb.append(separator);
				sb.append(movie.getMovieRating().toString());
				sb.append(separator);
				sb.append(movie.getMovieDuration());
				sb.append(separator);
				sb.append(movie.getProfitEarned());
				sb.append(separator);
				sb.append(movie.getOverallRatingScore());
                sb.append(separator);
				sb.append(movie.getReleaseDate());
				sb.append(separator);
				sb.append(movie.getEndOfShowingDate());
				sb.append(separator);
				sb.append(movie.getMovieType());
				sb.append('\n');
				csvWriter.append(sb.toString());
			}

			csvWriter.flush();
			csvWriter.close();
			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}
}
