package main;

import java.util.List;
import java.util.Scanner;
import java.io.FileReader;

import com.opencsv.*;

import main.Objects.Cineplex;


public class Admin {

    private Admin(){
        throw new IllegalStateException("Utility class");
    }

    public static void start(){
        
        System.out.println("");
        System.out.println("Starting Administrator Module...");
        System.out.println("\n.\n.\n.");
        
        int tries = 0; //Counter for number of tries
        
        while(!adminLogin()){ //Attempt login
            
            System.out.println("");
            tries++;
            if(tries > 2) { // Maximum tries
                System.out.println("You have exceeded the maximum number of tries.");
                return; //Exits the method and back to App
            }
            System.out.println("Login failed, please try again");
            
        }

        System.out.println("");
        System.out.println("Welcome!");
        System.out.println("");
        
        //Create and list Cineplex Objects using cinplexes.csv File

        try {

            String path = System.getProperty("user.dir") +"\\rsc\\cineplexes.csv"; //FilePath for login.csv
            FileReader filereader = new FileReader(path); //CSVReader Instantiation
            CSVReader csvReader = new CSVReader(filereader); 

            List<String[]> r = csvReader.readAll(); //Read File
    
            Cineplex[] cn = new Cineplex[r.size()+1]; //Create list of Cineplex Objects
            
            for(int i = 0; i < r.size(); i++){ //Loop to transfer lines of data from file to objects
                cn[i] = new Cineplex(r.get(i)[0],r.get(i)[1], Integer.valueOf(r.get(i)[2]));
            }

            
            for(int i = 0; i < r.size(); i++){ //Loop to print object data
                System.out.println(cn[i].getName()+" "+cn[i].getLocation()+" "+ cn[i].getCinemas());
            }

            //Config Cinemas in Cineplex 1

            cn[0].configCinema();

            

        } catch (Exception e) {
            // TODO: handle exception
        } 
        

    }

    public static boolean adminLogin(){

        Scanner scan = new Scanner(System.in); //Scanner Object Instantiation
    

        System.out.println("");
        System.out.println("Please enter login details:");
        System.out.println("");

        System.out.print("Enter Username: "); //Prompt for Username and Password
        String username = scan.nextLine();
        System.out.print("Enter Password: ");
        String password = scan.nextLine(); 


        try {
            String path = System.getProperty("user.dir") +"\\rsc\\login.csv"; //FilePath for login.csv
            FileReader filereader = new FileReader(path); //CSVReader Instantiation
            CSVReader csvReader = new CSVReader(filereader); 

            String[] Record; //String array to store data from one line using readNext()
            Record = csvReader.readNext();

            if(username.equals(Record[0]) && password.equals(Record[1])){ //Checking if user and system records match
                
                filereader.close();
                csvReader.close();
                return true;
            }

            filereader.close();
            csvReader.close();

        } catch (Exception e) {
        
        }
        
        return false;

    }

}
