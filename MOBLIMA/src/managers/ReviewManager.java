package managers;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import entities.*;

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
                                    " 4. Show All Past Reviews      		                         \n" +
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
        switch (option) {
            case 1:
                List<Review>reviewList = ReviewListManager.getInstance().getReviewList();
                addReview(reviewList, user.getUsername());
                break;
            case 2:
                break;
            case 3:
              
                break;
            case 4:
                showAllPastReviews(ReviewListManager.getInstance().getReviewList(), user.getUsername());
                break;
            case 5:
                if(StaffRemoveMovieManager.removeMovie(MovieListManager.getInstance().getMovieList())){
                    System.out.println("Movie removed!");
                }
                else{
                    System.out.println("Failed to remove movie!");
                }
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1-5.");
                break;
        }

        reviewMenu(0,user);
    }

    // adding reviews for the user. checks if he has previously inputted a review for that movie.
    // TO DO: CHECK AND PRINT THE MOVIES THAT HE HAS PREVIOUSLY WATCHED BEFORE.
    public boolean addReview(List<Review> reviewList, String username){
        System.out.println("#########################################################");
		System.out.println("#################### ADDING REVIEWS #####################");
		System.out.println("#########################################################");
        System.out.println("");

        int  reviewID;
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
        if (reviewList.stream().filter(o -> o.getMovieTitle().equalsIgnoreCase(movieTitleTmp) && username.equals(o.getReviewer())).findFirst().isPresent()) {
			System.out.println("You have already given your review for this movie!");
			return false;
		}
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
