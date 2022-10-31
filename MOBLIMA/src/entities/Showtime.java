package entities;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.opencsv.*;

public class Showtime extends Cinema{

    private int showtimeID;
    private int movieID;
    private SimpleDateFormat dateTime;

    //Unique movieID to specify the movie being shown for that showtime.
    private String movieTitle;
    /**
     * Movie Format of the specific showtime.
     */
    private String movieReso;
    /**
     * Cinema object with access to a seating plan. Cinema held is the cinema at which the movie is showing.
     */

    private CinemaStatus cinemaStatus;
    private int[][] seats;

    public Showtime(String name, String location, int numCinemas, int cinemaID, int showtimeID){
        super(name, location, numCinemas, cinemaID);
        this.showtimeID = showtimeID;
        
        try {
            
            Path path = Paths.get("data\\cineplexes\\"+name+ "\\hall"+Integer.toString(cinemaID+1)+ "\\"+getShowtimeID()+".csv");
            // System.out.println(path.toAbsolutePath().toString());

            FileReader filereader = new FileReader(path.toAbsolutePath().toString()); //CSVReader Instantiation
            CSVReader csvReader = new CSVReader(filereader); 

            List<String[]> r = csvReader.readAll(); //Read File


            //Set all variables with data from csv file
            this.movieID = Integer.valueOf(r.get(0)[0]);
            this.dateTime = new SimpleDateFormat(r.get(0)[1]);
            this.movieReso = r.get(0)[2];

            int[][] seats = new int[5][10];

            for (int i = 0; i < 5; i++){
                for(int j = 0; j < 10; j++){

                    seats[i][j] = Integer.valueOf(r.get(i+1)[j]); //Copying individual lines into seats array

                }
            }

            this.seats = seats;

            this.updateCinemaStatus();
            
        } catch (Exception e) {
            // TODO: handle exception
        }


    }

    //Methods


    // Updates the status of the cinema depending on proportion of seats filled.
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

    public void show(){ //Printer method for information
        System.out.println(super.getName()+", hall "+(super.getCinemaID()+1)+", movie " + getShowtimeID());

        System.out.println("Showtime ID: " + getShowtimeID());
        System.out.println("Movie ID: " + getMovieID());
        System.out.println("Date and Time: " + getDateTime());
        System.out.println("Type: " + getMovieFormat());
        System.out.println("Cinema Status: " + getCinemaStatus());
        
        for (int i = 0; i < 5; i++){
            for(int j = 0; j < 10; j++){

                System.out.print(seats[i][j]);

            }
            System.out.println("");
        }

    }

    public void showInfo(){ //Printer method for information
        System.out.println(super.getName()+", hall "+(super.getCinemaID()+1)+", movie " + getShowtimeID());

        System.out.println("Showtime ID: " + getShowtimeID());
        System.out.println("Movie ID: " + getMovieID());
        System.out.println("Date and Time: " + getDateTime());
        System.out.println("Type: " + getMovieFormat());
        System.out.println("Cinema Status: " + getCinemaStatus());
        
    }

    public void showSeats(){

        for (int i = 0; i < 5; i++){
            for(int j = 0; j < 10; j++){

                System.out.print(seats[i][j]);

            }
            System.out.println("");
        }

    }


    //Getters

    public Integer getShowtimeID() {return showtimeID;}
    public Integer getMovieID() {return movieID;}
    public String getDateTime() {return dateTime.toPattern();}
    public String getMovieTitle() {return movieTitle;}
    public String getMovieFormat() {return movieReso;}
    public String getCinemaStatus() {return cinemaStatus.toString();}

    //Setters

    public void setShowtimeID(Integer showtimeID) {this.showtimeID = showtimeID;}
    public void setMovieID() {this.movieID = movieID;}
    public void setDateTime(SimpleDateFormat dateTime) { this.dateTime = dateTime; }
    public void setMovieID(Integer movieID) {this.movieID = movieID;}
    public void setMovieFormat(String movieReso) {this.movieReso = movieReso;}
    public void setCinemaStatus(CinemaStatus cinemaStatus) {this.cinemaStatus = cinemaStatus;}

};
