package managers;

import java.util.*;
import view.adminApp;
import entities.*;

public class MovieManager {    
    private Scanner sc = new Scanner(System.in);

    private static MovieManager single_instance = null;
    public static MovieManager getInstance()
    {
        if (single_instance == null)
            single_instance = new MovieManager();
        return single_instance;
    }


    private MovieManager(){
    }

    public void staffMenu(int choice,Account account){
        int option = 0;
        try{
            if(choice == 0){
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
                    staffMenu(0, account);
                }
            }
        }
        catch(InputMismatchException e){
            System.out.println("Invalid Input. Please input a number from 1-8 only!");
            sc.nextLine();
            staffMenu(0, account);
        }
        sc.nextLine();
        List<Movie>movieList = MovieListManager.getInstance().getMovieList();
        List<Review>reviewList = ReviewListManager.getInstance().getReviewList();
        switch (option) {
            case 1:
                if (StaffAddMovieToList.staffAddMovie(movieList)) {
                    System.out.println("Movie created!");
                } 
                else {
                    System.out.println("Failed to create movie!");
                }
                break;
            // Staff can view full list of movies without restriction
            case 2:
                StaffPrintMovieManager.printMovieList(movieList, reviewList);
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
                if(StaffPrintMovieManager.printMovieByID(movieList,reviewList, movieID) == 0){
                    System.out.println("Movie not found!");
                }
                break;
            case 4:
                if(StaffUpdateMovieManager.updateMovie(movieList) == 1){
                    System.out.println("Movie Updated!");
                }
                else if(StaffUpdateMovieManager.updateMovie(movieList) == 2){
                    System.out.println("No updates made.");
                }
                else{
                    System.out.println("Failed to update movie!");
                }
                break;
            case 5:
                if(StaffRemoveMovieManager.setToEndShowing(movieList) == 1){
                    System.out.println("Movie successfully set to end showing!");
                }
                else if (StaffRemoveMovieManager.setToEndShowing(movieList) == 2){
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
                if(StaffRemoveMovieManager.removeMovieFromDatabase(movieList,reviewList) == 1){
                    System.out.println("Movie successfully removed!");
                }
                else if (StaffRemoveMovieManager.removeMovieFromDatabase(movieList,reviewList) == 2){
                    ;
                }
                else{
                    System.out.println("Failed to remove movie!");
                }
                break;
            
            case 8:
                System.out.println("Back to StaffApp......");
                adminApp.getInstance().displayLoggedInMenu(account);
            default:
                System.out.println("Invalid choice. Please enter a number between 1-8.");
                break;
        }

        staffMenu(0, account);
    }

}
