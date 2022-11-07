package entities;


/**
 * Status of the cinema, depending on the proportion of seats filled.
 */
public enum CinemaStatus {
    SOLD_OUT ("SOLD OUT"),
    SELLING_FAST ("SELLING FAST"),
    AVAILABLE ("AVAILABLE");

    /**
     * Constructor for CinemaStatus enum, taking in the string value of the enum and setting it as an attribute.
     * @param name of the enum.
     */
    private CinemaStatus(String name) { }
}