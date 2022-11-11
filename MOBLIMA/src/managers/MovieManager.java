package managers;

import java.util.*;

import boundaries.AdminMenu;
import entities.*;

/**
 * A manager class for all actions related to the staff to manage movies
 * @author Wei Kang
 * @version 2.5
 * @since 01-11-2022
 */
public class MovieManager {    
	/**
	 * The scanner for reading input of user
	 */
    private Scanner sc = new Scanner(System.in);
	/**
	 * For singleton pattern adherence. This MovieManager instance persists throughout runtime.
	 */
    private static MovieManager single_instance = null;
	/**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
    public static MovieManager getInstance()
    {
        if (single_instance == null)
            single_instance = new MovieManager();
        return single_instance;
    }
	/**
	 * The default constructor for the MovieManager class
	 */
    private MovieManager(){
    }
	/**
	 * Staff's Menu to manage movies
     * Choose options to create, read, update, pseudo-delete movies, show top 5 movies, remove movies from database
     * @param account User's account
	 */
    public void staffMenu(Account account){
        int option = 0;
        try{
                System.out.println("=================== MOVIE MENU (STAFF) ==================\n" +
                                    " 1. Create Movies 						    		     \n" +
                                    " 2. View Full List Of Movies                            \n" +
                                    " 3. View Movie By ID                                    \n" +
                                    " 4. Update Movies       		                         \n" +
                                    " 5. Set Movie to End of Showing                         \n" +
                                    " 6. Show Top 5 Movies                                   \n" +
                                    " 7. Remove Movie from Database                          \n" +
                                    " 8. Go Back                                             \n" +
                                    "==========================================================");
                System.out.println("Enter choice: ");
                option = sc.nextInt();
                if(!(option >= 1 && option <=8)){
                    System.out.println("Please only enter a number from 1-8.");
                    sc.nextLine();
                    staffMenu(account);
                }
        }
        catch(InputMismatchException e){
            System.out.println("Invalid Input. Please input a number from 1-8 only!");
            sc.nextLine();
            staffMenu(account);
        }
        sc.nextLine();
        List<Movie>movieList = MovieListManager.getInstance().getMovieList();
        List<Review>reviewList = ReviewListManager.getInstance().getReviewList();
        int result;
        switch (option) {
            case 1:
                if (StaffMovieCRUDManager.getInstance().staffAddMovie(movieList)) {
                    System.out.println("Movie created!");
                } 
                else {
                    System.out.println("Failed to create movie!");
                }
                break;
            // Staff can view full list of movies without restriction
            case 2:
                StaffMovieCRUDManager.getInstance().printMovieList(movieList, reviewList);
                break;
            // Staff can search the movie info by movieID
            case 3:
                System.out.println("Enter MovieID: ");
                int movieID;
                while(true){
                    try{
                        movieID = sc.nextInt();
                        break;
                    }catch(InputMismatchException e){
                        System.out.println("Enter numbers only!");
                        sc.nextLine();
                        continue;
                    }
                }
                result = StaffMovieCRUDManager.getInstance().printMovieByID(movieList,reviewList, movieID);
                if(result == 0){
                    System.out.println("Movie not found!");
                }
                break;
            case 4:
                result = StaffMovieCRUDManager.getInstance().updateMovie(movieList);
                if(result == 1){
                    System.out.println("Movie Updated!");
                }
                else if(result == 2){
                    System.out.println("No updates made.");
                }
                else{
                    System.out.println("Failed to update movie!");
                }
                break;
            case 5:
                result = StaffMovieCRUDManager.getInstance().setToEndShowing(movieList);
                if(result == 1){
                    System.out.println("Movie successfully set to end showing!");
                }
                else if (result == 2){
                    ;
                }
                else{
                    System.out.println("Failed to set movie to end showing!");
                }
                break;
            case 6:
                SystemSettings.getInstance().top5Movies(movieList);
                break;
            case 7:
                result = StaffMovieCRUDManager.getInstance().removeMovieFromDatabase(movieList,reviewList);
                if(result == 1){
                    System.out.println("Movie successfully removed!");
                }
                else if (result == 2){
                    ;
                }
                else{
                    System.out.println("Failed to remove movie!");
                }
                break;
            
            case 8:
                System.out.println("Back to StaffApp......");
                AdminMenu.getInstance().display(account);
            default:
                System.out.println("Invalid choice. Please enter a number between 1-8.");
                break;
        }

        staffMenu(account);
    }

}
