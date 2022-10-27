package managers;


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
                                    " 2. Update Movie Status		                             \n" +
                                    " 3. Manage Reviews          	                         \n" +
                                    " 4. Show Top 5 Movies                                     \n" +
                                    " 5. Go Back                                               \n"+
                                    "==========================================================");
                System.out.println("Enter choice: ");
                option = sc.nextInt();
                if(!(option >= 1 && option <=5)){
                    System.out.println("Please only enter a number from 1-5.");
                    staffMenu(0);
                }
            }
        }
        catch(InputMismatchException e){
            System.out.println("Invalid Input.");
            staffMenu(0);
        }
        MovieListManager movListManager = new MovieListManager();
        ReviewListManager reviewListManager = new ReviewListManager();
        switch (option) {
            case 1:
                if (StaffAddMovieToList.staffAddMovie(movListManager.getMovieList(), reviewListManager.getReviewList())) {
                    System.out.println("Movie created!");
                } 
                else {
                    System.out.println("Failed to create movie!");
                }
                break;
            case 2:
                break;
            case 5:
                System.out.println("Back to StaffApp......");
                movListManager = null;
                reviewListManager = null;
                return;
            default:
                System.out.println("Invalid choice. Please enter a number between 1-5.");
                break;
        }

        // clear garbage
        movListManager = null;
        reviewListManager = null;
        staffMenu(0);
    }

}
