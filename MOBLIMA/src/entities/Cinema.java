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
        super(name, location, numCinemas, cineplexID);

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
            Path path = Paths.get(System.getProperty("user.dir")+"\\data\\cineplexes\\"+super.getName()+ "\\hall"+Integer.toString(cinemaID+1)+ "\\"+numShowtimes+".csv");
            File file = new File(path.toAbsolutePath().toString());
            

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
            
            //Write to cineplexes.csv

            path = Paths.get(System.getProperty("user.dir")+"\\data\\cineplexes.csv");
            FileReader filereader = new FileReader(path.toAbsolutePath().toString()); //CSVReader Instantiation
            CSVReader csvReader = new CSVReader(filereader); 
            List<String[]> r = csvReader.readAll(); //Read File

            ArrayList<String[]> cineplexesFile = new ArrayList<String[]>(r);
            
            String[] array= cineplexesFile.get(super.getCineplexID()+1);
            
            array[cinemaID+3] = Integer.toString(Integer.valueOf(array[cinemaID+3])+1);

            cineplexesFile.set(super.getCineplexID()+1, array);

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
            
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void deleteShowtime(int showtimeID){

        //check if showtime exists
        if (showtimeID >= numShowtimes||showtimeID < 0){
            System.out.println("No such showtime");
            return;
        }

        //Delete showtime csv file
        Path path = Paths.get(System.getProperty("user.dir")+"\\data\\cineplexes\\"+super.getName()+ "\\hall"+Integer.toString(cinemaID+1)+ "\\"+showtimeID+".csv");
        File file = new File(path.toAbsolutePath().toString());

        if (file.delete()) { 
            // System.out.println("File deleted.");
          } else {
            System.out.println("Failed to delete the file.");
            return;
          }

        //Update cineplexes csv

        try {

            path = Paths.get(System.getProperty("user.dir")+"\\data\\cineplexes.csv");
            FileReader filereader = new FileReader(path.toAbsolutePath().toString()); //CSVReader Instantiation
            CSVReader csvReader = new CSVReader(filereader); 
            List<String[]> r = csvReader.readAll(); //Read File

            ArrayList<String[]> cineplexesFile = new ArrayList<String[]>(r);
            
            String[] array= cineplexesFile.get(super.getCineplexID()+1);
            
            array[cinemaID+3] = Integer.toString(Integer.valueOf(array[cinemaID+3])-1);

            cineplexesFile.set(super.getCineplexID()+1, array);

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

        //Update filename of showtimes after and update numShowtimes
        for(int i = showtimeID + 1; i < numShowtimes; i++){
            Path pathTwo = Paths.get(System.getProperty("user.dir")+"\\data\\cineplexes\\"+super.getName()+ "\\hall"+Integer.toString(cinemaID+1)+ "\\"+i+".csv");
            File fileTwo = new File(pathTwo.toAbsolutePath().toString());

            Path newPath = Paths.get(System.getProperty("user.dir")+"\\data\\cineplexes\\"+super.getName()+ "\\hall"+Integer.toString(cinemaID+1)+ "\\"+(i-1)+".csv");
            File newFile = new File(newPath.toAbsolutePath().toString());

            fileTwo.renameTo(newFile);

        }
        numShowtimes--;

        //Update Showtimes
        configShowtimes(numShowtimes);

        System.out.println("Showtime Deleted");

    }

    public int getCinemaID(){
        return cinemaID;
    }
    
    public Showtime[] getShowtimes(){
        return showtimes;
    } 
    
    
}
