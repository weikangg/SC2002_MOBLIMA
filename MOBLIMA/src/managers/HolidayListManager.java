package managers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import entities.Holidays;
/**
 * A manager class for all actions related to the list of holidays in our data base 
 * @author Aloysius
 * @version 2.5
 * @since 07-11-2022
 */
public class HolidayListManager {
	/**
	 * For singleton pattern adherence. This HolidayListManager instance persists throughout runtime.
	 */
    private static HolidayListManager single_instance = null;
	/**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
	public static HolidayListManager getInstance()
    {
        if (single_instance == null)
            single_instance = new HolidayListManager();
        return single_instance;
    }
	/**
	 * The default constructor for the HolidayListManager class
	 */
    public HolidayListManager(){}
    /**
	 * The path to the CSV file that stores all the holidays
	 */
    static String path = System.getProperty("user.dir") +"\\data\\staffs\\holidaySettings.csv";
	/**
	 * The separator for the columns in the csv file
	 */
    static String separator = ",";
	/**
	 * Fetch all the holidays inside the holidaySettings.csv file and compile them into a list 
	 * @return holidayList
	 */
    public List<Holidays>getHolidayList(){
        List<Holidays>holidayList = new ArrayList<>();
    	BufferedReader br = null;
		String line = "";
		Holidays holidaytmp;
		try {
			br = new BufferedReader(new FileReader(path));
			while((line = br.readLine()) !=null ) {
				String[] holidaysCSV = line.split(separator);
				if(!holidaysCSV[0].equals("HOLIDAY_NAME")) {
					String str = holidaysCSV[1];
					LocalDate date = LocalDate.parse(str,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					holidaytmp = new Holidays(holidaysCSV[0],date);
					holidayList.add(holidaytmp);		
				}
			}
			br.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File not found!");
		}
		catch(ArrayIndexOutOfBoundsException e){
			return holidayList;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return holidayList;
    }
	/**
	 * Appends the new movie to the existing list of movies and updates the list of movies in the database
	 * @param holidayList 		 Existing list of holidays
	 * @param holName            This is the name of the holiday
	 * @param holDate            This is the date of the holiday
	 */ 
    public boolean addHolidayList(List<Holidays> holidayList, String holName, LocalDate holDate) {
        Holidays newHol = new Holidays(holName, holDate);
        holidayList.add(newHol);
        return updateHolidayListCSV(holidayList);
    }
	/**
	 * Writes the list of holidays to the holidaySettings.csv file for storage
	 * @param holidayList Existing list of holidays
	 * @return true if update was successful, false if update was unsuccessful
	 */
    public boolean updateHolidayListCSV(List<Holidays> holidayList) {
		FileWriter csvWriter;
		try {
			csvWriter = new FileWriter(path,false);
			csvWriter.append("HOLIDAY_NAME");
			csvWriter.append(separator);
			csvWriter.append("HOLIDAY_DATE");
			csvWriter.append("\n");

			for (Holidays holiday : holidayList) {
				StringBuilder sb = new StringBuilder();
				sb.append(holiday.getHolidayName());
				sb.append(separator);
				sb.append(holiday.getHolidayDate());
				sb.append('\n');
				csvWriter.append(sb.toString());
			}

			csvWriter.flush();
			csvWriter.close();
			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}
}
