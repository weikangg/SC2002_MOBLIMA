package managers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import entities.*;

/**
 * A manager class for all actions related to Accounts (inclusive of both staffs and customers)
 * @author Wei Kang
 * @version 2.5
 * @since 01-11-2022
 */

public class AccountManager {
    /**
	 * The path to the CSV file that stores all the accounts
	 */
    private static String path = System.getProperty("user.dir") +"\\data\\accounts\\accounts.csv";
	/**
	 * The separator for the columns in the csv file
	 */
    private static String item_Separator = ",";	
	/**
	 * The scanner for reading input of user
	 */
    Scanner sc = new Scanner(System.in);
	/**
	 * For singleton pattern adherence. This AccountManager instance persists throughout runtime.
	 */
    private static AccountManager single_instance = null;
	/**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
    public static AccountManager getInstance()
    {
        if (single_instance == null)
            single_instance = new AccountManager();
        return single_instance;
    }
	/**
	 * The default constructor for the AccountManager class
	 */
    private AccountManager(){}

    /**
	 * Fetch all the accounts inside the accounts.csv file and compile them into a list 
	 * @return accountList
	 */
    public List<Account> getAccountList(){
        List<Account>accounts = new ArrayList<>();
    	BufferedReader br = null;
		String line = "";
		Account accountTemp;
		try {
			br = new BufferedReader(new FileReader(path));
			while((line = br.readLine()) !=null ) {
				String[] custcsv = line.split(item_Separator);
				if(!custcsv[0].equals("USERNAME")) {
                    int customerMobile = Integer.parseInt(custcsv[2]);
                    int customerAge = Integer.parseInt(custcsv[3]);
					accountTemp = new CustomerAcc(custcsv[0],custcsv[1],customerMobile,customerAge,custcsv[4],custcsv[5]);
					accounts.add(accountTemp);
				}
			}
			br.close();
		}
        catch(NumberFormatException e){
            System.out.println("Check age and mobile are numbers in database!");
        }
		catch(ArrayIndexOutOfBoundsException e){
			return accounts;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return accounts;
    }
    /**
	 * Checks login details by checking whether the username and password exists in the database, accounts.csv
     * @param accountList List of existing accounts
     * @param username Username of user to be checked
     * @param password Password of user to be checked
	 * @return true if exists, false if does not exist
	 */
    public boolean checkLogin(List<Account>accountList, String username, String password){
        try{
            String line = "";

            Path path = Paths.get(System.getProperty("user.dir")+"\\data\\accounts\\accounts.csv");
            // System.out.println(path.toAbsolutePath().toString());

            BufferedReader br = new BufferedReader(new FileReader(path.toAbsolutePath().toString()));
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                if(values[0].equals(username) && values[4].equals(password)){
                    br.close();
                    return true;
                }
            }
            // IF NO MATCH
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
	 * Fetches the account inside the accounts.csv file based on the particular username and the password 
     * @param accountList List of existing accounts
     * @param username Username of the account that is to be fetched
     * @param password Password of the account that is to be fetched
	 * @return  account or null if the account does not exist
	 */
    public Account getAccount(List<Account>accountList, String username, String password){
        for(Account account : accountList){
            if(account.getUsername().equals(username) && account.getPassword().equals(password)){
                return account;
            }
        }
        return null;        
    }
    /**
	 * Checks if the account exists inside the accounts.csv file based on the particular username 
     * @param accountList List of existing accounts
     * @param username Username of the account to be checked if the account exists
	 * @return  true if account exists, false if account does not exist
	 */
    public boolean checkAccountExists(List<Account>accountList, String username){
        for(Account a: accountList){
            if(a.getUsername().equalsIgnoreCase(username)){
                return true;
            }
        }
        return false;
    }

    /**
	 * Checks if the customer account exists inside the accounts.csv file based on the particular username 
     * @param accountList List of existing accounts
     * @param username Username of the account to be checked if the account exists and is a customer
	 * @return  true if account exists, false if account does not exist
	 */
    public boolean checkAccountExistsAndCustomer(List<Account>accountList, String username){
        for(Account a:accountList){
            if(a.getUsername().equals(username) && a.getAccessLevel().equals("C")){
                return true;
            }
        }
        return false;
    }

    /**
	 * Checks if the email input is valid based on pre-defined regex when creating new account
     * For the Local Part:
     * It allows numeric values from 0 to 9. Both uppercase and lowercase letters from a to z are allowed.
     * Allowed are underscore “_”, hyphen “-“, and dot “.” Dot isn't allowed at the start and end of the local part.
     * Consecutive dots aren't allowed. Maximum of 64 characters are allowed.
     * For the Domain part:
     * It allows numeric values from 0 to 9. We allow both uppercase and lowercase letters from a to z.
     * Hyphen “-” and dot “.” aren't allowed at the start and end of the domain part. No consecutive dots.
     * @param emailAddress Email to be checked for validity
	 * @return  true if email is valid, false if email is not valid
	 */
    public boolean checkValidEmail(String emailAddress){
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern)
        .matcher(emailAddress)
        .matches();
    }
    /**
	 * Checks if the phone number input is valid based on pre-defined regex when creating new account
     * Allows only a string of length 8
     * First number must begin with 8 or 9
     * 2nd to 8th number can contain any digit
     * @param mobile Mobile Number to be checked for validity
	 * @return  true if mobile is valid, false if mobile is not valid
	 */
    public boolean checkValidPhoneNumber(String mobile){
        Pattern p = Pattern.compile("(8|9)?[0-9]{7}");
        Matcher m = p.matcher(mobile);
        return (m.find() && m.group().equals(mobile));
    }

    /**
     * Generates a random name for Customers who choose to make a review as a Guest
     * Generates random alphanumeric name of length 7 and appends it to "Guest" to signify that it is a guest name
     * @return generatedString
     */
    public String randomNameGenerator() {
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 7;
        Random random = new Random();
    
        String generatedString = random.ints(leftLimit, rightLimit + 1)
          .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();
    
        return "Guest" + generatedString;
    }
}
