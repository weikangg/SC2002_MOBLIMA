package managers;


import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import entities.Showtime;
import entities.TicketPrice;
import entities.Ticket;
import entities.Holidays;
import entities.Seat;
import entities.TicketType;
import utils.IOUtils;
import utils.deleteM;

/**
 * Function class that provides the ticketing and calculation features for customers
 * @author Jovan Sie
 * @version 3.0
 * @since 06-11-2022
 */
public class TicketManager implements deleteM{

    /**
     * Scanner class
     */
    private Scanner sc = new Scanner(System.in);
    /**
	 * For singleton pattern adherence. This TicketManager instance persists throughout runtime.
	 */
    private static TicketManager tm = null;
    /**
     * Stores the list of tickets to be provided for transactionmanager and bookingmanager
     */
    private ArrayList<Ticket> ticketList;
    /**
     * stores the booked seats as confirmed seats in string
     */
    private ArrayList<String> cS;
    /**
     * stores the current showtime value
     */
    private Showtime showtime;
    /**
     * initialises the ticket prices stored in csv files
     */
    private TicketPrice tp = new TicketPrice();
    /**
     * stores the holiday list locally
     */
    private ArrayList<LocalDate> hlList;
    /**
     * gets the list of holidays in csv
     */
    private List<Holidays> holiDates;
    /**
     * for loop break upon exit
     */
    Boolean noQuit = true;
	

    	
    /**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
    public static TicketManager newTM()
	{
	    if (tm == null) {
	    	 tm = new TicketManager();
	    }	        
	    return tm;
	}
	
	
	
    /** 
     * Main function in TicketManager, asks the customer to check if they have selected the right tickets and give the customer the
     * ability to remove tickets if they do not want to purchase them. If the customer confirms the tickets, the calculate function
     * will be called and the user will be brought to the transaction manager page.
     * @param showtime
     * @param confirmedSeats
     * @param plan
     */
    public void ticketMenu(Showtime showtime, ArrayList<String> confirmedSeats, Seat[][] plan)
	{
        //System.out.println("Inside Ticket Menu");
        setH(HolidayListManager.getInstance().getHolidayList());
        ArrayList<Ticket> ticketArray = new ArrayList<>();
        setTicketArray(ticketArray);
        ArrayList<LocalDate> holidayLD = new ArrayList<LocalDate>();
        setHList(holidayLD);
        setHolidayDates();
        setShowtime(showtime);
        setCS(confirmedSeats);
        //printTransaction();
        noQuit = true;
        while(noQuit)
        {
            if(getCSArray().size()==0)
                return;
            System.out.println(
                "====================Confirm Purchase of Tickets====================\n"
            );
            for(int i = 0; i<getCSArray().size();i++)
            {
                String rowcol = confirmedSeats.get(i);
                String[] tokens = rowcol.split("/");
                int row = Integer.parseInt(tokens[0]);
                int col = Integer.parseInt(tokens[1]);        
                System.out.println(
                "Ticket [" + (i+1) + "]: Seat " + (char)(row+65) + "" + col
                );
            }
    
            System.out.println(
                "Options [1,2,3]\n" +
                "1. Confirm \n" +
                "2. Remove ticket \n" +
                "3. Back"
            );
            int option = IOUtils.check(1, 3, sc);
            switch(option)
            {
                case 1:
                    //Confirm tickets -> Calculate tickets based on ticket type -> calculate total price of tickets -> call transactionMgr
                    confirmTicket();
                    TransactionManager.getInstance().transactionMenu(getShowtime(), getTicketArray());
                    //deleteTicketM();
                    //noQuit = false;
                    break;
                case 2:
                    //Choose ticket to remove -> update arraylist -> check if empty
                    System.out.println("Enter ticket number to remove: ");
                    printTransaction();
                    int ticketNo = IOUtils.check(1, getCSArray().size(), sc);
                    deleteTicket(ticketNo, plan);
                    break;
                case 3:
                    noQuit = false;
                    //deleteTicketM();
                    break;
                default:
                    System.out.println("Please enter an integer value from 1-3.");
                    break;
            }
        }
    }

    
    /** 
     * Deletes the ticket of the customers choice 
     * @param index
     * @param plan
     */
    public void deleteTicket(int index, Seat[][] plan)
    {
        String rowcol = getCS(index-1);
        String[] tokens = rowcol.split("/");
        int row = Integer.parseInt(tokens[0]);
        int col = Integer.parseInt(tokens[1]);
        plan[row][col].setState(0);
        getCSArray().remove(index-1);
    }

    
    /** 
     * Calculate the price of a ticket, using the formula of Ticket Type's Price * Cinema Class's Price * Movie Type's Price * Seat Type's Price
     * @param tt
     * @param row
     * @param col
     * @return double
     */
    public double calcTicketPrice(TicketType tt, int row, int col)
    {
        double price = tp.getMappedPrice().get(tt);
        double cc = tp.getMappedCinemaClassPrice().get(getShowtime().getCinemaClassCC());
        double modifier = tp.getMappedMovieTypePrice().get(getShowtime().getMovieType());
        double sc = tp.getMappedSeatTypePrice().get(getShowtime().getSeatS()[row][col].getSeatType());
        price = price * modifier * cc * sc;
        System.out.println(
            "[" + tt + "] " +
            "[" +  getShowtime().getCinemaClassCC() + "] " +
            "[" + getShowtime().getMovieType() + "] " +
            "[" + getShowtime().getSeatS()[row][col].getSeatType() + "]");
        System.out.printf("Price is $%.2f \n",price);
        return price;
    }


    /**
     * displays the tickets
     */
    public void printTransaction()
    {
        int row, col;
        System.out.println(
            "Current Tickets: \n"
        );
        for(int i = 0; i <getCSArray().size();i++)
        {
            String rowcol = getCS(i);
            String[] tokens = rowcol.split("/");
            row = Integer.parseInt(tokens[0]);
            col = Integer.parseInt(tokens[1]);
            System.out.println(
                "Ticket [" + (i+1) + "]: Seat " + (char)(row+65) + "" + col
                );
            
        }
    }


    /**
     * Creates the ticket class for each confirmed seat
     */

    public void confirmTicket()
    {
        for(int i = 0; i<getCSArray().size(); i++)
        {
            System.out.println("Ticket [" + (i+1) + "] is: ");
            String rowcol = getCS(i);
            String[] tokens = rowcol.split("/");
            int row = Integer.parseInt(tokens[0]);
            int col = Integer.parseInt(tokens[1]);
            TicketType newTT = getTicketType();
            Ticket newTicket = new Ticket(getShowtime().getDateTimeLDT(), getShowtime().getMovieType(),
            newTT,getShowtime().getCinemaClassCC(),getShowtime().getSeatS()[row][col].getSeatType(),
            row,col,calcTicketPrice(newTT, row, col));
            addToTicketArray(newTicket);
        }

    }


    
    /** 
     * Add the created ticket to the ticket array
     * @param ticket
     */
    public void addToTicketArray(Ticket ticket)
    {
        getTicketArray().add(ticket);
    }

    
    /** 
     * sets the current ticket list
     * @param ticketList
     */
    public void setTicketArray(ArrayList<Ticket> ticketList)
    {
        this.ticketList = ticketList;
    }
    
    /** 
     * sets the bookedseats from bookingmanager into confirmed seats
     * @param confirmedSeat
     */
    public void setCS(ArrayList<String> confirmedSeat)
    {
        this.cS = confirmedSeat;
    }
    
    /** 
     * sets the current showtime
     * @param showtime
     */
    public void setShowtime(Showtime showtime)
    {
        this.showtime = showtime;
    }


    
    /** 
     * gets the list of ticket
     * @return current ArrayList<Ticket>
     */
    public ArrayList<Ticket> getTicketArray()
    {
        return this.ticketList;
    }
    
    /** 
     * gets the list of confirmed seats
     * @return current ArrayList<String>
     */
    public ArrayList<String> getCSArray()
    {
        return this.cS;
    }
    
    /** 
     * gets a confirmed seat
     * @param index
     * @return current string of confirmed seat
     */
    public String getCS(int index)
    {
        return this.cS.get(index);
    }
    
    /** 
     * gets the showtime
     * @return current Showtime
     */
    public Showtime getShowtime()
    {
        return this.showtime;
    }
    
    /** 
     * Gets the type of ticket the customer would like to purchase for, returns Holiday if the 
     * selected showtime is on a holiday
     * @return TicketType
     */
    public TicketType getTicketType()
    {
        if(isHoliday())
        {
            return TicketType.HOLIDAY;
        }
        System.out.println(
            "Choose Ticket Type:\n" +
            "1. Normal\n" +
            "2. Student\n" +
            "3. Senior"
            );
        int tt = IOUtils.check(1, 3, sc);
        LocalDateTime time = getShowtime().getDateTimeLDT();
        DayOfWeek day = DayOfWeek.of(time.get(ChronoField.DAY_OF_WEEK));
        if(day == DayOfWeek.SUNDAY || day==DayOfWeek.SATURDAY)
        {
            System.out.println("Weekend prices are not eligible for discounts");
            return TicketType.WEEKEND;
        }
        else
        {
            if(tt == 1)
            {
                if(day == DayOfWeek.MONDAY || day==DayOfWeek.TUESDAY || day == DayOfWeek.WEDNESDAY)
                {
                    return TicketType.MTW;
                }
                else if(day == DayOfWeek.THURSDAY)
                {
                    return TicketType.THURS;
                }
                else
                {
                    if(time.getHour() < 18)
                    {
                        return TicketType.FRIBS;
                    }
                    else
                    {
                        return TicketType.FRIAS;
                    }
                }
            }
            else if(tt == 2)
            {
                System.out.println("Please present student identification upon entry to cinema!");
                return TicketType.STUDENTWD;
            }
            else
            {
                return TicketType.SENIORWD;
            }
        }
    }

    /**
     * Deletes the ticket manager instance
     */
    public void deleteM()
    {
        ticketList.clear();
        cS.clear();
        showtime = null;
    }

    
    /** 
     * sets the local holiday list
     * @param ArrayListHList
     */
    public void setHList(ArrayList<LocalDate>HList)
    {
        this.hlList = HList;
    }

    
    /** 
     * gets the local holiday list
     * @return ArrayList<LocalDate>
     */
    public ArrayList<LocalDate> getHList()
    {
        return this.hlList;
    }


    
    /** 
     * set the holiday list from csv
     * @param ListHList
     */
    public void setH(List<Holidays>HList)
    {
        this.holiDates = HList;
    }

    
    /** 
     * get the holidates
     * @return List<Holidays>
     */
    public List<Holidays> getH()
    {
        return this.holiDates;
    }

    /**
     * Add csv values from holidates to local list HList
     */
    public void setHolidayDates()
    {
        for(Holidays h : getH())
        {
            getHList().add(h.getHolidayDate());
        }
    }

    
    /** 
     * Checks if showtime is a holiday
     * @return Boolean
     */
    public Boolean isHoliday()
    {
        LocalDate lDate = getShowtime().getDateTimeLDT().toLocalDate();
        //System.out.println(dtf.format(now)); 
        for(LocalDate ld : getHList())
        {
            if(lDate.isEqual(ld))
                return true;
        }
        return false;
    }
}
