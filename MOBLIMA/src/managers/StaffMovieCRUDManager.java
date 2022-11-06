package managers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import entities.*;
import static managers.ReviewListManager.*;
import static utils.IOUtils.*;
import java.util.Scanner;
/**
 * A manager class for all actions related to the staff to create, read, update and delete movies
 * @author Wei Kang
 * @version 2.5
 * @since 01-11-2022
 */
public class StaffMovieCRUDManager {
	/**
	 * The separator for the columns in the csv file
	 */
    private static String csv_Separator = ",";
    /**
	 * The separator used for array of string in the csv file such as if the movie has more than 1 cast
	 */
	private static String splitter = ";";
	/**
	 * The scanner for reading input of user
	 */
    private static Scanner sc = new Scanner(System.in);
	/**
	 * For singleton pattern adherence. This StaffMovieCRUDManager instance persists throughout runtime.
	 */
    private static StaffMovieCRUDManager single_instance = null;
	/**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
    public static StaffMovieCRUDManager getInstance()
    {
        if (single_instance == null)
            single_instance = new StaffMovieCRUDManager();
        return single_instance;
    }

    /**
	 * Function to add new movie for the staffs.
     * Checks if the movie that he wants to review already exists. 
     * @param mList  Existing List of movies
     * @return true if addition of movie was successful, false if unsuccessful
	 */
    public boolean staffAddMovie(List<Movie> mList) {
		ShowingStatus status = null;
		String movieTitle, synopsis, movieDirector, cast, genres, synopsisTmp, movieDirectorTmp, castTmp, genreTmp, movieTitleTmp, str;
		int choice;
		int movieDuration = 0,movieID;
		double rating = 0.0;
        MovieRating movieRating = null;
		int sale = 0;
		MovieType movieType = null;
		LocalDate releaseDate = null, endofShowingDate = null;
		System.out.println("#########################################################");
		System.out.println("#################### ADDING MOVIES ######################");
		System.out.println("#########################################################");
		System.out.println("");
		// If movie List is empty, we assign it an ID of 1.
		if(mList.size() == 0){
			movieID = 1;
		}
		// Else if it's not empty, we find the last ID in the list and add 1.
		else{ 
			movieID = mList.get((mList.size()-1)).getMovieID() + 1;
		}

		System.out.print("Enter Movie Title: ");
		movieTitleTmp = sc.nextLine();
		if (mList.stream().filter(o -> o.getMovieTitle().equalsIgnoreCase(movieTitleTmp)).findFirst().isPresent()) {
			System.out.println("Movie Already Exists!");
			return false;
		}
		movieTitle = movieTitleTmp.replaceAll(csv_Separator, splitter);
		while(true){
			System.out.println("Choose Movie Status");
			System.out.println("1: COMING_SOON");
			System.out.println("2: PREVIEW");
			System.out.println("3: NOW_SHOWING");
			System.out.println("4: FINISHED_SHOWING");
			try{
				choice = sc.nextInt();
			}
			catch(InputMismatchException e){
				System.out.println("Enter numbers only!");
				sc.nextLine();
				continue;
			}
			switch (choice) {
			case 1: {
				status = ShowingStatus.COMING_SOON;
				break;
			}
			case 2: {
				status = ShowingStatus.PREVIEW;
				break;
			}
			case 3: {
				status = ShowingStatus.NOW_SHOWING;
				break;
			}
			case 4: {
				status = ShowingStatus.FINISHED_SHOWING;
				break;
			}
			default:
				System.out.println("Error Input! Please only input values from 1-4.\n");
				continue;
			}
			break;
		}
        sc.nextLine();
		System.out.print("Enter Movie Synopsis: ");
		synopsisTmp = sc.nextLine();
		synopsis = synopsisTmp.replaceAll(csv_Separator, splitter);
		System.out.print("Enter Director Name: ");
		movieDirectorTmp = sc.nextLine();
		movieDirector = movieDirectorTmp.replaceAll(csv_Separator, splitter);
		System.out.print("Enter Cast Name: ");
		castTmp = sc.nextLine();
		cast = castTmp.replaceAll(csv_Separator, splitter);
		System.out.print("Enter Movie Genres: ");
		genreTmp = sc.nextLine();
		genres = genreTmp.replaceAll(csv_Separator, splitter);
		while(true){
			System.out.println("Choose Movie Rating");
			System.out.println("1: G");
			System.out.println("2: PG");
			System.out.println("3: PG-13");
			System.out.println("4: NC-16");
			System.out.println("5: M-18");
			System.out.println("6: R-21");
			try{
				choice = sc.nextInt();
			}catch(InputMismatchException e){
				System.out.println("Please input numbers only!");
				sc.nextLine();
				continue;
			}
			switch (choice) {
			case 1: {
				movieRating = MovieRating.G;
				break;
			}
			case 2: {
				movieRating = MovieRating.PG;
				break;
			}
			case 3: {
				movieRating = MovieRating.PG13;
				break;
			}
			case 4: {
				movieRating = MovieRating.NC16;
				break;
			}
			case 5: {
				movieRating = MovieRating.M18;
				break;
			}
			case 6: {
				movieRating = MovieRating.R21;
				break;
			}
			default:
				System.out.println("Error Input! Please only input values from 1-6.\n");
				continue;
			}
			break;
		}
        sc.nextLine();
		//  Checks movie duration
		while (true){
			System.out.println("Enter Movie Duration (minutes):");
			try{
				movieDuration = sc.nextInt();
				if(movieDuration < 0 || movieDuration > 1440){
					System.out.println("Movie Duration invalid! Re-enter correct movie Duration!");
					continue;
				}
				break;
			}
			catch(InputMismatchException e){
				System.out.println("Invalid input! Enter numbers only!");
				sc.nextLine();
				continue;
			}
		}
		sc.nextLine();
		
		while (true){
			try{
				System.out.println("Enter Release Date (DD/MM/YYYY)");
				str = sc.nextLine();
				releaseDate = LocalDate.parse(str,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				break;
			}catch(DateTimeParseException e){
				System.out.println("Wrong format, enter again!");
				continue;
			}
		}

		while (true){
			try{
				System.out.println("Enter End Of Showing Date (DD/MM/YYYY)");
				str = sc.nextLine();
				endofShowingDate = LocalDate.parse(str,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				if(endofShowingDate.isBefore(releaseDate) || endofShowingDate.isEqual(releaseDate)){
					System.out.println("Invalid End of showing date! End of showing date should be after Release Date!");
					continue;
				}
				break;
			}catch(DateTimeParseException e){
				System.out.println("Wrong format, enter again!");
				continue;
			}
		}

		while(true){
			System.out.println("Choose Movie Type");
			System.out.println("1: TWOD");
			System.out.println("2: THREED");
			System.out.println("3: IMAX");
			System.out.println("4: BLOCKBUSTER");
			try{
				choice = sc.nextInt();
			}catch(InputMismatchException e){
				System.out.println("Please input numbers only!");
				sc.nextLine();
				continue;
			}
			switch (choice) {
				case 1: {
					movieType = MovieType.TWOD;
					break;
				}
				case 2: {
					movieType = MovieType.THREED;
					break;
				}
				case 3: {
					movieType = MovieType.IMAX;
					break;
				}
				case 4: {
					movieType = MovieType.BLOCKBUSTER;
					break;
				}
				default:
					System.out.println("Error Input! Please only input values from 1-4.\n");
					continue;
				}
			break;
		}
        sc.nextLine();
		if (MovieListManager.getInstance().addMovieList(mList,movieID, movieTitle, synopsis, movieDirector, cast, genres, movieDuration, status, sale, movieRating,rating, releaseDate , endofShowingDate, movieType))
			return true;
		 return false;
    }

    /**
	 * Function for staff to update a movie from the list of movies and database regardless of movie status based on the movie title.
     * @param movieList   Existing List of movies
     * @return true if updating of movie was successful, false if unsuccessful
	 */
    public int updateMovie(List<Movie> movieList){
        System.out.println("#########################################################");
        System.out.println("#################### UPDATING MOVIES ####################");
        System.out.println("#########################################################");
        System.out.println("");
        System.out.println("Please enter Movie Title to update:");
        String newTitle = sc.nextLine();
        
        // Search if movie exists first

        Movie temp = null;
        for(Movie m: movieList){
            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                 temp = m;
            }
        }
        if(!movieList.contains(temp)){
            System.out.println("Movie does not exist!");
            return 0;
        }

        // Then do the updating if it exists
        while (true){
            System.out.println("Please choose an option:");
            System.out.println("1. Update Movie Title");
            System.out.println("2. Update Showing Status");
            System.out.println("3. Update Synopsis");
            System.out.println("4. Update Movie Director");
            System.out.println("5. Update Casts");
            System.out.println("6. Update Genres");
            System.out.println("7. Update Movie Rating");
            System.out.println("8. Update Movie Duration");
            System.out.println("9. Update Profit Earned");
            System.out.println("10. Update Overall Rating Score");
            System.out.println("11. Update Release Date");
            System.out.println("12. Update End Of Showing Date");
            System.out.println("13. Update Movie Type");
            System.out.println("14. Exit");
            int option;
            try{
                option = sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Please only enter a number from 1-14 only!");
                sc.nextLine();
                continue;
            }

            switch(option){
                case 1:
                    if(confirm("Confirm Update Title")){
                        String title = read("New Title:");
                        if (movieList.stream().filter(o -> o.getMovieTitle().equalsIgnoreCase(title)).findFirst().isPresent()) {
                            System.out.println("Movie Already Exists!");
                            sc.nextLine();
                            return 0;
                        }
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                m.setMovieTitle(title);
                                break;
                            }
                        }

                    }
                    else{
                        return 2;
                    }
                    break;
                case 2:
                    ShowingStatus status = null;
                    if(confirm("Confirm Update Showing Status")){
                        int choice;
                        while(true){
                            System.out.println("Choose Movie Status");
                            System.out.println("1: COMING_SOON");
                            System.out.println("2: PREVIEW");
                            System.out.println("3: NOW_SHOWING");
                            System.out.println("4: FINISHED_SHOWING");
                            try{
                                choice = sc.nextInt();
                                sc.nextLine();
                                if(choice < 1 || choice > 4){
                                    System.out.println("Please only input values from 1-4 only.");
                                    continue;
                                }
                                break;
                            }catch(InputMismatchException e){
                                System.out.println("Please only input values from 1-4 only");
                                sc.nextLine();
                                continue;
                            }
                        }
                        switch (choice) {
                            case 1: {
                                status = ShowingStatus.COMING_SOON;
                                break;
                            }
                            case 2: {
                                status = ShowingStatus.PREVIEW;
                                break;
                            }
                            case 3: {
                                status = ShowingStatus.NOW_SHOWING;
                                break;
                            }
                            case 4: {
                                status = ShowingStatus.FINISHED_SHOWING;
                                break;
                            }
                            default:
                                System.out.println("Error Input! Please only input values from 1-4.\n");
                                continue;
                        }    
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                m.setShowingStatus(status);
                                break;
                            }
                        }
                    }
                    else{
                        return 2;
                    }
                    break;
                case 3:
                    if(confirm("Confirm Update Synopsis")){
                        String synopsisTmp = read("New Synopsis:");
                        String synopsis = synopsisTmp.replaceAll(csv_Separator, splitter);
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                m.setSynopsis(synopsis);
                                break;
                            }
                        }
                    }
                    else{
                        return 2;
                    }
                    break;
                case 4:
                    if(confirm("Confirm Update Movie Director")){
                        String movieDirector = read("New Movie Director:");
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                m.setMovieDirector(movieDirector);
                                break;
                            }
                        }
                    }
                    else{
                        return 2;
                    }
                    break;
                case 5:
                    if(confirm("Confirm Update Casts")){
                        String castTmp = read("New Casts:");
                        String casts = castTmp.replaceAll(csv_Separator, splitter);
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                m.setCast(casts);
                                break;
                            }
                        }
                    }
                    else{
                        return 2;
                    }
                    break;
                case 6:
                    if(confirm("Confirm Update Genres")){
                        String genreTmp = read("New Genres:");
                        String genres = genreTmp.replaceAll(csv_Separator, splitter);
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                m.setGenres(genres);
                                break;
                            }
                        }
                    }
                    else{
                        return 2;
                    }
                    break;
                case 7:
                    MovieRating movieRating = null;
                    if(confirm("Confirm Update Movie Rating")){
                        int choice;
                        while(true){
                            System.out.println("Choose Movie Rating");
                            System.out.println("1: G");
                            System.out.println("2: PG");
                            System.out.println("3: PG-13");
                            System.out.println("4: NC-16");
                            System.out.println("5: M-18");
                            System.out.println("6: R-21");
                            try{
                                choice = sc.nextInt();
                                sc.nextLine();
                                if (choice < 1 || choice > 6){
                                    System.out.println("Please only input values from 1-6 only.");
                                    continue;
                                }
                                break;
                            }catch(InputMismatchException e){
                                System.out.println("Please only input values from 1-6 only.");
                                sc.nextLine();
                                continue;
                            }
                        }
                        switch (choice) {
                        case 1: {
                            movieRating = MovieRating.G;
                            break;
                        }
                        case 2: {
                            movieRating = MovieRating.PG;
                            break;
                        }
                        case 3: {
                            movieRating = MovieRating.PG13;
                            break;
                        }
                        case 4: {
                            movieRating = MovieRating.NC16;
                            break;
                        }
                        case 5: {
                            movieRating = MovieRating.M18;
                            break;
                        }
                        case 6: {
                            movieRating = MovieRating.R21;
                            break;
                        }
                        default:
                            System.out.println("Error Input! Please only input values from 1-6.\n");
                            continue;
                        }
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                m.setMovieRating(movieRating);
                                break;
                            }
                        }
                    }
                    else{
                        return 2;
                    }
                    break;
                case 8:
                    if(confirm("Confirm Update Movie Duration")){
                        while(true){
                            try{
                                print("New Movie Duration: ");
                                int movieDuration = sc.nextInt();
                                if(movieDuration < 0 || movieDuration > 1440){ // 1 day
                                    System.out.println("Please input a valid movie duration!");
                                    sc.nextLine();
                                    continue;
                                }
                                for(Movie m:movieList){
                                    if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                        m.setMovieDuration(movieDuration);
                                        break;
                                    }
                                }
                                sc.nextLine();
                                break;
                            }catch(InputMismatchException e){
                                print("Please input a valid Movie Duration.");
                                sc.nextLine();
                                continue;
                            }
                        }

                    }
                    else{
                        return 2;
                    }
                    break;
                case 9:
                    if(confirm("Confirm Update Profit Earned")){
                        while(true){
                            try{
                                print("New Profit Earned: ");
                                double profitEarned = sc.nextDouble();
                                if(profitEarned < 0){
                                    System.out.println("Please enter a valid amount of profit earned!");
                                    sc.nextLine();
                                    continue;
                                }
                                for(Movie m:movieList){
                                    if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                        m.setProfitEarned(profitEarned);
                                        break;
                                    }
                                }
                                sc.nextLine();
                                break;
                            }catch(InputMismatchException e){
                                print("Please input a valid amount of profit earned!");
                                sc.nextLine();
                                continue;
                            }
                        }

                    }
                    else{
                        return 2;
                    }
                    break;
                case 10:
                    if(confirm("Confirm Update Overall Rating Score")){
                        while(true){
                            try{
                                print("New Overall Rating Score: ");
                                double overallRatingScore = sc.nextDouble();
                                if(overallRatingScore < 0 || overallRatingScore > 5){
                                    print("Overall Rating can only be between 0 and 5!");
                                    sc.nextLine();
                                    continue;
                                }
                                for(Movie m:movieList){
                                    if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                        m.setOverallRatingScore(overallRatingScore);
                                        break;
                                    }
                                }
                                sc.nextLine();
                                break;
                            }catch(InputMismatchException e){
                                print("Overall Rating can only be between 0 and 5!");
                                sc.nextLine();
                                continue;
                            }
                        }

                    }
                    else{
                        return 2;
                    }
                    break;
                case 11:
                    int valid = 0;
                    if(confirm("Confirm Update Release Date")){
                        while(true){
                            String releaseDateStr = read("New Overall Release Date (YYYY-MM-DD): ");
                            try{
                                LocalDate releaseDate = LocalDate.parse(releaseDateStr);
                                for(Movie m:movieList){
                                    if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                        if(releaseDate.isAfter(m.getEndOfShowingDate())){
                                            System.out.println("Invalid! Release Date should only be before or same as end of showing date!");
                                            break;
                                        }
                                        else{
                                            m.setReleaseDate(releaseDate);
                                            valid = 1;
                                            break;
                                        }
                                    }
                                }
                                if(valid == 1){
                                    break;
                                }else{
                                    continue;
                                }
                            }catch(DateTimeParseException e){
                                System.out.println("Wrong format, enter again!");
                                continue;
                            }
                        }
                    }
                    else{
                        return 2;
                    }
                    break;
                case 12:
                    int valid2 = 0;
                    if(confirm("Confirm Update End Of Showing Date")){
                        while(true){
                            String endShowingStr = read("New Overall End Of Showing Date (YYYY-MM-DD): ");
                            try{
                                LocalDate endOfShowingDate = LocalDate.parse(endShowingStr);
                                for(Movie m:movieList){
                                    if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                        if(endOfShowingDate.isAfter(m.getReleaseDate()) || endOfShowingDate.isEqual(m.getReleaseDate())){
                                            m.setEndOfShowingDate(endOfShowingDate);
                                            valid2 = 1;
                                            break;
                                        }
                                        else{
                                            System.out.println("Invalid Input! End of Showing Date must be after Release Date!");
                                            break;
                                        }
                                    }
                                }
                                if(valid2 == 1){
                                    break;
                                }else{
                                    continue;
                                }
                            }catch(DateTimeParseException e){
                                System.out.println("Wrong format, enter again!");
                                continue;
                            }
                        }
                    }
                    else{
                        return 2;
                    }
                    break;
                case 13:
                    MovieType movieType = null;
                    if(confirm("Confirm Update Movie Type")){
                        int choice;
                        while(true){
                            System.out.println("Choose Movie Type");
                            System.out.println("1: TWOD");
                            System.out.println("2: THREED");
                            System.out.println("3: IMAX");
                            System.out.println("4: BLOCKBUSTER");
                            try{
                                choice = sc.nextInt();
                                sc.nextLine();
                                if(choice < 1 || choice > 4){
                                    System.out.println("Please only input values from 1-4 only.");
                                    continue;
                                }
                                break;
                            }catch(InputMismatchException e){
                                System.out.println("Please only input values from 1-4 only.");
                                sc.nextLine();
                                continue;
                            }
                        }
                        switch (choice) {
                        case 1: {
                            movieType = MovieType.TWOD;
                            break;
                        }
                        case 2: {
                            movieType = MovieType.THREED;
                            break;
                        }
                        case 3: {
                            movieType = MovieType.IMAX;
                            break;
                        }
                        case 4: {
                            movieType = MovieType.BLOCKBUSTER;
                            break;
                        }
                        default:
                            System.out.println("Error Input! Please only input values from 1-4.\n");
                            continue;
                        }
                        for(Movie m:movieList){
                            if(m.getMovieTitle().equalsIgnoreCase(newTitle)){
                                m.setMovieType(movieType);
                                break;
                            }
                        }
                    }
                    else{
                        return 2;
                    }
                    break;
                case 14:
                    print("Returning to Movie Menu....");
                    sc.nextLine();
                    return 2;
                default:
                    print("Please enter a number from 1-14 only.");
                    continue;
            }
            break;
        }
        
        if(MovieListManager.getInstance().updateMovieListCSV(movieList)){
            return 1;
        }
        return 0;
    }
    /**
	 * Function to psuedo-delete a movie from the list of movies by setting the showing status to end of showing based on the movie  title.
     * Checks if the movie to be deleted exists
     * @param mList Existing List of movies
     * @return true if pseudo-deletion of movie was successful, false if unsuccessful
	 */
    public int setToEndShowing(List<Movie>mList){
        System.out.println("#########################################################");
		System.out.println("#################### REMOVING MOVIES ####################");
		System.out.println("#########################################################");
		System.out.println("");

        
        String title; 
        System.out.println("Enter Movie Title: ");
        title = sc.nextLine();
    
        for(Movie m: mList){
            if(m.getMovieTitle().equalsIgnoreCase(title)){
                if(confirm("Confirm Remove Title ")){
                    m.setShowingStatus(ShowingStatus.FINISHED_SHOWING);
                    MovieListManager.getInstance().updateMovieListCSV(mList);
                    return 1;
                }
                else{
                    return 2;
                }
            }
        }
        
        System.out.println("Movie not found!");
        return 0;
    }
   /**
	 * Function to delete a movie from the list of movies and database entirely based on the movie  title.
     * Checks if the movie to be deleted exists
     * Deletes all reviews related to the movie as well
     * @param mList Existing List of movies
     * @param rList Existing list of reviews
     * @return true if deletion of movie was successful, false if unsuccessful
	 */
    public int removeMovieFromDatabase(List<Movie> mList, List<Review> rList){
        System.out.println("#########################################################");
		System.out.println("#################### REMOVING MOVIES ####################");
		System.out.println("#########################################################");
		System.out.println("");

        String title;
        List<Movie>newList = new ArrayList<Movie>();
        List<Review>newList2 = new ArrayList<Review>();
        System.out.println("Enter Movie Title: ");
        title = sc.next();
        
        // Search if movie exists first
        Movie temp = null;
        for(Movie m: mList){
            if(m.getMovieTitle().equalsIgnoreCase(title)){
                 temp = m;
            }
        }
        if(!mList.contains(temp)){
            System.out.println("Movie does not exist!");
            return 0;
        }

        if(confirm("Confirm Remove Title")){
            for(Movie m : mList){
                if(!m.getMovieTitle().equals(title)){
                    int movieID = m.getMovieID();
                    String movieTitle = m.getMovieTitle();
                    ShowingStatus showingStatus =  m.getShowingStatus();
                    String synopsis = m.getSynopsis();
                    String movieDirector =  m.getMovieDirector();
                    String casts = m.getCast();
                    String genres = m.getGenres();
                    MovieRating movieRating = m.getMovieRating();
                    int movieDuration =  m.getMovieDuration();
                    double profitEarned =  m.getProfitEarned();
                    double overallRatingScore =  m.getOverallRatingScore();
                    LocalDate releaseDate =  m.getReleaseDate();
                    LocalDate endOfShowingDate =  m.getEndOfShowingDate();
                    MovieType movieType = m.getMovieType();
                    Movie newMovie = new Movie(movieID,movieTitle,showingStatus, synopsis, movieDirector, casts, genres, movieRating, movieDuration, profitEarned,  overallRatingScore, releaseDate,endOfShowingDate, movieType);
                    newList.add(newMovie);
                }
            }
            for(Review r: rList){
                if(!r.getMovieTitle().equals(title)){
                    int reviewID = r.getReviewID();
                    String movieTitle = r.getMovieTitle();
                    String reviewer = r.getReviewer();
                    String reviewDescription = r.getDescription();
                    double ratingScore = r.getRatingScore();
                    Review newReview = new Review(reviewID,movieTitle,reviewer,reviewDescription,ratingScore);
                    newList2.add(newReview);
                }
            }
            if(MovieListManager.getInstance().updateMovieListCSV(newList) && updateReviewInCSV(newList2)){
                return 1;
            }
            else{
                return 0;
            }
        }
        else{
            return 2;
        }
    }
   /**
	 * Function for staff to view all movie from the list of movies and database regardless of movie status.
     * Staff can view all reviews for each movie
     * @param mList Existing List of movies
     * @param rList Existing list of reviews
	 */
	public void printMovieList(List<Movie>mList, List<Review>rList) {
		
		int movieCount = 1;
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
				cast = casttmp.replaceAll(splitter, csv_Separator);
				System.out.println("Casts: "+ cast);
				cast = casttmp.replaceAll(splitter, csv_Separator);
				genre = genretmp.replaceAll(splitter, csv_Separator);
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
   /**
	 * Function for staff to view a movie from the list of movies and database regardless of movie status based on the movieID.
     * Staff can view all reviews for the movie
     * @param mList   Existing List of movies
     * @param rList   Existing list of reviews
     * @param movieID ID of movie
	 */
	public int printMovieByID(List<Movie>mList, List<Review>rList, int movieID) {
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
				cast = casttmp.replaceAll(splitter, csv_Separator);
				System.out.println("Casts: "+ cast);
				cast = casttmp.replaceAll(splitter, csv_Separator);
				genre = genretmp.replaceAll(splitter, csv_Separator);
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
