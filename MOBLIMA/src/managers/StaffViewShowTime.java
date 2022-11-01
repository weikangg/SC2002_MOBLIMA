package managers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class StaffViewShowTime {
    static String separator = ",";

    public static void viewShowTime(String cineplexName, int cinemaNo, int showTimeID){
        String path = "\\data\\cineplexes\\" + cineplexName + "\\hall" + String.valueOf(cinemaNo) + "\\" + String.valueOf(showTimeID) + ".csv";
        String fullPath = System.getProperty("user.dir") + path;
        System.out.println(fullPath);
        BufferedReader br = null;
        String line = "";
        int count = 0;
        String datetime = "", movieID = "", movieType = "";
		try {
			br = new BufferedReader(new FileReader(fullPath));
			while((line = br.readLine()) !=null ) {
				String[] showTimeCSV = line.split(separator);
				if(showTimeCSV[0].equals("MovieID")) {
                    count++;
				}
                else if(count == 1){
                    movieID = showTimeCSV[0];
                    datetime = showTimeCSV[1];
                    movieType = showTimeCSV[2];
                    System.out.println("MovieID: " + movieID );
                    System.out.println("Date & Time: " + datetime);
                    System.out.println("Movie Type: " + movieType);
                    count++;
                }
                else{
                    System.out.println(line);
                    count++;
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
