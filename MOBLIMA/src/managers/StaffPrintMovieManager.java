package managers;

import java.util.List;



import entities.*;

public class StaffPrintMovieManager {
    	/**
	 * The seperator for array of string in csv
	 */
	static String SplitBy = ";";
	/**
	 * The csv seperator
	 */
	static String cvsSplitBy = ",";
	/**
	 * The seperator that represent "," when stored in csv
	 */
	static String SplitByColon = ":";
	public static void printMovieList(List<Movie>mList) {
		
		int count = 1;
		// ReviewListManager file = new ReviewListManager();
		// List<Review> rlist = file.getReviewList();
		System.out.println("#########################################################");
		System.out.println("################## DISPLAYING MOVIES ####################");
		System.out.println("#########################################################");
		System.out.println("");

		for(Movie m : mList) {
				String cast, genre;
				String casttmp = m.getCast();
				String genretmp = m.getGenres();
				String[] rating;

				System.out.printf("----------------- MOVIE %d -----------------\n", count);
				System.out.println("Movie Title: "+ m.getMovieTitle());
				System.out.println("Showing Status: "+ m.getShowingStatus());
				System.out.println("Synopsis: " + m.getSynopsis());
				System.out.println("Movie Director: "+ m.getMovieDirector());
				cast = casttmp.replaceAll(SplitBy, cvsSplitBy);
				System.out.println("Casts: "+ cast);
				cast = casttmp.replaceAll(SplitBy, cvsSplitBy);
				genre = genretmp.replaceAll(SplitBy, cvsSplitBy);
				System.out.println("Genres: "+ genre);
				System.out.println("Movie Rating: " + m.getMovieRating());
				System.out.println("Movie Duration: " + m.getMovieDuration());
				System.out.println("Profit Earned: " + m.getProfitEarned());
				System.out.println("Overall Rating Score: " + m.getOverallRatingScore());
				System.out.println("Release Date: " + m.getReleaseDate().toString());
				System.out.println("Movie Type: " + m.getMovieType());
				count++;
				System.out.println("");
			}
	}
}

