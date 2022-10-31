package entities;
import java.util.ArrayList;


public class Booking {
	private String TID;
	private double totalPrice;
	private Movie movie;
	private Cinema cinema;
	private ArrayList<Ticket> tt;
	private User user;
	//private Slot slot;
	
	/*public Booking(String tid, double totalPrice, Movie movie, Cinema cinema, ArrayList<Ticket> tt, User user, Slot slot)
	{
		this.TID = tid;
		this.totalPrice = totalPrice;
		this.movie = movie;
		this.cinema = cinema;
		this.tt = tt;
		this.user = user;
		//this.slot = slot;
	}
	*/
	
	
	public String getTID() {return this.TID;}
	public double getTotalPrice() {return this.totalPrice;}
	public Movie getMovie() {return this.movie;}
	public Cinema getCinema() {return this.cinema;}
	public ArrayList<Ticket> getTicketList() {return this.tt;}
	public Ticket getTicket(int index) {return this.tt.get(index);}
	public User getUser() {return this.user;}
	//public Slot getSlot() {return this.slot;}
	
	public void setTID(String TID){this.TID = TID;}
	public void setTotalPrice(double totalPrice){this.totalPrice = totalPrice;}
	public void setMovie(Movie movie){this.movie = movie;}
	public void setCinema(Cinema cinema){this.cinema = cinema;}
	public void setTicketList(ArrayList<Ticket> tt){this.tt = tt;}
	public void setTicket(int index, Ticket ticket) {this.tt.set(index, ticket);}
	public void setUser(User user){this.user = user;}
	//public void setSlot(Slot slot){this.slot = slot;}
	
	public void addTicket(Ticket tt)
	{
		this.tt.add(tt);
	}
	
	public void removeTicket(int index)
	{
		this.tt.remove(index);
	}
	
}
