package entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;

import com.opencsv.*;

public class Movie {
    // Attributes
    private int movieID;
    private String movieTitle;
    private ShowingStatus showingStatus;
    private String synopsis;
    private String movieDirector;
    private String cast; // at least 2 cast, so need string
    private String genres;
    private MovieRating movieRating;
    private int movieDuration;
    private double profitEarned;
    private double overallRatingScore;
    private LocalDateTime releaseDateTime;



    // private List<String>pastReviews; // list of reviews
    // private List<String>pastReviewRating;
    // private double totalRatingScore;
    // private double totalNoOfReviews;


    // Constructor

    // public Movie(String movieTitle,ShowingStatus showingStatus, String synopsis,String movieDirector, String cast, String genres, 
    //             MovieRating movieRating,int movieDuration,  double profitEarned, double overallRatingScore, LocalDateTime releaseDateTime){
    //     this.movieTitle = movieTitle;
    //     this.showingStatus = showingStatus;
    //     this.synopsis = synopsis;
    //     this.movieDirector = movieDirector;
    //     this.cast = cast;
    //     this.genres = genres;
    //     this.movieRating = movieRating;
    //     this.movieDuration = movieDuration;
    //     this.profitEarned = profitEarned;
    //     // this.pastReviews = new ArrayList<String>();
    //     // this.pastReviewRating = new ArrayList<String>();
    //     // this.totalRatingScore = 0;
    //     // this.totalNoOfReviews = 0;
    //     this.overallRatingScore = overallRatingScore;
    //     this.releaseDateTime = releaseDateTime; // gets current time.
    // }

    

    

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
    // public List<String> getPastReviews() {
    //     return this.pastReviews;
    // }
    // public List<String> getPastReviewRating() {
    //     return this.pastReviewRating;
    // }
    public double getOverallRatingScore() {
        return this.overallRatingScore;
    }
    // public double getTotalNoOfReviews() {
    //     return this.totalNoOfReviews;
    // }
    // public double getTotalRatingScore() {
    //     return this.totalRatingScore;
    // }
    public ShowingStatus getShowingStatus() {
        return this.showingStatus;
    }
    public LocalDateTime getReleaseDate() {
        return this.releaseDateTime;
    }

    public double getProfitEarned() {
        return this.profitEarned;
    }

    // Settors

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
    // public void setPastReviews(ArrayList<String> pastReviews) {
    //     this.pastReviews = pastReviews;
    // }
    // public void setPastReviewRating(ArrayList<String> pastReviewRating) {
    //     this.pastReviewRating = pastReviewRating;
    // }
    public void setOverallRatingScore(double overallRatingScore) {
        this.overallRatingScore = overallRatingScore;
    }
    // public void setTotalNoOfReviews(int totalNoOfReviews) {
    //     this.totalNoOfReviews = totalNoOfReviews;
    // }
    // public void setTotalRatingScore(double totalRatingScore) {
    //     this.totalRatingScore = totalRatingScore;
    // }
    public void setShowingStatus(ShowingStatus showingStatus) {
        this.showingStatus = showingStatus;
    }
    public void setReleaseDateTime(LocalDateTime releaseDateTime) {
        this.releaseDateTime = releaseDateTime;
    }
    public void setProfitEarned(double profitEarned) {
        this.profitEarned = profitEarned;
    }

    // // Methods
    // public void showMovieInfo() {
    // 	// Title
    // 	System.out.printf("Movie Title: %s\n", getMovieTitle());

    //     // Showing status
    //     System.out.printf("Showing Status: %s\n", getShowingStatus().toString());

    //     // Movie Duration
    //     System.out.printf("Movie Duration: %d minutes\n", getMovieDuration());

    //     // Synopsis
    //     System.out.printf("Synopsis: %s\n", getSynopsis());

    //     // Director
    //     System.out.printf("Movie Director: %s\n", getMovieDirector());

    // 	// Genres
    //     System.out.printf("Genres: %s\n", getGenres());
        	
    //     // MovieRating
    //     System.out.printf("Rating: %s\n", getMovieRating().toString());

    //     // Cast
    //     System.out.printf("Cast: %s\n",getCast());


    // 	// Overall Rating Score
    //     if(getTotalNoOfReviews()<=1){
    //         System.out.println("Overall Rating Score: N/A");
    //     }
    //     else{
    //         System.out.println("Overall Rating Score: " + getOverallRatingScore());
    //     }

    //     // Past Reviews & Reviewers' Ratings
    //     // Allow to view all reviews
    //     for(int i = 0 ; i < getPastReviews().size(); i++){
    //         System.out.printf("%d. " + getPastReviews().get(i).toString() + ", ", i+1);
    //         System.out.printf("Rating Score: %d\n", getPastReviewRating().get(i).toString());
    //     }
    // }

    // // Adding & removing movie review.
    // public void addMovieReview(String review, String ratingScore){
    //     this.pastReviews.add(review);
    //     this.pastReviewRating.add(ratingScore);
    // }

    // public void removeMovieReview(String review){
    //     for(int i = 0 ; i < getPastReviews().size();i++){
    //         if(getPastReviews().get(i).equals(review)){
    //             pastReviews.remove(i);
    //             pastReviewRating.remove(i);
    //         }
    //     }
    // }
}
