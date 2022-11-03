package managers;


import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.util.Scanner;
import java.util.ArrayList;
import entities.Showtime;
import entities.TicketPrice;
import entities.Ticket;
import entities.TicketType;

public class TicketManager {

    private Scanner sc = new Scanner(System.in);
    private static TicketManager tm = null;
    private ArrayList<Ticket> ticketList;
    private ArrayList<String> cS;
    private Showtime showtime;
    private TicketPrice tp = new TicketPrice();
	
	public static TicketManager newTM()
	{
	    if (tm == null) {
	    	 tm = new TicketManager();
	    }	        
	    return tm;
	}
	
	
	public void ticketMenu(Showtime showtime, ArrayList<String> confirmedSeats, int[][] plan)
	{
        Boolean noQuit = true;
        setShowtime(showtime);
        setCS(confirmedSeats);
        printTransaction();
        while(noQuit)
        {
            System.out.println(
                "====================Confirm Purchase of Tickets====================\n"
            );
            for(int i = 0; i<confirmedSeats.size();i++)
            {
                String rowcol = confirmedSeats.get(i);
                String[] tokens = rowcol.split("/");
                int row = Integer.parseInt(tokens[0]);
                int col = Integer.parseInt(tokens[1]);        
                System.out.println(
                "Ticket [" + i+1 + "]: Row- " + row + " Column- " + col + "."
                );
            }
    
            System.out.println(
                "Options [1,2,3]\n" +
                "1. Confirm \n" +
                "2. Remove ticket \n" +
                "3. Back"
            );
            int option = sc.nextInt();
            while(option < 0 || option > 3)
            {
                System.out.println("Enter a valid input from 1-3");
                option = sc.nextInt();
            }
            switch(option)
            {
                case 1:
                    //Confirm tickets -> Calculate tickets based on ticket type -> calculate total price of tickets -> call transactionMgr
                    confirmTicket();
                    TransactionManager.getInstance().transactionMenu(getShowtime(), getTicketArray());
                    break;
                case 2:
                    //Choose ticket to remove -> update arraylist -> check if empty
                    System.out.println("Enter ticket number to remove: ");
                    int ticketNo = sc.nextInt();
                    if(ticketNo>confirmedSeats.size())
                    {
                        System.out.println("Enter a valid ticket number");
                        ticketNo = sc.nextInt();
    
                    }
                    deleteTicket(ticketNo, confirmedSeats, plan);
                    break;
                case 3:
                    deleteTicketM();
                    noQuit = false;
                    break;
                default:
                    System.out.println("Please enter an integer value from 1-3.");
            }
        }
    }

    public void deleteTicket(int index, ArrayList<String> confirmedSeats, int[][] plan)
    {
        getCSArray().remove(index-1);
        String rowcol = getCS(index-1);
        String[] tokens = rowcol.split("/");
        int row = Integer.parseInt(tokens[0]);
        int col = Integer.parseInt(tokens[1]);
        plan[row][col]=1;
    }

    public double calcTicketPrice(TicketType tt)
    {
        double price = tp.getMappedPrice().get(tt);
        /*
        //check for holiday (uncomment once holiday implemented)
        LocalDateTime check = getShowtime().getDateTime();
        for(holidays : holidays)
        {
            if check == holidays;
            price = HOLIDAY PRICE;
        }
        */

        double modifier = tp.getMappedMovieTypePrice().get(getShowtime().getMovieType());
        price = price * modifier;
        return price;
    }

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
            System.out.println("Ticket ["+(i+1)+"]: Row-"+row+" Col-"+col);
            
        }
    }

    public void confirmTicket()
    {
        for(int i = 0; i<getCSArray().size(); i++)
        {
            String rowcol = getCS(i);
            String[] tokens = rowcol.split("/");
            int row = Integer.parseInt(tokens[0]);
            int col = Integer.parseInt(tokens[1]);
            TicketType newTT = getTicketType();
            Ticket newTicket = new Ticket(getShowtime().getDateTime(), getShowtime().getMovieType(),newTT,row,col,calcTicketPrice(newTT));
            addToTicketArray(newTicket);
        }

    }


    public void addToTicketArray(Ticket ticket)
    {
        getTicketArray().add(ticket);
    }

    public void setTicketArray(ArrayList<Ticket> ticketList)
    {
        this.ticketList = ticketList;
    }
    public void setCS(ArrayList<String> confirmedSeat)
    {
        this.cS = confirmedSeat;
    }
    public void setShowtime(Showtime showtime)
    {
        this.showtime = showtime;
    }


    public ArrayList<Ticket> getTicketArray()
    {
        return this.ticketList;
    }
    public ArrayList<String> getCSArray()
    {
        return this.cS;
    }
    public String getCS(int index)
    {
        return this.cS.get(index);
    }
    public Showtime getShowtime()
    {
        return this.showtime;
    }
    public TicketType getTicketType()
    {
        System.out.println(
            "Choose Ticket Type:" +
            "1. Normal" +
            "2. Student" +
            "3. Senior"
            );
        int tt = sc.nextInt();
        while(tt<0 || tt>3)
        {
            System.out.println("Enter valid option");
            tt = sc.nextInt();
        }
        LocalDateTime time = getShowtime().getDateTime();
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


    public void deleteTicketM()
    {
        ticketList.clear();
        cS.clear();
        showtime = null;
    }
}
