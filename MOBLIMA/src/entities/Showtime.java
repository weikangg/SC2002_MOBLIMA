package entities;
//import entities.Cinema;
//import entities.CinemaStatus;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Showtime implements Serializable {

    private String showtimeID;

    private LocalDateTime dateTime;

    //Unique movieID to specify the movie being shown for that showtime.
    private String movieID;
    /**
     * Movie Format of the specific showtime.
     */
    private MovieResolution mR;
    /**
     * Cinema object with access to a seating plan. Cinema held is the cinema at which the movie is showing.
     */
    private Cinema cinema;
    /**
     * Name of the cineplex where the movie is showing at this specific showtime.
     */
    private String cineplexName;
    /**
     * Status of the Showtime, whether its AVAILABLE, or SELLING_FAST etc.
     */
    private CinemaStatus cinemaStatus;


    //Methods


    /* Updates the status of the cinema depending on proportion of seats filled.
    public void updateCinemaStatus() {
    	double percentageFilled = (double) getCinema().getOccupiedSeatsNo() / (double) getCinema().getTotalSeatNo();
    	if (percentageFilled <= 0.50) {
    		setCinemaStatus(CinemaStatus.AVAILABLE);
    	}
    	else if (percentageFilled < 1) {
    		setCinemaStatus(CinemaStatus.SELLING_FAST);
    	}
    	else {
    		setCinemaStatus(CinemaStatus.SOLD_OUT);
    	}
    }
    */


    //Getters

    public String getShowtimeID() {return showtimeID;}
    public LocalDateTime getDateTime() {return dateTime;}
    public String getMovieID() {return movieID;}
    public MovieResolution getMovieFormat() {return mR;}
    public Cinema getCinema() {return cinema;}
    public String getCineplexName() {return cineplexName;}
    public CinemaStatus getCinemaStatus() {return cinemaStatus;}

    //Setters

    public void setShowtimeID(String showtimeID) {this.showtimeID = showtimeID;} 
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public void setMovieID(String movieID) {this.movieID = movieID;}
    public void setMovieFormat(MovieResolution mR) {this.mR = mR;}
    public void setCinema(Cinema cinema) {this.cinema = cinema;}
    public void setCineplexName(String cineplexName) {this.cineplexName = cineplexName;}
    public void setCinemaStatus(CinemaStatus cinemaStatus) {this.cinemaStatus = cinemaStatus;}

};
