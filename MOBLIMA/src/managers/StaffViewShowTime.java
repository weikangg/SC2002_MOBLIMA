package managers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import entities.*;

public class StaffViewShowTime {
    static String separator = ",";

    public static void viewShowTime(String cineplexName, int cinemaNo, int showTimeID){
        String path = "\\data\\cineplexes\\" + cineplexName + "\\hall" + String.valueOf(cinemaNo) + "\\" + String.valueOf(showTimeID) + ".csv";
        String fullPath = System.getProperty("user.dir") + path;
        BufferedReader br = null;
        String line = "";
        int count = 0;
        String datetime = "", movieID = "", movieType = "";
        // MovieListManager movListManager = MovieListManager.getInstance();
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
                    System.out.println("Movie ID: " + movieID);
                    printEssentialMovieInfo(MovieListManager.getInstance().getMovieList(), Integer.parseInt(movieID));
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

    // helper function to print essential info of the movie when viewing showtimes
    public static void printEssentialMovieInfo(List<Movie>mList,int movieID){
        for (Movie m:mList){
            if(m.getMovieID() == movieID){
                System.out.println("Movie Title: "+ m.getMovieTitle());
				System.out.println("Showing Status: "+ m.getShowingStatus());
                System.out.println("Movie Rating: " + m.getMovieRating());
                System.out.println("Movie Type: " + m.getMovieType());
            }
        }
    }
}
