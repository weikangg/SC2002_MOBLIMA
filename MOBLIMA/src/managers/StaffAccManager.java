package managers;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import entities.*;
import static utils.IOUtils.*;

public class StaffAccManager {
    AccountManager accountManager = AccountManager.getInstance();
    List<Account>accountList = accountManager.getAccountList();
    private static Scanner scan = new Scanner(System.in);
    private static StaffAccManager single_instance = null;
    static String path = System.getProperty("user.dir") +"\\data\\accounts\\accounts.csv";
    public static StaffAccManager getInstance()
    {
        if (single_instance == null)
            single_instance = new StaffAccManager();
        return single_instance;
    }
    private StaffAccManager(){}

    // public boolean checkSuperAdmin(List<Account>accountList, String username){
    //     for(Account a: accountList){
    //         if(a.getUsername().equals(username) && a.getAccessLevel().equals("SA")){
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    public boolean createStaffAccount(List<Account>accountList){
        System.out.println("#########################################################");
		System.out.println("################### CREATING ACCOUNT ####################");
		System.out.println("#########################################################");
		System.out.println("");
        String name;
        String email;
        int mobile;
        int age;
		String password;
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
         
        System.out.println("Enter your email:");
        email = scan.nextLine();

        while(true){
            try{
                System.out.println("Enter mobile:");
                mobile = scan.nextInt();
                scan.nextLine();
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
                break;
            }catch(InputMismatchException e){
                System.out.println("Please enter a valid age!");
                scan.nextLine();
                continue;
            }
        }

		System.out.println("Enter password:");
		password = scan.nextLine();
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
            return 2;
        }
    }
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
