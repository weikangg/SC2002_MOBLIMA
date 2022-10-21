package main.Objects;

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


}
