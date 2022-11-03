package managers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import entities.*;
import static utils.IOUtils.*;

public class Top5Movies {
    
    private String customerViewTop5Sales;
    private String customerViewTop5OverallRating;
    private static Scanner sc = new Scanner(System.in);
    // Ensure single instance
    private static Top5Movies single_instance = null;
	public static Top5Movies getInstance()
    {
        if (single_instance == null)
            single_instance = new Top5Movies();
        return single_instance;
    }

    // Constructor
    public Top5Movies(){
        this.customerViewTop5Sales = "Y";
        this.customerViewTop5OverallRating = "Y";
    }

    static String path = System.getProperty("user.dir") +"\\data\\staffs\\top5Settings.csv";
    static String separator = ",";

    // Gettors
    public String getTop5SalesPermission(){
        return this.customerViewTop5Sales;
    }
    public String getTop5OverallRatingPermission(){
        return this.customerViewTop5OverallRating;
    }

    // Settors
    public void setTop5SalesPermission(String permission){
        this.customerViewTop5Sales = permission;
    }
    public void setTop5OverallRatingPermission(String permission){
        this.customerViewTop5OverallRating = permission;
    }


    // Methods
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
					this.setTop5SalesPermission(top5SalesPermission);
                    this.setTop5OverallRatingPermission(top5OverallRatingPermission);		
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
		}
    }

    // View Top 5 Menu Normally

    public void top5Movies(List<Movie>mList){
        int choice = 0;
        while(true){
            System.out.println("================== SHOW TOP 5 MOVIES ====================\n" +
                               " 1. Top 5 Movies By Sales 						         \n" +
                               " 2. Top 5 Movies By Ratings                              \n" +
                               " 3. Exit                                                 \n" +
                               "==========================================================");
            print("Choice:");
            try{
                choice = sc.nextInt();
                if(choice < 1 || choice > 3){
                    print("Please choose only 1 - 3.");
                    continue;
                }
                sc.nextLine();
            }catch(InputMismatchException e){
                print("Please enter numbers only!");
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
                    int i = 1;
                    Map<String, Double> movieList = new HashMap<String, Double>();

                    for (Movie movieObj : mList) {
                        if (!(movieObj.getShowingStatus().equals(ShowingStatus.FINISHED_SHOWING)
                                || movieObj.getShowingStatus().equals(ShowingStatus.COMING_SOON))) {
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
                            BigDecimal bd = new BigDecimal(value);
                            print(i + ". " + key + " [Ticket Sales: " + bd.toPlainString() + " ]");
                            i++;
                        }
                    } else {
                        for(String key:keys){
                            double value = sortedList.get(key);
                            BigDecimal bd = new BigDecimal(value);
                            print(i + ". " + key + " [Ticket Sales: " + bd.toPlainString() + " ]");
                            i++;
                        }
                    }
                    continue;
                case 2:
                    int j = 1;
                    Map<String, Double> movList = new HashMap<String, Double>();

                    for (Movie movieObj : mList) {
                        if (!(movieObj.getShowingStatus().equals(ShowingStatus.FINISHED_SHOWING)
                                || movieObj.getShowingStatus().equals(ShowingStatus.COMING_SOON))) {
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
                            print(j + ". " + key + " [Overall Rating Score: " + reverseSortedList.get(key) + " ]");
                            j++;
                        }
                    } else {
                        for(String key:Keys){
                            print(j + ". " + key + " [Overall Rating Score: " + reverseSortedList.get(key) + " ]");
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

    // View top 5 Sales only 

    public void top5SalesOnly(List<Movie>mList){
        int choice = 0;
        while(true){
            System.out.println("================== SHOW TOP 5 MOVIES ====================\n" +
                               " 1. Top 5 Movies By Sales 						         \n" +
                               " 2. Top 5 Movies By Ratings                              \n" +
                               " 3. Exit                                                 \n" +
                               "==========================================================");
            print("Choice:");
            try{
                choice = sc.nextInt();
                if(choice < 1 || choice > 3){
                    print("Please choose only 1 - 3.");
                    continue;
                }
                sc.nextLine();
            }catch(InputMismatchException e){
                print("Please enter numbers only!");
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
                    int i = 1;
                    Map<String, Double> movieList = new HashMap<String, Double>();

                    for (Movie movieObj : mList) {
                        if (!(movieObj.getShowingStatus().equals(ShowingStatus.FINISHED_SHOWING)
                                || movieObj.getShowingStatus().equals(ShowingStatus.COMING_SOON))) {
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
                            BigDecimal bd = new BigDecimal(value);
                            print(i + ". " + key + " [Ticket Sales: " + bd.toPlainString() + " ]");
                            i++;
                        }
                    } else {
                        for(String key:keys){
                            double value = sortedList.get(key);
                            BigDecimal bd = new BigDecimal(value);
                            print(i + ". " + key + " [Ticket Sales: " + bd.toPlainString() + " ]");
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
    
    // View top 5 ratings only
    public void top5RatingsOnly(List<Movie>mList){
        int choice = 0;
        while(true){
            System.out.println("================== SHOW TOP 5 MOVIES ====================\n" +
                               " 1. Top 5 Movies By Sales 						         \n" +
                               " 2. Top 5 Movies By Ratings                              \n" +
                               " 3. Exit                                                 \n" +
                               "==========================================================");
            print("Choice:");
            try{
                choice = sc.nextInt();
                if(choice < 1 || choice > 3){
                    print("Please choose only 1 - 3.");
                    continue;
                }
                sc.nextLine();
            }catch(InputMismatchException e){
                print("Please enter numbers only!");
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
                                || movieObj.getShowingStatus().equals(ShowingStatus.COMING_SOON))) {
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
                            print(j + ". " + key + " [Overall Rating Score: " + reverseSortedList.get(key) + " ]");
                            j++;
                        }
                    } else {
                        for(String key:Keys){
                            print(j + ". " + key + " [Overall Rating Score: " + reverseSortedList.get(key) + " ]");
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

    // View None
    public void viewNone(){
        int choice = 0;
        while(true){
            System.out.println("================== SHOW TOP 5 MOVIES ====================\n" +
                               " 1. Top 5 Movies By Sales 						         \n" +
                               " 2. Top 5 Movies By Ratings                              \n" +
                               " 3. Exit                                                 \n" +
                               "==========================================================");
            print("Choice:");
            try{
                choice = sc.nextInt();
                if(choice < 1 || choice > 3){
                    print("Please choose only 1 - 3.");
                    continue;
                }
                sc.nextLine();
            }catch(InputMismatchException e){
                print("Please enter numbers only!");
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
