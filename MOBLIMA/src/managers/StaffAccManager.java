package managers;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import entities.*;
import utils.PasswordStrengthChecker;
import static utils.IOUtils.*;

/**
 * A manager class for all actions related to Staff Accounts
 * @author Wei Kang
 * @version 2.5
 * @since 01-11-2022
 */

 public class StaffAccManager {
    /**
	 * The path to the CSV file that stores all the accounts
	 */
    private static String path = System.getProperty("user.dir") +"\\data\\accounts\\accounts.csv";
    /**
	 * An instance of account manager to get the list of accounts
	 */
    private static AccountManager accountManager = AccountManager.getInstance();
	/**
	 * The scanner for reading input of user
	 */
    private static Scanner scan = new Scanner(System.in);
    /**
	 * For singleton pattern adherence. This StaffAccManager instance persists throughout runtime.
	 */
    private static StaffAccManager single_instance = null;
	/**
	 * For singleton pattern adherence. 
	 * @return instance The static instance that persists throughout runtime.
	 */
    public static StaffAccManager getInstance()
    {
        if (single_instance == null)
            single_instance = new StaffAccManager();
        return single_instance;
    }
	/**
	 * The default constructor for the StaffAccManager class
	 */
    private StaffAccManager(){}
    /**
	 * Function to create new staff account
     * Checks for empty username input and whether the username already exists
     * Checks if the email is valid according to pre-defined regex function in AccountManager class
     * Checks if the mobile number is valid according to pre-defined regex function in AccountManager class
     * Checks if the age is valid. Ages below 0 and above 150 are not allowed.
     * Checks if the password is strong according to pre-defined function in utils/PasswordStrengthChecker class
     * @param accountList Existing List of accounts
     * @return true if creation of staff account was successful, false if unsuccessful
	 */
    public boolean createStaffAccount(List<Account>accountList){
        System.out.println("#########################################################");
		System.out.println("################### CREATING ACCOUNT ####################");
		System.out.println("#########################################################");
		System.out.println("");
        String name;
        String email;
        int mobile;
        int age;
		String password="";
        String pwStrength="";
        while(true){
            System.out.println("Enter username: ");
            name = scan.nextLine();
            if(name.equals("")){
                System.out.println("Username cannot be empty!");
                continue;
            }
            if(accountManager.checkAccountExists(accountList, name)){
                System.out.println("Account already exists! Try another username!");
                continue;
            }
            break;
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

        String accessLevel = "A";
        StaffAcc acc = new StaffAcc(name, email, mobile, age, password,accessLevel);
        accountList.add(acc);
        String separator = ",";
		try {
            FileWriter csvWriter = new FileWriter(path,false);
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
			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }
    /**
	 * Function to remove staff account
     * Checks whether the username exists
     * Removal of staff account only allowed for super admin
     * @param accountList Existing List of accounts
     * @return true if deletion of staff account was successful, false if unsuccessful
	 */
    public int removeStaffAccount(List<Account> accountList){
        System.out.println("#########################################################");
		System.out.println("#################### REMOVING ACCOUNT ###################");
		System.out.println("#########################################################");
		System.out.println("");
        String username;
        List<Account>newList = new ArrayList<Account>();
        System.out.println("Enter Username: ");
        username = scan.next();
        
        // Search if account  exists first
        Account temp = null;
        for(Account a: accountList){
            if(a.getUsername().equals(username)&& a.getAccessLevel().equals("A")){
                 temp = a;
            }
        }
        if(!accountList.contains(temp)){
            System.out.println("Staff does not exist!");
            return 0;
        }
        scan.nextLine();
        // if yes , remove user
        if(confirm("Confirm Remove Account")){
            for(Account a : accountList){
                if(!a.getUsername().equals(username)){
                    String userName = a.getUsername();
                    String email = a.getEmail();
                    int mobile = a.getMobile();
                    int age = a.getAge();
                    String password = a.getPassword();
                    String accessLevel = a.getAccessLevel();
                    Account account = new StaffAcc(userName,email,mobile,age,password,accessLevel);
                    newList.add(account);
                }
            }
            if(updateAccountListCSV(newList)){
                return 1;
            }
            else{
                return 0;
            }
        }
        else{
            System.out.println("Account not removed!");
            return 2;
        }
    }
    /**
	 * Writes the list of accounts to the accounts.csv file for storage
	 * @param reviewList Existing list of accounts
	 * @return true if update was successful, false if update was unsuccessful
	 */
    public boolean updateAccountListCSV(List<Account>accountList){
        String separator = ",";
        try {
            FileWriter csvWriter = new FileWriter(path,false);
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
			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }
   
}
