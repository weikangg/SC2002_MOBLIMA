package managers;

import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

import entities.Cineplex;
import entities.Cinema;

/**
 * Function Class that configures and returns the array of Cineplex objects
 * @author Andrew Leung
 * @version 3.0
 * @since 06-11-2022
 */
public class CineplexManager {

    /**
	 * For singleton pattern adherence. This CineplexManager instance persists throughout runtime.
	 */
    private static CineplexManager newInstance = null;

    /**
	 * The default constructor for the CineplexManager class
	 */
    private CineplexManager(){}

    /**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
    public static CineplexManager getInstance(){
        if (newInstance == null){
            newInstance = new CineplexManager();
        }
        return newInstance;
    }
    
    /**
     * Function to configure and returna the array of Cineplex objects
     * @return cineplexes[] A list of Cineplex objects
     */
    public static Cineplex[] configCineplexes(){ 

        try {
            Path path = Paths.get(System.getProperty("user.dir")+"\\data\\cineplexes\\cineplexes.csv");
            // System.out.println(path.toAbsolutePath().toString());
            
            FileReader filereader = new FileReader(path.toAbsolutePath().toString()); //CSVReader Instantiation
            CSVReader csvReader = new CSVReader(filereader); 

            List<String[]> r = csvReader.readAll(); //Read File
    
            Cineplex[] cineplexes = new Cineplex[r.size()-1]; //Create list of Cineplex Objects
            
            for(int i = 0; i < r.size()-1; i++){ //Loop to transfer lines of data from file to objects
                cineplexes[i] = new Cineplex(r.get(i+1)[0],r.get(i+1)[1], Integer.valueOf(r.get(i+1)[2]), i);

            }


            //Config Cinemas in all Cineplexes

            for(int i = 0; i < cineplexes.length; i++){
                ArrayList<String> str = new ArrayList<String>();
                for(int j = 3+Integer.valueOf(r.get(i+1)[2]); j < 3+2*Integer.valueOf(r.get(i+1)[2]); j++){
                    str.add(r.get(i+1)[j]);
                }

                cineplexes[i].configCinema(str);                
            }


            //Config Movies in Cinemas in Cineplexes
            for(int i = 0; i < cineplexes.length; i++){
                Cinema[] cinemas = cineplexes[i].getCinemas();  
                for(int j = 0; j < cinemas.length; j++){
                    cinemas[j].configShowtimes(Integer.valueOf(r.get(i+1)[j+3]));    
                }
            }
            csvReader.close();
            return cineplexes;

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (CsvException e){
            System.out.println("CSV Error!");
        } catch (IOException e){
            System.out.println("IO Error!");
        }

        return null;

    }

}
