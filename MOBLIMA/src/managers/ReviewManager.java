package managers;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import entities.Review;

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

    public void reviewMenu(int choice){
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
                    reviewMenu(0);
                }
            }
        }
        catch(InputMismatchException e){
            System.out.println("Invalid Input. Please input a number from 1-5 only!");
            sc.nextLine();
            reviewMenu(0);
        }
        switch (option) {
            case 1:
            
                break;
            case 2:
                break;
            case 3:
              
                break;
            case 4:
                showAllPastReviews(ReviewListManager.getInstance().getReviewList(), "Hairy1");
                break;
            case 5:
                if(StaffRemoveMovieManager.removeMovie(MovieListManager.getMovieList())){
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

        reviewMenu(0);
    }

    // Show movie title, review description, and overall rating score
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
