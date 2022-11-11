package entities;

import java.time.LocalDate;
//import java.util.*;

/**
 * A class defining a Holiday Object.
 * @author Aloysius
 * @version 2.5
 * @since 11-11-2022
 */
public class Holidays {
    /**
	* This String is used to store the holiday's name.
	*/
    private String nameOfHoliday;
    /**
	* This LocalDate is used to store the holiday's date.
	*/
    private LocalDate dateOfHoliday;
    /**
	* This String is used to store the path to csv file where the holidays are stored.
	*/
    static String path = System.getProperty("user.dir") +"\\data\\staffs\\holidaySettings.csv";
    /**
	 * The separator for the columns in the csv file
	 */
    static String separator = ",";

    /**
	 * This constructor is used to create a new Holidays object
     * @param nameOfHoliday      This is the holiday's name
	 * @param dateOfHoliday	     This is the holiday's date
	 */
    public Holidays(String nameOfHoliday, LocalDate dateOfHoliday){
        this.nameOfHoliday = nameOfHoliday;
        this.dateOfHoliday = dateOfHoliday;
    }

    //Gettors
    /**
	 * Get holiday's name (String). Public method.
	 * @return Name of Holiday.
	 */
    public String getHolidayName(){return this.nameOfHoliday;}
    /**
	 * Get holiday's date (LocalDate). Public method.
	 * @return Date of Holiday.
	 */
    public LocalDate getHolidayDate(){return this.dateOfHoliday;}

    //Settors
    /**
	 * Set holiday name. Public method.
	 * @param holidayName String containing new holiday name.
	 */
    public void setHolidayName(String holidayName){this.nameOfHoliday = holidayName;}
    /**
	 * Set holiday's date. Public method.
	 * @param holidayDate LocalDate containing new holiday date.
	 */
    public void setHolidayDate(LocalDate holidayDate){this.dateOfHoliday = holidayDate;}

}

