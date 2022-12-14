package managers;

import java.util.Scanner;
//import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import entities.*;
import utils.*;

/**
 * Function class that provides the booking and seat selection features for customers
 * @author Jovan Sie
 * @version 3.0
 * @since 06-11-2022
 */
public class BookingManager implements deleteM{
	
	//show available seats
	//booking manager allows customer to pick a seat, if available, and book it.
	//allow user to select multiple seats, allow user to delete selected seats.
	/**
     * scanner 
     */
	private Scanner sc = new Scanner(System.in);
    /**
     * value to check if booking instance is complete
     */
    private Boolean done = false;
    /**
     * store a copy of the seats, to perform updates without affecting csv
     */
    private Seat[][] seats;
    /**
     * stores the string values of the booked seat numbers
     */
	private ArrayList<String> bookedSeats;
    /**
     * stores the booked tickets
     */
    private ArrayList<Ticket> bookedTickets;
    /**
     * stores the booking class
     */
    private Booking booking;
    /**
     * stores the current showtime class for use
     */
    private Showtime showtime;
    /**
     * stores the current users information for use
     */
    private Account user;
    /**
     * to quit the loop upon exit
     */
    Boolean notQuit = true;
    
	/**
	 * For singleton pattern adherence. This BookingManager instance persists throughout runtime.
	 */
	private static BookingManager bm = null;
	
	
   /**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
    public static BookingManager newBM()
	{
	    if (bm == null) {
	    	 bm = new BookingManager();
	    }	        
	    return bm;
	}

    /**
     * Constructor
     */
    private BookingManager(){}

	


	
	
    /** 
     * main page for users to book their seats. Shows the available seats, and calls ticket manager once user confirms
     * their seat selections
     * @param showtime selected showtime
     * @param user current user that is logged in
     */
    public void bookingMenu(Showtime showtime, Account user)
	{
        ArrayList<String> bookedSeats = new ArrayList<>();
        setBookedSeats(bookedSeats);
        setUser(user);

		//display seats at show time
		//get user input to choose seats
        setShowTime(showtime);
		setSeats(showtime.getSeatS());
        //getShowtime().showSeats();
        notQuit = true;
        while(notQuit)
        {
            System.out.println("Selected showtime is on " + getShowtime().getDateTime());
            showSeatsType();
            showSeat();
            System.out.println
            ("====================BOOKING MENU=====================\n"+
             " 1. Add seats                                        \n"+
             " 2. Remove seats                                     \n"+
             " 3. Confirm seat selections                          \n"+
             " 0. Exit			                                  \n"+
             "======================================================");
            displayAddedSeats();
            System.out.println("Please select a choice:");
            int choice = IOUtils.check(0, 3, sc);
            switch(choice)
            {
                case 0:
                    notQuit = false;
                    deleteM();
                    break;
                case 1: //Add seats to cart
                    addSeat();
                    break;
                case 2: //Delete seats from cart
                    deleteSeat();
                    break;
                case 3://check at least 1 seat selected
                    if(confirmSelection(showtime))
                    {
                        //System.out.println("Going to Ticket Menu");
                        //System.out.println("Booked Seat: " + getBookedSeats().get(0));
                        //System.out.println("Seats = " + getSeats()[1][5]);
                        TicketManager.newTM().ticketMenu(showtime, getBookedSeats(), getSeats());
                        //Call ticket manager
                        //notQuit = false;
                        if(getDone())
                        {
                            deleteM();
                            return;
                        }
                    }
                    break;
                default:
                    System.out.println("Please enter an integer value from 0-3.");

            }
        }

		
	}

    
    /** 
     * Checks if the seat is empty and available for selection
     * @param row row of specific seat
     * @param col column of specific seat
     * @return Boolean
     */
    public Boolean isSeatEmpty(int row, int col) //Check for showtime seats
    {
        //0 = available, 1 = unavailable, 2 = booked
        if(getSeats()[row][col].getState() == 0)
        {
            System.out.println("Its available");
            return true;
        }
        else
        {
            System.out.println("Its unavailable");
            return false;
        }

    }

    /**
     * Displays the seats that the customer has chosen
     */
    public void displayAddedSeats()
    {
        if(getBookedSeats().isEmpty())
        {
            System.out.println("No seats have been added");
            return;
        }
        System.out.println("Booked Seats: ");
        for(int i = 0; i<getBookedSeats().size();i++)
        {
            String rowcol = getBookedSeats().get(i);
            String[] tokens = rowcol.split("/");
            int row = Integer.parseInt(tokens[0]);
            int col = Integer.parseInt(tokens[1]);
            System.out.println("Seat " + (i+1) + ": " + (char)(row+65) + col);
        }
    }


    /**
     * Add seats into booking class attribute ArrayList tickets
     */
    public void addSeat()
    {
        int row,col;
        row = 0; col = 0;
        System.out.println("Row (A-E):");
        row = ((int)sc.next().charAt(0));
        while(true)
        {
            if (row >= 65 && row <= 69)
            {
                row = row-65;
                break;
            }
            else if(row >= 97 && row <= 101)
            {
                row = row-97;
                break;
            }
            else
            {
                System.out.println("Invalid input! Row (A-E):");
                row = ((int)sc.next().charAt(0));
            }
        }
        System.out.println("Column (0-9):");
        col = IOUtils.check(0, 9, sc);
        if (isSeatEmpty(row, col)) {						
			System.out.println("Seat " + (char)(row+65)+ col + " added to cart");
	    	updateSeats(row,col,1);
            String newSeat = row+"/"+col;
            getBookedSeats().add(newSeat);
		}
        else
        {
            if(getSeats()[row][col].getState()==1)
            System.out.println("Seat " + (char)(row+65) + col + " unavailable.");
            else
            System.out.println("Seat " + (char)(row+65) + col + " already selected.");
        }
    }

    /**
     * Delete seats from customers current selection
     */
    public void deleteSeat()
    {
        if(getBookedSeats().isEmpty())
        {
            System.out.println("No seats to delete");
            return;
        }

        displayAddedSeats();
        int size = getBookedSeats().size();
        System.out.println("Please choose seat to delete");
        int seat = IOUtils.check(1, size, sc);
        String rowcol = getBookedSeats().get(seat-1);

        String[] tokens = rowcol.split("/");
        int row = Integer.parseInt(tokens[0]);
        int col = Integer.parseInt(tokens[1]);        
        updateSeats(row, col, 3);
        getBookedSeats().remove(seat-1);
        System.out.println("Seat removed...");
    }

    
    /** 
     * Confirms the customers selections
     * @param showtime selected showtime
     * @return Boolean
     */
    public Boolean confirmSelection(Showtime showtime)
    {
        if(this.bookedSeats.size()==0)
        {
            System.out.println("No seats selected");
            System.out.println("Please choose another option");
            return false;   
        }
        if(IOUtils.confirm("Confirm booking?"))
        {
            for(int i = 0; i<getBookedSeats().size(); i++)
            {
                String rowcol = getBookedSeats().get(i);
                String[] tokens = rowcol.split("/");
                int row = Integer.parseInt(tokens[0]);
                int col = Integer.parseInt(tokens[1]);        
                updateSeats(row, col, 1);
            }
            return true;
        }
        else
            return false;

    }


    
    /** 
     * Updates the state of the seats to allow showSeat function to display accurate information
     * @param row row of selected seat
     * @param col column of selected seat
     * @param choice 0,1,2 depending on removing,confirming or adding seat respectively
     */
    public void updateSeats(int row, int col, int choice)
    {
        switch(choice)
        {
            case 1: //add seats
                getSeats()[row][col].setState(2);
                break;
            case 2: //confirmed seats
                getSeats()[row][col].setState(1);
                break;
            case 3: //remove seats
                getSeats()[row][col].setState(0);
                break;
            default:
                break;
        }
    }

    
    /** 
     * Returns the seat array
     * @return current seat array
     */
    public Seat[][] getSeats()
    {
        return this.seats;
    }
    
    /** 
     * return the list of booked seats
     * @return the current string list of booked seats
     */
    public ArrayList<String> getBookedSeats()
    {
        return this.bookedSeats;
    }
    
    /** 
     * gets the list of tickets
     * @return the current ticket list
     */
    public ArrayList<Ticket> getBookedTickets()
    {
        return this.bookedTickets;
    }
    
    /** 
     * gets the booking
     * @return current Booking
     */
    public Booking getBooking()
    {
        return this.booking;
    }
    
    /** 
     * gets the showtime
     * @return current showtime
     */
    public Showtime getShowtime()
    {
        return this.showtime;
    }
    
    /** 
     * gets the user account
     * @return current Account
     */
    public Account getUser()
    {
        return this.user;
    }




    
    /** 
     * stores a new set of seats
     * @param seats new seat array
     */
    public void setSeats(Seat[][] seats)
    {
        this.seats = seats;
    }
    
    /** 
     * stores a new array list of booked seats in string
     * @param bookedSeats new booked seat array
     */
    public void setBookedSeats(ArrayList<String> bookedSeats)
    {
        this.bookedSeats = bookedSeats;
    }
    
    /** 
     * stores a new array of tickets
     * @param bookedTickets new booked ticket array
     */
    public void setBookedTickets(ArrayList<Ticket> bookedTickets)
    {
        this.bookedTickets=bookedTickets;
    }
    
    /** 
     * stores the new booking class
     * @param booking new booking class
     */
    public void setBooking(Booking booking)
    {
        this.booking = booking;
    }
    
    /** 
     * sets the current showtime
     * @param showtime selected showtime
     */
    public void setShowTime(Showtime showtime)
    {
        this.showtime = showtime;
    }
    
    /** 
     * sets the current user
     * @param user current logged in user
     */
    public void setUser(Account user)
    {
        this.user = user;
    }


    /**
     * pushes the updated seating plan to the csv files
     */
    public void updateShowtimeCSV()
    {
        for(int i = 0; i<getBookedSeats().size();i++)
        {
            String rowcol = getBookedSeats().get(i);
            String[] tokens = rowcol.split("/");
            int row = Integer.parseInt(tokens[0]);
            int col = Integer.parseInt(tokens[1]);        
            getShowtime().reserveSeat(row, col);
        }
    }



    /**
     * Final function which creates the Booking and Transaction class and stores all the information into a csv file
     * update all necessary csv files and finally stop all instances
     * 
     */
    public void createBooking()
    {
        ArrayList<Booking> userBookings = new ArrayList<Booking>();
        String path = System.getProperty("user.dir") +"\\data\\bookings\\";
        String username = getUser().getUsername();
        String filename = path + username + "/bookings.csv";
        TransactionManager.getInstance().makeTransaction();
        File check = new File(filename);
        FileInputStream fis = null;
		ObjectInputStream in = null;
        if(check.exists())
        {
            try
            {
                fis = new FileInputStream(filename);
                in = new ObjectInputStream(fis);
                userBookings = (ArrayList<Booking>) in.readObject();
                in.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("IOException is caught 1");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                System.out.println("IOException is caught 2");
            }
            //System.out.println("Total Price of first booking is: " + userBookings.get(0).getTotalPrice());
            //System.out.println("Transaction Number: " + userBookings.get(0).getTransaction().getID());
            //System.out.println("User info: " + userBookings.get(0).getUserInfo());
        }
        String userBooks = "B" + (userBookings.size()/2);
        Booking newB = new Booking(userBooks,TransactionManager.getInstance().getUserInfo(),TransactionManager.getInstance().getTotalPrice(),getShowtime().getMovieTitle(),
        getShowtime().getCinemaID(),getShowtime().getCineplexID(),TransactionManager.getInstance().getTList(),
        getShowtime().getDateTimeLDT(),TransactionManager.getInstance().getTransaction());
        userBookings.add(newB);
        updateShowtimeCSV();
        //check = new File(filename);
        try{
            check.getParentFile().mkdirs();
            FileOutputStream file = new FileOutputStream(check);
            ObjectOutputStream out = new ObjectOutputStream(file);
            //System.out.println(file.getAbsolutePath());
            userBookings.add(getBooking());
            out.writeObject(userBookings);
            out.close();
            file.close();

        }catch(IOException ex){
            System.out.println("IOException is caught");
        }
        TransactionManager.getInstance().updateTotalSales();
        setDone();
        TransactionManager.getInstance().deleteM();
        TicketManager.newTM().deleteM();
        System.out.println("Booking and payment success!\nPlease view under booking history");

    }
    

    /**
     * set to true if booking was done
     */
    public void setDone()
    {
        this.done = true;
    }
    
    /** 
     * checks if booking was done
     * @return current value of done
     */
    public boolean getDone()
    {
        return this.done;
    }

    /**
     * deletes the BookingManager instance
     */
    public void deleteM()
    {
        //private Scanner sc = new Scanner(System.in);
        //private int[][] seats;
        bookedSeats = null;
        bookedTickets = null;
        booking = null;
        showtime = null;
        done = false;
        //TicketManager.newTM().deleteTicketM();
        //TransactionManager.getInstance().deleteTransactionM();
    }

    /**
     * displays the seats
     */
    public void showSeat(){
        System.out.println("      Screen");
        for (int i = 0; i < 5; i++){
            System.out.print((char)(i+65)+"  ");
            for(int j = 0; j < 10; j++){
                if(j == 5) System.out.print("  ");
                if(getSeats()[i][j].getState() == 0) System.out.print("\u001B[32m" + "O" + "\u001B[0m");
                else if(getSeats()[i][j].getState() == 1) System.out.print("\u001B[31m" + "X" + "\u001B[0m");
                else System.out.print("\u001B[34m" + "S" + "\u001B[0m");
            }
            System.out.println("  "+(char)(i+65));
        }
        System.out.println("   01234  56789\n");
    }
    /**
     * displays the type of each seats
     */
    public void showSeatsType(){
        System.out.format("%8s", getShowtime().getCinemaClass());
        System.out.println(" Cinema");
        System.out.println("      Screen");
        for (int i = 0; i < 5; i++){
            System.out.print((char)(i+65)+"  ");
            for(int j = 0; j < 10; j++){
                if(j == 5) System.out.print("  ");
                if(getSeats()[i][j].getState() == 0) System.out.print("\u001B[32m" + seats[i][j].getSeatType().toString().charAt(0) + "\u001B[0m");
                else if(getSeats()[i][j].getState() == 1) System.out.print("\u001B[31m" + "X" + "\u001B[0m");
                else System.out.print("\u001B[34m" + "S" + "\u001B[0m");
            }
            System.out.println("  "+(char)(i+65));
        }
        System.out.println("   01234  56789\n");
    }

    
}
