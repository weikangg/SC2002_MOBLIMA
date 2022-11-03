package managers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import view.adminApp;

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
                    staffMenu(0);
                }
            }
        }
        catch(InputMismatchException e){
            System.out.println("Invalid Input. Please input a number from 1-8 only!");
            sc.nextLine();
            staffMenu(0);
        }
        switch (option) {
            case 1:
                if (StaffAddMovieToList.staffAddMovie(MovieListManager.getInstance().getMovieList())) {
                    System.out.println("Movie created!");
                } 
                else {
                    System.out.println("Failed to create movie!");
                }
                break;
            case 2:
                StaffPrintMovieManager.printMovieList(MovieListManager.getInstance().getMovieList());
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
                StaffPrintMovieManager.printMovieByID(MovieListManager.getInstance().getMovieList(), movieID);
                break;
            case 4:
                if(StaffUpdateMovieManager.updateMovie(MovieListManager.getInstance().getMovieList()) == 1){
                    System.out.println("Movie Updated!");
                }
                else if(StaffUpdateMovieManager.updateMovie(MovieListManager.getInstance().getMovieList()) == 2){
                    System.out.println("No updates made.");
                }
                else{
                    System.out.println("Failed to update movie!");
                }
                break;
            case 5:
                if(StaffRemoveMovieManager.setToEndShowing(MovieListManager.getInstance().getMovieList()) == 1){
                    System.out.println("Movie successfully set to end showing!");
                }
                else if (StaffRemoveMovieManager.setToEndShowing(MovieListManager.getInstance().getMovieList()) == 2){
                    ;
                }
                else{
                    System.out.println("Failed to set movie to end showing!");
                }
                break;
            case 6:
                Top5Movies.getInstance().top5Movies(MovieListManager.getInstance().getMovieList());
                break;
            case 7:
                if(StaffRemoveMovieManager.removeMovieFromDatabase(MovieListManager.getInstance().getMovieList()) == 1){
                    System.out.println("Movie successfully removed!");
                }
                else if (StaffRemoveMovieManager.removeMovieFromDatabase(MovieListManager.getInstance().getMovieList()) == 2){
                    ;
                }
                else{
                    System.out.println("Failed to remove movie!");
                }
                break;
            
            case 8:
                System.out.println("Back to StaffApp......");
                sc.nextLine();
                adminApp a = adminApp.getInstance();
                try{
                    Method m = adminApp.class.getDeclaredMethod("displayLoggedInMenu");
                    m.setAccessible(true);
                    m.invoke(a);
                }catch(NoSuchMethodException e){
                    System.out.println("No such menu!");
                }catch(InvocationTargetException e){
                    System.out.println("Invocation error!");
                }catch(Exception e){
                    System.out.println("Error!");
                }
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1-8.");
                break;
        }

        staffMenu(0);
    }

}
