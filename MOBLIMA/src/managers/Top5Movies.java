package managers;

import java.util.*;

import entities.*;
import static utils.IOUtils.*;

public class Top5Movies {
    
    public Top5Movies(){}

    public static void top5Movies(List<Movie>mList){
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while(true){
            System.out.println("=============== SHOW TOP 5 MOVIES (STAFF) ===============\n" +
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
                            print(i + ". " + key + " [Ticket Sales: " + sortedList.get(key) + " ]");
                            i++;
                        }
                    } else {
                        for(String key:keys){
                            print(i + ". " + key + " [Ticket Sales: " + sortedList.get(key) + " ]");
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
    
}
