package entities;


import java.util.List;
import java.util.Scanner;
import java.io.FileReader;

import com.opencsv.*;

public class Cinema extends Cineplex{

    private int cinemaID;
    private int cinemaClass;
    private int[] numMovies;
    private Movie[] movies;

    public Cinema(String name, String location, int cinemas, int cinemaID){
        super(name, location, cinemas);

        this.cinemaID = cinemaID;

    }

    public void configMovies(int[] numMovies){

        this.numMovies = numMovies;

        Movie[] movies = new Movie[numMovies[cinemaID]]; //Creating object array for Movie objects

        for (int i = 0; i < numMovies[cinemaID]; i++){
            movies[i] = new Movie(super.getName(), super.getLocation(), super.getNumCinema(), cinemaID, i);
        }

        this.movies = movies;

    }

    public int getCinemaID(){
        return cinemaID;
    }
    
    public Movie[] getMovies(){
        return movies;
    } 
    
    
}
