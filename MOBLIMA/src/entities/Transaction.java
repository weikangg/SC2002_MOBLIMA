package entities;

public class Transaction {
    private String id;
    private String movieName;
    private String tranDateTime;
    private ArrayList<Ticket> tix;

    public Transaction(String id, String movname, String tranDateTime, ArrayList<Ticket> tix)
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
    public ArrayList<Ticket> getTicketList(){
        return this.tix;
    }
    
    public Ticket getTicket(int index){
    	return this.tix.get(index);
    }
    
    public void setID(String id) {this.id = id;}
    public void setMovieName(String movname) {this.movieName= movname;}
    public void setTranDateTime(String tdt) {this.tranDateTime=tdt;}
    public void setTicketList(ArrayList<Ticket> tix) {this.tix = tix;}
    public void setTicket(int index, Ticket ticket) {this.tix.set(index, ticket);}
    
 
}
