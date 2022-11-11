package boundaries;

import java.util.*;
import entities.*;
import managers.*;
/**
 * The class for the customer logged in menu
 * @author Wei Kang
 * @version 3.0
 * @since 11-11-2022
 */

public class CustomerLoggedInMenu {
    /**
	 * For singleton pattern adherence. This MovieListManager instance persists throughout runtime.
	 */
    private static CustomerLoggedInMenu newInstance = null;
    /**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
    public static CustomerLoggedInMenu getInstance(){
        if (newInstance == null){
            newInstance = new CustomerLoggedInMenu();
        }
        return newInstance;
    }
    /**
	 * The scanner for reading input of user
	 */
    Scanner scan = new Scanner(System.in);
     /**
     * Function to show logged in user menu
     * Logged in users can make bookings, view movies, search movies, view top 5 and also give review
     * There is also a log out selection which will bring user back to the mainapp menu
     * @param account account of user logged in
     */
    public void display(Account account){
        List<Movie> movieList = MovieListManager.getInstance().getMovieList();
        List<Review> reviewList = ReviewListManager.getInstance().getReviewList();
        CustomerMovieManager custMovieManager = CustomerMovieManager.getInstance();
        boolean exit = false;
        String strinput;
        int input;
        do{
            //main menu
            System.out.print("================= MOBLIMA CUSTOMER INTERFACE =================\n"+
                                "1. Make Bookings for movies\n"+
                                "2. Show all movies\n"+
                                "3. Search Movie by Name\n"+
                                "4. Top 5 Movies\n"+
                                "5. Give Review\n" + 
                                "6. Log out\n"+
                            "==============================================================\n");
            //scanner
            while(true){
                try{
                    System.out.println("Please Enter Your Choice: ");
                    input = scan.nextInt();
                    scan.nextLine();
                    break;
                }catch(InputMismatchException e){
                    scan.nextLine();
                    System.out.println("Please enter a valid option from 1-6 only!");
                    continue;
                }
            }

            //cases for each input
            switch(input){
                case 1:
                    CustomerBookingAndReviewMenu.getInstance().display(account);
                    break;

                case 2:
                    custMovieManager.printMovieList(movieList,reviewList);
                    break;

                case 3:
                //search
                    System.out.println("Please enter the movie name:");
                    strinput = scan.nextLine();
                    if(custMovieManager.searchMovie(movieList, reviewList, strinput) == 0){
                        System.out.println("Movie not found!");
                    }
                    break;

                case 4:
                    int displaySetting;
                    SystemSettings top5Movies = SystemSettings.getInstance();
                    
                    // Getting permissions
                    top5Movies.updatePermissions();

                    // Setting display setting
                    if(top5Movies.getTop5OverallRatingPermission().equalsIgnoreCase("Y")  && top5Movies.getTop5SalesPermission().equalsIgnoreCase("Y") ){
                        displaySetting = 0;
                    }
                    else if(top5Movies.getTop5OverallRatingPermission().equalsIgnoreCase("N")  && top5Movies.getTop5SalesPermission().equalsIgnoreCase("Y")){
                        displaySetting = 1;
                    }
                    else if(top5Movies.getTop5OverallRatingPermission().equalsIgnoreCase("Y")  && top5Movies.getTop5SalesPermission().equalsIgnoreCase("N")){
                        displaySetting = 2;
                    }
                    else{
                        displaySetting = 3;
                    }
                    switch(displaySetting){
                        
                        // Normal setting
                        case 0:
                            SystemSettings.getInstance().top5Movies(MovieListManager.getInstance().getMovieList());
                            break;
                        
                        // Only view top 5 sales
                        case 1:
                            SystemSettings.getInstance().top5SalesOnly(MovieListManager.getInstance().getMovieList());
                            break;
                        
                        // Only view top 5 overall Rating score
                        case 2:
                            SystemSettings.getInstance().top5RatingsOnly(MovieListManager.getInstance().getMovieList());
                            break;
                        // Else both cannot display (if the movie has very bad sales & ratings)
                        case 3:
                            SystemSettings.getInstance().viewNone();
                            break;
                    }
                    
                    break;
                case 5:
                    //review movie
                    CustomerReviewMenu.getInstance().display(account);
                    break;
                case 6:
                    System.out.println("Logging out...");
                    MainMenu.getInstance().display();
                    break;
                
                default:
                    System.out.println("Please enter a valid option from 1-6 only!");
                    display(account);
            }
        }while(exit == false);
    }
}
