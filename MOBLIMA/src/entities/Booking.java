package entities;
import java.io.Serializable;
import java.util.ArrayList;


public class Booking implements Serializable{
	private String bookingID;
	private double totalPrice;
	private String movieTitle;
	private Cinema cinema;
	private ArrayList<Ticket> tt;
	//private User user;
	private Showtime showtime;
	
	public Booking(String bookingID, double totalPrice, String movie, Cinema cinema, ArrayList<Ticket> tt, Showtime showtime, Transaction transaction)
	{
		this.bookingID = bookingID;
		this.totalPrice = totalPrice;
		this.movieTitle = movie;
		this.cinema = cinema;
		this.tt = tt;
		//this.user = user;
		this.showtime = showtime;
		this.transaction = transaction;
	}

	
	
	public String getbookingID() {return this.bookingID;}
	public double getTotalPrice() {return this.totalPrice;}
	public String getMovie() {return this.movieTitle;}
	public Cinema getCinema() {return this.cinema;}
	public ArrayList<Ticket> getTicketList() {return this.tt;}
	public Ticket getTicket(int index) {return this.tt.get(index);}
	//public User getUser() {return this.user;}
	public Showtime getShowtime() {return this.showtime;}
	
	public void setbookingID(String bookingID){this.bookingID = bookingID;}
	public void setTotalPrice(double totalPrice){this.totalPrice = totalPrice;}
	public void setMovie(String movie){this.movieTitle = movie;}
	public void setCinema(Cinema cinema){this.cinema = cinema;}
	public void setTicketList(ArrayList<Ticket> tt){this.tt = tt;}
	public void setTicket(int index, Ticket ticket) {this.tt.set(index, ticket);}
	//public void setUser(User user){this.user = user;}
	public void setShowTime(Showtime showtime){this.showtime = showtime;}
	
	public void addTicket(Ticket tt)
	{
		this.tt.add(tt);
	}
	
	public void removeTicket(int index)
	{
		this.tt.remove(index);
	}
	
}
