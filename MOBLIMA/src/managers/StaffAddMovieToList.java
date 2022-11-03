package managers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import entities.*;
import java.util.Scanner;

public class StaffAddMovieToList {
    static String csv_Separator = ",";
	static String splitter = ";";

    public static boolean staffAddMovie(List<Movie> mList) {
		ShowingStatus status = null;
		String movieTitle, synopsis, movieDirector, cast, genres, synopsisTmp, movieDirectorTmp, castTmp, genreTmp, movieTitleTmp, str;
		int choice;
		int movieDuration = 0,movieID;
		double rating = 0.0;
        MovieRating movieRating = null;
		int sale = 0;
		MovieType movieType = null;
		LocalDate releaseDate = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("#########################################################");
		System.out.println("#################### ADDING MOVIES ######################");
		System.out.println("#########################################################");
		System.out.println("");
		// If movie List is empty, we assign it an ID of 1.
		if(mList.size() == 0){
			movieID = 1;
		}
		// Else if it's not empty, we find the last ID in the list and add 1.
		else{ 
			movieID = mList.get((mList.size()-1)).getMovieID() + 1;
		}

		System.out.print("Enter Movie Title: ");
		movieTitleTmp = sc.nextLine();
		if (mList.stream().filter(o -> o.getMovieTitle().equalsIgnoreCase(movieTitleTmp)).findFirst().isPresent()) {
			System.out.println("Movie Already Exists!");
			return false;
		}
		movieTitle = movieTitleTmp.replaceAll(csv_Separator, splitter);
		while(true){
			System.out.println("Choose Movie Status");
			System.out.println("1: COMING_SOON");
			System.out.println("2: PREVIEW");
			System.out.println("3: NOW_SHOWING");
			System.out.println("4: FINISHED_SHOWING");
			try{
				choice = sc.nextInt();
			}
			catch(InputMismatchException e){
				System.out.println("Enter numbers only!");
				sc.nextLine();
				continue;
			}
			switch (choice) {
			case 1: {
				status = ShowingStatus.COMING_SOON;
				break;
			}
			case 2: {
				status = ShowingStatus.PREVIEW;
				break;
			}
			case 3: {
				status = ShowingStatus.NOW_SHOWING;
				break;
			}
			case 4: {
				status = ShowingStatus.FINISHED_SHOWING;
				break;
			}
			default:
				System.out.println("Error Input! Please only input values from 1-4.\n");
				continue;
			}
			break;
		}
        sc.nextLine();
		System.out.print("Enter Movie Synopsis: ");
		synopsisTmp = sc.nextLine();
		synopsis = synopsisTmp.replaceAll(csv_Separator, splitter);
		System.out.print("Enter Director Name: ");
		movieDirectorTmp = sc.nextLine();
		movieDirector = movieDirectorTmp.replaceAll(csv_Separator, splitter);
		System.out.print("Enter Cast Name: ");
		castTmp = sc.nextLine();
		cast = castTmp.replaceAll(csv_Separator, splitter);
		System.out.print("Enter Movie Genres: ");
		genreTmp = sc.nextLine();
		genres = genreTmp.replaceAll(csv_Separator, splitter);
		while(true){
			System.out.println("Choose Movie Rating");
			System.out.println("1: G");
			System.out.println("2: PG");
			System.out.println("3: PG-13");
			System.out.println("4: NC-16");
			System.out.println("5: M-18");
			System.out.println("6: R-21");
			try{
				choice = sc.nextInt();
			}catch(InputMismatchException e){
				System.out.println("Please input numbers only!");
				sc.nextLine();
				continue;
			}
			switch (choice) {
			case 1: {
				movieRating = MovieRating.G;
				break;
			}
			case 2: {
				movieRating = MovieRating.PG;
				break;
			}
			case 3: {
				movieRating = MovieRating.PG13;
				break;
			}
			case 4: {
				movieRating = MovieRating.NC16;
				break;
			}
			case 5: {
				movieRating = MovieRating.M18;
				break;
			}
			case 6: {
				movieRating = MovieRating.R21;
				break;
			}
			default:
				System.out.println("Error Input! Please only input values from 1-6.\n");
				continue;
			}
			break;
		}
        sc.nextLine();
		while (true){
			System.out.println("Enter Movie Duration (minutes):");
			try{
				movieDuration = sc.nextInt();
				break;
			}
			catch(InputMismatchException e){
				System.out.println("Invalid input! Enter numbers only!");
				sc.nextLine();
				continue;
			}
		}
		sc.nextLine();
		while (true){
			try{
				System.out.println("Enter Release Date (DD/MM/YYYY)");
				str = sc.nextLine();
				releaseDate = LocalDate.parse(str,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				break;
			}catch(DateTimeParseException e){
				System.out.println("Wrong format, enter again!");
				continue;
			}
		}

		while(true){
			System.out.println("Choose Movie Type");
			System.out.println("1: TWOD");
			System.out.println("2: THREED");
			System.out.println("3: IMAX");
			System.out.println("4: BLOCKBUSTER");
			try{
				choice = sc.nextInt();
			}catch(InputMismatchException e){
				System.out.println("Please input numbers only!");
				sc.nextLine();
				continue;
			}
			switch (choice) {
				case 1: {
					movieType = MovieType.TWOD;
					break;
				}
				case 2: {
					movieType = MovieType.THREED;
					break;
				}
				case 3: {
					movieType = MovieType.IMAX;
					break;
				}
				case 4: {
					movieType = MovieType.BLOCKBUSTER;
					break;
				}
				default:
					System.out.println("Error Input! Please only input values from 1-4.\n");
					continue;
				}
			break;
		}
        sc.nextLine();
		if (MovieListManager.addMovieList(mList,movieID, movieTitle, synopsis, movieDirector, cast, genres, movieDuration, status, sale, movieRating,rating, releaseDate ,movieType))
			return true;
		 return false;
    }
}
