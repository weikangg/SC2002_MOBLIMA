package managers;

import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

import entities.Cineplex;

public class CineplexManager {
    
    public static Cineplex[] configCineplexes(){

        try {

            String path = System.getProperty("user.dir") +"\\data\\cineplexes.csv"; //FilePath for login.csv
            FileReader filereader = new FileReader(path); //CSVReader Instantiation
            CSVReader csvReader = new CSVReader(filereader); 

            List<String[]> r = csvReader.readAll(); //Read File
    
            Cineplex[] cn = new Cineplex[r.size()+1]; //Create list of Cineplex Objects
            
            for(int i = 0; i < r.size(); i++){ //Loop to transfer lines of data from file to objects
                cn[i] = new Cineplex(r.get(i)[0],r.get(i)[1], Integer.valueOf(r.get(i)[2]));
            }


            //Config Cinemas in Cineplex 1

            for(int i = 0; i <r.size(); i++){
                cn[i].configCinema();
            }
            
            return cn;

            

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
