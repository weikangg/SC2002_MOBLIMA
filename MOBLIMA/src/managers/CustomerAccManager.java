package managers;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomerAccManager {
    
    static String path = System.getProperty("user.dir") +"\\data\\customer\\account.csv";
    static String item_Separator = ",";	
	static String row_Separator =";";
	static String first_Item =" ;";

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
				if(!custcsv[0].equals("MOVIE_TITLE")) {
					custemp = new CustomerAcc(custcsv[0],custcsv[1],custcsv[2],custcsv[3],custcsv[4]);
					customers.add(custemp);
				}
			}
			br.close();
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
        String mobile;
        String age;
		String password;
        Scanner scan = new Scanner(System.in);
        List<CustomerAcc> customers = CustomerAccManager.getCustomerList();

        //ask for user information and use try to check if name or email or mobile exists
        //to add exceptions
        System.out.println("Enter username: ");
        name = scan.nextLine();
         
        System.out.println("Enter your email:");
        email = scan.nextLine();

        System.out.println("Enter mobile:");
        mobile = scan.nextLine();

        System.out.println("Enter age:");
        age = scan.nextLine();

		System.out.println("Enter password:");
		password = scan.nextLine();

        CustomerAcc acc = new CustomerAcc(name, email, mobile, age, password);

        FileWriter csvWriter;
		String separator = ",";
		try {
			csvWriter = new FileWriter(path,true);
			csvWriter.append("NAME");
			csvWriter.append(separator);           
			csvWriter.append("EMAIL");
			csvWriter.append(separator);
			csvWriter.append("MOBILE");
			csvWriter.append(separator);
			csvWriter.append("AGE");
			csvWriter.append("PASSWORD");
			csvWriter.append("\n");

            for (CustomerAcc cust : customers){
                StringBuilder sb = new StringBuilder();
			    sb.append(cust.getName());
			    sb.append(separator);
			    sb.append(cust.getEmail());
			    sb.append(separator);
			    sb.append(cust.getMobile());
			    sb.append(separator);
			    sb.append(cust.getAge());
			    sb.append(separator);
				sb.append(cust.getPassword());
				sb.append(separator);
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

            Path path = Paths.get("/data/customer/account.csv");

            BufferedReader br = new BufferedReader(new FileReader(path.toAbsolutePath().toString()));
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                // System.out.println("IN DATABASE..");
                // System.out.println("Username: " + values[0].substring(1,values[0].length()) 
                //                     + "\nPassword: " + values[1].substring(0, values[1].length()-1));

                // IF USERNAME AND PASSWORD MATCH, here I take the substring of what's read in from
                // THE CSV BECAUSE FOR SOME REASON WHEN IT READS IN THE LINE FROM THE CSV, IT HAS AN INVERTED COMMA AT THE START
                // FOR THE USERNAME AND CLOSING COMMAS FOR THE PASSWORD.
                if(values[0].substring(1,values[0].length()).equals(username) 
                && values[1].substring(0, values[1].length()-1).equals(password)){
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

    public boolean userLogOut(){
        return false;
    }
}
