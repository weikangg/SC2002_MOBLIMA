package entities;
import java.io.Serializable;
import java.time.LocalDateTime;


public class Ticket implements Serializable{
	private LocalDateTime date;
	//private Boolean promotion;
	private double ticketPrice;
	private TicketType tt;
	private MovieType mt;
	private int row;
	private int col;
	
	//constructor
	public Ticket(LocalDateTime date, MovieType mt, TicketType tt, int row, int col, double ticketPrice)
	{
		this.date = date;
		this.mt = mt;
		this.tt = tt;
		this.row = row;
		this.col = col;
		this.ticketPrice = ticketPrice;
	}
	//get
	public LocalDateTime getDate()
	{
		return this.date;
	}
	/*public Boolean isPromo()
	{
		return this.promotion;
	}*/	
	public TicketType getTicketType()
	{
		return this.tt;
	}
	public double getTicketPrice()
	{
		return this.ticketPrice;
	}
	public MovieType getMovieType()
	{
		return this.mt;
	}
	public int getRow()
	{
		return this.row;
	}
	public int getCol()
	{
		return this.col;
	}
	//set
	public void setDate(LocalDateTime date)
	{
		this.date = date;
	}
	/*public void setPromo(Boolean promo)
	{
		this.promotion = promo;
	}*/
	public void setTicketType(TicketType tt)
	{
		this.tt = tt;
	}
	public void setTicketPrice(double tp)
	{
		this.ticketPrice = tp;
	}
	public void setMovieType(MovieType mt)
	{
		this.mt = mt;
	}

}
