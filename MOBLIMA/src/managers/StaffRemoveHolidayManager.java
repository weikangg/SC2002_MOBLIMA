package managers;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entities.Holidays;

import static utils.IOUtils.*;
import static managers.HolidayListManager.*;

public class StaffRemoveHolidayManager {
    
    private static Scanner sc = new Scanner(System.in);
    // Remove holiday from database entirely
    public static boolean removeHolidayFromDatabase(List<Holidays> hList){
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
}
