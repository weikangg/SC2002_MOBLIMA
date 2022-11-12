package managers;

import java.util.Scanner;
import entities.Account;
import entities.CustomerAcc;
import entities.Ticket;
import entities.Booking;
import utils.PasswordStrengthChecker;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;

/**
 * Function class that configures user account information
 * @author Ling Hin
 * @version 2.5
 * @since 08-11-2022
 */
@SuppressWarnings("unchecked")
public class CustomerAccManager {
    /**
	 * The path to the CSV file that stores all the accounts
	 */
    static String path = System.getProperty("user.dir") +"\\data\\accounts\\accounts.csv";
    //static String item_Separator = ",";
    /**
	 * The scanner for reading input of user
	 */
    private static Scanner scan = new Scanner(System.in);

/**
	 * For singleton pattern adherence. This StaffAccManager instance persists throughout runtime.
	 */
    private static CustomerAccManager single_instance = null;
	/**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
    public static CustomerAccManager getInstance()
    {
        if (single_instance == null)
            single_instance = new CustomerAccManager();
        return single_instance;
    }

    /**
     * Function to create new user account
     * Asks user for username and checks if username already exist in the list of accounts
     * Ask user for email and checks if email entered is a valid email address using pre-defined regex function in accountManager class
     * Ask user for mobile number and check if mobile number entered is a valid number using pre-defined regex function in accountManager class
     * Ask user for age and check if age is valid
     * Ask user for password and check if password is strong using function in utils/PasswordStrengthChecker class
     * After getting information, all the information will be stored in acc and appended to the csv file with the list of accounts
     * @param accountList existing list of accounts
     * @return returns an instance of the customer account
     */
    public CustomerAcc createAcc(List<Account>accountList){
        String name;
        String email;
        int mobile;
        int age;
		String password="";
        String pwStrength="Weak";

        AccountManager accountManager = AccountManager.getInstance();

        //ask for user information and use try to check if name or email or mobile exists
        //to add exceptions
        while(true){
            System.out.println("Enter username: ");
            name = scan.nextLine();
            if(accountManager.checkAccountExists(accountList, name)){
                System.out.println("Account already exists! Try another username!");
                continue;
            }
            else{
                break;
            }
        }

         
        while(true){
            System.out.println("Enter your email:");
            email = scan.nextLine();
            if(email.length() == 0){
                System.out.println("Email cannot be empty!");
                continue;
            }
            if(!accountManager.checkValidEmail(email)){
                System.out.println("Invalid Email! Email addresses can only be like username@domain.com.");
                continue;
            }
            break;
        }

        while(true){
            try{
                System.out.println("Enter mobile:");
                mobile = scan.nextInt();
                scan.nextLine();
                String mobileStr = Integer.toString(mobile);
                if(!accountManager.checkValidPhoneNumber(mobileStr)){
                    System.out.println("Invalid Phone Number! Phone numbers can only be 8 digits, starting with 8 or 9.");
                    continue;
                }
                break;
            }catch(InputMismatchException e){
                System.out.println("Please enter a valid mobile number!");
                scan.nextLine();
                continue;
            }
        }
        
        while(true){
            try{
                System.out.println("Enter age:");
                age = scan.nextInt();
                scan.nextLine();
                if(age < 0 || age > 150){
                    System.out.println("Invalid Age!");
                    continue;
                }
                break;
            }catch(InputMismatchException e){
                System.out.println("Please enter a valid age!");
                scan.nextLine();
                continue;
            }
        }

        while (pwStrength!="Strong"){
		    System.out.println("Enter password:");
            System.out.println("(Length must be at least 8 characters,inclusive of 1 special character (!@#$%^&*),1 uppercase, 1 lowercase and 1 digit)");
		    password = scan.nextLine();
            pwStrength=PasswordStrengthChecker.passwordStrength(password);
            if (pwStrength=="Weak" || pwStrength=="Medium"){
                System.out.println("Password Strength: " + pwStrength);
                System.out.println("Please enter a stronger password. ");
            }
            else{System.out.println("Password Strength: " + pwStrength);}
        }

        String accessLevel = "C";
        CustomerAcc acc = new CustomerAcc(name, email, mobile, age, password,accessLevel);
	    accountList.add(acc);
	    

		String separator = ",";
		try {
            FileWriter csvWriter = new FileWriter(path, false);
            csvWriter.append("USERNAME");
			csvWriter.append(separator);
			csvWriter.append("EMAIL");
			csvWriter.append(separator);
			csvWriter.append("MOBILE");
			csvWriter.append(separator);
			csvWriter.append("AGE");
			csvWriter.append(separator);
			csvWriter.append("PASSWORD");
            csvWriter.append(separator);
			csvWriter.append("ACCESS_LEVEL");
			csvWriter.append("\n");
            for (Account a : accountList){
                StringBuilder sb = new StringBuilder();
			    sb.append(a.getUsername());
			    sb.append(separator);
			    sb.append(a.getEmail());
			    sb.append(separator);
			    sb.append(a.getMobile());
			    sb.append(separator);
			    sb.append(a.getAge());
			    sb.append(separator);
				sb.append(a.getPassword());
                sb.append(separator);
				sb.append(a.getAccessLevel());
			    sb.append('\n');
			    csvWriter.append(sb.toString());
            }
			csvWriter.flush();
			csvWriter.close();
			//return true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return false;
		}

        //add into csv file
        //for each account created, create a file inside tranaction
        return acc;
    }

    /**
     * Function checks if customer's login information is correct
     * Search and checks for customer's username and password in the csv that stores all the accounts
     * @param username customer's username
     * @param password customer's password
     * @return returns true if customer's information are correct, else returns false
     */
	public boolean checkLogin(String username , String password){
        try{
            String line = "";

            String path = System.getProperty("user.dir") +"\\data\\accounts\\account.csv";

            BufferedReader br = new BufferedReader(new FileReader(path));
            while((line = br.readLine()) != null){
                String[] values = line.split(",");

                if(values[0].equals(username) && values[4].equals(password) && values[5].equals("C")){
                    br.close();
                    return true;
                }
            }

            // IF NO MATCH
            System.out.println("User Account not found!");
            br.close();
            return false;
        }catch(FileNotFoundException e){
            System.out.println("Cannot find input file!");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        catch (NullPointerException e){
            System.out.println("Null Pointer Error!");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        catch(IOException e){
            System.out.println("Input/Output Error!");
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return false;
    }
    /**
     * Function to check if the user is logged out
     * @return returns false when the user is logged out
     */
    public boolean userLogOut(){
        return false;
    }

    /**
     * Opens the user's booking history csv file and extract the information
     * Prints out all user's booking history information such as booking ID, movie name, cineplex, cinema, movie date and time, ticket seat and cost, transaction date and time and total amount paid
     * @param user current logged in user account
     */
    public void checkHistory(Account user){
        String name = user.getUsername();
        String path = System.getProperty("user.dir") +"\\data\\bookings\\";
        String location = path + name +"/bookings.csv";
        int input = -1;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        //String filename = ".csv";
        //String trans;
        //int fileCount;
        ArrayList<Booking> book = new ArrayList<Booking>();
        ArrayList<Ticket> tix;

        try{
            FileInputStream file = new FileInputStream(location);
            ObjectInputStream in = new ObjectInputStream(file);
            if(book instanceof Object){
                book = (ArrayList<Booking>) in.readObject();
            }


            //System.out.println(book);

            for(Booking b : book)
            {
                if(b != null)
                {
                    System.out.println("Booking ID: " + b.getbookingID());
                    System.out.println("Movie Title: " + b.getMovie());
                    System.out.println("Cineplex: " + (char)(b.getCineplexID()+65));
                    System.out.println("Cinema: " + (b.getCinemaID()+1));
                    System.out.println("Movie Date: " + b.getShowtime().format(dateFormatter));
                    System.out.println("Movie Time: " + b.getShowtime().format(timeFormatter));
                    System.out.println("Tickets:");
                    tix = b.getTicketList();
                    for(int i = 0; i < tix.size(); i++)
                    {
                        System.out.println("    Ticket " + (i+1) + " : " + " Row " + tix.get(i).getRow() + " Column " + tix.get(i).getCol() + " $" + String.format("%.2f", tix.get(i).getTicketPrice()));
                    }
                    System.out.println("Transaction Date: " + b.getTransaction().getTranDateTime().substring(0,4)  + "-" +  b.getTransaction().getTranDateTime().substring(4,6) + "-" +  b.getTransaction().getTranDateTime().substring(6,8));
                    System.out.println("Transaction Time: " +  b.getTransaction().getTranDateTime().substring(8,10) + ":" +  b.getTransaction().getTranDateTime().substring(10));
                    System.out.println("Total Price: $" + String.format("%.2f", b.getTotalPrice()) );
                    System.out.println("========================================================");
                }
            }
            
            in.close();
            file.close();

        }catch(IOException ex){
            System.out.println("No bookings have been made yet");
        } catch (ClassNotFoundException exp) {
            System.out.println("No bookings have been made yet");
        }
        while(input != 0)
        {
            System.out.println("Enter 0 to exit.");
            try{
                input = scan.nextInt();
            }catch(InputMismatchException e){
                scan.nextLine();
            }
        }
    }
}
