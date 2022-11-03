package managers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Holidays {
    private String nameOfHoliday;
    private LocalDate dateOfHoliday;
    private Scanner sc = new Scanner(System.in);
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


    public static void listHolidays(){
        BufferedReader br = null;
        String line = "";
		try {
			br = new BufferedReader(new FileReader(path));
			while((line = br.readLine()) !=null ) {
				String[] holidaysCSV = line.split(separator);
				if(!holidaysCSV[0].equals("HOLIDAY_NAME")) {
                    System.out.print("Holiday Name: [" + holidaysCSV[0] + "] \t");
                    System.out.println("Holiday Date: [" + holidaysCSV[1] + "]");
				}
			}
			br.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File not found!");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
    }

}

