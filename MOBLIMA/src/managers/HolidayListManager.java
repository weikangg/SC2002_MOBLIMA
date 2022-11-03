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

public class HolidayListManager {

    private static HolidayListManager single_instance = null;
	public static HolidayListManager getInstance()
    {
        if (single_instance == null)
            single_instance = new HolidayListManager();
        return single_instance;
    }
	
    // Constructor
    public HolidayListManager(){}
    
    static String path = System.getProperty("user.dir") +"\\data\\staffs\\holidaySettings.csv";
    static String separator = ",";

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

    public static boolean addHolidayList(List<Holidays> holidayList, String holName, LocalDate holDate) {
        Holidays newHol = new Holidays(holName, holDate);
        holidayList.add(newHol);
        return updateHolidayListCSV(holidayList);
    }
    public static boolean updateHolidayListCSV(List<Holidays> holidayList) {
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
