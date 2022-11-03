package managers;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import entities.*;

public class ReviewListManager {
	static String path = System.getProperty("user.dir") +"\\data\\reviews\\reviews.csv";

    // csv separator
    static String item_Separator = ",";	

	private static ReviewListManager single_instance = null;
	public static ReviewListManager getInstance()
    {
        if (single_instance == null)
            single_instance = new ReviewListManager();
        return single_instance;
    }

	// Constructor
	public ReviewListManager(){}


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

    public static boolean addReviewList(List<Review> reviewList, int reviewID, String movieTitle, String username, String reviewDescription, double ratingScore) {
		Review review = new Review(reviewID, movieTitle, username, reviewDescription, ratingScore);
		reviewList.add(review);
		return updateReviewInCSV(reviewList);
	}

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
