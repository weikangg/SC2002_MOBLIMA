package managers;

import java.util.Scanner;

import entities.CustomerAcc;

import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class CustomerAccManager {
    
    static String path = System.getProperty("user.dir") +"\\data\\accounts\\accounts.csv";
    static String item_Separator = ",";	

    Scanner scan = new Scanner(System.in);

    /*public ArrayList<CustomerAcc> readCustomerFile(){
        //read in account.csv file, then put the names, email, mobile and age into constructor and create an array of customer information

    }*/

    public static List<CustomerAcc> getCustomerList(){
        List<CustomerAcc>customers = new ArrayList<>();
    	BufferedReader br = null;
		String line = "";
		CustomerAcc custemp;
		try {
			br = new BufferedReader(new FileReader(path));
			while((line = br.readLine()) !=null ) {
				String[] custcsv = line.split(item_Separator);
				if(!custcsv[0].equals("USERNAME")) {
                    int customerMobile = Integer.parseInt(custcsv[2]);
                    int customerAge = Integer.parseInt(custcsv[3]);
					custemp = new CustomerAcc(custcsv[0],custcsv[1],customerMobile,customerAge,custcsv[4],custcsv[5]);
					customers.add(custemp);
				}
			}
			br.close();
		}
        catch(NumberFormatException e){
            System.out.println("Check age and mobile are numbers in database!");
        }
		catch(ArrayIndexOutOfBoundsException e){
			return customers;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return customers;
    }

    public static CustomerAcc createAcc(){
        String name;
        String email;
        int mobile;
        int age;
		String password;
        Scanner scan = new Scanner(System.in);
        List<CustomerAcc> customers = CustomerAccManager.getCustomerList();

        //ask for user information and use try to check if name or email or mobile exists
        //to add exceptions
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
        String accessLevel = "C";
        CustomerAcc acc = new CustomerAcc(name, email, mobile, age, password,accessLevel);
	    customers.add(acc);
	    

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
            for (CustomerAcc cust : customers){
                StringBuilder sb = new StringBuilder();
			    sb.append(cust.getUsername());
			    sb.append(separator);
			    sb.append(cust.getEmail());
			    sb.append(separator);
			    sb.append(cust.getMobile());
			    sb.append(separator);
			    sb.append(cust.getAge());
			    sb.append(separator);
				sb.append(cust.getPassword());
                sb.append(separator);
				sb.append(cust.getAccessLevel());
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

	public static boolean checkLogin(String username , String password){
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

    public boolean userLogOut(){
        return false;
    }
}
