package managers;
import java.util.*;
import entities.Transaction;
import utils.IOUtils;
import utils.deleteM;
import entities.Ticket;
import entities.Movie;
import entities.Showtime;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;


/**
 * Function class that provides the transaction and confirmation features for customers
 * @author Jovan Sie
 * @version 3.0
 * @since 06-11-2022
 */
public class TransactionManager implements deleteM
{
    /**
     * stores the trans information
     */
	private Transaction trans;
    /**
     * stores the ticket array from ticketmanager
     */
    private ArrayList<Ticket> tList;
    /**
     * stores the number of tickets
     */
    private int numberOfTickets;
    /**
     * stores the current showtime information
     */
    private Showtime showtime;
    /**
     * total price of the tickets
     */
    private double totalPrice;
    /**
     * checks if promo is applied
     */
    private Boolean isPromo = false;
    /**
     * stop loop upon exit
     */
	Boolean notQuit = true;
    /**
     * Scanner
     */
	private Scanner sc = new Scanner(System.in);
    /**
     * stores the user info as a string
     */
    private String userInfo;

    /**
	 * For singleton pattern adherence. This TicketManager instance persists throughout runtime.
	 */
    private static TransactionManager single_instance = null;
    
    /**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
    public static TransactionManager getInstance()
    {
        if (single_instance == null)
            single_instance = new TransactionManager();
        return single_instance;
    }

    
    //constructor
    private TransactionManager(){
    }

    
    /** 
     * Main function of transaction menu, displays the total price and asks the customer to either 
     * make payment or apply voucher discount, once the customer chooses to make payment, it will call makeBooking()
     * function to end all instances. The customers email and mobile phone number is captured, along with the customers real name
     * which the system will ask for here
     * @param showtime selected showtime
     * @param tickets ticket array from ticket manager
     */
    public void transactionMenu(Showtime showtime, ArrayList<Ticket> tickets)
    {
        setTList(tickets);
        setShowtime(showtime);
    	//printTransaction(index);
    	
    	int option = 0;
        notQuit = true;
        while(notQuit)
        {
                System.out.println( "===================  TRANSACTION MENU  =================\n" +
                                    " 1. Make Payment						    		     \n" +
                                    " 2. Apply Voucher                                       \n" +
                                    " 3. Back          	                                     \n" +
                                    "========================================================");
                System.out.println("Enter choice: ");
                option = IOUtils.check(1, 3, sc);       
        /*Need to add ticket*/
        
        
                switch (option) 
                {
                    case 1: //Make payment
                        setTotalPrice();
                        System.out.printf("Total payment will be $ %.2f \n", getTotalPrice());
                        enterUserInfo();
                        BookingManager.newBM().createBooking();
                        notQuit = false;
                        break;
                    case 2: //Apply vouchers
                        usePromo();
                        break;
                    case 3:
                        System.out.println("Back to Ticketing Page......");
                        notQuit=false;
                        //deleteTransactionM();
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1-3.");
                        break;
                }
        }
    }


    /**
     * Asks the customer to input their name and displays their username, email and mobile phone number and saves this information
     * as a string in the booking class
     */
    public void enterUserInfo()
    {        
        boolean checks = true;
        System.out.println("Hello customer \033[1m" + BookingManager.newBM().getUser().getUsername() + "\033[0m");
        System.out.println(
            "===================  Information  =================\n" +
            "Username: " + BookingManager.newBM().getUser().getUsername() + "\n" +
            "Email: " + BookingManager.newBM().getUser().getEmail() + "\n" +
            "Mobile Number: (+65) " +  BookingManager.newBM().getUser().getMobile() + "\n" +
            "Please enter your name to confirm booking: "
            );
        String name = sc.nextLine();
        while(checks)
        {
            for (int i = 0; i < name.length(); ++i)
            {
                char ch = name.charAt(i);
                if (Character.isDigit(ch)) {
                    checks = true;
                    System.out.println("Name: ");
                    name = sc.nextLine();
                    break;
                }
            }checks = false;
        }
        checks = true;
        //System.out.println("Paying...");
        String userInfo = "Email: " + BookingManager.newBM().getUser().getEmail() +
        "\nMobile Number: (+65)" +  BookingManager.newBM().getUser().getMobile() + "\nName: " +
        name;
        this.userInfo=userInfo;
    }

    /**
     * Creates the transaction class and adds all information into it
     */
    public void makeTransaction()
    {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String dateportion = now.format(format);
        int cID = getShowtime().getCinemaID();
        String cinemaId = String.format("%03d",cID) + dateportion;
        //MovieType mt = getShowtime().getMovieFormat();
        Transaction transaction = new Transaction(cinemaId, dateportion, getTList());
        System.out.println("Transaction ID: " + cinemaId);
        setTransaction(transaction);
        /*getTransaction().setID(cinemaId);
        getTransaction().setMovieName(moviename);
        getTransaction().setTranDateTime(dateportion);
        getTransaction().setTicketList(getTList());
        getTransaction().setTotalPrice(getTotalPrice());*/
    }

    /** 
     * gets the user information
     * @return current user information
     */
    public String getUserInfo()
    {
        return this.userInfo;
    }
    
    /** gets the array list of tickets
     * @return current array list of tickets
     */
    public ArrayList<Ticket> getTList()
    {
        return this.tList;
    }
    
    /** gets the showtime 
     * @return the current Showtime
     */
    public Showtime getShowtime()
    {
        return this.showtime;
    }   
    
    /** gets the total price
     * @return the total price in double
     */
    public double getTotalPrice()
    {
        return this.totalPrice;
    }
    
    /** 
     * gets the total number of tickets
     * @return integer value of current total tickets
     */
    public int getNumberOfTickets()
    {
        return this.numberOfTickets;
    }
    
    /** 
     * gets the isPromo value
     * @return the current isPromo value
     */
    public Boolean getPromo()
    {
        return this.isPromo;
    }


    
    /** sets the ticket array list with the ticketmanager's
     * @param tList new ticket array list
     */
    public void setTList(ArrayList<Ticket>tList)
    {
        this.tList = tList;
    }
    
    /** 
     * sets the total number of tickets
     * @param i new total number of tickets
     */
    public void setNumberOfTickets(int i)
    {
        this.numberOfTickets = i;
    }
    
    /** 
     * sets the current showtime
     * @param showtime new current showtime
     */
    public void setShowtime(Showtime showtime)
    {
        this.showtime = showtime;
    }
    /**
     * calculates the total price by adding each ticket's price together. Currently isPromo instantly grants a 10% discount
     */
    public void setTotalPrice()
    {
        double totalPrice = 0;
        for(int i = 0; i<getTList().size();i++)
        {
            totalPrice += getTList().get(i).getTicketPrice();
        }
        if(getPromo()==true)
        {
            System.out.println("Promotion of 10% has been applied");
            totalPrice = totalPrice * 0.90;
        }
        this.totalPrice = totalPrice;
    }
    /*
     * sets the isPromo to true;
     */
    public void usePromo()
    {
        System.out.println("Applying 10% discount!");
        this.isPromo = true;
    }

    /**
     * clears up all information stored in TransactionManager
     */
    public void deleteM()
    {
        trans = null;
        tList.clear();
        numberOfTickets = 0;
        showtime = null;
        totalPrice = 0;
        isPromo = false;
    }

    /**
     * updates the total sale for the selected movie and adds it to the movie csv
     */
    public void updateTotalSales()
    {
        List<Movie> newML = new ArrayList<>();
        for(Movie m : MovieListManager.getInstance().getMovieList())
        {
            //System.out.println("Movie Title = " + m.getMovieTitle());
            if(m.getMovieTitle().equals(getShowtime().getMovieTitle()))
            {
                //System.out.println("Movie Title is the same = " + m.getMovieID());
                m.setProfitEarned(m.getProfitEarned() + getTotalPrice());
                //MovieListManager.getInstance().updateMoveListPrice(m.getMovieID(), 10.5);
                //MovieListManager.updateMovieListCSV(MovieListManager.getInstance().getMovieList());
            }
            newML.add(m);
        }
        MovieListManager.getInstance().updateMovieListCSV(newML);
        //System.out.println("Price updated");
    }

    /**
     * gets the transaction
     * @return the current transaction
     */
    public Transaction getTransaction() {return this.trans;}
    
    
    /** 
     * sets the transaction
     * @param transaction new transaction
     */
    public void setTransaction(Transaction transaction)
    {
    	this.trans = transaction;
    }


}
