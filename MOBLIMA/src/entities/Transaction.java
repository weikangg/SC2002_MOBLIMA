package customer;

public class Transaction {
    private String id;
    private String movieName;
    private String movDateTime;
    private String tranDateTime;
    private float cost;

    public Transaction(String id, String movname, String movDateTime, String tranDateTime, float cost)
    {
        this.id = id;
        this.movieName = movname;
        this.movDateTime = movDateTime;
        this.tranDateTime = tranDateTime;
        this.cost = cost;
    }

    public String getID(){
        return this.id;
    }
    public String getMovieName(){
        return this.movieName;
    }
    public String getMovDateTime(){
        return this.movDateTime;
    }
    public String getTranDateTime(){
        return this.tranDateTime;
    }
    public float getCost(){
        return this.cost;
    }

    //print transaction
    public void printTransaction() {
    	System.out.println(this.getID());
		System.out.println(this.getMovieName());
		System.out.println(this.getMovDateTime());
		System.out.println(this.getTranDateTime());
		System.out.println(this.getCost());
    }
}
