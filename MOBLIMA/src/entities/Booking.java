package entities;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents bookings made by Customers
 * Customer can have multiple bookings
 * @author	Jovan Sie
 * @version 1.0
 * @since 	2022-10-23
 */
public class Booking implements Serializable{
	/**
	 * Booking Identifier
	 */
	private String bookingID;
	/**
	 * The information of each user info class
	 */
	private String userInfo;
	/**
	 * Total price of each booking
	 */
	private double totalPrice;
	/**
	 * Title of the movie that Customer is booking
	 */
	private String movieTitle;
	/**
	 * Cineplex Identifier
	 */
	private int cineplexID;
	/**
	 * Cinema Identifier
	 */
	private int cinemaID;
	/**
	 * List of tickets in each booking
	 */
	private ArrayList<Ticket> tt;
	//private User user;
	/**
	 * The date and time of the specific showtime slot
	 */
	private LocalDateTime showtime;
	/**
	 * The information of each transaction class
	 */
	private Transaction transaction;
	

	/**
	 * Constructer class for Booking
	 * Creates new Booking with necessary information
	 * @param bookingID booking ID of the current booking
	 * @param totalPrice total price of the tickets
	 * @param movie title of the movie
	 * @param cinema cinema number
	 * @param cineplexID cineplex id
	 * @param tt ticket array
	 * @param showtime date and time of showtime
	 * @param transaction transaction information
	 */
	public Booking(String bookingID, String userInfo, double totalPrice, String movie, int cinema, int cineplexID, ArrayList<Ticket> tt, LocalDateTime showtime, Transaction transaction)
	{
		this.bookingID = bookingID;
		this.userInfo = userInfo;
		this.totalPrice = totalPrice;
		this.movieTitle = movie;
		this.cinemaID = cinema;
		this.cineplexID = cineplexID;
		this.tt = tt;
		this.showtime = showtime;
		this.transaction = transaction;
	}

	
	/**
	 * Gets the booking ID
	 * @return this booking's ID
	 */
	public String getbookingID() {return this.bookingID;}
	/**
	 * Gets the total price of booking
	 * @return this booking's price
	 */
	public double getTotalPrice() {return this.totalPrice;}
	/**
	 * Gets the movie title
	 * @return this booking's movie title
	 */
	public String getMovie() {return this.movieTitle;}
	/**
	 * Gets the cinema identifier
	 * @return this booking's cinema identifier
	 */
	public int getCinemaID() {return this.cinemaID;}
	/**
	 * Gets the cineplex identifier
	 * @return this booking's cineplex identifier
	 */
	public int getCineplexID() {return this.cineplexID;}
	/**
	 * get the list of tickets purchased
	 * @return this booking's tickets
	 */
	public ArrayList<Ticket> getTicketList() {return this.tt;}
	/**
	 * gets the specific tickets details
	 * @param index index of the specific ticket
	 * @return one of the booking's ticket
	 */
	public Ticket getTicket(int index) {return this.tt.get(index);}
	//public User getUser() {return this.user;}
	/**
	 * get the date and time of the showtime
	 * @return the showtime for the booking
	 */
	public LocalDateTime getShowtime() {return this.showtime;}
	/**
	 * gets the transaction details
	 * @return this booking's transaction details
	 */
	public Transaction getTransaction() {return this.transaction;}
	/**
	 * gets the userinfo details
	 * @return this booking's userinfo details
	 */
	public String getUserInfo() {return this.userInfo;}
	

	/**
	 * change the booking id of this booking
	 * @param bookingID the string of the booking id. B1, B2
	 */
	public void setbookingID(String bookingID){this.bookingID = bookingID;}
	/**
	 * change the total price of this booking
	 * @param totalPrice the total price of the booking
	 */
	public void setTotalPrice(double totalPrice){this.totalPrice = totalPrice;}
	/**
	 * change the movie title of this booking
	 * @param movie the name of the movie 
	 */
	public void setMovie(String movie){this.movieTitle = movie;}
	/**
	 * change the cinema identifier of this booking
	 * @param cinema cinema id 
	 */
	public void setCinemaID(int cinema){this.cinemaID = cinema;}
	/**
	 * change the cineplex identifer of this booking
	 * @param cineplex cineplex id
	 */
	public void setCineplexID(int cineplex){this.cineplexID = cineplex;}
	/**
	 * change the list of tickets for this booking
	 * @param tt list of tickets from transaction
	 */
	public void setTicketList(ArrayList<Ticket> tt){this.tt = tt;}
	/**
	 * change a specific ticket for this booking
	 * @param index index of the specific ticket
	 * @param ticket ticket information
	 */
	public void setTicket(int index, Ticket ticket) {this.tt.set(index, ticket);}
	//public void setUser(User user){this.user = user;}
	/**
	 * change the showtime for this booking
	 * @param showtime date time of selected showtime
	 */
	public void setShowTime(LocalDateTime showtime){this.showtime = showtime;}
	/**
	 * change the transaction details for this booking
	 * @param transaction current transaction class storing information
	 */
	public void setTransaction(Transaction transaction){this.transaction = transaction;}

	/**
	 * change the user info for this booking
	 * @param userInfo string of user's email, mobile number and name
	 */
	public void setUserInfo(String userInfo){this.userInfo = userInfo;}

	/**
	 * Add a ticket to this booking's list of tickets
	 * @param tt adds this ticket to the array list
	 */
	public void addTicket(Ticket tt)
	{
		this.tt.add(tt);
	}
	
	/**
	 * remove a ticket from this booking's list of tickets
	 * @param index index of ticket to remove
	 */
	public void removeTicket(int index)
	{
		this.tt.remove(index);
	}
	
}
