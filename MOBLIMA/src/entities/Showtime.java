package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import managers.MovieListManager;
import entities.MovieType;

import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

public class Showtime extends Cinema{

    private int showtimeID;
    private int movieID;
    private LocalDateTime dateTime;

    //Unique movieID to specify the movie being shown for that showtime.
    /**
     * Movie Format of the specific showtime.
     */
    private MovieType movieType;
    /**
     * Cinema object with access to a seating plan. Cinema held is the cinema at which the movie is showing.
     */

    private CinemaStatus cinemaStatus;
    private int[][] seats;
    private Movie movie;

    public Showtime(String name, String location, int numCinemas, int cineplexID, int cinemaID, int showtimeID){
        super(name, location, numCinemas, cineplexID, cinemaID);
        this.showtimeID = showtimeID;
        
        try {
            
            Path path = Paths.get("data\\cineplexes\\"+name+ "\\hall"+Integer.toString(cinemaID+1)+ "\\"+getShowtimeID()+".csv");
            // System.out.println(path.toAbsolutePath().toString());

            FileReader filereader = new FileReader(path.toAbsolutePath().toString()); //CSVReader Instantiation
            CSVReader csvReader = new CSVReader(filereader); 

            List<String[]> r = csvReader.readAll(); //Read File

            //Set all variables with data from csv file
            this.movieID = Integer.valueOf(r.get(1)[0]);
            String str = r.get(1)[1];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
            this.dateTime = dateTime;

            if(r.get(1)[2].equals("TWOD")){
                this.movieType = MovieType.TWOD;
            }else if(r.get(1)[2].equals("THREED")){
                this.movieType = MovieType.THREED;
            }else if(r.get(1)[2].equals("IMAX")){
                this.movieType = MovieType.IMAX;
            }else{
                this.movieType = MovieType.BLOCKBUSTER;
            }

            int[][] seats = new int[5][10];

            for (int i = 0; i < 5; i++){
                for(int j = 0; j < 10; j++){

                    seats[i][j] = Integer.valueOf(r.get(i+2)[j]); //Copying individual lines into seats array

                }
            }

            this.seats = seats;

            Movie movie = MovieListManager.getMovie(movieID);
            this.movie = movie;

            this.updateCinemaStatus();
            csvReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println("IO Error!");
            System.out.println(e.getMessage());
        } catch (CsvException e){
            System.out.println("CSV Error!");
            System.out.println(e.getMessage());
        }
    }

    //Methods

    /**
     * Updates the status of the cinema depending on proportion of seats filled.
     */
    public void updateCinemaStatus() {

        int seatsFilled = 0;

        for (int i = 0; i < 5; i++){
            for(int j = 0; j < 10; j++){

                if (seats[i][j] == 1) seatsFilled++;

            }
        }
       
        double percentageFilled = (double)seatsFilled/50;

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

    /**
     * Printer method for showtime information and seats
     */
    public void show(){ 
        System.out.println(super.getName()+", hall "+(super.getCinemaID()+1)+", movie " + getShowtimeID());

        System.out.println("Showtime ID: " + getShowtimeID());
        System.out.println("Movie ID: " + getMovieID());
        System.out.println("Movie Title: " + getMovieTitle());
        System.out.println("Date and Time: " + getDateTime().toString());
        System.out.println("Type: " + getMovieType().toString());
        System.out.println("Cinema Status: " + getCinemaStatus());
        
        for (int i = 0; i < 5; i++){
            for(int j = 0; j < 10; j++){

                System.out.print(seats[i][j]);

            }
            System.out.println("");
        }

    }

    /**
     * Printer method for showtime information
     */
    public void showInfo(){ 
        System.out.println(super.getName()+", hall "+(super.getCinemaID()+1)+", movie " + getShowtimeID());

        System.out.println("Showtime ID: " + getShowtimeID());
        System.out.println("Movie ID: " + getMovieID());
        System.out.println("Movie Title: " + getMovieTitle());
        System.out.println("Date and Time: " + getDateTime().toString());
        System.out.println("Type: " + getMovieType().toString());
        System.out.println("Cinema Status: " + getCinemaStatus());
        
    }

    /**
     * Printer method for showtime seats
     */
    public void showSeats(){

        for (int i = 0; i < 5; i++){
            for(int j = 0; j < 10; j++){

                System.out.print(seats[i][j]);

            }
            System.out.println("");
        }

    }

    public void update(){

        try {

            Path path = Paths.get("data\\cineplexes\\"+getName()+ "\\hall"+Integer.toString(getCinemaID()+1)+ "\\"+getShowtimeID()+".csv");
            // System.out.println(path.toAbsolutePath().toString());

            FileWriter filewriter = new FileWriter(path.toAbsolutePath().toString()); //CSVReader Instantiation
            CSVWriter csvwriter = new CSVWriter(filewriter, 
                                                CSVWriter.DEFAULT_SEPARATOR,
                                                CSVWriter.NO_QUOTE_CHARACTER,
                                                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                                                CSVWriter.RFC4180_LINE_END);

            ArrayList<String[]> data = new ArrayList<String[]>();
            data.add(new String[] { "MovieID", "DateTime" , "MovieType" });
            data.add(new String[] { Integer.toString(getMovieID()), getDateTime().toString(), getMovieType().toString() });
            
            for (int i = 0; i < 5; i++){
                String[] s = new String[10];
                for(int j = 0; j < 10; j++){
    
                    s[j] = Integer.toString(seats[i][j]);
    
                }
                data.add(s);
            }

            csvwriter.writeAll(data);

            csvwriter.close();
                
        } catch (Exception e) {
            // TODO: handle exception
        }
        

    }


    //Getters

    public Integer getShowtimeID() {return showtimeID;}
    public Integer getMovieID() {return movieID;}
    public String getDateTime() {
        String[] str = dateTime.toString().split("T",2);
        return str[0]+" "+str[1];
    }
    public String getMovieTitle() {return movie.getMovieTitle();}
    public MovieType getMovieType() {return movieType;}
    public String getCinemaStatus() {return cinemaStatus.toString();}
    public int[][] getSeats(){return seats;}
    //Setters


    public void setDateTime(LocalDateTime dateTime) {this.dateTime = dateTime;update();}
    public void setMovieID(Integer movieID) {this.movieID = movieID;update();}
    public void setMovieType(MovieType movieReso) {this.movieType = movieReso;update();}
    public void setCinemaStatus(CinemaStatus cinemaStatus) {this.cinemaStatus = cinemaStatus;}
    public void reserveSeat(int i, int j){this.seats[i][j] = 1;update();}


};
