package managers;

import java.util.Scanner;
import java.util.ArrayList;
import entities.Showtime;

public class BookingManager {
	
	//show available seats
	//booking manager allows customer to pick a seat, if available, and book it.
	//allow user to select multiple seats, allow user to delete selected seats.
	
	private Scanner sc = new Scanner(System.in);
    private int[][] seats;
	private ArrayList<String> bookedSeats;
	
	private static BookingManager bm = null;
	
	public static BookingManager newBM()
	{
	    if (bm == null) {
	    	 bm = new BookingManager();
	    }	        
	    return bm;
	}
	
	
	public void bookingMenu(Showtime showtime)
	{

		//display seats at show time
		//get user input to choose seats
		this.seats = showtime.getSeats();
        showtime.showSeats();
        Boolean notQuit = true;
        while(notQuit)
        {
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
                choice = sc.nextInt();
            }
            displayAddedSeats();
            switch(choice)
            {
                case 0:
                    notQuit = false;
                    break;
                case 1: //Add seats to cart
                    addSeat();
                    break;
                case 2: //Delete seats from cart
                    deleteSeat();
                case 3://check at least 1 seat selected
                    if(confirmSelection()==true)
                    {
                        //Call ticket manager
                        notQuit = false;
                    }
                    else{break;}

            }
        }

		
	}

    public Boolean isSeatEmpty(int row, int col) //Check for showtime seats
    {
        //0 = unavailable, 1 = available, 2 = booked, 3 = added
        if(this.seats[row][col] == 1)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public void displayAddedSeats()
    {
        if(this.bookedSeats.isEmpty())
        {
            System.out.println("No seats have been added");
            return;
        }
        System.out.println("Booked Seats (Row/Col): ");
        for(int i = 0; i<this.bookedSeats.size();i++)
        {
            System.out.println("Seat " + (i+1) + ":" + this.bookedSeats.get(i));
        }
    }



    public void addSeat() //Add seats into booking class attribute ArrayList tickets
    {
        int row,col;
        row = 0; col = 0;
        System.out.println("Please enter row number:");
		while (!sc.hasNextInt()) { // Not a string
			sc.next(); // Remove newline character
			System.out.println("Invalid input! Please enter a valid seat ID.");
        }
        row = sc.nextInt();

        System.out.println("Please enter column number:");
		while (!sc.hasNextInt()) { // Not a string
			sc.next(); // Remove newline character
			System.out.println("Invalid input! Please enter a valid seat ID.");
		}
        col = sc.nextInt();

        if (isSeatEmpty(row, col)) {						
			System.out.println("Seat " + row + " " + col + " added to cart");
	    	updateSeats(row,col,1);
            String newSeat = row+"/"+col;
            this.bookedSeats.add(newSeat);
		}
    }

    public void deleteSeat()
    {
        if(this.bookedSeats.isEmpty())
        {
            System.out.println("No seats to delete");
            return;
        }

        displayAddedSeats();
        int size = this.bookedSeats.size();
        System.out.println("Please choose seat to delete");
        int seat = sc.nextInt();

        while(seat > size || seat < 1)
        {
            System.out.println("Please enter valid number");
            seat = sc.nextInt();
        }
        String rowcol = this.bookedSeats.get(seat-1);

        String[] tokens = rowcol.split("/");
        int row = Integer.parseInt(tokens[0]);
        int col = Integer.parseInt(tokens[1]);        
        updateSeats(row, col, 3);
        this.bookedSeats.remove(seat-1);
    }

    public Boolean confirmSelection()
    {
        if(this.bookedSeats.size()==0)
        {
            System.out.println("No tickets selected");
            System.out.println("Please choose another option");
            return false;   
        }
        System.out.println("Confirm booking? (Yes/No)");
        String answer = sc.nextLine();
        while(answer!="Yes" || answer!="No")
        {
            System.out.println("Confirm booking? (Yes/No)");
            answer = sc.nextLine();
        }
        if(answer == "No")
        {
            return false;
        }
        else
        {
            for(int i = 0; i<this.bookedSeats.size();i++)
            {
                String rowcol = this.bookedSeats.get(i);
                String[] tokens = rowcol.split("/");
                int row = Integer.parseInt(tokens[0]);
                int col = Integer.parseInt(tokens[1]);        
                updateSeats(row, col, 2);
            }
        }
        return true;

    }
    public void updateSeats(int row, int col, int choice)
    {
        switch(choice)
        {
            case 1: //add seats
                this.seats[row][col]=3;
            case 2: //confirmed seats
                this.seats[row][col]=2;
            case 3: //remove seats
                this.seats[row][col]=1;
        }

    }
	

}