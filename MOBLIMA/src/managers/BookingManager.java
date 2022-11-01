package managers;

import java.util.Scanner;

public class BookingManager {
	
	//show available seats
	//booking manager allows customer to pick a seat, if available, and book it.
	//allow user to select multiple seats, allow user to delete selected seats.
	
	private Scanner sc = new Scanner(System.in);
	
	
	private static BookingManager bm = null;
	
	public static BookingManager getInstance()
	{
	    if (bm == null) {
	    	 bm = new BookingManager();
	    }	        
	    return bm;
	}
	
	
	public static void bookingMenu()
	{
		//display seats at show time
		//get user input to choose seats
		
		System.out.println
		           ("====================BOOKING MENU=====================\n"+
				    " 1. Add seats                                        \n"+
					" 2. Remove seats                                     \n"+
					" 3. Confirm seat selections                          \n"+
					" 0. Exit			                                  \n"+
				    "======================================================");
		System.out.println("Please select a choice:");
		int choice = 0;
		choice = sc.nextInt();
		
		while(choice < 0 || choice >3)
		{
			System.out.println("Invalid input type. Please enter an integer value.");
    		sc.next();
		}
		
	}
	
    public static void main()
    {
        bookingMenu();
    }
	

}