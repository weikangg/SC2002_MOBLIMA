package entities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Cineplex {
    private String name;
    private String location;
    private int cinemas; 
    private Cinema[] list[];

    public Cineplex(String name, String location, int cinemas){
        this.name = name;
        this.location = location;
        this.cinemas = cinemas;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getCinemas() {
        return cinemas;
    }

    public void configCinema(){

        Cinema list[] = new Cinema[cinemas]; //Creating object array for Cinema objects



        for (int i = 0; i < cinemas; i++){
            list[i] = new Cinema(name, location, cinemas, i);
        }

        for (int i = 0; i < cinemas; i++){
            list[i].showSeats();
        }


    }

    private void cineplexOpener(String name) {
        try{
            String line = "";
            String path = System.getProperty("user.dir") +"\\MOBLIMA\\rsc\\cineplexes.csv";
            BufferedReader br = new BufferedReader(new FileReader(path));
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                if(values[0].substring(1,values[0].length()).equals(username) 
                && values[1].substring(0, values[1].length()-1).equals(password)){
                    br.close();
                    return true;
                }
            }

            // IF NO MATCH
            br.close();
            return false;
        }catch(FileNotFoundException e){
            System.out.println("Cannot find input file!");
            System.out.println(e.getMessage());
            System.exit(0);

        } catch(IOException e){
            System.out.println("Input/Output Error!");
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return false;
    }       

}
