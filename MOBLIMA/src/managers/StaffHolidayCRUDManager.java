package managers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import static utils.IOUtils.*;
import entities.Holidays;
import static managers.HolidayListManager.*;

public class StaffHolidayCRUDManager {
    static Scanner sc = new Scanner(System.in);
    static String csv_Separator = ",";
	static String splitter = ";";
	static String converter = ":";

    private static StaffHolidayCRUDManager single_instance = null;
    public static StaffHolidayCRUDManager getInstance()
    {
        if (single_instance == null)
            single_instance = new StaffHolidayCRUDManager();
        return single_instance;
    }

    public boolean staffAddHoliday(List<Holidays> hList) {
		String holName,holNametmp,dateStr;
        LocalDate holDate=null;
		System.out.println("#########################################################");
		System.out.println("#################### ADDING HOLIDAY #####################");
		System.out.println("#########################################################");


		System.out.print("Enter Holiday Name: ");
		holNametmp = sc.nextLine();
		if (hList.stream().filter(o -> o.getHolidayName().equalsIgnoreCase(holNametmp)).findFirst().isPresent()) {
			System.out.println("Holiday Already Exists!");
			return false;
		}
		holName = holNametmp.replaceAll(csv_Separator, converter);
		while (true){
			try{
				System.out.println("Enter Holiday Date (YYYY-MM-DD)");
				dateStr = sc.nextLine();
				holDate = LocalDate.parse(dateStr,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				break;
			}catch(DateTimeParseException e){
				System.out.println("Wrong format, enter again!");
				continue;
			}
        }
        if(confirm("Confirm Add Holiday: ")){
            if (HolidayListManager.addHolidayList(hList,holName,holDate))
                return true;
            return false;
        }
        return false;
    }

     // Remove holiday from database entirely
     public boolean removeHolidayFromDatabase(List<Holidays> hList){
        System.out.println("#########################################################");
		System.out.println("#################### REMOVING HOLIDAYS ##################");
		System.out.println("#########################################################");
		System.out.println("");

        String name;
        List<Holidays> newList = new ArrayList<Holidays>();
        System.out.println("Enter Holiday Name: ");
        name = sc.nextLine();
        
        // Search if holiday exists first

        Holidays temp = null;
        for(Holidays h: hList){
            if(h.getHolidayName().equalsIgnoreCase(name)){
                 temp = h;
            }
        }
        if(!hList.contains(temp)){
            System.out.println("Holiday does not exist!");
            return false;
        }

        if(confirm("Confirm Remove Holiday: ")){
            for(Holidays h : hList){
                if(!h.getHolidayName().equals(name)){
                    String holName = h.getHolidayName();
                    LocalDate holDate =  h.getHolidayDate();
                    Holidays newHoliday = new Holidays(holName,holDate);
                    newList.add(newHoliday);
                }
            }
            return updateHolidayListCSV(newList);
        }
        else{
            System.out.println("Removing Movie Cancelled, Returning to Original Menu...");
            return true;
        }
    }

    //Print out list of holidays
    public void listHolidays(){
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
