package managers;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entities.Transaction;
import view.customerApp;
import entities.Ticket;
import entities.Movie;
import entities.Showtime;

import java.time.LocalDate;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;


public class TransactionManager
{
	private Transaction trans;
	private String creditCardInfo;
    private ArrayList<Ticket> tList;
    private int numberOfTickets;
    private Showtime showtime;
    private double totalPrice;
    private Boolean isPromo = false;
	Boolean notQuit = true;
	private Scanner sc = new Scanner(System.in);
    String userInfo;

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
                        setTotalPrice();
                        System.out.println("Total payment will be $" + getTotalPrice());
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

    public void enterUserInfo()
    {
        boolean checks = true;
        System.out.println("Hello Customer " + BookingManager.newBM().getUser().getUsername());
        System.out.println(
            "===================  Enter Information  =================\n" +
            "Name (NRIC): "
            );
        String name = sc.nextLine();
        while(checks)
        {
            for (int i = 0; i < name.length(); ++i)
            {
                char ch = name.charAt(i);
                if (Character.isDigit(ch)) {
                    checks = true;
                    System.out.println("Name (NRIC): ");
                    name = sc.nextLine();
                    break;
                }
            }checks = false;
        }
        checks = true;
        System.out.println("Mobile Number: +65");
        String mobileNo = "+65 " + sc.nextLine();
        //sc.nextLine();
        while(checks)
        {
            Pattern singaporeRegex = Pattern.compile("^(\\+\\d{2}( )?)?\\d{4}[- .]?\\d{4}$");
            Matcher m = singaporeRegex.matcher(mobileNo);
            if(m.matches())
            {
                checks = false;
            }
            else
            {
                System.out.println("Mobile Number: +65");
                mobileNo = "+65 " + sc.nextLine();
            }
        }
        System.out.println("Email: ");
        String email = sc.nextLine();
        checks = true;
        while(checks)
        {
            Pattern regex = Pattern.compile("^(.+)@(.+)$");
            Matcher mTwo = regex.matcher(email);
            if(mTwo.matches())
            {
                checks = false;
            }
            else
            {
                System.out.println("Email: ");
                email = sc.nextLine();
            }
        }
        this.userInfo = "Name: " + name + "Mobile Number: " + mobileNo + "Email: " + email + "\n"; 
        
    }
    public String getUserInfo()
    {
        return this.userInfo;
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
        Transaction transaction = new Transaction(cinemaId, moviename, dateportion, getTList());
        setTransaction(transaction);
        /*getTransaction().setID(cinemaId);
        getTransaction().setMovieName(moviename);
        getTransaction().setTranDateTime(dateportion);
        getTransaction().setTicketList(getTList());
        getTransaction().setTotalPrice(getTotalPrice());*/
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
        MovieListManager.updateMovieListCSV(newML);
        //System.out.println("Price updated");
    }


}
