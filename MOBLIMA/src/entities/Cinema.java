package entities;

public class Cinema extends Cineplex{

    private int cinemaID;
    private int numShowtimes;
    private Showtime[] showtimes;

    public Cinema(String name, String location, int numCinemas, int cineplexID, int cinemaID){
        super(name, location, numCinemas, cinemaID);

        this.cinemaID = cinemaID;

    }
    
    /**
     * Function to configure the list of showtimes
     */
    public void configShowtimes(int numShowtimes){ 

        this.numShowtimes = numShowtimes;

        Showtime[] showtimes = new Showtime[numShowtimes]; //Creating object array for Movie objects

        for (int i = 0; i < numShowtimes; i++){
            showtimes[i] = new Showtime(super.getName(), super.getLocation(), super.getNumCinema(),super.getCineplexID(), cinemaID, i);
        }

        this.showtimes = showtimes;

    }


    /**
     * Function to show details of all showtimes
     */
    public void showShowtimes(){ 
        
        System.out.println("Showtimes: " + numShowtimes);

        for(int i = 0; i < numShowtimes; i++){
            showtimes[i].showInfo();
            System.out.println("");
        }

    }

    public int getCinemaID(){
        return cinemaID;
    }
    
    public Showtime[] getShowtimes(){
        return showtimes;
    } 
    
    
}
