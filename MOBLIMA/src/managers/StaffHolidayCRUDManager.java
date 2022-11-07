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
import java.util.InputMismatchException;
import java.util.Scanner;
import static utils.IOUtils.*;
import entities.Holidays;
import static managers.HolidayListManager.*;

/**
 * A manager class for all actions related to the staff to create, read, update and delete holidays
 * @author Aloysius
 * @version 2.5
 * @since 06-11-2022
 */
public class StaffHolidayCRUDManager {
    /**
	 * The scanner for reading input of user
	 */
    static Scanner sc = new Scanner(System.in);
    /**
	 * The separator for the columns in the csv file
	 */
    static String csv_Separator = ",";
    /**
	 * The separator used for array of string in the csv file such as if the holiday has more than 1 name
	 */
	static String splitter = ";";
    /**
	 * For singleton pattern adherence. This StaffHolidayCRUDManager instance persists throughout runtime.
	 */
    private static StaffHolidayCRUDManager single_instance = null;
    /**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
    public static StaffHolidayCRUDManager getInstance()
    {
        if (single_instance == null)
            single_instance = new StaffHolidayCRUDManager();
        return single_instance;
    }
     /**
	 * Function to add new holidays for the staffs.
     * Checks if the holidays that he wants to add already exists. 
     * @param holidayList  Existing List of holidays
     * @return true if addition of holiday was successful, false if unsuccessful
	 */
    public boolean staffAddHoliday(List<Holidays> holidayList) {
		String holName,holNametmp,dateStr;
        LocalDate holDate=null;
		System.out.println("#########################################################");
		System.out.println("#################### ADDING HOLIDAY #####################");
		System.out.println("#########################################################");


		System.out.print("Enter Holiday Name: ");
		holNametmp = sc.nextLine();
		if (holidayList.stream().filter(o -> o.getHolidayName().equalsIgnoreCase(holNametmp)).findFirst().isPresent()) {
			System.out.println("Holiday Already Exists!");
			return false;
		}
		holName = holNametmp.replaceAll(csv_Separator, splitter);
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
            if (HolidayListManager.getInstance().addHolidayList(holidayList,holName,holDate))
                return true;
            return false;
        }
        return false;
    }

     /**
	 * Function to delete a holiday from the list of holidays and database entirely based on holiday name.
     * Checks if the holiday to be deleted exists
     * Deletes both holiday name and date
     * @param hList Existing List of holidays
     * @return true if deletion of holiday was successful, false if unsuccessful
	 */
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
            return HolidayListManager.getInstance().updateHolidayListCSV(newList);
        }
        else{
            System.out.println("Removing Movie Cancelled, Returning to Original Menu...");
            return true;
        }
    }

    /**
	 * Function for staff to update a holiday from the list of holidays and database based on the holiday name.
     * @param holidayList   Existing List of holidays
     * @return true if updating of holiday was successful, false if unsuccessful.
	 */
    public int staffUpdateHoliday(List<Holidays> holidayList){
        System.out.println("#########################################################");
        System.out.println("#################### UPDATING HOLIDAYS ##################");
        System.out.println("#########################################################");
        System.out.println("");
        System.out.println("Please enter Holiday Name to update:");
        String newName = sc.nextLine();
        
        // Search if movie exists first

        Holidays temp = null;
        for(Holidays h: holidayList){
            if(h.getHolidayName().equalsIgnoreCase(newName)){
                 temp = h;
            }
        }
        if(!holidayList.contains(temp)){
            System.out.println("Holiday does not exist!");
            return 0;
        }

        // Then do the updating if it exists
        while (true){
            System.out.println("Please choose an option:");
            System.out.println("1. Update Holiday Name");
            System.out.println("2. Update Holiday Date");
            System.out.println("3. Exit");
            int option;
            try{
                option = sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Please only enter a number from 1-3 only!");
                sc.nextLine();
                continue;
            }

            switch(option){
                case 1:
                    if(confirm("Confirm Holiday Name")){
                        String name = read("New Holiday Name:");
                        if (holidayList.stream().filter(o -> o.getHolidayName().equalsIgnoreCase(name)).findFirst().isPresent()) {
                            System.out.println("Holiday Already Exists!");
                            sc.nextLine();
                            return 0;
                        }
                        for(Holidays h:holidayList){
                            if(h.getHolidayName().equalsIgnoreCase(newName)){
                                h.setHolidayName(name);
                                break;
                            }
                        }

                    }
                    else{
                        return 2;
                    }
                    break;
                
                case 2:
                    int valid = 0;
                    if(confirm("Confirm Update Holiday Date")){
                        while(true){
                            String holidayDateStr = read("New Holiday Date (YYYY-MM-DD): ");
                            try{
                                LocalDate holidayDate = LocalDate.parse(holidayDateStr);
                                for(Holidays h:holidayList){
                                    if(h.getHolidayName().equalsIgnoreCase(newName)){
                                        h.setHolidayDate(holidayDate);
                                        valid = 1;
                                        break;
                                    }
                                }
                                if(valid == 1){
                                    break;
                                }else{
                                    continue;
                                }
                            }catch(DateTimeParseException e){
                                System.out.println("Wrong format, enter again!");
                                continue;
                            }
                        }
                    }
                    else{
                        return 2;
                    }
                    break;
                
                case 3:
                    print("Returning to Holiday Menu....");
                    sc.nextLine();
                    return 2;
                default:
                    print("Please enter a number from 1-3 only.");
                    continue;
            }
            break;
        }
        
        if(HolidayListManager.getInstance().updateHolidayListCSV(holidayList)){
            return 1;
        }
        return 0;
    }

    /**
	 * Function for staff to view all holidays.
	 */
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
