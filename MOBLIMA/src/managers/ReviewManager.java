package managers;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import boundaries.AdminMenu;
import boundaries.CustomerGuestMenu;
import boundaries.CustomerLoggedInMenu;
import entities.*;

import static utils.IOUtils.*;

/**
 * A manager class for all actions related to the staff to manage reviews
 * @author Wei Kang
 * @version 2.5
 * @since 01-11-2022
 */
public class ReviewManager {
	/**
	 * The scanner for reading input of user
	 */
    private Scanner sc = new Scanner(System.in);    
    /**
	 * The separator used for array of string in the csv file such as if the review description is more than one sentence
	 */
	private static String SplitBy = ";";
	/**
	 * The separator for the columns in the csv file
	 */
	private static String csvSplitBy = ",";
	/**
	 * For singleton pattern adherence. This MovieManager instance persists throughout runtime.
	 */
    private static ReviewManager single_instance = null;
	/**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
    public static ReviewManager getInstance()
    {
        if (single_instance == null)
            single_instance = new ReviewManager();
        return single_instance;
    }
	/**
	 * The default constructor for the ReviewListManager class
	 */
    private ReviewManager(){
    }

    
    /**
	 * Function to add reviews for the user.
     * Checks if he has previously inputted a review for that movie. Only 1 review per movie is allowed.
     * Checks if the movie that he wants to review exists. User can only input review for existing movie
     * @param reviewList Existing List of reviews
     * @param movieList  Existing List of movies
     * @param username   User's username
     * @return true if addition of review was successful, false if unsuccessful
	 */
    public boolean addReview(List<Review> reviewList, List<Movie> movieList, String username){
        System.out.println("#########################################################");
		System.out.println("#################### ADDING REVIEWS #####################");
		System.out.println("#########################################################");
        System.out.println("");

        System.out.println("List of available movies you can make reviews for: ");
        StaffMovieCRUDManager.getInstance().printMovieTitles(movieList);
        int  reviewID, found = 0;
        double ratingScore;
        String movieTitleTmp, movieTitle,reviewer, descriptionTmp,description;
        if (reviewList.size() == 0){
            reviewID = 1;
        }
        else{
            reviewID = (reviewList.get((reviewList.size()-1)).getReviewID()) + 1;
        }
        System.out.println("Enter Movie Title: ");
        movieTitleTmp = sc.nextLine();

        // Checking if he has previously inputted a review for movie.

        if (reviewList.stream().filter(o -> o.getMovieTitle().equalsIgnoreCase(movieTitleTmp) && username.equals(o.getReviewer())).findFirst().isPresent()) {
			System.out.println("You have already given your review for this movie!");
			return false;
		}

        // Checking if the movie he wants to review exists.
        for(Movie m : movieList){
            if(m.getMovieTitle().equalsIgnoreCase(movieTitleTmp)){
                found = 1; 
                break;
            }
        }
        if(found == 0){
            System.out.println("Movie does not exist!");
            return false;
        }

        // If the movie exists, add review
        else{
            movieTitle = movieTitleTmp.replaceAll(csvSplitBy, SplitBy);
            reviewer = username;
            System.out.println("Enter your Review Description for the Movie!");
            descriptionTmp = sc.nextLine();
            description = descriptionTmp.replaceAll(csvSplitBy, SplitBy);
            while(true){
                System.out.println("Enter your Rating Score (1-5):");
                try{
                    ratingScore = sc.nextDouble();
                    if(ratingScore > 5 || ratingScore < 1){
                        System.out.println("Enter again! Rating can only be from 1-5!");
                        continue;
                    }
                    sc.nextLine();
                    break;
                }catch(InputMismatchException e){
                    System.out.println("Invalid Input! Enter numbers only!");
                    sc.nextLine();
                    continue;
                }
            }
            if(ReviewListManager.addReviewList(reviewList, reviewID, movieTitle, reviewer, description, ratingScore)){
                return true;
            }
            return false;
        }

    }   

    /**
	 * Function to update previous reviews for the user.
     * Checks if he has previously inputted a review for that movie. Only 1 review per movie is allowed.
     * Checks if the movie that he wants to review exists. User can only input review for existing movie
     * @param reviewList Existing List of reviews
     * @param username   User's username
     * @return true if updating of review was successful, false if unsuccessful
	 */
    public int updateReview(List<Review> reviewList, String username){
        System.out.println("#########################################################");
		System.out.println("#################### EDITING REVIEWS ####################");
		System.out.println("#########################################################");
        System.out.println("");

        showAllPastReviews(reviewList, username);

        System.out.println("Please enter Movie Title to update:");
        String movieTitle = sc.nextLine();
        
        if (reviewList.size() == 0){
            System.out.println("You have not given a review for any movies yet!");
        }

        // Search if the user has made a review before

        Review temp = null;
        for(Review r: reviewList){
            if(r.getMovieTitle().equalsIgnoreCase(movieTitle) && (r.getReviewer().equals(username))){
                temp = r;
            }
        }
        if(!reviewList.contains(temp)){
            System.out.println("You have not given a review for this movie yet!");
            return 0;
        }

        // Do the updating

        while(true){
            System.out.println("Please choose an option:");
            System.out.println("1. Update Review Description");
            System.out.println("2. Update Rating Score");
            int option;
            try{
                option = sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Please enter a valid number from 1-2 only!");
                sc.nextLine();
                continue;
            }
            switch(option){
                case 1:
                    if(confirm("Confirm Update Review Description")){
                        String newDescription = read("New Description:");
                        for(Review r: reviewList){
                            if(r.getMovieTitle().equalsIgnoreCase(movieTitle) && r.getReviewer().equals(username)){
                                r.setDescription(newDescription);
                            }
                        }
                    }
                    else{
                        System.out.println("No updates made!");
                        return 2;
                    }
                    break;
                case 2:
                    if(confirm("Confirm Update Rating Score")){
                        System.out.println("New Rating Score:");
                        double newScore;
                        try{
                            newScore = sc.nextDouble();
                            if(newScore < 1 || newScore > 5){
                                System.out.println("Please input a valid Rating Score from 1-5 only!");
                                continue;
                            }
                            for(Review r: reviewList){
                                if(r.getMovieTitle().equalsIgnoreCase(movieTitle) && r.getReviewer().equals(username)){
                                    r.setRatingScore(newScore);
                                    break;
                                }
                            }
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            System.out.println("Please input a valid Rating Score from 1-5 only!");
                            sc.nextLine();
                            continue;
                        }
                    }
                    else{
                        System.out.println("No updates made!");
                        return 2;
                    }
                    break;
            }
            break;
        }

        if(ReviewListManager.updateReviewInCSV(reviewList)){
            return 1;
        }else{
            return 0;
        }
    }
    /**
	 * Function to remove previous reviews for the staff to remove customer's review based on the movie  title.
     * Checks if the customer has previously inputted a review for that movie.
     * @param reviewList Existing List of reviews
     * @param username   Customer's username
     * @return true if deletion of review was successful, false if unsuccessful
	 */
     public int removeCustomerReview(List<Review> reviewList, String username){
        System.out.println("#########################################################");
		System.out.println("#################### REMOVING REVIEW ####################");
		System.out.println("#########################################################");
		System.out.println("");

        showAllPastReviews(reviewList, username);
        
        String title; 
        System.out.println("Enter Movie Title: ");
        title = sc.next();
        List<Review>newList = new ArrayList<Review>();

         // Search if user has made a review for that particular movie

         Review temp = null;
         for(Review r: reviewList){
             if(r.getMovieTitle().equalsIgnoreCase(title) && r.getReviewer().equals(username)){
                  temp = r;
             }
         }
         if(!reviewList.contains(temp)){
             System.out.println(username + " has not made a review for this movie yet!");
             return 0;
         }

         if(confirm("Confirm Remove Review ")){
            for(Review r:reviewList){
                if(!(r.getMovieTitle().equalsIgnoreCase(title) && r.getReviewer().equals(username))){
                    int reviewID = r.getReviewID();
                    String movieTitle = r.getMovieTitle();
                    String reviewer = r.getReviewer();
                    String reviewDescription = r.getDescription();
                    double ratingScore = r.getRatingScore();
                    Review newReview = new Review(reviewID,movieTitle,reviewer,reviewDescription,ratingScore);
                    newList.add(newReview);
                }
            }
            if(ReviewListManager.updateReviewInCSV(newList)){
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
	 * Function to remove previous reviews for the user based on the movie title.
     * Checks if he has previously inputted a review for that movie.
     * @param reviewList Existing List of reviews
     * @param username   User's username
     * @return true if deletion of review was successful, false if unsuccessful
	 */
    public int removeReview(List<Review> reviewList, String username){
        System.out.println("#########################################################");
		System.out.println("#################### REMOVING REVIEW ####################");
		System.out.println("#########################################################");
		System.out.println("");

        showAllPastReviews(reviewList, username);
        String title; 
        System.out.println("Enter Movie Title: ");
        title = sc.next();
        List<Review>newList = new ArrayList<Review>();

         // Search if user has made a review for that particular movie

         Review temp = null;
         for(Review r: reviewList){
             if(r.getMovieTitle().equalsIgnoreCase(title) && r.getReviewer().equals(username)){
                  temp = r;
             }
         }
         if(!reviewList.contains(temp)){
             System.out.println("You have not made a review for this movie yet!");
             return 0;
         }

         if(confirm("Confirm Remove Review ")){
            for(Review r:reviewList){
                if(!(r.getMovieTitle().equalsIgnoreCase(title) && r.getReviewer().equals(username))){
                    int reviewID = r.getReviewID();
                    String movieTitle = r.getMovieTitle();
                    String reviewer = r.getReviewer();
                    String reviewDescription = r.getDescription();
                    double ratingScore = r.getRatingScore();
                    Review newReview = new Review(reviewID,movieTitle,reviewer,reviewDescription,ratingScore);
                    newList.add(newReview);
                }
            }
            if(ReviewListManager.updateReviewInCSV(newList)){
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
	 * Function to show all past reviews made by the user who is logged in
     * @param reviewList Existing List of reviews
     * @param username   User's username
	 */
    public void showAllPastReviews(List<Review> reviewList, String username){
        int count = 1;
        if (reviewList.size() == 0){
			System.out.println("No Movies to display.");
            return;
		}
        for(Review r : reviewList){
            if(r.getReviewer().equals(username)){
                String description;
                String descriptionTmp = r.getDescription();
    
                System.out.printf("----------------- REVIEW %d -----------------\n", count);
                System.out.println("Movie Title: "+ r.getMovieTitle());
                description = descriptionTmp.replaceAll(SplitBy, csvSplitBy);
                System.out.println("Review Description: "+ description);
                System.out.println("Rating Score: " + r.getRatingScore());
                count++;
                System.out.println("");
            }
        }
        if(count ==1){
            System.out.println("You have not made any reviews before!");
            return;
        }
    }
    /**
	 * Function to show all reviews made by every user
     * @param reviewList Existing List of reviews
	 */
    public void showAllReviews(List<Review> reviewList){
        int count = 1;
        if (reviewList.size() == 0){
			System.out.println("No Movies to display.");
            return;
		}
        for(Review r : reviewList){
            String description;
            String descriptionTmp = r.getDescription();

            System.out.printf("----------------- REVIEW %d -----------------\n", count);
            System.out.println("Movie Title: "+ r.getMovieTitle());
            description = descriptionTmp.replaceAll(SplitBy, csvSplitBy);
            System.out.println("Username: " + r.getReviewer());
            System.out.println("Review Description: "+ description);
            System.out.println("Rating Score: " + r.getRatingScore());
            count++;
            System.out.println("");
        }
    }
    /**
	 * Function to show all reviews made by every user for each movie
     * @param reviewList Existing List of reviews
     * @param movieTitle movieTitle name to be searched
	 */
    public void showAllReviewsForMovie(List<Review> reviewList, String movieTitle){
        int count = 1, found = 0;
        if (reviewList.size() == 0){
			System.out.println("Movie not found!");
            return;
		}
        for(Review r : reviewList){
            if(r.getMovieTitle().equals(movieTitle)){
                found = 1;
                String description;
                String descriptionTmp = r.getDescription();
    
                System.out.printf("----------------- REVIEW %d -----------------\n", count);
                System.out.println("Movie Title: "+ r.getMovieTitle());
                description = descriptionTmp.replaceAll(SplitBy, csvSplitBy);
                System.out.println("Username: " + r.getReviewer());
                System.out.println("Review Description: "+ description);
                System.out.println("Rating Score: " + r.getRatingScore());
                count++;
                System.out.println("");
            }
        }
        if (found == 0){
            System.out.println("Movie not found!");
        }
    }
}
