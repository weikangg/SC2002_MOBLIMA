package managers;

import java.math.BigDecimal;
import java.util.List;
import entities.*;

public class StaffPrintMovieManager {
	/**
	 * The separator for array of string in csv
	 */
	static String SplitBy = ";";
	/**
	 * The csv seperator
	 */
	static String csvSplitBy = ",";

	// STAFF CAN PRINT EVERYTHING & SEE ALL MOVIES REGARDLESS OF MOVIE STATUS 
	// STAFF CAN ALSO VIEW ALL REVIEWS
	public static void printMovieList(List<Movie>mList, List<Review>rList) {
		
		int movieCount = 1;
		// ReviewListManager file = new ReviewListManager();
		// List<Review> rlist = file.getReviewList();
		System.out.println("#########################################################");
		System.out.println("################## DISPLAYING MOVIES ####################");
		System.out.println("#########################################################");
		System.out.println("");

		if (mList.size() == 0){
			System.out.println("No Movies to display.");
			return;
		}

		for(Movie m : mList) {
				String cast, genre;
				String casttmp = m.getCast();
				String genretmp = m.getGenres();
				int reviewCount = 0, hasReviews = 0, printOverallRating = 0;
				System.out.printf("----------------- MOVIE %d -----------------\n", movieCount);
				System.out.println("Movie Title: "+ m.getMovieTitle());
				System.out.println("Showing Status: "+ m.getShowingStatus());
				System.out.println("Synopsis: " + m.getSynopsis());
				System.out.println("Movie Director: "+ m.getMovieDirector());
				cast = casttmp.replaceAll(SplitBy, csvSplitBy);
				System.out.println("Casts: "+ cast);
				cast = casttmp.replaceAll(SplitBy, csvSplitBy);
				genre = genretmp.replaceAll(SplitBy, csvSplitBy);
				System.out.println("Genres: "+ genre);
				System.out.println("Movie Rating: " + m.getMovieRating());
				System.out.println("Movie Duration: " + m.getMovieDuration() + " minutes");
				double profitEarned = m.getProfitEarned();
				BigDecimal bd = new BigDecimal(profitEarned);
				System.out.println("Profit Earned: " + bd.toPlainString());
				for(Review r: rList){
					if(reviewCount > 1 ){
						printOverallRating = 1;
						break;
					}
					if(r.getMovieTitle().equalsIgnoreCase(m.getMovieTitle())){
						reviewCount++;
					}
				}
				if(printOverallRating == 1){
					System.out.printf("Overall Rating Score: %.1f/5\n" , m.getOverallRatingScore() );
				}else{
					System.out.println("Overall Rating Score: NA");
				}
				reviewCount = 0;
				System.out.println("Release Date: " + m.getReleaseDate().toString());
				System.out.println("Movie Type: " + m.getMovieType());
				movieCount++;
				System.out.println("");
				System.out.println("Some Review Information of " + m.getMovieTitle() + ":");
				for(Review r: rList){
					if(r.getMovieTitle().equalsIgnoreCase(m.getMovieTitle())){
						System.out.println(Integer.toString(reviewCount+1)+". " + r.getDescription() + " [" + r.getRatingScore() + "/5.0]" + " - " + r.getReviewer());
						hasReviews = 1;
						reviewCount++;
					}
				}
				if(hasReviews == 0){
					System.out.println("No reviews available for this movie right now!");
				}
				System.out.println("");
			}
	}

	public static int printMovieByID(List<Movie>mList, List<Review>rList, int movieID) {
		int found = 0;
		System.out.println("#########################################################");
		System.out.println("################## DISPLAYING MOVIES ####################");
		System.out.println("#########################################################");
		System.out.println("");

		if (mList.size() == 0){
			System.out.println("Error! No Movies to display.");
		}

		for(Movie m : mList) {
			if(m.getMovieID() == movieID){
				found = 1;
				String cast, genre;
				String casttmp = m.getCast();
				String genretmp = m.getGenres();
				int reviewCount = 0, hasReviews = 0, printOverallRating = 0;
				System.out.printf("----------------- MOVIE %d -----------------\n", movieID);
				System.out.println("Movie Title: "+ m.getMovieTitle());
				System.out.println("Showing Status: "+ m.getShowingStatus());
				System.out.println("Synopsis: " + m.getSynopsis());
				System.out.println("Movie Director: "+ m.getMovieDirector());
				cast = casttmp.replaceAll(SplitBy, csvSplitBy);
				System.out.println("Casts: "+ cast);
				cast = casttmp.replaceAll(SplitBy, csvSplitBy);
				genre = genretmp.replaceAll(SplitBy, csvSplitBy);
				System.out.println("Genres: "+ genre);
				System.out.println("Movie Rating: " + m.getMovieRating());
				System.out.println("Movie Duration: " + m.getMovieDuration() + " minutes");
				double profitEarned = m.getProfitEarned();
				BigDecimal bd = new BigDecimal(profitEarned);
				System.out.println("Profit Earned: " + bd.toPlainString());
				for(Review r: rList){
					if(reviewCount > 1 ){
						printOverallRating = 1;
						break;
					}
					if(r.getMovieTitle().equalsIgnoreCase(m.getMovieTitle())){
						reviewCount++;
					}
				}
				if(printOverallRating == 1){
					System.out.printf("Overall Rating Score: %.1f/5\n" , m.getOverallRatingScore() );
				}else{
					System.out.println("Overall Rating Score: NA");
				}
				reviewCount = 0;
				System.out.println("Release Date: " + m.getReleaseDate().toString());
				System.out.println("Movie Type: " + m.getMovieType());
				System.out.println("");
				System.out.println("Some Review Information of " + m.getMovieTitle() + ":");
				for(Review r: rList){
					if(r.getMovieTitle().equalsIgnoreCase(m.getMovieTitle())){
						System.out.println(Integer.toString(reviewCount+1)+". " + r.getDescription() + " [" + r.getRatingScore() + "/5.0]" + " - " + r.getReviewer());
						hasReviews = 1;
						reviewCount++;
					}
				}
				if(hasReviews == 0){
					System.out.println("No reviews available for this movie right now!");
				}
				System.out.println("");
			}
		}
		if(found == 1){
			return 1;
		}
		return 0;
	}
}

