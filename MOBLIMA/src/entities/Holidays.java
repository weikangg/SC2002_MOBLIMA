package entities;

import java.time.LocalDate;
//import java.util.*;

public class Holidays {
    private String nameOfHoliday;
    private LocalDate dateOfHoliday;
    //private static Scanner sc = new Scanner(System.in);
    // Ensure single instance

    //Constructor
    public Holidays(String nameOfHoliday, LocalDate dateOfHoliday){
        this.nameOfHoliday = nameOfHoliday;
        this.dateOfHoliday = dateOfHoliday;
    }

    static String path = System.getProperty("user.dir") +"\\data\\staffs\\holidaySettings.csv";
    static String separator = ",";

    //Gettors
    public String getHolidayName(){return this.nameOfHoliday;}
    public LocalDate getHolidayDate(){return this.dateOfHoliday;}

    //Settors
    public void setHolidayName(String holidayName){this.nameOfHoliday = holidayName;}
    public void setHolidayDate(LocalDate holidayDate){this.dateOfHoliday = holidayDate;}

}

