package managers;

public class MovieManager {
    private void subMovieMenu(Movie movie, String appType){
        if(appType.equals("Staff")) {
            int choice;
            do{
                System.out.println(	"====================== MOVIE CHOICES =====================\n" +
			                        " 1. Display/Edit Showtimes                              \n" +
			                        " 2. Edit Movie 						       		      \n" +
			                        " 3. Remove Movie		                                  \n" +
			                        " 4. View Reviews	                                      \n" +
			                        " 5. Delete Reviews	                                  \n" +
			                        " 0. Back to Movie Listings			                  \n"+
                                    "==========================================================");

                System.out.println("Enter choice: ");
                
                while (!sc.hasNextInt()) {
            		System.out.println("Invalid input type. Please choose a choice from 0-5.");
            		sc.next(); // Remove newline character
            	}
                choice = sc.nextInt();
                switch (choice) {
                //     //case 1:
                //         ShowtimeManager.getInstance().getMovieShowtimes(movie.getMovieID(), appType);
                //         break;
                //    // case 2:
                //         this.editMovies(movie);
                //         break;
                //     case 3:
                //         this.removeMovie(movie);
                //         break;
                    case 4:
                        ReviewManager.getInstance().printReviews(movie.getReviews());
                        break;
                    case 5:
                    	ReviewManager.getInstance().deleteReview(movie.getReviews());
                    	break;
                    case 0:
                    	System.out.println("Back to Movie Listings......");
                        break;
                    default:
                        System.out.println("Please enter a number between 0-5");
                }
            }while(choice != 0);
        }
        else if(appType.equals("Customer") && !movie.getShowingStatus().equals(ShowingStatus.COMING_SOON)){
            int choice;
            do{
                System.out.println(	"====================== MOVIE CHOICES =====================\n" +
			                        " 1. Display Showtimes                                   \n" +
			                        " 2. View Reviews                                        \n" +
			                        " 3. Leave Review                                        \n" +
			                        " 0. Back to Movie Listings                              \n"+
                                    "==========================================================");

                System.out.println("Enter your choice: ");
                
                while (!sc.hasNextInt()) {
            		System.out.println("Invalid input type. Please choose a choice from 0-2.");
            		sc.next(); // Remove newline character
            	}
                
                choice = sc.nextInt();
                sc.nextLine();
                
                switch (choice) {
                    case 1:
                        ShowtimeManager.getInstance().getMovieShowtimes(movie.getMovieID(),appType);
                        break;
                    case 2:
                        ReviewManager.getInstance().printReviews(movie.getReviews());
                        break;
                    case 3:
                    	ReviewManager.getInstance().addReview(movie.getMovieID());
                    	break;
                    case 0:
                    	System.out.println("Back to Movie Listings......");
                        break;
                    default:
                        System.out.println("Please enter a number between 0-3");
                        break;
                }
            } while(choice != 0);

        }
    }
}
