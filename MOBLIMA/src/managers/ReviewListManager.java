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
    // static String path = "src/data/reviews/reviews.csv";

    // csv separator
    static String item_Separator = ",";	
	static String row_Separator =";";
	static String first_Item =" ;";

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
                    reviewtmp = new Review(reviewcsv[0],reviewcsv[1],reviewcsv[2],reviewcsv[3],Double.parseDouble(reviewcsv[4]));
                    reviewList.add(reviewtmp);
                }
            }
        }
		catch(ArrayIndexOutOfBoundsException e){
			return reviewList;
			// System.out.println(e.getMessage());
			// e.printStackTrace();
		}
		catch (IOException e) {
            e.printStackTrace();
        }
        return reviewList;
    }

    public static boolean addReviewList(List<Review> reviewList, String movieTitle) {
		Review review;
		review = new Review();
		review.setMovieTitle(movieTitle);
        review.setReviewer(" ;");
        review.setReviewID("-1");
		review.setRatingScore(-1);
		review.setDescription(" ;");
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
			csvWriter.append("OVERALL_RATING_SCORE");
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
				sb.append(separator);
				sb.append('\n');
				csvWriter.append(sb.toString());
			}

			csvWriter.flush();
			csvWriter.close();
			return true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
}
