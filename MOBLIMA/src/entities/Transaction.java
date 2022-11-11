package entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Transaction class
 * @author Jovan Sie
 * @version 3.0
 * @since 06-11-2022
 */
public class Transaction implements Serializable{
    /**
     * id of the transaction XXXYYYYMMDDhhm
     */
    private String id;
    /**
     * string of the date time of the transaction
     */
    private String tranDateTime;
    /**
     * ticket array list
     */
    private ArrayList<Ticket> tix;
    /**
     * total price of the tickets in the ticket array
     */
    private double totalPrice = 0;

    /**
     * Constructor
     * @param id current id of the transaction
     * @param tranDateTime current date time of the transaction in string
     * @param tix current ticket array list
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
     * gets the transaction's date and time
     * @return String
     */
    public String getTranDateTime(){
        return this.tranDateTime;
    }
    
    /** 
     * gets the list of tickets purchased
     * @return current list of tickets purchased
     */
    public ArrayList<Ticket> getTicketList(){
        return this.tix;
    }
    
    
    /** 
     * gets one of the tickets in the transaction
     * @param index index of the specific ticket
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
     * @param id new transaction id XXXYYYYMMDDhhm
     */
    public void setID(String id) {this.id = id;}

    /**
     * sets the date and time of the transaction
     * @param tdt new time and date of transaction in string
     */
    public void setTranDateTime(String tdt) {this.tranDateTime=tdt;}
    /**
     * sets the list of tickets of the transaction
     * @param tix new list of tickets
     */
    public void setTicketList(ArrayList<Ticket> tix) {this.tix = tix;}
    /**
     * sets the ticket of the transaction
     * @param index index of the specific ticket
     * @param ticket new ticket
     */
    public void setTicket(int index, Ticket ticket) {this.tix.set(index, ticket);}
    /**
     * sets the total price of the transaction
     * @param price new total price of the transaction's ticket array
     */
    public void setTotalPrice(double price){this.totalPrice = price;}

    /**
     * prints the transaction details
     */
    public void printTransaction() {
    	System.out.println("ID: " +this.getID());
		System.out.println("Date & Time: " + this.getTranDateTime());

        //print out the information for each ticket
        for(int i = 0; i < tix.size(); i++)
        {
            System.out.print("Ticket " + (i+1) + ":");
            System.out.println("    " + this.getTicket(i).getDate());
            System.out.println("    " + this.getTicket(i).getTicketPrice());
        }

    }
}
