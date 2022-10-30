package entities;


import java.util.List;
import java.util.Scanner;
import java.io.FileReader;

import com.opencsv.*;

public class Cinema extends Cineplex{

    private int cinemaID;
    private int cinemaClass;
    private int[] numMovies;
    private Showtime[] showtimes;

    public Cinema(String name, String location, int cinemas, int cinemaID){
        super(name, location, cinemas);

        this.cinemaID = cinemaID;

    }

    public void configMovies(int[] numMovies){

        this.numMovies = numMovies;

        Showtime[] showtimes = new Showtime[numMovies[cinemaID]]; //Creating object array for Movie objects

        for (int i = 0; i < numMovies[cinemaID]; i++){
            showtimes[i] = new Showtime(super.getName(), super.getLocation(), super.getNumCinema(), cinemaID, i);
        }

        this.showtimes = showtimes;

    }

    public int getCinemaID(){
        return cinemaID;
    }
    
    public Showtime[] getShowtimes(){
        return showtimes;
    } 
    
    
}
