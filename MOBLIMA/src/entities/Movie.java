package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Movie {
    // Attributes
    private String movieID;
    private long ticketsSold;
    private double profitEarned;
    private int movieDuration;
    private ShowingStatus showingStatus;
    private String movieTitle;
    private List<Genre> genres;
    private String movieDirector;
    private List<String> cast; // at least 2 cast, so need string
    private String synopsis;
    private MovieRating movieRating;
    private List<String>pastReviews; // list of reviews
    private List<String>pastReviewRating;
    private double totalRatingScore;
    private double totalNoOfReviews;
    private double overallRatingScore;
    private LocalDateTime releaseDateTime;

    // Constructor
    public Movie(){
        this.movieID = "";
        this.ticketsSold = 0;
        this.profitEarned = 0;
        this.movieDuration = 0;
        this.showingStatus = ShowingStatus.COMING_SOON;
        this.movieTitle = "";
        this.genres = new ArrayList<Genre>();
        this.movieDirector = "";
        this.cast = new ArrayList<String>();
        this.synopsis = "";
        this.pastReviews = new ArrayList<String>();
        this.pastReviewRating = new ArrayList<String>();
        this.totalRatingScore = 0;
        this.totalNoOfReviews = 0;
        this.overallRatingScore = 0;
        this.releaseDateTime = LocalDateTime.now(); // gets current time.
        this.movieDuration = 0;
    }

    // Gettors
    public String getMovieID() {
        return this.movieID;
    }    
    public String getMovieTitle() {
        return this.movieTitle;
    }
    public List<Genre> getGenres() {
        return this.genres;
    }
    public String getMovieDirector() {
        return this.movieDirector;
    }
    public List<String> getCast() {
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
    public List<String> getPastReviews() {
        return this.pastReviews;
    }
    public List<String> getPastReviewRating() {
        return this.pastReviewRating;
    }
    public double getOverallRatingScore() {
        return this.overallRatingScore;
    }
    public double getTotalNoOfReviews() {
        return this.totalNoOfReviews;
    }
    public double getTotalRatingScore() {
        return this.totalRatingScore;
    }
    public ShowingStatus getShowingStatus() {
        return this.showingStatus;
    }
    public LocalDateTime getReleaseDate() {
        return this.releaseDateTime;
    }
    public long getTicketsSold() {
        return this.ticketsSold;
    }
    public double getProfitEarned() {
        return this.profitEarned;
    }

    // Settors
    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }
    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }
    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }
    public void setCast(ArrayList<String> cast) {
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
    public void setPastReviews(ArrayList<String> pastReviews) {
        this.pastReviews = pastReviews;
    }
    public void setPastReviewRating(ArrayList<String> pastReviewRating) {
        this.pastReviewRating = pastReviewRating;
    }
    public void setOverallRatingScore(double overallRatingScore) {
        this.overallRatingScore = overallRatingScore;
    }
    public void setTotalNoOfReviews(int totalNoOfReviews) {
        this.totalNoOfReviews = totalNoOfReviews;
    }
    public void setTotalRatingScore(double totalRatingScore) {
        this.totalRatingScore = totalRatingScore;
    }
    public void setShowingStatus(ShowingStatus showingStatus) {
        this.showingStatus = showingStatus;
    }
    public void setReleaseDateTime(LocalDateTime releaseDateTime) {
        this.releaseDateTime = releaseDateTime;
    }
    public void setTicketsSold(long ticketsSold) {
        this.ticketsSold = ticketsSold;
    }
    public void setProfitEarned(double profitEarned) {
        this.profitEarned = profitEarned;
    }

    // Methods
    public void showMovieInfo() {
        // MovieID
        System.out.printf("Movie ID: %s\n", getMovieID());

    	// Title
    	System.out.printf("Movie Title: %s\n", getMovieTitle());

        // Showing status
        System.out.printf("Showing Status: %s\n", getShowingStatus().toString());

        // Movie Duration
        System.out.printf("Movie Duration: %d minutes\n", getMovieDuration());

        // Synopsis
        System.out.printf("Synopsis: %s\n", getSynopsis());

        // Director
        System.out.printf("Movie Director: %s\n", getMovieDirector());

    	// Genres
        System.out.print("Genres: ");
        for (int i = 0; i < getGenres().size(); i++) {
        	if (i+1 != getGenres().size()) {
                System.out.print(getGenres().get(i).toString() + ", ");
        	}
            else{
                System.out.println(getGenres().get(i).toString());
            }
        }
        	
        // MovieRating
        System.out.printf("Rating: %s\n", getMovieRating().toString());

        // Cast
        System.out.print("Cast: ");
        for (int i = 0; i < getCast().size(); i++) {  	
        	if (i+1 != getCast().size()) {
        		System.out.print(getCast().get(i).toString() + ", ");
        	}
            else{
                System.out.println(getCast().get(i).toString());
            }
        }

    	// Overall Rating Score
        if(getTotalNoOfReviews()<=1){
            System.out.println("Overall Rating Score: N/A");
        }
        else{
            System.out.println("Overall Rating Score: " + getOverallRatingScore());
        }

        // Past Reviews & Reviewers' Ratings
        // Allow to view all reviews
        for(int i = 0 ; i < getPastReviews().size(); i++){
            System.out.printf("%d. " + getPastReviews().get(i).toString() + ", ", i+1);
            System.out.printf("Rating Score: %d\n", getPastReviewRating().get(i).toString());
        }
    }

    // Adding & removing movie review.
    public void addMovieReview(String review, String ratingScore){
        this.pastReviews.add(review);
        this.pastReviewRating.add(ratingScore);
    }

    public void removeMovieReview(String review){
        for(int i = 0 ; i < getPastReviews().size();i++){
            if(getPastReviews().get(i).equals(review)){
                pastReviews.remove(i);
                pastReviewRating.remove(i);
            }
        }
    }
}
