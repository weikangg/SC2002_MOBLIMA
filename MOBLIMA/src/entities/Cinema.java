package entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileReader;
import java.io.FileWriter;
import com.opencsv.*;

/**
 * Represents a Cinema, inside a Cineplex
 * Has multiple Showtimes
 * @author Andrew Leung
 * @version 3.0
 * @since 06-11-2022
 */
public class Cinema {
    
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
     * ID of Cinema
     */
    private int cinemaID;

    /**
     * Class of Cinema
     */
    private CinemaClass cinemaClass;

    /**
     * Number of Showtimes in Cinema
     */
    private int numShowtimes;

    /**
     * Object array of Showtime objects
     */
    private Showtime[] showtimes;

    /**
     * Creates a new Cinema by inheriting from Cineplex, and also the Cinema's ID and Class
     * @param name Name of Cineplex
     * @param location Location of Cineplex
     * @param numCinemas Location of Cineplex
     * @param cineplexID ID of Cineplex
     * @param cinemaID Name of Cineplex
     * @param cinemaClass Location of Cineplex
     */
    public Cinema(String name, String location, int numCinemas, int cineplexID, int cinemaID, CinemaClass cinemaClass){
        
        this.name = name;
        this.location = location;
        this.numCinemas = numCinemas;
        this.cineplexID = cineplexID;
        this.cinemaID = cinemaID;
        this.cinemaClass = cinemaClass;

    }
    
    /**
     * Function to configure the array of Showtime objects showtimes[]
     * @param numShowtimes an integer storing the number of Showtimes this cinema has
     */
    public void configShowtimes(int numShowtimes){ 

        this.numShowtimes = numShowtimes;

        Showtime[] showtimes = new Showtime[numShowtimes]; //Creating object array for Movie objects

        for (int i = 0; i < numShowtimes; i++){
            showtimes[i] = new Showtime(name, location, numCinemas, cineplexID, cinemaID, cinemaClass, i);
        }

        this.showtimes = showtimes;

    }

    /**
     * Function to show details of all Showtimes
     */
    public void showShowtimes(){ 
        
        System.out.println("Showtimes: " + numShowtimes);

        for(int i = 0; i < numShowtimes; i++){
            showtimes[i].showInfo();
            System.out.println("");
        }

    }

    /**
     * Function to add a new Showtime
     * @param movieID ID of movie to be referenced in Movie List
     * @param dateTime Date and Time of Showtime
     * @param movieType Type of Movie being showed
     */
    public void addShowtime(int movieID, LocalDateTime dateTime, MovieType movieType){

        try {
            //Create File
            Path path = Paths.get(System.getProperty("user.dir")+"\\data\\cineplexes\\"+name+ "\\hall"+Integer.toString(cinemaID+1)+ "\\"+numShowtimes+".csv");
            
            String[] str = dateTime.toString().split("T",2);
            String strDateTime = str[0]+" "+str[1];

            FileWriter filewriter = new FileWriter(path.toAbsolutePath().toString()); //CSVReader Instantiation
            CSVWriter csvwriter = new CSVWriter(filewriter, 
                                                CSVWriter.DEFAULT_SEPARATOR,
                                                CSVWriter.NO_QUOTE_CHARACTER,
                                                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                                                CSVWriter.RFC4180_LINE_END);

            ArrayList<String[]> data = new ArrayList<String[]>();
            data.add(new String[] { "MovieID", "DateTime" , "MovieType" });
            data.add(new String[] { Integer.toString(movieID), strDateTime, movieType.toString() });
            
            for (int i = 0; i < 5; i++){
                String[] s = new String[10];
                for(int j = 0; j < 10; j++){

                    s[j] = "0";

                }
                data.add(s);
            }

            csvwriter.writeAll(data);
            csvwriter.close();
            
            //Write to cineplexes.csv

            path = Paths.get(System.getProperty("user.dir")+"\\data\\cineplexes\\cineplexes.csv");
            FileReader filereader = new FileReader(path.toAbsolutePath().toString()); //CSVReader Instantiation
            CSVReader csvReader = new CSVReader(filereader); 
            List<String[]> r = csvReader.readAll(); //Read File

            ArrayList<String[]> cineplexesFile = new ArrayList<String[]>(r);
            
            String[] array= cineplexesFile.get(cineplexID+1);
            
            array[cinemaID+3] = Integer.toString(Integer.valueOf(array[cinemaID+3])+1);

            cineplexesFile.set(cineplexID+1, array);

            FileWriter filewriterTwo = new FileWriter(path.toAbsolutePath().toString()); //CSVReader Instantiation
            CSVWriter csvwriterTwo = new CSVWriter(filewriterTwo, 
                                                CSVWriter.DEFAULT_SEPARATOR,
                                                CSVWriter.NO_QUOTE_CHARACTER,
                                                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                                                CSVWriter.RFC4180_LINE_END);

            csvwriterTwo.writeAll(cineplexesFile);
            csvwriterTwo.close();

            //Update Showtimes
            numShowtimes++;
            configShowtimes(numShowtimes);
            
            System.out.println("Showtime added");
            csvReader.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
    
    /**
     * Function to delete a Showtime using it's ID
     * @param showtimeID ID of Showtime
     */
    public void deleteShowtime(int showtimeID){

        //check if showtime exists
        if (showtimeID >= numShowtimes||showtimeID < 0){
            System.out.println("No such showtime");
            return;
        }

        //Delete showtime csv file
        Path path = Paths.get(System.getProperty("user.dir")+"\\data\\cineplexes\\"+name+ "\\hall"+Integer.toString(cinemaID+1)+ "\\"+showtimeID+".csv");
        File file = new File(path.toAbsolutePath().toString());

        if (file.delete()) { 
            // System.out.println("File deleted.");
          } else {
            System.out.println("Failed to delete the file.");
            return;
          }

        //Update cineplexes csv

        try {

            path = Paths.get(System.getProperty("user.dir")+"\\data\\cineplexes\\cineplexes.csv");
            FileReader filereader = new FileReader(path.toAbsolutePath().toString()); //CSVReader Instantiation
            CSVReader csvReader = new CSVReader(filereader); 
            List<String[]> r = csvReader.readAll(); //Read File

            ArrayList<String[]> cineplexesFile = new ArrayList<String[]>(r);
            
            String[] array= cineplexesFile.get(cineplexID+1);
            
            array[cinemaID+3] = Integer.toString(Integer.valueOf(array[cinemaID+3])-1);

            cineplexesFile.set(cineplexID+1, array);

            FileWriter filewriterTwo = new FileWriter(path.toAbsolutePath().toString()); //CSVReader Instantiation
            CSVWriter csvwriterTwo = new CSVWriter(filewriterTwo, 
                                                CSVWriter.DEFAULT_SEPARATOR,
                                                CSVWriter.NO_QUOTE_CHARACTER,
                                                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                                                CSVWriter.RFC4180_LINE_END);

            csvwriterTwo.writeAll(cineplexesFile);
            csvwriterTwo.close();
            csvReader.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

        //Update filename of showtimes after and update numShowtimes
        for(int i = showtimeID + 1; i < numShowtimes; i++){
            Path pathTwo = Paths.get(System.getProperty("user.dir")+"\\data\\cineplexes\\"+name+ "\\hall"+Integer.toString(cinemaID+1)+ "\\"+i+".csv");
            File fileTwo = new File(pathTwo.toAbsolutePath().toString());

            Path newPath = Paths.get(System.getProperty("user.dir")+"\\data\\cineplexes\\"+name+ "\\hall"+Integer.toString(cinemaID+1)+ "\\"+(i-1)+".csv");
            File newFile = new File(newPath.toAbsolutePath().toString());

            fileTwo.renameTo(newFile);

        }
        numShowtimes--;

        //Update Showtimes
        configShowtimes(numShowtimes);

        System.out.println("Showtime Deleted");

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
     * Function to return the ID of the Cinema
     * @return ID of Cinema
     */
    public int getCinemaID(){
        return cinemaID;
    }

    /**
     * Function to return the Class of the Cinema
     * @return Class of Cinema as String
     */
    public String getCinemaClass(){
        return cinemaClass.toString();
    }

    /**
     * Function to return the Class of the Cinema
     * @return Class of Cinema of CinemaClass type 
     */
    public CinemaClass getCinemaClassCC(){
        return this.cinemaClass;
    }

    /**
     * Function to return the number of Showtimes in the Cinema
     * @return Number of Showtimes in Cinema
     */
    public int getNumShowtimes(){
        return numShowtimes;
    }

    /**
     * Function to return the array of Showtime objects showtimes[]
     * @return A list of Showtime objects
     */
    public Showtime[] getShowtimes(){
        return showtimes;
    }
    
    
}
