package managers;

import java.util.List;
import entities.*;

public class CustomerMovieManager {
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

	// Displays all info about movie + limited 3 reviews on each movie. If 0 reviews, prints a placeholder message
	// Only shows info of movies which are preview or coming soon
	public static void printMovieList(List<Movie>mList, List<Review>rList) {
		int movieCount = 1;
		System.out.println("#########################################################");
		System.out.println("################## DISPLAYING MOVIES ####################");
		System.out.println("#########################################################");
		System.out.println("");

		if (mList.size() == 0){
			System.out.println("No Movies to display.");
		}

		for(Movie m : mList) {
			if(m.getShowingStatus().equals(ShowingStatus.PREVIEW) || m.getShowingStatus().equals(ShowingStatus.NOW_SHOWING)){
				String cast, genre;
				String casttmp = m.getCast();
				String genretmp = m.getGenres();
				int reviewCount = 1, hasReviews = 0;
				System.out.printf("----------------- MOVIE %d -----------------\n", movieCount);
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
				System.out.println("Movie Duration: " + m.getMovieDuration() + " minutes");
				//double profitEarned = m.getProfitEarned();
				//BigDecimal bd = new BigDecimal(profitEarned);
				//System.out.println("Profit Earned: " + bd.toPlainString());
				System.out.println("Overall Rating Score: " + m.getOverallRatingScore());
				System.out.println("Release Date: " + m.getReleaseDate().toString());
				System.out.println("Movie Type: " + m.getMovieType());
				System.out.println("");
				System.out.println("Some Review Information of " + m.getMovieTitle() + ":");
				for(Review r: rList){
					if(reviewCount > 3 ){
						break;
					}
					if(r.getMovieTitle().equalsIgnoreCase(m.getMovieTitle())){
						System.out.println(Integer.toString(reviewCount)+". " + r.getDescription() + " [" + r.getRatingScore() + "/5.0]" + " - " + r.getReviewer());
						hasReviews = 1;
						reviewCount++;
					}
				}
				if(hasReviews == 0){
					System.out.println("No reviews available for this movie right now!");
				}
				System.out.println("");
				movieCount++;
			}
		}
	}

	public static int searchMovie(List<Movie>mList, List<Review> rList, String name, boolean print){
		int found = 0;
		for(Movie m : mList){
			String cast, genre;
			String casttmp = m.getCast();
			String genretmp = m.getGenres();
			int count = 1, hasReviews = 0;
			if(m.getMovieTitle().equalsIgnoreCase(name)){
				found = 1;
				if(print){
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
					System.out.println("Overall Rating Score: " + m.getOverallRatingScore());
					System.out.println("Release Date: " + m.getReleaseDate().toString());
					System.out.println("Movie Type: " + m.getMovieType());
					System.out.println("");

					System.out.println("Some Review Information of " + m.getMovieTitle() + ":");
					for(Review r: rList){
						if(count > 3 ){
							break;
						}
						if(r.getMovieTitle().equalsIgnoreCase(m.getMovieTitle())){
							System.out.println(Integer.toString(count)+". " + r.getDescription() + " [" + r.getRatingScore() + "/5.0]" + " - " + r.getReviewer());
							hasReviews = 1;
							count++;
						}
					}
					if(hasReviews == 0){
						System.out.println("No reviews available for this movie right now!");
					}
					System.out.println("");
					break;
				}
		   }
		}
		if(found == 0){
			return 0;
		}else{
			return 1;
		}

	}
}
