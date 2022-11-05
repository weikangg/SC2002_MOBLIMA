package entities;

import java.time.LocalDate;

public class Movie {
   
    // Attributes
    private int movieID;
    private String movieTitle;
    private ShowingStatus showingStatus;
    private String synopsis;
    private String movieDirector;
    private String cast; 
    private String genres;
    private MovieRating movieRating;
    private int movieDuration;
    private double profitEarned;
    private double overallRatingScore;
    private LocalDate releaseDate;
    private LocalDate endOfShowingDate;
    private MovieType movieType;

    // Constructor

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
    public int getMovieID(){
        return movieID;
    }
    public String getMovieTitle() {
        return this.movieTitle;
    }
    public String getGenres() {
        return this.genres;
    }
    public String getMovieDirector() {
        return this.movieDirector;
    }
    public String getCast() {
        return this.cast;
    }
    public String getSynopsis() {
        return this.synopsis;
    }
    public MovieRating getMovieRating() {
        return this.movieRating;
    }
    public int getMovieDuration() {
        return this.movieDuration;
    }

    public double getOverallRatingScore() {
        return this.overallRatingScore;
    }

    public ShowingStatus getShowingStatus() {
        return this.showingStatus;
    }
    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }
    public LocalDate getEndOfShowingDate(){
        return this.endOfShowingDate;
    }
    public double getProfitEarned() {
        return this.profitEarned;
    }

    public MovieType getMovieType(){
        return this.movieType;
    }
    
    // Settors
    public void setMovieID(int movieID){
        this.movieID = movieID;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
    public void setGenres(String genres) {
        this.genres = genres;
    }
    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }
    public void setCast(String cast) {
        this.cast = cast;
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    public void setMovieRating(MovieRating movieRating) {
        this.movieRating = movieRating;
    }
    public void setMovieDuration(int movieDuration) {
        this.movieDuration = movieDuration;
    }
    public void setOverallRatingScore(double overallRatingScore) {
        this.overallRatingScore = overallRatingScore;
    }

    public void setShowingStatus(ShowingStatus showingStatus) {
        this.showingStatus = showingStatus;
    }
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    public void setEndOfShowingDate(LocalDate endOfShowingDate){
        this.endOfShowingDate = endOfShowingDate;
    }

    public void setProfitEarned(double profitEarned) {
        this.profitEarned = profitEarned;
    }

    public void setMovieType(MovieType movieType){
        this.movieType = movieType;
    }
}
