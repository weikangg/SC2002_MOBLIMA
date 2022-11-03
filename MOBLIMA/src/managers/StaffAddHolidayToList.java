package managers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

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

		// If movie List is empty, we assign it an ID of 1.
		// if(hList.size() == 0){
		// 	movieID = 1;
		// }
		// // Else if it's not empty, we find the last ID in the list and add 1.
		// else{ 
		// 	movieID = mList.get((mList.size()-1)).getMovieID() + 1;
		// }

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
		if (HolidayListManager.addHolidayList(hList,holName,holDate))
			return true;
		 return false;
    }
}

