package managers;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import entities.*;

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
        String name;
        String email;
        int mobile;
        int age;
		String password;
        System.out.println("Enter username: ");
        name = scan.nextLine();
         
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
}
