package entities;

public enum SeatType {
    REGULAR ("R"),
    ELITE ("E"),
    COUPLE ("C"),
    ULTIMATE ("U");

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
     * @return boolean on whether the String value of MovieFormat is equals to otherName.
     */
    public boolean equalsString(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false 
        return name.equals(otherName);
    }

    /**
     *
     * @return String value of MovieFormat for string comparison purposes.
     */
    public String toString() {
       return this.name;
    }
    
}
