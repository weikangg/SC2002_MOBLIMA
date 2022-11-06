package entities;

/**
 * An enum class defining Cinema Class
 * @author Andrew Leung
 * @version 1.0
 * @since 06-11-2022
 */
public enum CinemaClass {
    SILVER ("SILVER"),
    GOLD ("GOLD"),
    PLATINUM ("PLATINUM"),
    DIAMOND ("DIAMOND");

    private final String name;

    /**
     * Constructor for the CinemaClass enum, taking in the string value of the enum and setting it as an attribute.
     * @param s of the enum.
     */
    private CinemaClass(String s) {
        name = s;
    }

    /**
     * For string comparison.
     * @param otherName String to be compared to.
     * @return boolean on whether the String value of CinemaClass is equals to otherName.
     */
    public boolean equalsString(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false 
        return name.equals(otherName);
    }

    /**
     *
     * @return String value of CinemaClass for string comparison purposes.
     */
    public String toString() {
        return this.name;
     }


}
