package managers;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import entities.Showtime;
import entities.Cinema;
import entities.Cineplex;
import entities.Movie;

public class CustomerShowtime {
    String movieName;
    List<Movie> movieList = MovieListManager.getInstance().getMovieList();

    public ArrayList<Showtime> searchMovie(int MovieID){
        ArrayList<Showtime> list = new ArrayList<Showtime>();
        Cineplex[] cineplexes = CineplexManager.configCineplexes(); 
        Cinema[] cinemas = cineplexes[0].getCinemas();

        for(int i = 0; i < cinemas.length; i++)
        {
            Showtime[] showtimes = cinemas[i].getShowtimes();
            for(int j = 0; j < showtimes.length; j++)
            {
                if(showtimes[i].getMovieID() == MovieID)list.add(showtimes[i]);
            }
        }
        return list;
    }

    public static ArrayList<Showtime> searchMovieID(String name, int cineplexID)
    {
        List<Movie> movieList = MovieListManager.getInstance().getMovieList();
        ArrayList<Showtime> list = new ArrayList<Showtime>();
        int id = -1;
        for(Movie mov : movieList)
        {
            if(name.equals(mov.getMovieTitle()))
            {
                id = mov.getMovieID();
            }
        }
        if(id == -1)
        {
            System.out.println("Movie not found");
            return list;
        }

        Cineplex[] cineplexes = CineplexManager.configCineplexes(); 
        Cinema[] cinemas = cineplexes[cineplexID].getCinemas();

        for(int i = 0; i < cinemas.length; i++)
        {
            Showtime[] showtimes = cinemas[i].getShowtimes();
            for(int j = 0; j < showtimes.length; j++)
            {
                if(showtimes[i].getMovieID() == id)list.add(showtimes[i]);
            }
        }
        return list;
    }



    // ArrayList<Showtime> list = cineplexes[0].searchMovie(5); (search for movie)

    // for(int i = 0; i < list.size(); i++){
    //     list.get(i).showInfo();
    //     System.out.println("");
    // }
}
