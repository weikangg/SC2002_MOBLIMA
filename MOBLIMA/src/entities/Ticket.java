package entities;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents movie tickets 
 * @author Jovan Sie
 * @version 3.0
 * @since 06-11-2022
 */
public class Ticket implements Serializable{
	/**
	 * local date time of the ticket
	 */
	private LocalDateTime date;
	/**
	 * ticket price
	 */
	private double ticketPrice;
	/**
	 * ticket type
	 */
	private TicketType tt;
	/**
	 * movie type
	 */
	private MovieType mt;
	/**
	 * cinema class
	 */
	private CinemaClass cc;
	/**
	 * seat type
	 */
	private SeatType st;
	/**
	 * row of the seat
	 */
	private int row;
	/**
	 * column of the seat
	 */
	private int col;
	
	//constructor
	/**
	 * 
	 * @param date date of the ticket booking
	 * @param mt type of movie, affects the price of ticket
	 * @param tt ticket type, affects the price of the ticket
	 * @param cc cinema class, affects the price of the ticket
	 * @param st seat type, affects the price of the ticket
	 * @param row seat row
	 * @param col seat col
	 * @param ticketPrice price of the ticket
	 */
	public Ticket(LocalDateTime date, MovieType mt, TicketType tt, CinemaClass cc, SeatType st, int row, int col, double ticketPrice)
	{
		this.date = date;
		this.mt = mt;
		this.tt = tt;
		this.cc = cc;
		this.st = st;
		this.row = row;
		this.col = col;
		this.ticketPrice = ticketPrice;
	}
	
	/** 
	 * Gets the date and time of the ticket
	 * @return LocalDateTime
	 */
	public LocalDateTime getDate()
	{
		return this.date;
	}
	
	/** 
	 * Gets the ticket's type
	 * @return TicketType
	 */
	public TicketType getTicketType()
	{
		return this.tt;
	}
	
	/** 
	 * gets the ticket's price
	 * @return double
	 */
	public double getTicketPrice()
	{
		return this.ticketPrice;
	}
	
	/** 
	 * gets the movie of the ticket's type
	 * @return MovieType
	 */
	public MovieType getMovieType()
	{
		return this.mt;
	}

	/** 
	 * gets the cinema class of the ticket
	 * @return CinemaClass
	 */
	public CinemaClass getCinemaClass()
	{
		return this.cc;
	}

	/** 
	 * gets the seat type of the ticket
	 * @return SeatType
	 */
	public SeatType getSeatType()
	{
		return this.st;
	}
	
	/** 
	 * gets the row number for the seat of the ticket
	 * @return int
	 */
	public int getRow()
	{
		return this.row;
	}
	
	/** 
	 * gets the column number for the seat of the ticket
	 * @return int
	 */
	public int getCol()
	{
		return this.col;
	}
	
	/** 
	 * sets the date for the ticket
	 * @param date date of the showtime
	 */
	public void setDate(LocalDateTime date)
	{
		this.date = date;
	}

	/**
	 * sets the ticket's type 
	 * @param tt new ticket type
	 */
	public void setTicketType(TicketType tt)
	{
		this.tt = tt;
	}
	
	/** 
	 * sets the ticket's price
	 * @param tp new price
	 */
	public void setTicketPrice(double tp)
	{
		this.ticketPrice = tp;
	}
	
	/** 
	 * set the movie of the ticket's type
	 * @param mt new movie type
	 */
	public void setMovieType(MovieType mt)
	{
		this.mt = mt;
	}

	/** 
	 * set the cinema class of the ticket
	 * @param cc new cinema class
	 */
	public void setCinemaClass(CinemaClass cc)
	{
		this.cc = cc;
	}

	/** 
	 * set the seat type of the ticket
	 * @param st new seat type
	 */
	public void setSeatType(SeatType st)
	{
		this.st = st;
	}

}
