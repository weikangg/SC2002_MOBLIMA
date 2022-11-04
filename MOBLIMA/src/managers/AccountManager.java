package managers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entities.*;

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
            if(a.getUsername().equalsIgnoreCase(username)){
                return true;
            }
        }
        return false;
    }
}
