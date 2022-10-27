package entities;

import movie_entities.Ticket;

public class Transaction {
    private String id;
    private String movieName;
    private String movDateTime;
    private String tranDateTime;
    private float cost;

    public Transaction(String id, String movname, String movDateTime, String tranDateTime, float cost)
    {
    private String id;
    private String movieName;
    private String tranDateTime;
    private Ticket tix;

    public Transaction(String id, String movname, String tranDateTime, Ticket tix)
    {
        this.id = id;
        this.movieName = movname;
        this.tranDateTime = tranDateTime;
        this.tix = tix;
    }

    public String getID(){
        return this.id;
    }
    public String getMovieName(){
        return this.movieName;
    }
    
    public String getTranDateTime(){
        return this.tranDateTime;
    }
    public Ticket getTicket(){
        return this.tix;
    }

    //print transaction
    public void printTransaction() {
    	System.out.println(this.getID());
		System.out.println(this.getMovieName());
		System.out.println(this.getTicket().getDate());
		System.out.println(this.getTranDateTime());
		System.out.println(this.getTicket().getTicketPrice());
    }
}
