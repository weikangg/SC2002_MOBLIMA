package entities;


/**
 * Status of the cinema, depending on the proportion of seats filled.
 */
public enum CinemaStatus {
    
    /**
    * When the seats are sold out
    */
    SOLD_OUT ("SOLD OUT"),
    
    /**
    * When the seats are selling fast
    */
    SELLING_FAST ("SELLING FAST"),

    /**
    * When the seats are availble
    */
    AVAILABLE ("AVAILABLE");

    /**
     * Constructor for CinemaStatus enum, taking in the string value of the enum and setting it as an attribute.
     * @param name of the enum.
     */
    private CinemaStatus(String name) { }
}