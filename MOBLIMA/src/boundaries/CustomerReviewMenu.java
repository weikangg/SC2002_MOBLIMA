package boundaries;
import java.util.*;
import entities.*;
import managers.*;
public class CustomerReviewMenu implements BaseMenuWithAccount {
    /**
	 * The scanner for reading input of user
	 */
    private Scanner sc = new Scanner(System.in);    
    /**
	 * For singleton pattern adherence. This MovieManager instance persists throughout runtime.
	 */
    private static CustomerReviewMenu single_instance = null;
	/**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
    public static CustomerReviewMenu getInstance()
    {
        if (single_instance == null)
            single_instance = new CustomerReviewMenu();
        return single_instance;
    }
    /**
	 * Customer's Menu to manage reviews
     * Choose options to create, read, update, delete reviews
     * @param user User's account
	 */
    public void display(Account user){
        int option = 0;
        try{
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
                    display(user);
                }
            }
        catch(InputMismatchException e){
            System.out.println("Invalid Input. Please input a number from 1-5 only!");
            sc.nextLine();
            display(user);
        }
        sc.nextLine();
        
        List<Movie>movieList = MovieListManager.getInstance().getMovieList();
        List<Review>reviewList = ReviewListManager.getInstance().getReviewList();
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
            case 4:
            // show past reviews of the user.
                ReviewManager.getInstance().showAllPastReviews(ReviewListManager.getInstance().getReviewList(), user.getUsername());
                break;
            case 5:
                System.out.println("Returning to Customer Menu...");
                if(user.getAccessLevel().equals("G")){
                    CustomerGuestMenu.getInstance().display();
                }
                else{
                    CustomerLoggedInMenu.getInstance().display(user);
                }

            default:
                System.out.println("Invalid choice. Please enter a number between 1-5.");
                break;
        }

        display(user);
    }
}
