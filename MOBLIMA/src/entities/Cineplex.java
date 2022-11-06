package entities;

import java.util.ArrayList;
/**
 * Represents a Cineplex managed by the vendor
 * Has multiple Cinemas
 * @author Andrew Leung
 * @version 3.0
 * @since 06-11-2022
 */
public class Cineplex {

    /**
     * Name of Cineplex
     */
    private String name;

    /**
     * Location of Cineplex
     */
    private String location;

     /**
     * Number of Cinemas in Cineplex
     */
    private int numCinemas;

    /**
     * ID of Cineplex
     */
    private int cineplexID;
    
    /**
     * Object array of Cinema objects
     */
    private Cinema[] cinemas;

    /**
     * Creates a new Cineplex with the Cineplex's name, location, the number of cinemas and ID
     * @param name Name of Cineplex
     * @param location Location of Cineplex
     * @param numCinemas Location of Cineplex
     * @param cineplexID ID of Cineplex
     */
    public Cineplex(String name, String location, int numCinemas, int cineplexID){
        this.name = name;
        this.location = location;
        this.numCinemas = numCinemas;
        this.cineplexID = cineplexID;
    }

    /**
     * Function to configure the array of Cinema objects cinemas[]
     * @param str An ArrayList storing the classes of the Cinemas, indexed by cinemaID
     */
    public void configCinema(ArrayList<String> str){ 

        Cinema[] cinemas = new Cinema[numCinemas]; //Creating object array for Cinema objects

        for (int i = 0; i < numCinemas; i++){

            CinemaClass cinemaClass;
            
            if(str.get(i).equals("SILVER")){
                cinemaClass = CinemaClass.SILVER;
            } else if(str.get(i).equals("GOLD")){
                cinemaClass = CinemaClass.GOLD;
            } else if(str.get(i).equals("PLATINUM")){
                cinemaClass= CinemaClass.PLATINUM;
            } else{
                cinemaClass = CinemaClass.DIAMOND;
            }

            cinemas[i] = new Cinema(this.name, this.location, this.numCinemas, this.cineplexID,i, cinemaClass);
        
        }

        this.cinemas = cinemas;

    }

    /**
     * Returns ArrayList of Showtimes in Cineplex that have movieID as the Movie's ID
     * @param movieID ID that corresponds to a Movie
     * @return list A list of Showtimes 
     */
    public ArrayList<Showtime> searchMovie(int movieID){
        
        ArrayList<Showtime> list = new ArrayList<Showtime>(); 
        
        for(int i = 0; i < cinemas.length; i++){
            Showtime[] showtimes = cinemas[i].getShowtimes();
            for(int j = 0; j < showtimes.length; j++){
                if (showtimes[j].getMovieID() == movieID)list.add(showtimes[j]);
            }
        }

        return list;
    }

    /**
     * Function to return the Name of the Cineplex
     * @return Name of Cineplex
     */
    public String getName() {
        return name;
    }

    /**
     * Function to return the ID of the Cineplex
     * @return ID of Cineplex
     */
    public int getCineplexID(){
        return cineplexID;
    }

    /**
     * Function to return the Location of the Cineplex
     * @return Location of Cineplex
     */
    public String getLocation() {
        return location;
    }

    /**
     * Function to return the number of Cinemas in the Cineplex
     * @return Number of Cinemas in Cineplex
     */
    public int getNumCinema() {
        return numCinemas;
    }

    /**
     * Function to return the array of Cinema objects cinemas[]
     * @return A list of Cinema objects
     */
    public Cinema[] getCinemas() {
        return cinemas;
    }
}