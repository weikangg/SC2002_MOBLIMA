package managers;

import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

import entities.Cineplex;
import entities.Cinema;

public class CineplexManager {
    
    public static Cineplex[] configCineplexes(){

        try {

            Path path = Paths.get("data\\cineplexes.csv");
            // System.out.println(path.toAbsolutePath().toString());

            
            FileReader filereader = new FileReader(path.toAbsolutePath().toString()); //CSVReader Instantiation
            CSVReader csvReader = new CSVReader(filereader); 

            List<String[]> r = csvReader.readAll(); //Read File
    
            Cineplex[] cineplexes = new Cineplex[r.size()]; //Create list of Cineplex Objects
            
            for(int i = 0; i < r.size(); i++){ //Loop to transfer lines of data from file to objects
                cineplexes[i] = new Cineplex(r.get(i)[0],r.get(i)[1], Integer.valueOf(r.get(i)[2]));
            }


            //Config Cinemas in all Cineplexes

            for(int i = 0; i < cineplexes.length; i++){
                cineplexes[i].configCinema();
            }

            //Config Movies in Cinemas in Cineplexes
            for(int i = 0; i < cineplexes.length; i++){
                Cinema[] cinemas = cineplexes[i].getCinemas();
                
                for(int j = 0; j < cinemas.length; j++){
                    int numCinemas = Integer.valueOf(r.get(i)[2]);
                    int[] numMovies = new int[numCinemas];
                    for(int z = 3; z < 3 + numCinemas; z++){
                        numMovies[z-3] = Integer.valueOf(r.get(i)[z]);
                    }
                    cinemas[j].configMovies(numMovies);
                }
                

            }

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
