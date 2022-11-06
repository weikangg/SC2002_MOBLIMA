package managers;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import entities.Showtime;
import entities.Cinema;
import entities.Cineplex;
import entities.Movie;
import entities.Review;

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
                if(showtimes[j].getMovieID() == id)list.add(showtimes[j]);
            }
        }
        return list;
    }

    public static ArrayList<Showtime> searchMovieShowtime(List<Movie> movieList, List<Review> reviewList)
    {
        String name;
        char cineplex;
        int cineplexID;

        Scanner scan = new Scanner(System.in);

        //ask user for cineplex

        while(true){
            System.out.println("Please choose a Cineplex(A,B,C):");
            cineplex = scan.next().charAt(0);
            scan.nextLine();
            cineplexID = (int)cineplex;
            if((cineplexID < 65) || (cineplexID > 90 && cineplexID < 97) || (cineplexID > 122)){
                System.out.println("Please key in an alphabet");
                continue;
            }
            else{
                break;
            }
        }

        if(cineplexID > 90)
        {
            cineplexID -= (int)'a';
        }
        else
        {
            cineplexID -= (int)'A';
        }
        
        //ask user for movie
        CustomerMovieManager.printMovieList(movieList, reviewList);
        System.out.println("Please choose a movie:");
        name = scan.nextLine();
        

        //link the movie to showtime
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
                if(showtimes[j].getMovieID() == id)list.add(showtimes[j]);
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
