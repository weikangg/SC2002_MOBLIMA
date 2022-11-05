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
import java.util.concurrent.ThreadLocalRandom;



public class AccountManager {
    static String path = System.getProperty("user.dir") +"\\data\\accounts\\accounts.csv";
    static String item_Separator = ",";	
    Scanner sc = new Scanner(System.in);
   
    private static AccountManager single_instance = null;
    public static AccountManager getInstance()
    {
        if (single_instance == null)
            single_instance = new AccountManager();
        return single_instance;
    }
    private AccountManager(){}
    
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
    public Account getAccount(List<Account>accountList, String username, String password){
        for(Account a : accountList){
            if(a.getUsername().equals(username) && a.getPassword().equals(password)){
                return a;
            }
        }
        return null;        
    }

    public boolean checkAccountExists(List<Account>accountList, String username){
        for(Account a: accountList){
            if(a.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public boolean checkAccountExistsAndCustomer(List<Account>accountList, String username){
        for(Account a:accountList){
            if(a.getUsername().equals(username) && a.getAccessLevel().equals("C")){
                return true;
            }
        }
        return false;
    }

    // The following restrictions are imposed in the email address' local part by using this regex:
        // It allows numeric values from 0 to 9.
        // Both uppercase and lowercase letters from a to z are allowed.
        // Allowed are underscore “_”, hyphen “-“, and dot “.”
        // Dot isn't allowed at the start and end of the local part.
        // Consecutive dots aren't allowed.
        // For the local part, a maximum of 64 characters are allowed.
    
    // Restrictions for the domain part in this regular expression include:
        // It allows numeric values from 0 to 9.
        // We allow both uppercase and lowercase letters from a to z.
        // Hyphen “-” and dot “.” aren't allowed at the start and end of the domain part.
        // No consecutive dots.
    public boolean checkValidEmail(String emailAddress){
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern)
        .matcher(emailAddress)
        .matches();
    }

    // 1) Begins with 8 or 9
    // 2) Then contains 7 digits
    public boolean checkValidPhoneNumber(String mobile){
        Pattern p = Pattern.compile("(8|9)?[0-9]{7}");
        Matcher m = p.matcher(mobile);
        return (m.find() && m.group().equals(mobile));
    }

    // Generates random alphanumeric name of length 7 and appends it to "Guest" to signify that it is a guest name
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
