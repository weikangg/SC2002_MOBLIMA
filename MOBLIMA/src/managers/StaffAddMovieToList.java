package managers;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import entities.*;
import java.util.Scanner;

public class StaffAddMovieToList {
    static String csv_Separator = ",";
	static String splitter = ";";
	static String converter = ":";

    public static boolean staffAddMovie(List<Movie> mList) {
		ShowingStatus status = null;
		String movieTitle, synopsis, movieDirector, cast, genres, synopsisTmp, movieDirectorTmp, castTmp, genreTmp, movieTitleTmp;
		int choice;
		int movieDuration = 0;
		double rating = 0.0;
        MovieRating movieRating = null;
		int sale = 0;
        LocalDate releaseDateTime = LocalDate.now();
		MovieType movieType = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("#########################################################");
		System.out.println("#################### ADDING MOVIES ######################");
		System.out.println("#########################################################");
		System.out.print("Enter Movie Title: ");
		movieTitleTmp = sc.nextLine();
		if (mList.stream().filter(o -> o.getMovieTitle().equalsIgnoreCase(movieTitleTmp)).findFirst().isPresent()) {
			System.out.println("Movie Already Exists!");
			return false;
		}
		movieTitle = movieTitleTmp.replaceAll(csv_Separator, converter);
        System.out.println("Choose Movie Status");
		System.out.println("1: COMING_SOON");
		System.out.println("2: PREVIEW");
		System.out.println("3: NOW_SHOWING");
		System.out.println("4: FINISHED_SHOWING");
		choice = sc.nextInt();
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
        System.out.println("Choose Movie Rating");
		System.out.println("1: G");
		System.out.println("2: PG");
		System.out.println("3: PG-13");
		System.out.println("4: NC-16");
		System.out.println("5: M-18");
		System.out.println("6: R-21");
		choice = sc.nextInt();
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
        }
        sc.nextLine();
		System.out.println("Enter Movie Duration (minutes):");
        try{
			movieDuration = sc.nextInt();
		}
		catch(InputMismatchException e){
			System.out.println("Invalid input!");
			e.printStackTrace();
			System.out.println(e.getMessage());
			movieDuration = 0;
		}
        releaseDateTime = LocalDate.now();
		System.out.println("Choose Movie Type");
		System.out.println("1: TWOD");
		System.out.println("2: THREED");
		System.out.println("3: IMAX");
		System.out.println("4: BLOCKBUSTER");
		choice = sc.nextInt();
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
        }
        sc.nextLine();
		if (MovieListManager.addMovieList(mList, movieTitle, synopsis, movieDirector, cast, genres, movieDuration, status, sale, movieRating,rating, releaseDateTime,movieType))
			return true;
		 return false;
    }
}
