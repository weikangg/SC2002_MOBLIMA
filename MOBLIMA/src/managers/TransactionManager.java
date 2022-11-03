package managers;
import java.util.*;
import entities.Transaction;
import entities.Ticket;
import entities.Showtime;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;


public class TransactionManager
{
	private Transaction trans = null;
	private String creditCardInfo;
    private ArrayList<Ticket> tList;
    private int numberOfTickets;
    private Showtime showtime;
    private double totalPrice;
    private Boolean isPromo = false;
	
	private Scanner sc = new Scanner(System.in);

    private static TransactionManager single_instance = null;
    public static TransactionManager getInstance()
    {
        if (single_instance == null)
            single_instance = new TransactionManager();
        return single_instance;
    }
    
    public Transaction getTransaction() {return this.trans;}
    public String getString() {return this.creditCardInfo;}
    
    public void setTransaction(Transaction transaction)
    {
    	this.trans = transaction;
    }
    public void setCreditCardInfo(String cci)
    {
    	this.creditCardInfo = cci;
    }
    

    //constructor
    private TransactionManager(){
    }

    public void transactionMenu(Showtime showtime, ArrayList<Ticket> tickets)
    {
        setShowtime(showtime);
    	Boolean notQuit = true;
    	//printTransaction(index);
    	
    	int option = 0;
        while(notQuit)
        {
                System.out.println( "===================  TRANSACTION MENU  =================\n" +
                                    " 1. Make Payment						    		     \n" +
                                    " 2. Apply Voucher                                       \n" +
                                    " 3. Back          	                                     \n" +
                                    "========================================================");
                System.out.println("Enter choice: ");
                option = sc.nextInt();
                while(!(option >= 1 && option <=3))
                {
                    System.out.println("Please only enter a number from 1-3.");
                    option = sc.nextInt();
                }
        
        /*Need to add ticket*/
        
        
                switch (option) 
                {
                    case 1: //Make payment
                        double totalPrice = getTotalPrice();
                        System.out.println("Total payment will be $" + totalPrice);
                        BookingManager.newBM().createBooking();
                        break;
                    case 2: //Apply vouchers
                        break;
                    case 3:
                        System.out.println("Back to Ticketing Page......");
                        deleteTransactionM();
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1-3.");
                        break;
                }
        }
    }

    public void makeTransaction()
    {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String dateportion = now.format(format);
        int cID = getShowtime().getCinemaID();
        String cinemaId = String.format("%04d",cID);
        String moviename = getShowtime().getMovieTitle();
        //MovieType mt = getShowtime().getMovieFormat();
        getTransaction().setID(cinemaId);
        getTransaction().setMovieName(moviename);
        getTransaction().setTranDateTime(dateportion);
        getTransaction().setTicketList(getTList());
    }


    public ArrayList<Ticket> getTList()
    {
        return this.tList;
    }
    public Showtime getShowtime()
    {
        return this.showtime;
    }   
    public double getTotalPrice()
    {
        return this.totalPrice;
    }
    public int getNumberOfTickets()
    {
        return this.numberOfTickets;
    }
    public Boolean getPromo()
    {
        return this.isPromo;
    }


    public void setTList(ArrayList<Ticket>tList)
    {
        this.tList = tList;
    }
    public void setNumberOfTickets(int i)
    {
        this.numberOfTickets = i;
    }
    public void setShowtime(Showtime showtime)
    {
        this.showtime = showtime;
    }
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
    public void usePromo()
    {
        this.isPromo = true;
    }


    public void deleteTransactionM()
    {
        trans = null;
        creditCardInfo = null;
        tList.clear();
        numberOfTickets = 0;
        showtime = null;
        totalPrice = 0;
        isPromo = false;
    }
}