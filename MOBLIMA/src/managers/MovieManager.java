package managers;

import static managers.MovieListManager.*;
import java.util.*;


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

    public void staffMenu(int choice){
        int option = 0;
        try{
            if(choice == 0){
                System.out.println("=================== MOVIE MENU (STAFF) ==================\n" +
                                    " 1. Create Movies 						    		     \n" +
                                    " 2. View Full List Of Movies                            \n" +
                                    " 3. View Movie By ID                                    \n" +
                                    " 4. Update Movies       		                         \n" +
                                    " 5. Remove Movies                                       \n" +
                                    // " 4. Manage Reviews          	                         \n" +
                                    " 6. Show Top 5 Movies                                   \n" +
                                    " 7. Go Back                                             \n" +
                                    "==========================================================");
                System.out.println("Enter choice: ");
                option = sc.nextInt();
                if(!(option >= 1 && option <=7)){
                    System.out.println("Please only enter a number from 1-7.");
                    staffMenu(0);
                }
            }
        }
        catch(InputMismatchException e){
            System.out.println("Invalid Input.");
            sc.nextLine();
            staffMenu(0);
        }
        MovieListManager movListManager = MovieListManager.getInstance();
        ReviewListManager reviewListManager = new ReviewListManager();
        switch (option) {
            case 1:
                if (StaffAddMovieToList.staffAddMovie(MovieListManager.getMovieList())) {
                    System.out.println("Movie created!");
                } 
                else {
                    System.out.println("Failed to create movie!");
                }
                break;
            case 2:
                StaffPrintMovieManager.printMovieList(MovieListManager.getMovieList());
                break;
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
                StaffPrintMovieManager.printMovieByID(MovieListManager.getMovieList(), movieID);
                break;
            case 4:
                if(StaffUpdateMovieManager.updateMovie(MovieListManager.getMovieList()) == 1){
                    System.out.println("Movie Updated!");
                }
                else if(StaffUpdateMovieManager.updateMovie(MovieListManager.getMovieList()) == 2){
                    System.out.println("No updates made.");
                }
                else{
                    System.out.println("Failed to update movie!");
                }
                break;
            case 5:
                if(StaffRemoveMovieManager.removeMovie(MovieListManager.getMovieList())){
                    System.out.println("Movie removed!");
                }
                else{
                    System.out.println("Failed to remove movie!");
                }
                break;
            case 6:
                Top5Movies.top5Movies(MovieListManager.getMovieList());
                break;
            case 7:
                System.out.println("Back to StaffApp......");
                movListManager = null;
                reviewListManager = null;
                return;
            default:
                System.out.println("Invalid choice. Please enter a number between 1-7.");
                break;
        }

        // clear garbage
        movListManager = null;
        reviewListManager = null;
        staffMenu(0);
    }

}
