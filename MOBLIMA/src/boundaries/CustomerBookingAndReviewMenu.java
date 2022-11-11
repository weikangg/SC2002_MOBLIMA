package boundaries;

import java.util.*;
import entities.*;
import managers.*;

public class CustomerBookingAndReviewMenu implements BaseMenuWithAccount{
    /**
	 * For singleton pattern adherence. This MovieListManager instance persists throughout runtime.
	 */
    private static CustomerGuestMenu newInstance = null;
    /**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
    public static CustomerGuestMenu getInstance(){
        if (newInstance == null){
            newInstance = new CustomerGuestMenu();
        }
        return newInstance;
    }
    /**
	 * The scanner for reading input of user
	 */
    Scanner scan = new Scanner(System.in);
    /**
     * Function shows logged in user the booking menu when they have selected the booking option
     * User can choose to book and purchase tickets or look at their booking history
     * Selecting exit will log user out and bring them to the guest main menu
     * @param user account of user booking movie
     */
    public void display(Account user){
        int input = 5;
        boolean exit = false;
        List<Movie> movieList = MovieListManager.getInstance().getMovieList();
        List<Review> reviewList = ReviewListManager.getInstance().getReviewList();
        CustomerAccManager custAccManager = CustomerAccManager.getInstance();
        CustomerMovieManager custMovieManager = CustomerMovieManager.getInstance();
       
        do{
            //menu
            System.out.print("================= MOBLIMA CUSTOMER APP =================\n"+
                            "1. Book and Purchase Ticket\n"+
                            "2. Booking History\n"+
                            "3. Exit\n"+
                            "========================================================\n");
            System.out.println("Please Enter Your Choice:");

            try{
                input = scan.nextInt();
                scan.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Please enter a valid option from 1-3 only!");
                scan.nextLine();
                display(user);
            }

            switch(input){
                case 1:
                //book ticket
                int idx = 1;
                Showtime movst;
                List<Showtime> showtime;

                //link the movie to showtime
                showtime = custMovieManager.searchMovieShowtime(movieList, reviewList);
                if(showtime.isEmpty())
                {
                    break;
                }
                
                //show user movie timing
                for(Showtime st: showtime)
                {
                    System.out.println(idx + ". " + st.getDateTime());
                    idx++;
                }
                //ask user to choose a movie timing
                System.out.println("Please choose a timing:");
                input = scan.nextInt();
                movst = showtime.get(input - 1);

                //call bookingmenu in booking manager
                BookingManager.newBM().bookingMenu(movst, user);
                break;

                case 2:
                //show booking history
                System.out.println("=========================HISTORY=========================\n");
                custAccManager.checkHistory(user);
                    break;
                    
                case 3:
                    System.out.println("Exiting Customer App...");
                    customerApp.getInstance().customerGuestMenu();
                    break;

                default:
                System.out.println("Please enter a valid option (1-3) only.");
            }
        }while(exit == false);
    }
}
