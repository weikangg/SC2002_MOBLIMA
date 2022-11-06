package entities;
/**
 * A class defining a Review Object.
 * @author Wei Kang
 * @version 2.5
 * @since 01-11-2022
 */
public class Review {
    // Attributes

    /**
	* This int is used to store the Review's ID.
	*/
    private int reviewID;
    /**
	* This String is used to store the Reviewer's username.
	*/
    private String username;
    /**
	* This String is used to store the Review's Description.
	*/
    private String reviewDescription;
    /**
	* This double is used to store the Review's rating score.
	*/
    private double ratingScore;
    /**
	* This String is used to store the Review's movie Title.
	*/
    private String movieTitle;
 
    // Constructor
	/**
	 * This constructor is used to create a new Review object
     * @param reviewID           This is the review's ID
	 * @param movieTitle	     This is the review's movieTitle
	 * @param username	         This is the reviewer's username
	 * @param reviewDescription	 This is the review's description
	 * @param ratingScore	     This is the review's rating score
	 */
    public Review(int reviewID, String movieTitle, String username, String reviewDescription, double ratingScore){
        this.reviewID = reviewID;
        this.movieTitle = movieTitle;
        this.username = username;
        this.reviewDescription = reviewDescription;
        this.ratingScore = ratingScore;
    }

    //Gettors

    /**
	 * Get Review's ID (int). Public method.
	 * @return Review's ID.
	 */
    public int getReviewID(){
        return reviewID;
    }
    /**
	 * Get Review's reviewer (String). Public method.
	 * @return Review's reviewer.
	 */
    public String getReviewer(){
        return username;
    }
    /**
	 * Get Review's description (String). Public method.
	 * @return Review's description.
	 */
    public String getDescription(){
        return reviewDescription;
    }
    /**
	 * Get Review's rating score (Double). Public method.
	 * @return Review's rating score.
	 */
    public double getRatingScore(){
        return ratingScore;
    }
    /**
	 * Get Review's movie title (String). Public method.
	 * @return Review's movie title.
	 */
    public String getMovieTitle(){
        return movieTitle;
    }

    // Settors

	/**
	 * Set Review's ID. Public method.
	 * @param reviewID int containing new ID.
	 */
    public void setReviewID(int reviewID){
        this.reviewID = reviewID;
    }
	/**
	 * Set Review's reviewer username. Public method.
	 * @param reviewerUsername String containing new username.
	 */
    public void setReviewer(String reviewerUsername){
        this.username = reviewerUsername;
    }
	/**
	 * Set Review's reviewer description. Public method.
	 * @param reviewDescription String containing new description.
	 */
    public void setDescription(String reviewDescription){
        this.reviewDescription = reviewDescription;
    }
	/**
	 * Set Review's rating score. Public method.
	 * @param ratingScore String containing new rating score.
	 */
    public void setRatingScore(double ratingScore){
        this.ratingScore = ratingScore;
    }
	/**
	 * Set Review's movie title. Public method.
	 * @param movieTitle String containing new movie title.
	 */
    public void setMovieTitle(String movieTitle){
        this.movieTitle = movieTitle;
    }
}