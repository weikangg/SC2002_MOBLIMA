package entities;


import java.util.List;
import java.util.Scanner;
import java.io.FileReader;

import com.opencsv.*;

public class Cinema extends Cineplex{

    private int cinemaNum;
    private int cinemaClass;
    private int[][] seats = new int [5][10];

    public Cinema(String name, String location, int cinemas, int id) {
        super(name, location, cinemas);
        //TODO Auto-generated constructor stub

        cinemaNum = id;

        try {
            
            String path = System.getProperty("user.dir") +"\\rsc\\cineplexes\\"+name+ "\\hall"+Integer.toString(cinemaNum+1)+ ".csv"; //FilePath for login.csv
            System.out.println(path);
            FileReader filereader = new FileReader(path); //CSVReader Instantiation
            CSVReader csvReader = new CSVReader(filereader); 

            List<String[]> r = csvReader.readAll(); //Read File

            for (int i = 0; i < 5; i++){
                for(int j = 0; j < 10; j++){

                    seats[i][j] = Integer.valueOf(r.get(i)[j]); //Copying individual lines into seats array

                }
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void showSeats(){ //Printer method for seats
        System.out.println("hall"+(cinemaNum+1));
        
        for (int i = 0; i < 5; i++){
            for(int j = 0; j < 10; j++){

                System.out.print(seats[i][j]);

            }
            System.out.println("");
        }

        System.out.println("");
    }
    
    
}
