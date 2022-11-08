package managers;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import entities.*;

/**
 * Function class that display and output relevant information about the movies to the customers
 * @author Ling Hin
 * @version 2.5
 * @since 08-11-2022
 */

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
	/**
	 * The scanner for reading input of user
	 */
	private static Scanner scan = new Scanner(System.in);
	/**
	 * For singleton pattern adherence. This StaffAccManager instance persists throughout runtime.
	 */
    private static CustomerMovieManager single_instance = null;
	/**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
    public static CustomerMovieManager getInstance()
    {
        if (single_instance == null)
            single_instance = new CustomerMovieManager();
        return single_instance;
    }

	// Displays all info about movie + limited 5 reviews on each movie. If <= 1  review with rating score higher than the limit, prints a placeholder message "na"
	// Only shows info of movies which are preview or NOW SHOWING
	// Shows reviews according to rating score limit pre-set by staff.
	/**
	 *  Function prints all the movies and ratings for each movie
	 * Shows movie details such as movie title, showing status, synopsis, movie director, casts, genres, movie rating, duration, release date, movie type, rating scores and customers' rating
	 * @param mList list with all the movies
	 * @param rList list will all customers' reviews
	 */
	public void printMovieList(List<Movie>mList, List<Review>rList) {
		int movieCount = 1;
		System.out.println("#########################################################");
		System.out.println("################## DISPLAYING MOVIES ####################");
		System.out.println("#########################################################");
		System.out.println("");
		SystemSettings systemSettings = SystemSettings.getInstance();
		systemSettings.updatePermissions();
		double ratingScoreLimit = systemSettings.getRatingScoreLimit();

		if (mList.size() == 0){
			System.out.println("No Movies to display.");
		}

		for(Movie m : mList) {
			if(m.getShowingStatus().equals(ShowingStatus.PREVIEW) || m.getShowingStatus().equals(ShowingStatus.NOW_SHOWING)){
				String cast, genre;
				String casttmp = m.getCast();
				String genretmp = m.getGenres();
				int reviewCount = 0, hasReviews = 0, printOverallRating = 0;;
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
				for(Review r: rList){
					if(reviewCount > 1 ){
						printOverallRating = 1;
						break;
					}
					if(r.getMovieTitle().equalsIgnoreCase(m.getMovieTitle()) && r.getRatingScore() >= ratingScoreLimit){
						reviewCount++;
					}
				}
				if(printOverallRating == 1){
					System.out.printf("Overall Rating Score: %.1f/5\n" , m.getOverallRatingScore() );
				}else{
					System.out.println("Overall Rating Score: NA");
				}
				System.out.println("Release Date: " + m.getReleaseDate().toString());
				System.out.println("Movie Type: " + m.getMovieType());
				System.out.println("");
				System.out.println("Some Review Information of " + m.getMovieTitle() + ":");
				reviewCount = 0;
				for(Review r: rList){
					if(reviewCount >= 5 ){
						break;
					}
					if(r.getMovieTitle().equalsIgnoreCase(m.getMovieTitle()) && r.getRatingScore() >= ratingScoreLimit){
						System.out.println(Integer.toString(reviewCount+1)+". " + r.getDescription() + " [" + r.getRatingScore() + "/5.0]" + " - " + r.getReviewer());
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

	// if movie is end of showing or coming soon, will print a placeholder message instead of the movie info
	
	/**
	 * Function search for movie inside movie list
	 * If movie is found, function will print out movie informations such as 
	 * @param mList list of movies
	 * @param rList list of customers' reviews
	 * @param name movie name that user is searching for
	 * @return returns 1 if movie is found, returns 0 if movie is not found
	 */
	public int searchMovie(List<Movie>mList, List<Review> rList, String name){
		int found = 0;
		for(Movie m : mList){
			String cast, genre;
			String casttmp = m.getCast();
			String genretmp = m.getGenres();
			int count = 1, hasReviews = 0;
			if(m.getMovieTitle().equalsIgnoreCase(name) 
			&& !m.getShowingStatus().equals(ShowingStatus.FINISHED_SHOWING)
			&& !m.getShowingStatus().equals(ShowingStatus.COMING_SOON))
			{
				found = 1;
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
				System.out.printf("Overall Rating Score: %.1f/5\n", m.getOverallRatingScore());
				System.out.println("Release Date: " + m.getReleaseDate().toString());
				System.out.println("Movie Type: " + m.getMovieType());
				System.out.println("");

				System.out.println("Some Review Information of " + m.getMovieTitle() + ":");
				for(Review r: rList){
					if(count > 5 ){
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
			else if(m.getMovieTitle().equalsIgnoreCase(name) && m.getShowingStatus().equals(ShowingStatus.FINISHED_SHOWING)){
				System.out.println("Movie is not showing anymore!");
				return 1;
			}
			else if(m.getMovieTitle().equalsIgnoreCase(name) && m.getShowingStatus().equals(ShowingStatus.COMING_SOON)){
				System.out.println("Movie is coming soon!");
				return 1;
			}
		}
		if(found == 0){
			return 0;
		}else{
			return 1;
		}

	}

	public ArrayList<Showtime> searchMovieShowtime(List<Movie> movieList, List<Review> reviewList)
    {
		int i;
        String name;
        int cineplexID;
        List<Movie> cineMovieList = new ArrayList<>();
		int [] movid = new int[movieList.size() + 1];

        //ask user for cineplex
        while(true){
            System.out.print("====================CHOOSE A CINEPLEX====================\n"+
                                "1. Cineplex A\n"+
                                "2. Cineplex B\n"+
                                "3. Cineplex C\n");
            System.out.println("Enter your choice:");
            cineplexID = scan.nextInt();
            scan.nextLine();
            if(cineplexID > 3 || cineplexID < 1)
            {
                System.out.println("Please enter a valid option");
                continue;
            }
            else
            {
                break;
            }
        }
        
        //find movies that are in the cineplex
        cineplexID -= 1;
        Cineplex[] cineplexes = CineplexManager.getInstance().configCineplexes(); 
        Cinema[] cinemas = cineplexes[cineplexID].getCinemas();
		for(i = 0; i < movieList.size(); i++)
		{
			movid[i] = 0;
		}
        for(Cinema c : cinemas)
        {
            Showtime [] showtime = c.getShowtimes();
			for(Showtime s : showtime)
			{
				movid[(s.getMovieID())-1] = 1;
				//s.showInfo();
			}
        }

		for(i = 0; i < movieList.size() + 1; i++)
		{
			if(movid[i] == 1)
			{
				for(int j = 0; j < movieList.size(); j++)
				{
					if(movieList.get(j).getMovieID() == (i+1) )
					{
						System.out.println(movieList.get(j).getShowingStatus());
						cineMovieList.add(movieList.get(j));
					}
				}
				
			}
		}
        
		System.out.println("========================================================");

        
        
        //ask user for movie11
        this.printMovieList(cineMovieList, reviewList);
        System.out.println("Please choose a movie:");
        name = scan.nextLine();

        //link the movie to showtime
        ArrayList<Showtime> list = new ArrayList<Showtime>();
        int id = -1;
        for(Movie mov : cineMovieList)
        {
            if(name.equals(mov.getMovieTitle()))
            {
                id = mov.getMovieID();
            }
        }
        if(id == -1)
        {
            System.out.println("There are no existing showtimes for this movie in this cineplex for now!");
            return list;
        }

        

        for(i = 0; i < cinemas.length; i++)
        {
            Showtime[] showtimes = cinemas[i].getShowtimes();
            for(int j = 0; j < showtimes.length; j++)
            {
                if(showtimes[j].getMovieID() == id)list.add(showtimes[j]);
            }
        }
        return list;
        
    }
}
