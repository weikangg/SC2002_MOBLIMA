package entities;
import entities.Types.*;


public class Ticket {
	private String date;
	private Boolean promotion;
	private TicketPrice tp;
	private TicketType tt;
	private MovieType mt;
	private int seat;
	
	//constructor
	public Ticket(String date, MovieType mt, TicketType tt, int seat)
	{
		this.date = date;
		this.mt = mt;
		this.tt = tt;
		this.seat = seat;
	}
	//get
	public String getDate()
	{
		return this.date;
	}
	public Boolean isPromo()
	{
		return this.promotion;
	}	
	public TicketType getTicketType()
	{
		return this.tt;
	}
	public TicketPrice getTicketPrice()
	{
		return this.tp;
	}
	public MovieType getMovieType()
	{
		return this.mt;
	}
	public int getSeat()
	{
		return this.seat;
	}
	//set
	public void setDate(String date)
	{
		this.date = date;
	}
	public void setPromo(Boolean promo)
	{
		this.promotion = promo;
	}
	public void setTicketType(TicketType tt)
	{
		this.tt = tt;
	}
	public void setTicketPrice(TicketPrice tp)
	{
		this.tp = tp;
	}
	public void setMovieType(MovieType mt)
	{
		this.mt = mt;
	}

}
