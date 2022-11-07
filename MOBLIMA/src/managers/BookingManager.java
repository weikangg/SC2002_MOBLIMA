package managers;

import java.util.Scanner;
//import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import entities.*;
import utils.IOUtils;

public class BookingManager implements Serializable{
	
	//show available seats
	//booking manager allows customer to pick a seat, if available, and book it.
	//allow user to select multiple seats, allow user to delete selected seats.
	
	private Scanner sc = new Scanner(System.in);
    private Boolean done = false;
    private Seat[][] seats;
	private ArrayList<String> bookedSeats;
    private ArrayList<Ticket> bookedTickets;
    private Booking booking;
    private Showtime showtime;
    private Account user;
    Boolean notQuit = true;
    
	
	private static BookingManager bm = null;

	
    /** 
     * Creates a BookingManager class if empty
     * @return BookingManager
     */
    public static BookingManager newBM()
	{
	    if (bm == null) {
	    	 bm = new BookingManager();
	    }	        
	    return bm;
	}

	


	
	
    /** 
     * main page for users to book their seats. Shows the available seats, and calls ticket manager once user confirms
     * their seat selections
     * @param showtime
     * @param user
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
            int choice = 0;
            choice = sc.nextInt();
            sc.nextLine();
            while(choice < 0 || choice >3)
            {
                System.out.println("Invalid input type. Please enter an integer value.");
                choice = sc.nextInt();
                sc.nextLine();
            }   
            switch(choice)
            {
                case 0:
                    notQuit = false;
                    deleteBM();
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
                            deleteBM();
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
     * @param row
     * @param col
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
        System.out.println("Please enter row number:");
		while (!sc.hasNextInt()) { // Not a string
			sc.next(); // Remove newline character
			System.out.println("Invalid input! Please enter a valid seat ID.");
        }
        row = sc.nextInt();
        sc.nextLine();

        System.out.println("Please enter column number:");
		while (!sc.hasNextInt()) { // Not a string
			sc.next(); // Remove newline character
			System.out.println("Invalid input! Please enter a valid seat ID.");
		}
        col = sc.nextInt();
        sc.nextLine();

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
        int seat = sc.nextInt();
        sc.nextLine();

        while(seat > size || seat < 1)
        {
            System.out.println("Please enter valid number");
            seat = sc.nextInt();
            sc.nextLine();
        }
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
     * @param showtime
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

        /*String answer = sc.nextLine();
        System.out.println(answer);

        while(true)
        {
            //System.out.println(answer);
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
                    updateSeats(row, col, 1);
                    return true;
                }
            }
            else
            {
                System.out.println("Please re enter choice (Yes/No)");
                answer = sc.nextLine();
            }

        }*/

    }       //getShowtime().setSeats(getSeats());


    
    /** 
     * Updates the state of the seats to allow showSeat function to display accurate information
     * @param row
     * @param col
     * @param choice
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
     * @return int[][]
     */
    public Seat[][] getSeats()
    {
        return this.seats;
    }
    
    /** 
     * return the list of booked seats
     * @return ArrayList<String>
     */
    public ArrayList<String> getBookedSeats()
    {
        return this.bookedSeats;
    }
    
    /** 
     * gets the list of tickets
     * @return ArrayList<Ticket>
     */
    public ArrayList<Ticket> getBookedTickets()
    {
        return this.bookedTickets;
    }
    
    /** 
     * gets the current booking
     * @return Booking
     */
    public Booking getBooking()
    {
        return this.booking;
    }
    
    /** 
     * 
     * @return Showtime
     */
    public Showtime getShowtime()
    {
        return this.showtime;
    }
    
    /** 
     * @return Account
     */
    public Account getUser()
    {
        return this.user;
    }




    
    /** 
     * @param seats
     */
    public void setSeats(Seat[][] seats)
    {
        this.seats = seats;
    }
    
    /** 
     * @param bookedSeats
     */
    public void setBookedSeats(ArrayList<String> bookedSeats)
    {
        this.bookedSeats = bookedSeats;
    }
    
    /** 
     * @param bookedTickets
     */
    public void setBookedTickets(ArrayList<Ticket> bookedTickets)
    {
        this.bookedTickets=bookedTickets;
    }
    
    /** 
     * @param booking
     */
    public void setBooking(Booking booking)
    {
        this.booking = booking;
    }
    
    /** 
     * @param showtime
     */
    public void setShowTime(Showtime showtime)
    {
        this.showtime = showtime;
    }
    
    /** 
     * @param user
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
     * @exception IOException,ClassNotFoundException
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
                System.out.println("IOException is caught");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                System.out.println("IOException is caught");
            }
            //System.out.println("Total Price of first booking is: " + userBookings.get(0).getTotalPrice());
            //System.out.println("Transaction Number: " + userBookings.get(0).getTransaction().getID());
            System.out.println("User info: " + userBookings.get(0).getUserInfo());
        }
        String userBooks = "B" + userBookings.size();
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
        TransactionManager.getInstance().deleteTransactionM();
        TicketManager.newTM().deleteTicketM();
        System.out.println("Booking and payment success!\n Please view under booking history");



        /*
        String path = System.getProperty("user.dir") +"\\data\\bookings\\";
        ArrayList<Booking> userBookings = new ArrayList<Booking>();
        String username = getUser().getUsername();
        String location = path + username +"/Booking";
        String filename = ".txt";
        int i = 1;
        String finalFile = location +i+ filename;
        File check = new File(finalFile);
        TransactionManager.getInstance().makeTransaction();
        Booking newB = new Booking("",TransactionManager.getInstance().getTotalPrice(),getShowtime().getMovieTitle(),getShowtime().getCinemaID(),getShowtime().getCineplexID(),TransactionManager.getInstance().getTList(),getShowtime().getDateTimeLDT(),TransactionManager.getInstance().getTransaction());
        setBooking(newB);*/
        /*getBooking().setTicketList(TransactionManager.getInstance().getTList());
        getBooking().setCinemaID(getShowtime().getCinemaID());
        getBooking().setCineplexID(getShowtime().getCineplexID());
        getBooking().setMovie(getShowtime().getMovieTitle());
        getBooking().setShowTime(getShowtime().getDateTimeLDT());
        getBooking().setTotalPrice(TransactionManager.getInstance().getTotalPrice());
        getBooking().setTransaction(TransactionManager.getInstance().getTransaction());*/
        /*updateShowtimeCSV();
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


        ArrayList<Booking> pDetails = new ArrayList<>();
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			pDetails = (ArrayList) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		// print out the size
		//System.out.println(" Details Size: " + pDetails.size());
		//System.out.println();
		return pDetails;*/

        //deleteBM();
        

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
    

    /**
     * set to true if booking was done
     */
    public void setDone()
    {
        this.done = true;
    }
    
    /** 
     * checks if booking was done
     * @return boolean
     */
    public boolean getDone()
    {
        return this.done;
    }

    /**
     * deletes the BookingManager instance
     */
    public void deleteBM()
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


    /*public void showSeat()
    {

        System.out.println("      Screen");

        for (int i = 0; i < 5; i++){

            System.out.print((char)(i+65)+"  ");

            for(int j = 0; j < 10; j++){

                if(j == 5) System.out.print("  ");

                if(getSeats()[i][j] == 0) System.out.print("\u001B[32m" + "O" + "\u001B[0m");
                else if(getSeats()[i][j] == 1) System.out.print("\u001B[31m" + "X" + "\u001B[0m");
                else if(getSeats()[i][j] == 2) System.out.print("\u001B[34m" + "S" + "\u001B[0m");

            }
            System.out.println("  "+(char)(i+65));
        }
    }*/
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
    }
}
