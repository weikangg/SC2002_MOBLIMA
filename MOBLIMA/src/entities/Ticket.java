package entities;
import java.io.Serializable;
import java.time.LocalDateTime;


public class Ticket implements Serializable{
	private LocalDateTime date;
	//private Boolean promotion;
	private double ticketPrice;
	private TicketType tt;
	private MovieType mt;
	private CinemaClass cc;
	private SeatType st;
	private int row;
	private int col;
	
	//constructor
	/**
	 * 
	 * @param date
	 * @param mt
	 * @param tt
	 * @param row
	 * @param col
	 * @param ticketPrice
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
	//get
	public LocalDateTime getDate()
	{
		return this.date;
	}
	
	/** 
	 * Gets the ticket's type
	 * @return TicketType
	 */
	/*public Boolean isPromo()
	{
		return this.promotion;
	}*/	
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
	 * @param date
	 */
	//set
	public void setDate(LocalDateTime date)
	{
		this.date = date;
	}
	/*public void setPromo(Boolean promo)
	{
		this.promotion = promo;
	}*/
	/**
	 * sets the ticket's type 
	 * @param tt
	 */
	public void setTicketType(TicketType tt)
	{
		this.tt = tt;
	}
	
	/** 
	 * sets the ticket's price
	 * @param tp
	 */
	public void setTicketPrice(double tp)
	{
		this.ticketPrice = tp;
	}
	
	/** 
	 * set the movie of the ticket's type
	 * @param mt
	 */
	public void setMovieType(MovieType mt)
	{
		this.mt = mt;
	}

	/** 
	 * set the cinema class of the ticket
	 * @param cc
	 */
	public void setCinemaClass(CinemaClass cc)
	{
		this.cc = cc;
	}

	/** 
	 * set the seat type of the ticket
	 * @param st
	 */
	public void setSeatType(SeatType st)
	{
		this.st = st;
	}

}
