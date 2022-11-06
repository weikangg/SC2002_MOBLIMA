package entities;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Booking implements Serializable{
	private String bookingID;
	private double totalPrice;
	private String movieTitle;
	private int cineplexID;
	private int cinemaID;
	private ArrayList<Ticket> tt;
	//private User user;
	private LocalDateTime showtime;
	private Transaction transaction;
	
	public Booking(String bookingID, double totalPrice, String movie, int cinema, int cineplexID, ArrayList<Ticket> tt, /*User user, */LocalDateTime showtime, Transaction transaction)
	{
		this.bookingID = bookingID;
		this.totalPrice = totalPrice;
		this.movieTitle = movie;
		this.cinemaID = cinema;
		this.cineplexID = cineplexID;
		this.tt = tt;
		//this.user = user;
		this.showtime = showtime;
		this.transaction = transaction;
	}

	
	
	public String getbookingID() {return this.bookingID;}
	public double getTotalPrice() {return this.totalPrice;}
	public String getMovie() {return this.movieTitle;}
	public int getCinemaID() {return this.cinemaID;}
	public int getCineplexID() {return this.cineplexID;}
	public ArrayList<Ticket> getTicketList() {return this.tt;}
	public Ticket getTicket(int index) {return this.tt.get(index);}
	//public User getUser() {return this.user;}
	public LocalDateTime getShowtime() {return this.showtime;}
	public Transaction getTransaction() {return this.transaction;}
	
	public void setbookingID(String bookingID){this.bookingID = bookingID;}
	public void setTotalPrice(double totalPrice){this.totalPrice = totalPrice;}
	public void setMovie(String movie){this.movieTitle = movie;}
	public void setCinemaID(int cinema){this.cinemaID = cinema;}
	public void setCineplexID(int cineplex){this.cineplexID = cineplex;}
	public void setTicketList(ArrayList<Ticket> tt){this.tt = tt;}
	public void setTicket(int index, Ticket ticket) {this.tt.set(index, ticket);}
	//public void setUser(User user){this.user = user;}
	public void setShowTime(LocalDateTime showtime){this.showtime = showtime;}
	public void setTransaction(Transaction transaction){this.transaction = transaction;}
	
	public void addTicket(Ticket tt)
	{
		this.tt.add(tt);
	}
	
	public void removeTicket(int index)
	{
		this.tt.remove(index);
	}
	
}
