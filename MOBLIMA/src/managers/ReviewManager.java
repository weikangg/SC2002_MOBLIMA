package managers;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import entities.*;
import view.customerApp;
import static utils.IOUtils.*;

public class ReviewManager {
    private Scanner sc = new Scanner(System.in);
    static String path = System.getProperty("user.dir") +"\\data\\reviews\\reviews.csv";
    
    /**
	 * The separator for array of string in csv
	 */
	static String SplitBy = ";";
	/**
	 * The csv seperator
	 */
	static String csvSplitBy = ",";
    private static ReviewManager single_instance = null;
    public static ReviewManager getInstance()
    {
        if (single_instance == null)
            single_instance = new ReviewManager();
        return single_instance;
    }


    private ReviewManager(){
    }

    public void reviewMenu(int choice, User user){
        int option = 0;
        try{
            if(choice == 0){
                System.out.println("================= REVIEW MENU (CUSTOMER) ================\n" +
                                    " 1. Add Review 						    		     \n" +
                                    " 2. Edit Review                                         \n" +
                                    " 3. Delete Review                                       \n" +
                                    " 4. Show All Past Reviews      		                  \n" +
                                    " 5. Go Back                                             \n" +
                                    "==========================================================");
                System.out.println("Enter choice: ");
                option = sc.nextInt();
                if(!(option >= 1 && option <=5)){
                    System.out.println("Please only enter a number from 1-5.");
                    sc.nextLine();
                    reviewMenu(0,user);
                }
            }
        }
        catch(InputMismatchException e){
            System.out.println("Invalid Input. Please input a number from 1-5 only!");
            sc.nextLine();
            reviewMenu(0,user);
        }
        sc.nextLine();
        
        List<Movie>movieList = MovieListManager.getInstance().getMovieList();
        List<Review>reviewList = ReviewListManager.getInstance().getReviewList();
        
        switch (option) {
            case 1:
                if(addReview(reviewList, movieList,user.getUsername())){
                    System.out.println("Review successfully added!");
                }else{
                    System.out.println("Failed to add review!");
                }
                break;
            case 2:
                if(updateReview(reviewList,user.getUsername()) == 1){
                    System.out.println("Review successfully updated!");
                }else if(updateReview(reviewList,user.getUsername()) == 2){
                    ;
                }
                else{
                    System.out.println("Failed to update review!");
                }
                break;
            case 3:
                if(removeReview(reviewList,user.getUsername()) == 1){
                    System.out.println("Review successfully removed!");
                }
                else if(removeReview(reviewList,user.getUsername()) == 2){
                    ;
                }
                else{
                    System.out.println("Failed to remove review!");
                }
                break;
            case 4:
                showAllPastReviews(ReviewListManager.getInstance().getReviewList(), user.getUsername());
                break;
            case 5:
                System.out.println("Returning to Customer Menu...");
                customerApp.getInstance().logInMenu(user);
            default:
                System.out.println("Invalid choice. Please enter a number between 1-5.");
                break;
        }

        reviewMenu(0,user);
    }

    // adding reviews for the user. checks if he has previously inputted a review for that movie.
    // checks if the movie that he wants to review exists?
    // TO DO: CHECK AND PRINT THE MOVIES THAT HE HAS PREVIOUSLY WATCHED BEFORE.
    public boolean addReview(List<Review> reviewList, List<Movie> movieList, String username){
        System.out.println("#########################################################");
		System.out.println("#################### ADDING REVIEWS #####################");
		System.out.println("#########################################################");
        System.out.println("");

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

    // Update Reviews for the user

    // Checks if user has given a review for the movie before.
    public int updateReview(List<Review> reviewList, String username){
        System.out.println("#########################################################");
		System.out.println("#################### EDITING REVIEWS ####################");
		System.out.println("#########################################################");
        System.out.println("");

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

    // Remove Review based on movie title
    public int removeReview(List<Review> reviewList, String username){
        System.out.println("#########################################################");
		System.out.println("#################### REMOVING REVIEW ####################");
		System.out.println("#########################################################");
		System.out.println("");

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

    // Show movie title, review description, and overall rating score based on user who logged in
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
    }
}
