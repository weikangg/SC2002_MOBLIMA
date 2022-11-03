package managers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import entities.Holidays;

import static utils.IOUtils.*;

public class StaffAddHolidayToList {
    static Scanner sc = new Scanner(System.in);
    static String csv_Separator = ",";
	static String splitter = ";";
	static String converter = ":";

    public static boolean staffAddHoliday(List<Holidays> hList) {
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
}

