package entities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import static utils.IOUtils.*;

/**
 * A class defining a System Settings object.
 * @author Wei Kang
 * @version 2.5
 * @since 01-11-2022
 */
public class SystemSettings {
    /**
	 * Creating a new Locale
	 */
    private static Locale usa = new Locale("en", "US");
	/**
	 * Create a formatter given the Locale
	 */
    private static NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);
    /**
	* This string is used to store the permission for customer to view the top 5 movies by sales.
	*/
    private String customerViewTop5Sales;
    /**
	* This string is used to store the permission for customer to view the top 5 movies by overall rating score.
	*/
    private String customerViewTop5OverallRating;
    /**
	* This double is used to store the rating score limit.
	*/
    private double ratingScoreLimit;
	/**
	 * The scanner for reading input of user
	 */
    private static Scanner sc = new Scanner(System.in);
	/**
	 * For singleton pattern adherence. This SystemSettings instance persists throughout runtime.
	 */
    private static SystemSettings single_instance = null;
	/**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
	public static SystemSettings getInstance()
    {
        if (single_instance == null)
            single_instance = new SystemSettings();
        return single_instance;
    }

	/**
	 * The default constructor for the SystemSettings class
	 */
    public SystemSettings(){}
    /**
	 * The path to the CSV file that stores all the staff system settings
	 */
    private static String path = System.getProperty("user.dir") +"\\data\\staffs\\staffsSettings.csv";
	/**
	 * The separator for the columns in the csv file
	 */
    private static String separator = ",";

    // Gettors

    /**
	 * Get Permission for Customers to view Top 5 movies by Sales (String). Public method.
	 * @return Customer's permission to view top 5 movies by sales
	 */
    public String getTop5SalesPermission(){
        return this.customerViewTop5Sales;
    }
    /**
	 * Get Permission for Customers to view Top 5 movies by Overall Rating Score (String). Public method.
	 * @return Customer's permission to view top 5 movies by Overall Rating Score
	 */
    public String getTop5OverallRatingPermission(){
        return this.customerViewTop5OverallRating;
    }
    /**
	 * Get Rating Score Limit. Public method.
	 * @return ratingScoreLimit
	 */
    public double getRatingScoreLimit(){
        return this.ratingScoreLimit;
    }
    // Settors

	/**
	 * Set Permission for Customers to view Top 5 movies by Sales. Public method.
	 * @param permission String containing permission.
	 */
    public void setTop5SalesPermission(String permission){
        this.customerViewTop5Sales = permission;
    }
	/**
	 * Set Permission for Customers to view Top 5 movies by overall Rating Score . Public method.
	 * @param permission String containing permission.
	 */
    public void setTop5OverallRatingPermission(String permission){
        this.customerViewTop5OverallRating = permission;
    }
	/**
	 * Set Rating score limit for customers . Public method.
     * Customers can only view reviews above this limit set by the staff. For e.g. if the limit is 3.0, then customers can only see reviews with a rating score > 3.
	 * @param ratingScoreLimit double containing ratingScoreLimit.
	 */
    public void setRatingScoreLimit(double ratingScoreLimit){
        this.ratingScoreLimit = ratingScoreLimit;
    }

    // Methods

    /**
	 * Read in and update the current permissions of system settings. To be called before further updating the system settings.
	 */
    public void updatePermissions(){
        BufferedReader br = null;
		String line = "";
        try {
			br = new BufferedReader(new FileReader(path));
			while((line = br.readLine()) !=null ) {
                String[] permissions = line.split(separator);
				if(!permissions[0].equals("top5SalesPermission")) {
                    String top5SalesPermission = permissions[0];
                    String top5OverallRatingPermission = permissions[1];
                    String ratingScoreLimitStr = permissions[2];
					this.setTop5SalesPermission(top5SalesPermission);
                    this.setTop5OverallRatingPermission(top5OverallRatingPermission);		
                    this.setRatingScoreLimit(Double.parseDouble(ratingScoreLimitStr));
				}
			}
			br.close();
		}		
        catch(FileNotFoundException e){
			System.out.println("File not found!");
            return;
		}
		catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Array out of bounds error!");
			return ;
		}
		catch (IOException e) {
            System.out.println("IO Error!");
			return;
		}catch(NumberFormatException e){
            System.out.println("Change back the data format of rating score limit in database!");
            return;
        }
    }

    /**
	 * Function to display the menu where the customer can view the top 5 movies normally
     * Customer able to view both top 5 movies by sales and ratings
     * Excludes movies that have are not showing currently or in preview / have 0 overall rating score/ 0 profit earned.
     * @param mList  Existing List of movies
	 */
    public void top5Movies(List<Movie>mList){
        int choice = 0;
        while(true){
            System.out.print("================== SHOW TOP 5 MOVIES ====================\n" +
                               " 1. Top 5 Movies By Sales\n" +
                               " 2. Top 5 Movies By Ratings\n" +
                               " 3. Exit\n" +
                               "==========================================================\n");
            print("Choice:");
            try{
                choice = sc.nextInt();
                if(choice < 1 || choice > 3){
                    print("Please choose only 1 - 3.");
                    continue;
                }
                sc.nextLine();
            }catch(InputMismatchException e){
                print("Please enter a valid option from 1-3 only!");
                sc.nextLine();
                continue;
            }
            switch(choice){

                // Creates a Hashmap by reverse order, puts those movies which are showing now or previewing only
                // Then, because a hashmap only can have unique keys, this means the movieTitle must be the key, and the
                // sales is the key. Thus, since we have to sort by the sales, we put them in a new linked hash map after sorting.
                // then we get the keyset and just print each of them.

                // Rank by Value first, if tie, prints the first one in the map.
                // Excludes movies that have are not showing currently / have 0 overall rating score/ 0 profit earned
                case 1:
                    int i = 1;
                    DecimalFormat df = new DecimalFormat("0.00");
                    String result;
                    Map<String, Double> movieList = new HashMap<String, Double>();

                    for (Movie movieObj : mList) {
                        if (!(movieObj.getShowingStatus().equals(ShowingStatus.FINISHED_SHOWING)
                                || movieObj.getShowingStatus().equals(ShowingStatus.COMING_SOON) 
                                || movieObj.getOverallRatingScore() == 0
                                || movieObj.getProfitEarned() == 0 )) {
                            movieList.put(movieObj.getMovieTitle(), movieObj.getProfitEarned());
                        }
                    }
                    LinkedHashMap<String, Double> sortedList = new LinkedHashMap<>();
                    movieList.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(x -> sortedList.put(x.getKey(), x.getValue()));

                    Set<String> keys = sortedList.keySet();

                    System.out.println("======== Printing Top 5 Movies by Sales========");
                    if (sortedList.size() > 5) {
                        for(String key:keys){
                            if(i > 5){
                                break;
                            }
                            double value = sortedList.get(key);
                            result = df.format(value);
                            BigDecimal bd = new BigDecimal(result);
                            print(i + ". " + key + " [Ticket Sales: " + dollarFormat.format(bd) + "]");
                            i++;
                        }
                    } else {
                        for(String key:keys){
                            double value = sortedList.get(key);
                            result = df.format(value);
                            BigDecimal bd = new BigDecimal(result);
                            print(i + ". " + key + " [Ticket Sales: " + dollarFormat.format(bd) + "]");
                            i++;
                        }
                    }
                    continue;
                case 2:
                    int j = 1;
                    Map<String, Double> movList = new HashMap<String, Double>();
                    DecimalFormat df2 = new DecimalFormat("0.0");
                    String roundedRatingScore;
                    for (Movie movieObj : mList) {
                        if (!(movieObj.getShowingStatus().equals(ShowingStatus.FINISHED_SHOWING)
                                || movieObj.getShowingStatus().equals(ShowingStatus.COMING_SOON)
                                || movieObj.getOverallRatingScore() == 0
                                || movieObj.getProfitEarned() == 0 )) {
                                    movList.put(movieObj.getMovieTitle(), movieObj.getOverallRatingScore());
                        }
                    }
                    LinkedHashMap<String, Double> reverseSortedList = new LinkedHashMap<>();
                    movList.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(x -> reverseSortedList.put(x.getKey(), x.getValue()));

                    Set<String> Keys = reverseSortedList.keySet();

                    System.out.println("======== Printing Top 5 Movies by Overall Rating Score========");
                    if (reverseSortedList.size() > 5) {
                        for(String key:Keys){
                            if(j > 5){
                                break;
                            }
                            roundedRatingScore = df2.format(reverseSortedList.get(key));
                            print(j + ". " + key + " [Overall Rating Score: " + roundedRatingScore + "]");
                            j++;
                        }
                    } else {
                        for(String key:Keys){
                            roundedRatingScore = df2.format(reverseSortedList.get(key));
                            print(j + ". " + key + " [Overall Rating Score: " + roundedRatingScore + "]");
                            j++;
                        }
                    }
                    continue;                

                case 3:
                    print("Returning to Movie Menu...");
                    break;
            }
            break;
        }
    }

    /**
	 * Function to display the menu where the customer can only view top 5 movies by sales
     * Excludes movies that have are not showing currently or in preview / have 0 overall rating score/ 0 profit earned.
     * @param mList  Existing List of movies
	 */
    public void top5SalesOnly(List<Movie>mList){
        int choice = 0;
        DecimalFormat df = new DecimalFormat("0.00");
        while(true){
            System.out.print("================== SHOW TOP 5 MOVIES ====================\n" +
                               " 1. Top 5 Movies By Sales\n" +
                               " 2. Top 5 Movies By Ratings\n" +
                               " 3. Exit\n" +
                               "==========================================================\n");
            print("Choice:");
            try{
                choice = sc.nextInt();
                if(choice < 1 || choice > 3){
                    print("Please choose only 1 - 3.");
                    continue;
                }
                sc.nextLine();
            }catch(InputMismatchException e){
                print("Please enter a valid option from 1-3 only!");
                sc.nextLine();
                continue;
            }
            switch(choice){

                // Creates a Hashmap by reverse order, puts those movies which are showing now or previewing only
                // Then, because a hashmap only can have unique keys, this means the movieTitle must be the key, and the
                // sales is the key. Thus, since we have to sort by the sales, we put them in a new linked hash map after sorting.
                // then we get the keyset and just print each of them.

                // Rank by Value first, if tie, prints the first one in the map.
                // Excludes movies that have are not showing currently / have 0 overall rating score/ 0 profit earned
                case 1:
                    int i = 1;
                    Map<String, Double> movieList = new HashMap<String, Double>();

                    for (Movie movieObj : mList) {
                        if (!(movieObj.getShowingStatus().equals(ShowingStatus.FINISHED_SHOWING)
                                || movieObj.getShowingStatus().equals(ShowingStatus.COMING_SOON)
                                || movieObj.getOverallRatingScore() == 0
                                || movieObj.getProfitEarned() == 0)) {
                            movieList.put(movieObj.getMovieTitle(), movieObj.getProfitEarned());
                        }
                    }
                    LinkedHashMap<String, Double> sortedList = new LinkedHashMap<>();
                    movieList.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(x -> sortedList.put(x.getKey(), x.getValue()));

                    Set<String> keys = sortedList.keySet();

                    System.out.println("======== Printing Top 5 Movies by Sales========");
                    if (sortedList.size() > 5) {
                        for(String key:keys){
                            if(i > 5){
                                break;
                            }
                            double value = sortedList.get(key);
                            String result = df.format(value);
                            BigDecimal bd = new BigDecimal(result);
                            print(i + ". " + key + " [Ticket Sales: " + dollarFormat.format(bd) + "]");
                            i++;
                        }
                    } else {
                        for(String key:keys){
                            double value = sortedList.get(key);
                            String result = df.format(value);
                            BigDecimal bd = new BigDecimal(result);
                            print(i + ". " + key + " [Ticket Sales: " + dollarFormat.format(bd) + "]");
                            i++;
                        }
                    }
                    continue;
                case 2:
                    System.out.println("Sorry! We are currently not able to show you the Top 5 Movies by Ratings!");
                    System.out.println("Try again at a later date!");
                    continue;                

                case 3:
                    print("Returning to Movie Menu...");
                    break;
            }
            break;
        }
    }
    
    /**
	 * Function to display the menu where the customer can only view top 5 movies by ratings
     * Excludes movies that have are not showing currently or in preview / have 0 overall rating score/ 0 profit earned.
     * @param mList  Existing List of movies
	 */
    public void top5RatingsOnly(List<Movie>mList){
        int choice = 0;
        DecimalFormat df = new DecimalFormat("0.0");
        while(true){
            System.out.print("================== SHOW TOP 5 MOVIES ====================\n" +
                               " 1. Top 5 Movies By Sales\n" +
                               " 2. Top 5 Movies By Ratings\n" +
                               " 3. Exit\n" +
                               "==========================================================\n");
            print("Choice:");
            try{
                choice = sc.nextInt();
                if(choice < 1 || choice > 3){
                    print("Please choose only 1 - 3.");
                    continue;
                }
                sc.nextLine();
            }catch(InputMismatchException e){
                print("Please enter a valid option from 1-3 only!");
                sc.nextLine();
                continue;
            }
            switch(choice){

                // Creates a Hashmap by reverse order, puts those movies which are showing now or previewing only
                // Then, because a hashmap only can have unique keys, this means the movieTitle must be the key, and the
                // sales is the key. Thus, since we have to sort by the sales, we put them in a new linked hash map after sorting.
                // then we get the keyset and just print each of them.

                // Rank by Value first, if tie, prints the first one in the map.
                case 1:
                    System.out.println("Sorry! We are currently not able to show you the Top 5 Movies by Sales!");
                    System.out.println("Try again at a later date!");
                    continue;
                case 2:
                    int j = 1;
                    Map<String, Double> movList = new HashMap<String, Double>();

                    for (Movie movieObj : mList) {
                        if (!(movieObj.getShowingStatus().equals(ShowingStatus.FINISHED_SHOWING)
                                || movieObj.getShowingStatus().equals(ShowingStatus.COMING_SOON)
                                || movieObj.getOverallRatingScore() == 0
                                || movieObj.getProfitEarned() == 0)) {
                                    movList.put(movieObj.getMovieTitle(), movieObj.getOverallRatingScore());
                        }
                    }
                    LinkedHashMap<String, Double> reverseSortedList = new LinkedHashMap<>();
                    movList.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(x -> reverseSortedList.put(x.getKey(), x.getValue()));

                    Set<String> Keys = reverseSortedList.keySet();

                    System.out.println("======== Printing Top 5 Movies by Overall Rating Score========");
                    if (reverseSortedList.size() > 5) {
                        for(String key:Keys){
                            if(j > 5){
                                break;
                            }
                            String roundedRatingScore = df.format(reverseSortedList.get(key));
                            print(j + ". " + key + " [Overall Rating Score: " + roundedRatingScore + "]");
                            j++;
                        }
                    } else {
                        for(String key:Keys){
                            String roundedRatingScore = df.format(reverseSortedList.get(key));
                            print(j + ". " + key + " [Overall Rating Score: " + roundedRatingScore + "]");
                            j++;
                        }
                    }
                    continue;              

                case 3:
                    print("Returning to Movie Menu...");
                    break;
            }
            break;
        }

    }

    /**
	 * Function to display the menu where customer cannot view both top 5 movies by sales and ratings.
     * A placeholder message will be displayed instead.
	 */
    public void viewNone(){
        int choice = 0;
        while(true){
            System.out.print("================== SHOW TOP 5 MOVIES ====================\n" +
                               " 1. Top 5 Movies By Sales\n" +
                               " 2. Top 5 Movies By Ratings\n" +
                               " 3. Exit\n" +
                               "==========================================================\n");
            print("Choice:");
            try{
                choice = sc.nextInt();
                if(choice < 1 || choice > 3){
                    print("Please choose only 1 - 3.");
                    continue;
                }
                sc.nextLine();
            }catch(InputMismatchException e){
                print("Please enter a valid option from 1-3 only!");
                sc.nextLine();
                continue;
            }
            switch(choice){
                case 1:
                    System.out.println("Sorry! We are currently not able to show you the Top 5 Movies by Sales!");
                    System.out.println("Try again at a later date!");
                    continue;
                case 2:
                    System.out.println("Sorry! We are currently not able to show you the Top 5 Movies by Rating Score!");
                    System.out.println("Try again at a later date!");
                    continue;              

                case 3:
                    print("Returning to Movie Menu...");
                    break;
            }
            break;
        }
    }
}
