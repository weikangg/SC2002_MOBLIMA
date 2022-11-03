package entities;

public class Cineplex {
    private String name;
    private int cineplexID;
    private String location;
    private int numCinemas; 
    private Cinema[] cinemas;

    public Cineplex(String name, String location, int numCinemas, int cineplexID){
        this.name = name;
        this.location = location;
        this.numCinemas = numCinemas;
        this.cineplexID = cineplexID;
    }

    /**
     * Function to configure the list of cinemas
     */
    public void configCinema(){ 

        Cinema[] cinemas = new Cinema[numCinemas]; //Creating object array for Cinema objects

        for (int i = 0; i < numCinemas; i++){
            cinemas[i] = new Cinema(name, location, numCinemas, cineplexID,i);
        }

        this.cinemas = cinemas;

    }

    public String getName() {
        return name;
    }

    public int getCineplexID(){
        return cineplexID;
    }

    public String getLocation() {
        return location;
    }

    public int getNumCinema() {
        return numCinemas;
    }

    public Cinema[] getCinemas() {
        return cinemas;
    }
}