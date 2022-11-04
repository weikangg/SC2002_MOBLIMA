package managers;

import java.util.Scanner;
import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import entities.*;

public class BookingManager implements Serializable{
	
	//show available seats
	//booking manager allows customer to pick a seat, if available, and book it.
	//allow user to select multiple seats, allow user to delete selected seats.
	
	private Scanner sc = new Scanner(System.in);
    private int[][] seats;
	private ArrayList<String> bookedSeats;
    private ArrayList<Ticket> bookedTickets;
    private Booking booking;
    private Showtime showtime;
    
	
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
        ArrayList<String> bookedSeats = new ArrayList<>();
        setBookedSeats(bookedSeats);

		//display seats at show time
		//get user input to choose seats
        setShowTime(showtime);
		setSeats(showtime.getSeats());
        getShowtime().showSeats();
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
            displayAddedSeats();
            System.out.println("Please select a choice:");
            int choice = 0;
            choice = sc.nextInt();
            sc.nextLine();
            while(choice < 0 || choice >3)
            {
                System.out.println("Invalid input type. Please enter an integer value.");
                choice = sc.nextInt();
            }   
            switch(choice)
            {
                case 0:
                    deleteBM();
                    notQuit = false;
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
                        TicketManager.newTM().ticketMenu(showtime, getBookedSeats(), getSeats());
                        //Call ticket manager
                        notQuit = false;
                    }
                    break;
                default:
                    System.out.println("Please enter an integer value from 0-3.");

            }
        }

		
	}

    public Boolean isSeatEmpty(int row, int col) //Check for showtime seats
    {
        //0 = available, 1 = unavailable, 2 = booked, 3 = added
        if(getSeats()[row][col] == 0)
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
        if(getBookedSeats().isEmpty())
        {
            System.out.println("No seats have been added");
            return;
        }
        System.out.println("Booked Seats (Row/Col): ");
        for(int i = 0; i<getBookedSeats().size();i++)
        {
            System.out.println("Seat " + (i+1) + ":" + getBookedSeats().get(i));
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
            getBookedSeats().add(newSeat);
		}
        else
        {
            System.out.println("Seat " + row + " " + col + " is either selected or unavailable");
        }
    }

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
        int seat = sc.nextInt();

        while(seat > size || seat < 1)
        {
            System.out.println("Please enter valid number");
            seat = sc.nextInt();
        }
        String rowcol = getBookedSeats().get(seat-1);

        String[] tokens = rowcol.split("/");
        int row = Integer.parseInt(tokens[0]);
        int col = Integer.parseInt(tokens[1]);        
        updateSeats(row, col, 3);
        getBookedSeats().remove(seat-1);
    }

    public Boolean confirmSelection(Showtime showtime)
    {
        if(this.bookedSeats.size()==0)
        {
            System.out.println("No seats selected");
            System.out.println("Please choose another option");
            return false;   
        }
        System.out.println("Confirm booking? (Yes/No)");
        String answer = sc.nextLine();
        System.out.println(answer);
        while(true)
        {
            System.out.println(answer);
            if(answer.equals("No"))
            {
                return false;
            }
            else if (answer.equals("Yes"))
            {
                for(int i = 0; i<getBookedSeats().size();i++)
                {
                    String rowcol = getBookedSeats().get(i);
                    String[] tokens = rowcol.split("/");
                    int row = Integer.parseInt(tokens[0]);
                    int col = Integer.parseInt(tokens[1]);        
                    updateSeats(row, col, 2);
                    return true;
                }
            }
            else
            {
                System.out.println("Please re enter choice (Yes/No)");
                answer = sc.nextLine();
            }

        }

    }       //getShowtime().setSeats(getSeats());


    public void updateSeats(int row, int col, int choice)
    {
        switch(choice)
        {
            case 1: //add seats
                getSeats()[row][col]=3;
            case 2: //confirmed seats
                getSeats()[row][col]=2;
            case 3: //remove seats
                getSeats()[row][col]=0;
        }
    }

    public int[][] getSeats()
    {
        return this.seats;
    }
    public ArrayList<String> getBookedSeats()
    {
        return this.bookedSeats;
    }
    public ArrayList<Ticket> getBookedTickets()
    {
        return this.bookedTickets;
    }
    public Booking getBooking()
    {
        return this.booking;
    }
    public Showtime getShowtime()
    {
        return this.showtime;
    }




    public void setSeats(int[][] seats)
    {
        this.seats = seats;
    }
    public void setBookedSeats(ArrayList<String> bookedSeats)
    {
        this.bookedSeats = bookedSeats;
    }
    public void setBookedTickets(ArrayList<Ticket> bookedTickets)
    {
        this.bookedTickets=bookedTickets;
    }
    public void setBooking(Booking booking)
    {
        this.booking = booking;
    }
    public void setShowTime(Showtime showtime)
    {
        this.showtime = showtime;
    }

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



    public void createBooking()
    {
        ArrayList<Booking> userBookings = new ArrayList<Booking>();
        updateShowtimeCSV();
        String username = "user1";
        String location = "MOBLIMA/data/bookings/" + username +"/";
        String filename = ".txt";
        int i = 1;
        String finalFile = location +i+ filename;
        File check = new File(finalFile);
        TransactionManager.getInstance().makeTransaction();
        Booking newB = new Booking(null,0,null,0,0,null,null,null);
        setBooking(newB);
        getBooking().setTicketList(TransactionManager.getInstance().getTList());
        getBooking().setCinemaID(getShowtime().getCinemaID());
        getBooking().setCineplexID(getShowtime().getCineplexID());
        getBooking().setMovie(getShowtime().getMovieTitle());
        getBooking().setShowTime(getShowtime().getDateTimeLDT());
        getBooking().setTotalPrice(TransactionManager.getInstance().getTotalPrice());
        getBooking().setTransaction(TransactionManager.getInstance().getTransaction());
        updateShowtimeCSV();
        while(check.exists())
        {
            i++;
            finalFile = location +i+ filename;
            check = new File(finalFile);
            getBooking().setbookingID("B" + i);
        }
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
        deleteBM();

        //try{
             // Will create parent directories if not exists
            //C:\Users\eisna\Desktop\SC2002_OOP-main\SC2002_OOP\MOBLIMA\src\managers
            //MOBLIMA\src\managers\test.java
            //System.out.println(file.getPath());    
            //System.out.println(file.getCanonicalPath());
            //if(file.createNewFile()){System.out.println("Yay");}else{System.out.println("Ded");}
            //FileOutputStream s = new FileOutputStream(file,false);
            //}catch(IOException ex){System.out.println("Super ded");}

    }
    

    public void deleteBM()
    {
        //private Scanner sc = new Scanner(System.in);
        //private int[][] seats;
        bookedSeats = null;
        bookedTickets = null;
        booking = null;
        showtime = null;
        TicketManager.newTM().deleteTicketM();
        TransactionManager.getInstance().deleteTransactionM();
    }


    /*public ArrayList<Booking> getUserBookings()
    {
        ArrayList<Booking> getUB = new ArrayList<>();
        String username = "user1";
        String location = "MOBLIMA/data/bookings/" + username +".csv";
        try
        {   
            // Reading the object from a file
            FileInputStream file = new FileInputStream(location);
            ObjectInputStream in = new ObjectInputStream(file);
              
            // Method for deserialization of object
            getUB = (ArrayList)in.readObject();
              
            in.close();
            file.close();
              
            System.out.println("Object has been deserialized ");
        }
          
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
          
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
        return getUB;
    }*/


}
