package entities;

import java.time.LocalDate;
/**
 * A class defining a Movie Object.
 * @author Wei Kang
 * @version 2.5
 * @since 01-11-2022
 */
public class Movie {
   
    // Attributes
	
    /**
	* This int is used to store the Movie's ID.
	*/
    private int movieID;
    /**
	* This String is used to store the Movie's title.
	*/
    private String movieTitle;
    /**
	* This ShowingStatus is used to store the Movie's current ShowingStatus.
	*/
    private ShowingStatus showingStatus;
    /**
	* This string is used to store the Movie's synopsis.
	*/
    private String synopsis;
    /**
	* This string is used to store the Movie Director's name.
	*/
    private String movieDirector;
    /**
	* This String is used to store the Movie's casts.
	*/
    private String cast; 
    /**
	* This String is used to store the Movie's genres.
	*/
    private String genres;
    /**
	* This MovieRating is used to store the Movie's Rating.
	*/
    private MovieRating movieRating;
    /**
	* This int is used to store the Movie's duration.
	*/
    private int movieDuration;
    /**
	* This double is used to store the Movie's total profit earned.
	*/
    private double profitEarned;
    /**
	* This double is used to store the Movie's overall rating score as an average of all rating scores.
	*/
    private double overallRatingScore;
    /**
	* This LocalDate is used to store the Movie's release date.
	*/
    private LocalDate releaseDate;
    /**
	* This LocalDate is used to store the Movie's end of showing date.
	*/
    private LocalDate endOfShowingDate;
    /**
	* This MovieType is used to store the Movie's type.
	*/
    private MovieType movieType;

    // Constructor
	/**
	 * This constructor is used to create a new Movie object
     * @param movieID            This is the movie's ID
	 * @param movieTitle	     This is the movie's name
	 * @param showingStatus	     This is the movie's showingStatus
	 * @param synopsis		     This is the movie's synopsis
	 * @param movieDirector	     This is the movie's director(s)
	 * @param cast			     This is the movie's casts
	 * @param genres		     This is the movie's genres
	 * @param movieDuration	     This is the movie's duration
	 * @param profitEarned	     This is the movie's total profit earned
	 * @param overallRatingScore This is the movie's overall rating score
	 * @param releaseDate	     This is the movie's release date
	 * @param endOfShowingDate	 This is the movie's end of showing date
	 * @param movieType	         This is the movie's movie type
	 */
    public Movie(int movieID,String movieTitle,ShowingStatus showingStatus, String synopsis,String movieDirector, String cast, String genres, 
                MovieRating movieRating,int movieDuration,  double profitEarned, double overallRatingScore, LocalDate releaseDate,LocalDate endOfShowingDate,MovieType movieType){
        this.movieID = movieID;
        this.movieTitle = movieTitle;
        this.showingStatus = showingStatus;
        this.synopsis = synopsis;
        this.movieDirector = movieDirector;
        this.cast = cast;
        this.genres = genres;
        this.movieRating = movieRating;
        this.movieDuration = movieDuration;
        this.profitEarned = profitEarned;
        this.overallRatingScore = overallRatingScore;
        this.releaseDate = releaseDate; // gets current time.
        this.endOfShowingDate = endOfShowingDate;
        this.movieType = movieType;
    }

    // Gettors

    /**
	 * Get Movie's ID (int). Public method.
	 * @return Movie's ID.
	 */
    public int getMovieID(){
        return movieID;
    }
     /**
	 * Get Movie's Title (String). Public method.
	 * @return Movie's Title.
	 */   
    public String getMovieTitle() {
        return this.movieTitle;
    }
    /**
	 * Get Movie's Genres (String). Public method.
	 * @return Movie's Genres.
	 */   
    public String getGenres() {
        return this.genres;
    }
     /**
	 * Get Movie's Director Name (String). Public method.
	 * @return Movie's Director Name.
	 */   
    public String getMovieDirector() {
        return this.movieDirector;
    }
     /**
	 * Get Movie's Casts (String). Public method.
	 * @return Movie's Casts.
	 */   
    public String getCast() {
        return this.cast;
    }
     /**
	 * Get Movie's Synopsis (String). Public method.
	 * @return Movie's Synopsis.
	 */   
    public String getSynopsis() {
        return this.synopsis;
    }
     /**
	 * Get Movie's Rating (MovieRating). Public method.
	 * @return Movie's Rating.
	 */   
    public MovieRating getMovieRating() {
        return this.movieRating;
    }
     /**
	 * Get Movie's Duration (int). Public method.
	 * @return Movie's duration.
	 */   
    public int getMovieDuration() {
        return this.movieDuration;
    }
     /**
	 * Get Movie's Overall Rating Score (double). Public method.
	 * @return Movie's Overall Rating Score.
	 */   
    public double getOverallRatingScore() {
        return this.overallRatingScore;
    }
     /**
	 * Get Movie's ShowingStatus (ShowingStatus). Public method.
	 * @return Movie's ShowingStatus.
	 */   
    public ShowingStatus getShowingStatus() {
        return this.showingStatus;
    }
     /**
	 * Get Movie's Release Date (LocalDate). Public method.
	 * @return Movie's Release Date.
	 */   
    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }
     /**
	 * Get Movie's End of Showing Date (LocalDate). Public method.
	 * @return Movie's End of Showing date.
	 */   
    public LocalDate getEndOfShowingDate(){
        return this.endOfShowingDate;
    }
     /**
	 * Get Movie's profit earned (Double). Public method.
	 * @return Movie's profit earned.
	 */   
    public double getProfitEarned() {
        return this.profitEarned;
    }
     /**
	 * Get Movie's type (MovieType). Public method.
	 * @return Movie's type.
	 */   
    public MovieType getMovieType(){
        return this.movieType;
    }
    
    // Settors

	/**
	 * Set Movie's ID. Public method.
	 * @param movieID int containing new ID.
	 */
    public void setMovieID(int movieID){
        this.movieID = movieID;
    }
	/**
	 * Set Movie's title. Public method.
	 * @param movieTitle String containing new title.
	 */
    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
	/**
	 * Set Movie's genres. Public method.
	 * @param genres String containing new genres.
	 */
    public void setGenres(String genres) {
        this.genres = genres;
    }
	/**
	 * Set Movie's Director. Public method.
	 * @param movieDirector String containing new movie director.
	 */
    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }
	/**
	 * Set Movie's casts. Public method.
	 * @param cast String containing new cast.
	 */
    public void setCast(String cast) {
        this.cast = cast;
    }
	/**
	 * Set Movie's synopsis. Public method.
	 * @param synopsis String containing new synopsis.
	 */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
	/**
	 * Set Movie's rating. Public method.
	 * @param movieRating movieRating containing new movie rating.
	 */
    public void setMovieRating(MovieRating movieRating) {
        this.movieRating = movieRating;
    }
	/**
	 * Set Movie's duration. Public method.
	 * @param movieDuration int containing new movie duration.
	 */
    public void setMovieDuration(int movieDuration) {
        this.movieDuration = movieDuration;
    }
	/**
	 * Set Movie's overall rating score. Public method.
	 * @param overallRatingScore Double containing new overall rating score.
	 */
    public void setOverallRatingScore(double overallRatingScore) {
        this.overallRatingScore = overallRatingScore;
    }
	/**
	 * Set Movie's showing status. Public method.
	 * @param showingStatus ShowingStatus containing new showing status.
	 */
    public void setShowingStatus(ShowingStatus showingStatus) {
        this.showingStatus = showingStatus;
    }
	/**
	 * Set Movie's release date. Public method.
	 * @param releaseDate LocalDate containing new release date.
	 */
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
	/**
	 * Set Movie's end of showing date. Public method.
	 * @param endOfShowingDate LocalDate containing new end of showing date.
	 */
    public void setEndOfShowingDate(LocalDate endOfShowingDate){
        this.endOfShowingDate = endOfShowingDate;
    }
	/**
	 * Set Movie's profit earned. Public method.
	 * @param profitEarned Double containing new profit earned.
	 */
    public void setProfitEarned(double profitEarned) {
        this.profitEarned = profitEarned;
    }
	/**
	 * Set Movie's type. Public method.
	 * @param movieType MovieType containing new movie type.
	 */
    public void setMovieType(MovieType movieType){
        this.movieType = movieType;
    }
}
