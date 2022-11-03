package entities;

import java.io.Serializable;

public class Review implements Serializable {
    private int reviewID;
    private String username;
    private String reviewDescription;
    private double ratingScore;
    private String movieTitle;

    // Constructor
    public Review(){

    }

    public Review(int reviewID, String movieTitle, String username, String reviewDescription, double ratingScore){
        this.reviewID = reviewID;
        this.movieTitle = movieTitle;
        this.username = username;
        this.reviewDescription = reviewDescription;
        this.ratingScore = ratingScore;
    }

    //Gettors
    public int getReviewID(){
        return reviewID;
    }
    public String getReviewer(){
        return username;
    }
    public String getDescription(){
        return reviewDescription;
    }
    public double getRatingScore(){
        return ratingScore;
    }
    public String getMovieTitle(){
        return movieTitle;
    }

    // Settors
    public void setReviewID(int reviewID){
        this.reviewID = reviewID;
    }
    public void setReviewer(String reviewerUsername){
        this.username = reviewerUsername;
    }
    public void setDescription(String reviewDescription){
        this.reviewDescription = reviewDescription;
    }
    public void setRatingScore(double ratingScore){
        this.ratingScore = ratingScore;
    }
    public void setMovieTitle(String movieTitle){
        this.movieTitle = movieTitle;
    }
}