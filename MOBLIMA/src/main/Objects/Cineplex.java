package main.Objects;

public class Cineplex {
    private String name;
    private String location;
    private int cinemas; 

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


}
