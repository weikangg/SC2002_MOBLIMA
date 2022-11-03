package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileReader;
import java.io.FileWriter;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

public class Cinema extends Cineplex{

    private int cinemaID;
    private int numShowtimes;
    private Showtime[] showtimes;

    public Cinema(String name, String location, int numCinemas, int cineplexID, int cinemaID){
        super(name, location, numCinemas, cinemaID);

        this.cinemaID = cinemaID;

    }
    
    /**
     * Function to configure the list of showtimes
     */
    public void configShowtimes(int numShowtimes){ 

        this.numShowtimes = numShowtimes;

        Showtime[] showtimes = new Showtime[numShowtimes]; //Creating object array for Movie objects

        for (int i = 0; i < numShowtimes; i++){
            showtimes[i] = new Showtime(super.getName(), super.getLocation(), super.getNumCinema(),super.getCineplexID(), cinemaID, i);
        }

        this.showtimes = showtimes;

    }

    /**
     * Function to show details of all showtimes
     */
    public void showShowtimes(){ 
        
        System.out.println("Showtimes: " + numShowtimes);

        for(int i = 0; i < numShowtimes; i++){
            showtimes[i].showInfo();
            System.out.println("");
        }

    }

    /**
     * Function to add showtime
     */
    public void addShowtime(int MovieID, LocalDateTime dateTime, MovieType movieType){

        try {
            //Create File
            Path path = Paths.get("data\\cineplexes\\"+super.getName()+ "\\hall"+Integer.toString(cinemaID+1)+ "\\"+numShowtimes+".csv");
            File myObj = new File(path.toAbsolutePath().toString());

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
            data.add(new String[] { Integer.toString(MovieID), strDateTime, movieType.toString() });
            
            for (int i = 0; i < 5; i++){
                String[] s = new String[10];
                for(int j = 0; j < 10; j++){

                    s[j] = "0";

                }
                data.add(s);
            }

            csvwriter.writeAll(data);
            csvwriter.close();

            //Create Object
            Showtime showtime = new Showtime(super.getName(), super.getLocation(), super.getNumCinema(),super.getCineplexID(), cinemaID, numShowtimes);
            //Update Variables (numShowtimes) and add object into list of objects
            this.numShowtimes++;
            Showtime[] newShowtimes = new Showtime[numShowtimes];
            for(int i = 0; i < numShowtimes-1; i++){
                newShowtimes[i] = this.showtimes[i];
            }
            newShowtimes[numShowtimes-1] = showtime;

            this.showtimes = newShowtimes;
            
            //Write to cineplexes.csv

            path = Paths.get("data\\cineplexes.csv");
            FileReader filereader = new FileReader(path.toAbsolutePath().toString()); //CSVReader Instantiation
            CSVReader csvReader = new CSVReader(filereader); 
            List<String[]> r = csvReader.readAll(); //Read File

            ArrayList<String[]> cineplexesFile = new ArrayList<String[]>(r);
            
            String[] array= cineplexesFile.get(super.getCineplexID());
            array[cinemaID+3] = Integer.toString(Integer.valueOf(array[cinemaID+3])+1);
            cineplexesFile.set(super.getCineplexID(), array);

            System.out.println("here: "+array[cinemaID+3]);

            FileWriter filewriterTwo = new FileWriter(path.toAbsolutePath().toString()); //CSVReader Instantiation
            CSVWriter csvwriterTwo = new CSVWriter(filewriterTwo, 
                                                CSVWriter.DEFAULT_SEPARATOR,
                                                CSVWriter.NO_QUOTE_CHARACTER,
                                                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                                                CSVWriter.RFC4180_LINE_END);

            csvwriterTwo.writeAll(cineplexesFile);
            csvwriterTwo.close();
            
        } catch (Exception e) {
            // TODO: handle exception
        }

        
        
        

    }

    public int getCinemaID(){
        return cinemaID;
    }
    
    public Showtime[] getShowtimes(){
        return showtimes;
    } 
    
    
}
