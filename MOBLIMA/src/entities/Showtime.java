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

import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

/**
 * Represents one Showtime in the Cinema
 * @author Andrew Leung
 * @version 3.0
 * @since 06-11-2022
 */
public class Showtime extends Cinema{

    /**
     * ID of Shotwime
     */
    private int showtimeID;

    /**
     * Unique movieID to specify the movie being shown for that showtime
     */
    private int movieID;

    /**
     * Date and Time of Showtime
     */
    private LocalDateTime dateTime;

    /**
     * Movie Format of the specific showtime.
     */
    private MovieType movieType;

    /**
     * Status of how many seats are left of the specific showtime
     */
    private CinemaStatus cinemaStatus;

    /**
     * 2D array of Seat objects, stores the satus and type of Seat
     */
    private Seat[][] seats;

    /**
     * Movie object
     */
    private Movie movie;

    /**
     * Creates a new Showtime by inheriting from Cinema, and also showtimeID
     * @param showtimeID ID of Shotwime
     */
    public Showtime(String name, String location, int numCinemas, int cineplexID, int cinemaID, CinemaClass cinemaClass, int showtimeID){
        super(name, location, numCinemas, cineplexID, cinemaID, cinemaClass);
        this.showtimeID = showtimeID;
        
        try {
            
            Path path = Paths.get(System.getProperty("user.dir")+"\\data\\cineplexes\\"+name+ "\\hall"+Integer.toString(cinemaID+1)+ "\\"+getShowtimeID()+".csv");
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

            Seat[][] seats = new Seat[5][10];

            Path pathTwo = Paths.get(System.getProperty("user.dir")+"\\data\\cinemaLayout.csv");
            // System.out.println(pathTwo.toAbsolutePath().toString());

            FileReader filereaderTwo = new FileReader(pathTwo.toAbsolutePath().toString()); //CSVReader Instantiation
            CSVReader csvReaderTwo = new CSVReader(filereaderTwo); 
            
            List<String[]> s = csvReaderTwo.readAll(); //Read File

            for (int i = 0; i < 5; i++){
                for(int j = 0; j < 10; j++){

                    String temp = s.get(i)[j];
                    SeatType seatType;

                    if(temp.equals("R")){
                        seatType = SeatType.REGULAR;
                    }else if(temp.equals("C")){
                        seatType = SeatType.COUPLE;
                    }else if(temp.equals("E")){
                        seatType = SeatType.ELITE;
                    }else{
                        seatType = SeatType.ULTIMATE;
                    }

                    seats[i][j] = new Seat(Integer.valueOf(r.get(i+2)[j]), seatType); //Copying individual lines into seats array

                }
            }

            this.seats = seats;

            Movie movie = MovieListManager.getInstance().getMovie(movieID);
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

                if (seats[i][j].getState() == 1) seatsFilled++;

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
     * Printer method for Showtime information
     */
    public void showInfo(){ 
        System.out.println(super.getName()+", Hall "+(super.getCinemaID()+1)+", Class: " +super.getCinemaClass());

        System.out.println("Showtime ID: " + getShowtimeID());
        System.out.println("Movie ID: " + getMovieID());
        System.out.println("Movie Title: " + getMovieTitle());
        System.out.println("Date and Time: " + getDateTime());
        System.out.println("Type: " + getMovieType().toString());
        System.out.println("Cinema Status: " + getCinemaStatus());
        
    }

    /**
     * Printer method for Showtime seats
     */
    public void showSeats(){
        System.out.println("      Screen");
        for (int i = 0; i < 5; i++){
            System.out.print((char)(i+65)+"  ");
            for(int j = 0; j < 10; j++){
                if(j == 5) System.out.print("  ");
                if(seats[i][j].getState() == 0) System.out.print("\u001B[32m" + "O" + "\u001B[0m");
                else if(seats[i][j].getState() == 1) System.out.print("\u001B[31m" + "X" + "\u001B[0m");
            }
            System.out.println("  "+(char)(i+65));
        }
    }

    /**
     * Printer method for Showtime seats with types
     */
    public void showSeatsType(){
        System.out.println("      Screen");
        for (int i = 0; i < 5; i++){
            System.out.print((char)(i+65)+"  ");
            for(int j = 0; j < 10; j++){
                if(j == 5) System.out.print("  ");
                if(seats[i][j].getState() == 0) System.out.print("\u001B[32m" + seats[i][j].getSeatType().toString() + "\u001B[0m");
                else if(seats[i][j].getState() == 1) System.out.print("\u001B[31m" + "X" + "\u001B[0m");
            }
            System.out.println("  "+(char)(i+65));
        }
    }

    /**
     * Function for writing into Showtime CSV files
     */
    public void update(){

        try {

            Path path = Paths.get(System.getProperty("user.dir")+"\\data\\cineplexes\\"+getName()+ "\\hall"+Integer.toString(getCinemaID()+1)+ "\\"+getShowtimeID()+".csv");
            // System.out.println(path.toAbsolutePath().toString());

            FileWriter filewriter = new FileWriter(path.toAbsolutePath().toString()); //CSVReader Instantiation
            CSVWriter csvwriter = new CSVWriter(filewriter, 
                                                CSVWriter.DEFAULT_SEPARATOR,
                                                CSVWriter.NO_QUOTE_CHARACTER,
                                                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                                                CSVWriter.RFC4180_LINE_END);

            ArrayList<String[]> data = new ArrayList<String[]>();
            data.add(new String[] { "MovieID", "DateTime" , "MovieType" });
            data.add(new String[] { Integer.toString(getMovieID()), getDateTime(), getMovieType().toString() });
            
            for (int i = 0; i < 5; i++){
                String[] s = new String[10];
                for(int j = 0; j < 10; j++){
    
                    s[j] = Integer.toString(seats[i][j].getState());
    
                }
                data.add(s);
            }

            csvwriter.writeAll(data);

            csvwriter.close();
                
        } catch (Exception e) {
            // TODO: handle exception
        }
        

    }

    /**
     * Function that returns the type of the specified seat 
     * @param i Row of the Seat
     * @param j Column of the Seat
     */
    public SeatType getSeatType(int i, int j){
        return seats[i][j].getSeatType();
    }


    //Getters

    /**
     * Function to return ID of Shotwime
     * @return ID of Shotwime
     */
    public int getShowtimeID() {return showtimeID;}
    
    /**
     * Function to return Unique movieID
     * @return Unique movieID to specify the movie being shown for that showtime
     */
    public int getMovieID() {return movieID;}

    /**
     * Function to return Date and Time of Showtime
     * @return Date and Time of Showtime
     */
    public String getDateTime() {
        String[] str = dateTime.toString().split("T",2);
        return str[0]+" "+str[1];
    }

    /**
     * Function to return Date and Time of Showtime as a LocalDateTime object
     * @return Date and Time of Showtime as a LocalDateTime object
     */
    public LocalDateTime getDateTimeLDT(){return this.dateTime;}

    /**
     * Function to return Title of Movie
     * @return Title of Movie
     */
    public String getMovieTitle() {return movie.getMovieTitle();}

    /**
     * Function to return Movie Format of the specific showtime
     * @return Movie Format of the specific showtime
     */
    public MovieType getMovieType() {return movieType;}

    /**
     * Function to return status depending on proportion of seats filled
     * @return Status depending on proportion of seats filled
     */
    public String getCinemaStatus() {return cinemaStatus.toString();}
    /**
     *Function to return seat availbility as a Seat array
     * @return Seat availability as a Seat array
     */
    public Seat[][] getSeatS()
    {
        return this.seats;
    }
    
    /**
     * Function to return seat availiblilty as an integer array
     * @return Seat availiblilty as an integer array
     */
    public int[][] getSeats(){
        int[][] ret = new int [5][10];
        for (int i = 0; i < 5; i++){
            for(int j = 0; j < 10; j++){

                ret[i][j] = seats[i][j].getState();

            }
            
        }

        return ret;
    }

    //Setters

    /**
     * Function to set date and time of Showtime
     * @param dateTime Date and Time of Showtime
     */
    public void setDateTime(LocalDateTime dateTime) {this.dateTime = dateTime;update();}
    
    /**
     * Function to set Movie ID of Showtime
     * @param movieID Unique movieID to specify the movie being shown for that showtime
     */
    public void setMovieID(Integer movieID) {this.movieID = movieID;update();}
    
    /**
     * Function to set Movie Format of the specific showtime
     * @param movieReso Movie Format of the specific showtime
     */
    public void setMovieType(MovieType movieReso) {this.movieType = movieReso;update();}
    
    /**
     * Function to set status of how many seats are left of the specific showtime
     * @param cinemaStatus Status of how many seats are left of the specific showtime
     */
    public void setCinemaStatus(CinemaStatus cinemaStatus) {this.cinemaStatus = cinemaStatus;}
    
    /**
     * Function to set status seat to reserved
     * @param i Row of Seat
     * @param j Column of Seat
     */
    public void reserveSeat(int i, int j){this.seats[i][j].setState(1);update();}


}
