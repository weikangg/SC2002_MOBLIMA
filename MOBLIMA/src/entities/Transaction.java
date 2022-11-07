package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Transaction implements Serializable{
    private String id;
    //private String movieName;
    private String tranDateTime;
    private ArrayList<Ticket> tix;
    private double totalPrice = 0;

    /**
     * Constructor
     * @param id
     * @param tranDateTime
     * @param tix
     */
    public Transaction(String id, String tranDateTime, ArrayList<Ticket> tix)
    {
        this.id = id;
        this.tranDateTime = tranDateTime;
        this.tix = tix;
    }

    
    /** 
     * get the transaction id
     * @return String
     */
    public String getID(){
        return this.id;
    }
    
    /** 
     * gets the movie name of the transaction
     * @return String
     */
    /*public String getMovieName(){
        return this.movieName;
    }*/
    
    
    /** 
     * gets the transaction's date and time
     * @return String
     */
    public String getTranDateTime(){
        return this.tranDateTime;
    }
    
    /** 
     * gets the list of tickets purchased
     * @return ArrayList<Ticket>
     */
    public ArrayList<Ticket> getTicketList(){
        return this.tix;
    }
    
    
    /** 
     * gets one of the tickets in the transaction
     * @param index
     * @return Ticket
     */
    public Ticket getTicket(int index){
    	return this.tix.get(index);
    }

    
    /** 
     * gets the total price of the transaction
     * @return double
     */
    public double getTotalPrice()
    {
        return this.totalPrice;
    }
    
    /*
     * setters
     */
    /**
     * Set the transaction id
     * @param id
     */
    public void setID(String id) {this.id = id;}
    /**
     * sets the movie name of the transaction
     * @param movname
     */
    //public void setMovieName(String movname) {this.movieName= movname;}
    /**
     * sets the date and time of the transaction
     * @param tdt
     */
    public void setTranDateTime(String tdt) {this.tranDateTime=tdt;}
    /**
     * sets the list of tickets of the transaction
     * @param tix
     */
    public void setTicketList(ArrayList<Ticket> tix) {this.tix = tix;}
    /**
     * sets the ticket of the transaction
     * @param index
     * @param ticket
     */
    public void setTicket(int index, Ticket ticket) {this.tix.set(index, ticket);}
    /**
     * sets the total price of the transaction
     * @param price
     */
    public void setTotalPrice(double price){this.totalPrice = price;}
 
}

    public void printTransaction() {
    	System.out.println("ID: " +this.getID());
		//System.out.println("Movie Title: " +this.getMovieName());
		System.out.println("Date & Time: " + this.getTranDateTime());

        //print out the information for each ticket
        for(int i = 0; i < tix.size(); i++)
        {
            System.out.print("Ticket " + (i+1) + ":");
            System.out.println("    " + this.getTicket(i).getDate());
            System.out.println("    " + this.getTicket(i).getTicketPrice());
        }

    }
