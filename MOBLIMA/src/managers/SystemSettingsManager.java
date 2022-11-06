package managers;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import entities.*;
import view.adminApp;
import static utils.IOUtils.*;

/**
 * A manager class for all actions related to the staff to configure the system settings
 * @author Wei Kang
 * @version 2.5
 * @since 01-11-2022
 */
public class SystemSettingsManager {
	/**
	 * The scanner for reading input of user
	 */
    private Scanner sc = new Scanner(System.in);
	/**
	 * For singleton pattern adherence. This SystemSettingsManager instance persists throughout runtime.
	 */
    private static SystemSettingsManager single_instance = null;
	/**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
    public static SystemSettingsManager getInstance()
    {
        if (single_instance == null)
            single_instance = new SystemSettingsManager();
        return single_instance;
    }
	/**
	 * The default constructor for the SystemSettingsManager class
	 */
    private SystemSettingsManager(){}
    /**
	 * The path to the CSV file that stores all the staff system settings
	 */
    private static String path = System.getProperty("user.dir") +"\\data\\staffs\\staffsSettings.csv";
	/**
	 * The separator for the columns in the csv file
	 */
    private static String separator = ",";
	/**
	 * Staff's Menu to manage system settings
     * Choose options to configure ticket prices, customer's access to view top 5 movies, holidays, rating score limit for movies
     * @param choice User's option
     * @param account User's account
	 */
    public void staffMenu(int choice,Account account){
        int option = 0;
        
        try{
            if(choice == 0){
                System.out.println("==================== SYSTEM SETTINGS STAFF APP ====================\n" +
            					" 1. Configure Ticket Prices                                           \n" +
                                " 2. Configure Top 5 Movies Permissions                                \n" + 
			            		" 3. Configure Holidays                                                \n" +
                                " 4. Configure Rating Score Limit for Movies                           \n" +
			                    " 5. Back to Staff App                                                 \n" +
                                "=======================================================================");
                System.out.println("Enter choice: ");
                option = sc.nextInt();
                if(!(option >= 1 && option <=5)){
                    System.out.println("Please only enter a number from 1-5.");
                    staffMenu(0, account);
                }
            }
        }
        catch(InputMismatchException e){
            System.out.println("Invalid Input.");
            sc.nextLine();
            staffMenu(0, account);
        }
        switch (option) {
            case 1: 
                configureTicketPriceMenu(account);
                break;
            case 2:
                configureTop5(account);
                break;
            case 3:
                configureHolidays(account);
                break;
            case 4:
                configureRatingScoreLimit(account);
                break;
            case 5:
                System.out.println("Back to Staff App......");
                sc.nextLine();
                adminApp.getInstance().displayLoggedInMenu(account);

            default:
                System.out.println("Invalid choice. Please choose between 1-4.");
                staffMenu(0,account);
        }
    }

	/**
	 * Staff's Menu to manage configure ticket prices
     * Choose options to configure ticket prices for different ticket types and multiplier rates for different movie types, seat types & cinema classes
     * @param account User's account
	 */
    public void configureTicketPriceMenu(Account account){
        System.out.println("###########################################################");
		System.out.println("################# CONFIGURING TICKET PRICE ################");
		System.out.println("###########################################################");
		System.out.println("");
        while (true){
            System.out.println("Please choose an option:");
            System.out.println("1. Configure Ticket Price for Different Ticket Types");
            System.out.println("2. Configure Multiplier Rates for Different Movie Types");
            System.out.println("3. Configure Multiplier Rates for Different Seat Types");
            System.out.println("4. Configure Multiplier Rates for Different Cinema Classes");
            System.out.println("5. Back to SystemSettingsManager");
            int option;
            try{
                option = sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Enter a number from 1-5 only!");
                sc.nextLine();
                continue;
            }
            switch (option) {
                case 1:
                    configureTicketTypePrice(account);
                    break;
                case 2:
                    configureMovieTypeMultiplier(account);
                    break;
                case 3:
                    configureSeatTypeMultiplier(account);
                    break;
                case 4:
                    configureCinemaClassMultiplier(account);
                    break;
                case 5:
                    System.out.println("Back to System Settings......");
                    this.staffMenu(0, account);
                default:
                    System.out.println("Invalid choice. Please choose between 1-5 only.");
                    continue;
            }
            break;
        }
        this.staffMenu(0, account);
    }
    /**
	 * Function to configure cinema class multipler
     * @param account  staff account
	 */
    public void configureCinemaClassMultiplier(Account account){
        int count = 1;
        TicketPrice tpObj = new TicketPrice();
        Map<CinemaClass, Double> cinemaClassPriceList = new EnumMap<>(CinemaClass.class);
        cinemaClassPriceList = tpObj.getMappedCinemaClassPrice();
        while(true){
            System.out.println("Please choose a cinema class to change multiplier:");
            System.out.println("Current Rates: ");
            for (Entry<CinemaClass, Double> entry : cinemaClassPriceList.entrySet()) {
                System.out.println(Integer.toString(count) + ". " + entry.getKey() + ": \t[" + entry.getValue() + "]");     
                count++;
            }
            System.out.println(Integer.toString(count) + ". Exit");
            count = 1;
            int option;
            try{
                option = sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Please choose an option from 1-5 only!");
                sc.nextLine();
                continue;
            }
            double multiplier;
            switch (option) {
                case 1:
                    if(confirm("Confirm Update Multiplier for Silver Class")){
                        try{
                            System.out.println("Please enter the new multiplier rate: ");
                            multiplier =  sc.nextDouble();
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            System.out.println("Please enter a valid multiplier rate!");
                            sc.nextLine();
                            continue;
                        }
                        if(multiplier < 0 ){
                            System.out.println("Please enter a valid multiplier rate!");
                            continue;
                        }
                        tpObj.setCCPrice(0,multiplier);
                    }
                    else{
                        continue;
                    }
                    break;
                case 2:
                    if(confirm("Confirm Update Multiplier for Gold Class")){
                        try{
                            System.out.println("Please enter the new multiplier rate: ");
                            multiplier =  sc.nextDouble();
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            System.out.println("Please enter a valid multiplier rate!");
                            sc.nextLine();
                            continue;
                        }
                        if(multiplier < 0 ){
                            System.out.println("Please enter a valid multiplier rate!");
                            continue;
                        }
                        tpObj.setCCPrice(1,multiplier);
                    }
                    else{
                        continue;
                    }
                    break;
                case 3:
                    if(confirm("Confirm Update Multiplier for Platinum Class")){
                        try{
                            System.out.println("Please enter the new multiplier rate: ");
                            multiplier =  sc.nextDouble();
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            System.out.println("Please enter a valid multiplier rate!");
                            sc.nextLine();
                            continue;
                        }
                        if(multiplier < 0 ){
                            System.out.println("Please enter a valid multiplier rate!");
                            continue;
                        }
                        tpObj.setCCPrice(2,multiplier);
                    }
                    else{
                        continue;
                    }
                    break;
                case 4:
                    if(confirm("Confirm Update Multiplier for Diamond Class")){
                        try{
                            System.out.println("Please enter the new multiplier rate: ");
                            multiplier =  sc.nextDouble();
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            System.out.println("Please enter a valid multiplier rate!");
                            sc.nextLine();
                            continue;
                        }
                        if(multiplier < 0 ){
                            System.out.println("Please enter a valid multiplier rate!");
                            continue;
                        }
                        tpObj.setCCPrice(3,multiplier);
                    }
                    else{
                        continue;
                    }
                    break;
                case 5:
                    System.out.println("Back to System Settings......");
                    this.configureTicketPriceMenu(account);
                default:
                    System.out.println("Invalid choice. Please choose between 1-5 only.");
                    continue;
            }
            break;
        }
        System.out.println("Multiplier successfully updated!");
    }
    /**
	 * Function to configure seat type multipler
     * @param account  staff account
	 */
    public void configureSeatTypeMultiplier(Account account){
        int count = 1;
        TicketPrice tpObj = new TicketPrice();
        Map<SeatType, Double> seatTypePriceList = new EnumMap<>(SeatType.class);
        seatTypePriceList = tpObj.getMappedSeatTypePrice();
        while(true){
            System.out.println("Please choose a seat type to change multiplier:");
            System.out.println("Current Rates: ");
            for (Entry<SeatType, Double> entry : seatTypePriceList.entrySet()) {
                System.out.println(Integer.toString(count) + ". " + entry.getKey() + ": \t[" + entry.getValue() + "]");     
                count++;
            }
            System.out.println(Integer.toString(count) + ". Exit");
            count = 1;
            int option;
            try{
                option = sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Please choose an option from 1-5 only!");
                sc.nextLine();
                continue;
            }
            double multiplier;
            switch (option) {
                case 1:
                    if(confirm("Confirm Update Multiplier for Regular Seats")){
                        try{
                            System.out.println("Please enter the new multiplier rate: ");
                            multiplier =  sc.nextDouble();
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            System.out.println("Please enter a valid multiplier rate!");
                            sc.nextLine();
                            continue;
                        }
                        if(multiplier < 0 ){
                            System.out.println("Please enter a valid multiplier rate!");
                            continue;
                        }
                        tpObj.setSTPrice(0,multiplier);
                    }
                    else{
                        continue;
                    }
                    break;
                case 2:
                    if(confirm("Confirm Update Multiplier for Elite Seats")){
                        try{
                            System.out.println("Please enter the new multiplier rate: ");
                            multiplier =  sc.nextDouble();
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            System.out.println("Please enter a valid multiplier rate!");
                            sc.nextLine();
                            continue;
                        }
                        if(multiplier < 0 ){
                            System.out.println("Please enter a valid multiplier rate!");
                            continue;
                        }
                        tpObj.setSTPrice(1,multiplier);
                    }
                    else{
                        continue;
                    }
                    break;
                case 3:
                    if(confirm("Confirm Update Multiplier for Couple Seats")){
                        try{
                            System.out.println("Please enter the new multiplier rate: ");
                            multiplier =  sc.nextDouble();
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            System.out.println("Please enter a valid multiplier rate!");
                            sc.nextLine();
                            continue;
                        }
                        if(multiplier < 0 ){
                            System.out.println("Please enter a valid multiplier rate!");
                            continue;
                        }
                        tpObj.setSTPrice(2,multiplier);
                    }
                    else{
                        continue;
                    }
                    break;
                case 4:
                    if(confirm("Confirm Update Multiplier for Ultimate Seats")){
                        try{
                            System.out.println("Please enter the new multiplier rate: ");
                            multiplier =  sc.nextDouble();
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            System.out.println("Please enter a valid multiplier rate!");
                            sc.nextLine();
                            continue;
                        }
                        if(multiplier < 0 ){
                            System.out.println("Please enter a valid multiplier rate!");
                            continue;
                        }
                        tpObj.setSTPrice(3,multiplier);
                    }
                    else{
                        continue;
                    }
                    break;
                case 5:
                    System.out.println("Back to System Settings......");
                    this.configureTicketPriceMenu(account);
                default:
                    System.out.println("Invalid choice. Please choose between 1-5 only.");
                    continue;
            }
            break;
        }
        System.out.println("Multiplier successfully updated!");
    }
    /**
	 * Function to configure movie type multipler
     * @param account  staff account
	 */
    public void configureMovieTypeMultiplier(Account account){
        int count = 1;
        TicketPrice tpObj = new TicketPrice();
        Map<MovieType, Double> movieTypePriceList = new EnumMap<>(MovieType.class);
        movieTypePriceList = tpObj.getMappedMovieTypePrice();
        while(true){
            System.out.println("Please choose a movie type to change multiplier:");
            System.out.println("Current Rates: ");
            for (Entry<MovieType, Double> entry : movieTypePriceList.entrySet()) {
                if(count == 4){
                    System.out.println(Integer.toString(count) + ". " + entry.getKey() + ": [" + entry.getValue() + "]"); 
                }else{
                    System.out.println(Integer.toString(count) + ". " + entry.getKey() + ": \t[" + entry.getValue() + "]"); 
                }      
                count++;
            }
            System.out.println(Integer.toString(count) + ". Exit");
            count = 1;
            int option;
            try{
                option = sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Please choose an option from 1-5 only!");
                sc.nextLine();
                continue;
            }
            double multiplier;
            switch (option) {
                case 1:
                    if(confirm("Confirm Update Multiplier for TWOD")){
                        try{
                            System.out.println("Please enter the new multiplier rate: ");
                            multiplier =  sc.nextDouble();
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            System.out.println("Please enter a valid multiplier rate!");
                            sc.nextLine();
                            continue;
                        }
                        if(multiplier < 0 ){
                            System.out.println("Please enter a valid multiplier rate!");
                            continue;
                        }
                        tpObj.setMtPrice(0,multiplier);
                    }
                    else{
                        continue;
                    }
                    break;
                case 2:
                    if(confirm("Confirm Update Multiplier for THREED")){
                        try{
                            System.out.println("Please enter the new multiplier rate: ");
                            multiplier =  sc.nextDouble();
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            System.out.println("Please enter a valid multiplier rate!");
                            sc.nextLine();
                            continue;
                        }
                        if(multiplier < 0 ){
                            System.out.println("Please enter a valid multiplier rate!");
                            continue;
                        }
                        tpObj.setMtPrice(1,multiplier);
                    }
                    else{
                        continue;
                    }
                    break;
                case 3:
                    if(confirm("Confirm Update Multiplier for IMAX")){
                        try{
                            System.out.println("Please enter the new multiplier rate: ");
                            multiplier =  sc.nextDouble();
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            System.out.println("Please enter a valid multiplier rate!");
                            sc.nextLine();
                            continue;
                        }
                        if(multiplier < 0 ){
                            System.out.println("Please enter a valid multiplier rate!");
                            continue;
                        }
                        tpObj.setMtPrice(2,multiplier);
                    }
                    else{
                        continue;
                    }
                    break;
                case 4:
                    if(confirm("Confirm Update Multiplier for BLOCKBUSTER")){
                        try{
                            System.out.println("Please enter the new multiplier rate: ");
                            multiplier =  sc.nextDouble();
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            System.out.println("Please enter a valid multiplier rate!");
                            sc.nextLine();
                            continue;
                        }
                        if(multiplier < 0 ){
                            System.out.println("Please enter a valid multiplier rate!");
                            continue;
                        }
                        tpObj.setMtPrice(3,multiplier);
                    }
                    else{
                        continue;
                    }
                    break;
                case 5:
                    System.out.println("Back to System Settings......");
                    this.configureTicketPriceMenu(account);
                default:
                    System.out.println("Invalid choice. Please choose between 1-5 only.");
                    continue;
            }
            break;
        }
        System.out.println("Multiplier successfully updated!");
    }
    /**
	 * Function to configure ticket type multipler
     * @param account  staff account
	 */
    public void configureTicketTypePrice(Account account){
        int count = 1;
        TicketPrice tpObj = new TicketPrice();
        Map<TicketType, Double> priceList = new EnumMap<>(TicketType.class);
        priceList = tpObj.getMappedPrice();
        while(true){
            System.out.println("Please choose a ticket type to change price:");
            System.out.println("Current Rates: ");
            for (Entry<TicketType, Double> entry : priceList.entrySet()) {       
                System.out.println(Integer.toString(count) + ". " + entry.getKey() + ": \t[" + entry.getValue() + "]"); 
                count++;
            }
            System.out.println(Integer.toString(count) + ". Exit");
            count = 1;
            int option;
            try{
                option = sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Please choose an option from 1-9 only!");
                sc.nextLine();
                continue;
            }
            double price;
            switch (option) {
                case 1:
                    if(confirm("Confirm Update Price for Monday To Wednesday")){
                        try{
                            System.out.println("Please enter the new price: ");
                            price =  sc.nextDouble();
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            System.out.println("Please enter a valid price!");
                            sc.nextLine();
                            continue;
                        }
                        if(price < 0 ){
                            System.out.println("Please enter a valid price!");
                            continue;
                        }
                        tpObj.setPrice(0,price);
                    }
                    else{
                        continue;
                    }
                    break;
                case 2:
                    if(confirm("Confirm Update Price for Thursday")){
                        try{
                            System.out.println("Please enter the new price: ");
                            price =  sc.nextDouble();
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            System.out.println("Please enter a valid price!");
                            sc.nextLine();
                            continue;
                        }
                        if(price < 0 ){
                            System.out.println("Please enter a valid price!");
                            continue;
                        }
                        tpObj.setPrice(1,price);
                    }
                    else{
                        continue;
                    }
                    break;
                case 3:
                    if(confirm("Confirm Update Price for Friday before 6pm")){
                        try{
                            System.out.println("Please enter the new price: ");
                            price =  sc.nextDouble();
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            System.out.println("Please enter a valid price!");
                            sc.nextLine();
                            continue;
                        }
                        if(price < 0 ){
                            System.out.println("Please enter a valid price!");
                            continue;
                        }
                        tpObj.setPrice(2,price);
                    }
                    else{
                        continue;
                    }
                    break;
                case 4:
                    if(confirm("Confirm Update Price for Friday after 6pm")){
                        try{
                            System.out.println("Please enter the new price: ");
                            price =  sc.nextDouble();
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            System.out.println("Please enter a valid price!");
                            sc.nextLine();
                            continue;
                        }
                        if(price < 0 ){
                            System.out.println("Please enter a valid price!");
                            continue;
                        }
                        tpObj.setPrice(3,price);
                    }
                    else{
                        continue;
                    }
                    break;
                case 5:
                    if(confirm("Confirm Update Price for Weekend")){
                        try{
                            System.out.println("Please enter the new price: ");
                            price =  sc.nextDouble();
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            System.out.println("Please enter a valid price!");
                            sc.nextLine();
                            continue;
                        }
                        if(price < 0 ){
                            System.out.println("Please enter a valid price!");
                            continue;
                        }
                        tpObj.setPrice(4,price);
                    }
                    else{
                        continue;
                    }
                    break;
                case 6:
                    if(confirm("Confirm Update Price for Senior Weekday")){
                        try{
                            System.out.println("Please enter the new price: ");
                            price =  sc.nextDouble();
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            System.out.println("Please enter a valid price!");
                            sc.nextLine();
                            continue;
                        }
                        if(price < 0 ){
                            System.out.println("Please enter a valid price!");
                            continue;
                        }
                        tpObj.setPrice(5,price);
                    }
                    else{
                        continue;
                    }
                    break;
                case 7:
                    if(confirm("Confirm Update Price for Student Weekday")){
                        try{
                            System.out.println("Please enter the new price: ");
                            price =  sc.nextDouble();
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            System.out.println("Please enter a valid price!");
                            sc.nextLine();
                            continue;
                        }
                        if(price < 0 ){
                            System.out.println("Please enter a valid price!");
                            continue;
                        }
                        tpObj.setPrice(6,price);
                    }
                    else{
                        continue;
                    }
                    break;
                case 8:
                    if(confirm("Confirm Update Price for Holiday")){
                        try{
                            System.out.println("Please enter the new price: ");
                            price =  sc.nextDouble();
                            sc.nextLine();
                        }catch(InputMismatchException e){
                            System.out.println("Please enter a valid price!");
                            sc.nextLine();
                            continue;
                        }
                        if(price < 0 ){
                            System.out.println("Please enter a valid price!");
                            continue;
                        }
                        tpObj.setPrice(7,price);
                    }
                    else{
                        continue;
                    }
                    break;
                case 9:
                    System.out.println("Back to System Settings......");
                    this.configureTicketPriceMenu(account);
                default:
                    System.out.println("Invalid choice. Please choose between 1-9 only.");
                    continue;
            }
            break;
        }
        System.out.println("Price successfully updated!");
    }

    /**
	 * Function to configure rating score limit
     * Any movie that has a overall rating score below the rating score limit will not be displayed to the customer.
     * @param account  staff account
	 */
    public void configureRatingScoreLimit(Account account){
        System.out.println("###########################################################");
		System.out.println("############## CONFIGURING DISPLAY OF RATINGS #############");
		System.out.println("###########################################################");
		System.out.println("");
        SystemSettings systemSettings = SystemSettings.getInstance();
        systemSettings.updatePermissions();
        while (true){
            System.out.println("Please choose an option:");
            System.out.println("1. Configure Rating Score Limit for Customers");
            System.out.println("2. Back to SystemSettingsManager");
            int option;
            try{
                option= sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Enter a number from 1-2 only!");
                sc.nextLine();
                continue;
            }
            switch (option) {
                case 1:

                    if(confirm("Confirm To Configure Rating Score Limit for Customers")){
                        while(true){
                           try{
                                System.out.println("Enter Rating Score Limit for Customers:");
                                double limit = sc.nextDouble();
                                sc.nextLine();
                                if(limit < 1 || limit > 5){
                                    System.out.println("Enter a valid rating score from 1-5 only!");
                                    continue;
                                }
                                systemSettings.setRatingScoreLimit(limit);
                                break;
                            }catch(InputMismatchException e){
                                System.out.println("Enter a valid rating score from 1-5 only!");
                                sc.nextLine();
                                continue;
                            }
                        }
                        updateSystemSettingsCSV(systemSettings);
                        System.out.println("System Settings Updated!");
                    }
                    else{
                        continue;
                    }
                    break;
                case 2:
                    System.out.println("Back to System Settings......");
                    this.staffMenu(0, account);
                default:
                    System.out.println("Invalid choice. Please choose between 1 or 2 only.");
                    continue;
            }
            break;
        }
        this.staffMenu(0, account);
    }
    /**
	 * Function to configure top 5 movies settings
     * Staff can choose to hide the top 5 movies by sales rankings from the customer
     * Staff can choose to hide the top 5 movies by overall ratings from the customer
     * @param account  staff account
	 */
    public void configureTop5(Account account){
        System.out.println("###########################################################");
		System.out.println("#################### CONFIGURING TOP 5 ####################");
		System.out.println("###########################################################");
		System.out.println("");
        SystemSettings systemSettings = SystemSettings.getInstance();
        systemSettings.updatePermissions();
        while (true){
            System.out.println("Please choose an option:");
            System.out.println("1. Configure Top 5 Movies Settings");
            System.out.println("2. Back to SystemSettingsManager");
            int option;
            try{
                option = sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Enter a number from 1-2 only!");
                sc.nextLine();
                continue;
            }
            switch (option) {
                case 1:
                    if(confirm("Confirm To Configure Top 5 Movies Settings")){
                        while(true){
                            System.out.println("Should customers be able to see Top 5 Movies by Sales? (Y/N)");
                            String input = sc.nextLine();
                            if(input.equalsIgnoreCase("y")){
                                systemSettings.setTop5SalesPermission("Y");
                                break;
                            }
                            else if(input.equalsIgnoreCase("n")){
                                systemSettings.setTop5SalesPermission("N");
                                break;
                            }
                            else{
                                System.out.println("Please enter 'Y' for Yes or 'N' for No only!");
                            }
                        }
                        while(true){
                            System.out.println("Should customers be able to see Top 5 Movies by Overall Rating Score? (Y/N)");
                            String input = sc.nextLine();
                            if(input.equalsIgnoreCase("y")){
                                systemSettings.setTop5OverallRatingPermission("Y");
                                break;
                            }
                            else if(input.equalsIgnoreCase("n")){
                                systemSettings.setTop5OverallRatingPermission("N");
                                break;
                            }
                            else{
                                System.out.println("Please enter 'Y' for Yes or 'N' for No only!");
                            }
                        }
                        updateSystemSettingsCSV(systemSettings);
                        System.out.println("System Settings Updated!");
                    }
                    else{
                        continue;
                    }
                    break;
                case 2:
                    System.out.println("Back to System Settings......");
                    this.staffMenu(0, account);
                default:
                    System.out.println("Invalid choice. Please choose between 1 or 2 only.");
                    continue;
            }
            break;
        }
        this.staffMenu(0, account);
    }

    /**
	 * Function to configure holidays
     * Staff can view all current holidays, add, update and remove holidays
     * @param account  staff account
	 */
    public void configureHolidays(Account account){
        
        System.out.println("###########################################################");
		System.out.println("################## CONFIGURING HOLIDAYS ###################");
		System.out.println("###########################################################");
		System.out.println("");

        while (true){
            System.out.println("Please choose an option:");
            System.out.println("1. List Holidays");
            System.out.println("2. Add a Holiday");
            System.out.println("3. Update a Holiday");
            System.out.println("4. Remove a Holiday");
            System.out.println("5. Back to SystemSettingsManager");
            int option; 
            try{
                option = sc.nextInt();
                sc.nextLine();
            }
            catch(InputMismatchException e){
                System.out.println("Please enter integers only.");
                sc.nextLine();
                continue;
            }
            List<Holidays>holidayList = HolidayListManager.getInstance().getHolidayList();
            switch (option) {
                case 1:
                    System.out.println("Current Holidays: ");
                    StaffHolidayCRUDManager.getInstance().listHolidays();
                    break;
                case 2:
                    if (StaffHolidayCRUDManager.getInstance().staffAddHoliday(holidayList)) {
                        System.out.println("Holiday Added!");
                    } 
                    else {
                        System.out.println("Failed to add holiday!");
                    }
                    break;
                case 3:
                    int result = StaffHolidayCRUDManager.getInstance().staffUpdateHoliday(holidayList);
                    if(result == 1){
                        System.out.println("Holiday Updated!");
                    }
                    else if(result == 2){
                        System.out.println("No updates made.");
                    }
                    else{
                        System.out.println("Failed to update holiday!");
                    }
                    break;
                case 4:
                    if(StaffHolidayCRUDManager.getInstance().removeHolidayFromDatabase(holidayList)){
                        System.out.println("Holiday successfully removed!");
                    }
                    else{
                        System.out.println("Failed to remove holiday!");
                    }
                    break;
                case 5:
                    System.out.println("Back to Systems Settings......");
                    this.staffMenu(0,account);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose between 1-5.");
                    continue;
            }
            break;
        }
        this.staffMenu(0, account);
    }
	/**
	 * Writes the existing system settings to the systemsettings.csv file for storage
	 * @param systemSettings System settings object
	 * @return true if update was successful, false if update was unsuccessful
	 */
    public static boolean updateSystemSettingsCSV(SystemSettings systemSettings){
        FileWriter csvWriter;
        try {
			csvWriter = new FileWriter(path,false);
			csvWriter.append("top5SalesPermission");
			csvWriter.append(separator);
			csvWriter.append("top5OverallRatingPermission");
            csvWriter.append(separator);
			csvWriter.append("RatingScoreLimit");
			csvWriter.append("\n");

            StringBuilder sb = new StringBuilder();
            sb.append(systemSettings.getTop5SalesPermission());
            sb.append(separator);
            sb.append(systemSettings.getTop5OverallRatingPermission());
            sb.append(separator);
            sb.append(Double.toString(systemSettings.getRatingScoreLimit()));
            sb.append('\n');
            csvWriter.append(sb.toString());
			csvWriter.flush();
			csvWriter.close();
			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }
}
