package boundaries;
import java.util.*;
import entities.*;
import managers.*;
/**
 * The class for the staff review menu
 * @author Wei Kang
 * @version 3.0
 * @since 11-11-2022
 */

public class StaffReviewMenu implements BaseMenuWithAccount{
    /**
	 * The scanner for reading input of user
	 */
    private Scanner sc = new Scanner(System.in);    
    /**
	 * For singleton pattern adherence. This MovieManager instance persists throughout runtime.
	 */
    private static StaffReviewMenu single_instance = null;
	/**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
    public static StaffReviewMenu getInstance()
    {
        if (single_instance == null)
            single_instance = new StaffReviewMenu();
        return single_instance;
    }
    /**
	 * Staff's Menu to manage reviews
     * Choose options to create, read, update, delete reviews of customers, delete own reviews, show all reviews, search for all reviews for a movie
     * @param user User's account
	 */
    public void display(Account user){
        int option = 0;
        try{
            System.out.println("================== REVIEW MENU (STAFF) ====================\n" +
                                " 1. Add Review 						    		       \n" +
                                " 2. Edit Review                                           \n" +
                                " 3. Delete Review (Customer)                              \n" +
                                " 4. Delete Review (Self)                                  \n" +
                                " 5. Show All Past Reviews      		                   \n" +
                                " 6. Search All Reviews for a movie                        \n" +
                                " 7. Go Back                                               \n" +
                                "==========================================================");
            System.out.println("Enter choice: ");
            option = sc.nextInt();
            if(!(option >= 1 && option <=7)){
                System.out.println("Please only enter a number from 1-7.");
                sc.nextLine();
                display(user);
            
            }
        }
        catch(InputMismatchException e){
            System.out.println("Invalid Input. Please input a number from 1-7 only!");
            sc.nextLine();
            display(user);
        }
        sc.nextLine();
        List<Account>accountList = AccountManager.getInstance().getAccountList();
        List<Review>reviewList = ReviewListManager.getInstance().getReviewList();
        List<Movie>movieList = MovieListManager.getInstance().getMovieList();
        int result;
        switch (option) {
            case 1:
                if(ReviewManager.getInstance().addReview(reviewList, movieList,user.getUsername())){
                    System.out.println("Review successfully added!");
                }else{
                    System.out.println("Failed to add review!");
                }
                break;
            case 2:
                result = ReviewManager.getInstance().updateReview(reviewList,user.getUsername());
                if(result == 1){
                    System.out.println("Review successfully updated!");
                }else if(result == 2){
                    ;
                }
                else{
                    System.out.println("Failed to update review!");
                }
                break;
            case 3:
            // Delete customer review for a particular movie
                System.out.println("Enter Customer Username to remove review:");
                String username = sc.nextLine();
                if(!AccountManager.getInstance().checkAccountExistsAndCustomer(accountList,username)){
                    System.out.println("User does not exist!");
                    break;
                }
                result = ReviewManager.getInstance().removeCustomerReview(reviewList,username);
                if( result== 1){
                    System.out.println("Review successfully removed!");
                }
                else if(result == 2){
                    ;
                }
                else{
                    System.out.println("Failed to remove review!");
                }
                break;
            case 4:
                result = ReviewManager.getInstance().removeReview(reviewList,user.getUsername());
                if( result== 1){
                    System.out.println("Review successfully removed!");
                }
                else if(result == 2){
                    ;
                }
                else{
                    System.out.println("Failed to remove review!");
                }
                break;
            case 5:
                // show all reviews for every movie
                ReviewManager.getInstance().showAllReviews(reviewList);
                break;
            case 6:
                System.out.println("Enter Movie Title: ");
                String movieTitle = sc.nextLine();
                ReviewManager.getInstance().showAllReviewsForMovie(reviewList,movieTitle);
                break;
            case 7:
                System.out.println("Returning to Customer Menu...");
                AdminMenu.getInstance().display(user);

            default:
                System.out.println("Invalid choice. Please enter a number between 1-7.");
                break;
        }

        display(user);
    }
}
