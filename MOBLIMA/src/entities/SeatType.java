package entities;
/**
 * An enum class defining Seat Type
 * @author Andrew Leung
 * @version 1.0
 * @since 06-11-2022
 */
public enum SeatType {

    /**
     * Regular type seat
     */
    REGULAR ("REGULAR"),

    /**
     * Elite type seat
     */
    ELITE ("ELITE"),
    
    /**
     * Couple type seat
     */
    COUPLE ("COUPLE"),
    
    /**
     * Ultimate type seat
     */
    ULTIMATE ("ULTIMATE");

    private final String name;

    /**
     * Constructor for the SeatType enum, taking in the string value of the enum and setting it as an attribute.
     * @param s of the enum.
     */
    private SeatType(String s) {
        name = s;
    } 
    /**
     * For string comparison.
     * @param otherName String to be compared to.
     * @return boolean on whether the String value of SeatType is equals to otherName.
     */
    public boolean equalsString(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false 
        return name.equals(otherName);
    }

    /**
     *
     * @return String value of SeatType for string comparison purposes.
     */
    public String toString() {
       return this.name;
    }
    
}
