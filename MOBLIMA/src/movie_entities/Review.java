package movie_entities;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Review implements Serializable {
    private String reviewID;
    private String username;
    private String reviewDescription;
    private double ratingScore;
    private LocalDateTime timestamp;
    private String movieID;

    //Gettors
    public String getReviewID(){
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
    public LocalDateTime getTimeStamp(){
        return timestamp;
    }
    public String getMovieID(){
        return movieID;
    }

    // Settors
    public void setReviewID(String reviewID){
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
    public void setTimeStamp(LocalDateTime timestamp){
        this.timestamp = timestamp;
    }
    public void setMovieID(String movieID){
        this.movieID = movieID;
    }
}