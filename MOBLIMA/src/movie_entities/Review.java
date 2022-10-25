package main.Objects;

import java.time.LocalDateTime;

public class Review {

    //Attributes
    private String reviewID; //ID Number of Review
    private String nameOfReviewer; 
    private String titleOfReview;
    private String bodyOfReview;
    private String movieID;
    private double movieScore;
    private LocalDateTime reviewDT; //Actual Date and Time of review

    //Accessors
    public String getReviewID() {
        return reviewID;
    }
    public String getReviewerName() {
        return nameOfReviewer;
    }
    public String getReviewerTitle() {
        return titleOfReview;
    }
    public String getReviewBody() {
        return bodyOfReview;
    }
    public String getMovieID() {
        return movieID;
    }
    public double getMovieScore() {
        return movieScore;
    }
    public LocalDateTime getReviewDT() {
        return reviewDT;
    }

    //Mutators
    public String setReviewID(String reviewID) {
        this.reviewID = reviewID;
    }
    public String setReviewerName(String nameOfReviewer) {
        this.nameOfReviewer = nameOfReviewer;
    }
    public String setReviewTitle(String titleOfReview) {
        this.titleOfReview = titleOfReview;
    }
    public String setReviewBody(String bodyOfReview) {
        this.bodyOfReview = bodyOfReview;
    }
    public String setMovieID(String movieID) {
        this.movieID = movieID;
    }
    public String setMovieScore(double movieScore) {
        this.movieScore = movieScore;
    }
    public LocalDateTime setReviewDT(LocalDateTime reviewDT) {
        this.reviewDT = reviewDT;
    }
}














}