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
@SuppressWarnings("unchecked")

/**
 * Function class that configures user account information
 * @author Ling Hin
 * @version 2.5
 * @since 08-11-2022
 */

public class CustomerAccManager {
    /*
     * 
     */
    static String path = System.getProperty("user.dir") +"\\data\\accounts\\accounts.csv";
    //static String item_Separator = ",";	
    private static Scanner scan = new Scanner(System.in);

    public static CustomerAcc createAcc(List<Account>accountList){
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

    public static void checkHistory(Account user){
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
                    System.out.println("Cineplex: " + b.getCineplexID());
                    System.out.println("Cinema: " + b.getCinemaID());
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
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException exp) {
            System.out.println("Class not found");
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
        
        
        

        //File directory = new File(path + name);
        //fileCount = directory.list().length;
        //debug
        //System.out.println(fileCount);

        /*for(int i = 1; i <= fileCount; i++)//go through all the files in customer and print out the transaction information
        {
            
            trans = location + i + filename;
            System.out.println(trans);
            System.out.println("========================================================");
            
            
            try{
                FileInputStream file = new FileInputStream(trans);
                ObjectInputStream in = new ObjectInputStream(file);
                book = (ArrayList<Booking>) in.readObject();

                System.out.println(book);

                for(Booking b : book)
                {
                    System.out.println("Booking ID: " + b.getbookingID());
                    System.out.println("Movie Title: " + b.getMovie());
                    System.out.println("Cineplex: " + b.getCineplexID());
                    System.out.println("Cinema: " + b.getCinemaID());
                    System.out.println("");

                }
                
                in.close();
                file.close();
    
            }catch(IOException ex){
                System.out.println("IOException is caught");
            } catch (ClassNotFoundException exp) {
                System.out.println("Class not found");
            }
            System.out.println("========================================================");    
        }*/
    }
}
