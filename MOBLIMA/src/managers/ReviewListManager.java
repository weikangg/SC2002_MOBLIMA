package managers;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import entities.*;

/**
 * A manager class for all actions related to the list of reviews in our data base 
 * @author Wei Kang
 * @version 2.5
 * @since 01-11-2022
 */
public class ReviewListManager {
    /**
	 * The path to the CSV file that stores all the reviews
	 */
	private static String path = System.getProperty("user.dir") +"\\data\\reviews\\reviews.csv";
	/**
	 * The separator for the columns in the csv file
	 */
    private static String item_Separator = ",";	
	/**
	 * For singleton pattern adherence. This ReviewListManager instance persists throughout runtime.
	 */
	private static ReviewListManager single_instance = null;
	/**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
	public static ReviewListManager getInstance()
    {
        if (single_instance == null)
            single_instance = new ReviewListManager();
        return single_instance;
    }

	/**
	 * The default constructor for the ReviewListManager class
	 */
	public ReviewListManager(){}

    /**
	 * Fetch all the reviews inside the reviews.csv file and compile them into a list 
	 * @return reviewList
	 */
    public List<Review>getReviewList(){
        List<Review>reviewList = new ArrayList<>();
        BufferedReader br = null;
        String line ="";
        Review reviewtmp;
        try {
            br = new BufferedReader(new FileReader(path));
            while((line = br.readLine()) !=null) {
                String[] reviewcsv = line.split(item_Separator);
                if(!reviewcsv[0].equals("REVIEW_ID")) {
                    reviewtmp = new Review(Integer.parseInt(reviewcsv[0]),reviewcsv[1],reviewcsv[2],reviewcsv[3],Double.parseDouble(reviewcsv[4]));
                    reviewList.add(reviewtmp);
                }
            }
        }
		catch(ArrayIndexOutOfBoundsException e){
			return reviewList;
		}
		catch (IOException e) {
            e.printStackTrace();
        }
        return reviewList;
    }
/**
	 * Appends the new review to the existing list of reviews and updates the list of reviews in the database
	 * @param reviewList 		 Existing list of reviews
	 * @param reviewID           This is the review's ID
	 * @param movieTitle	     This is the review's movie title
	 * @param username		     This is the review's reviewer username
	 * @param reviewDescription	 This is the review's description
	 * @param ratingScore		 This is the review's rating score
	 * @return true if update was successful, false if update was unsuccessful
	 */
    public static boolean addReviewList(List<Review> reviewList, int reviewID, String movieTitle, String username, String reviewDescription, double ratingScore) {
		Review review = new Review(reviewID, movieTitle, username, reviewDescription, ratingScore);
		reviewList.add(review);
		return updateReviewInCSV(reviewList);
	}
	/**
	 * Writes the list of reviews to the reviews.csv file for storage
	 * @param reviewList Existing list of reviews
	 * @return true if update was successful, false if update was unsuccessful
	 */
    public static boolean updateReviewInCSV(List<Review> reviewList) {
		FileWriter csvWriter;
		String separator = ",";
 
		try {
			csvWriter = new FileWriter(path);
			csvWriter.append("REVIEW_ID");
			csvWriter.append(separator);           
			csvWriter.append("MOVIE_TITLE");
			csvWriter.append(separator);
			csvWriter.append("REVIEWER");
			csvWriter.append(separator);
			csvWriter.append("REVIEW_DESCRIPTION");
			csvWriter.append(separator);
			csvWriter.append("RATING_SCORE");
			csvWriter.append("\n");

			for (Review reviewtmp : reviewList) {
				StringBuilder sb = new StringBuilder();
				sb.append(reviewtmp.getReviewID());
				sb.append(separator);
				sb.append(reviewtmp.getMovieTitle());
				sb.append(separator);
				sb.append(reviewtmp.getReviewer());
				sb.append(separator);
				sb.append(reviewtmp.getDescription());
				sb.append(separator);
				sb.append(reviewtmp.getRatingScore());
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
