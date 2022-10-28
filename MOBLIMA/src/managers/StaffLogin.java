package managers;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class StaffLogin {
    public static StaffLogin object = null;

    public static StaffLogin getInstance(){
        if (object == null){
            object = new StaffLogin();
        }
        return object;
    }
    private StaffLogin(){}

    public boolean checkLogin(String username , String password){
        try{
            String line = "";
            String path = System.getProperty("user.dir") +"\\data\\staffs\\staffs.csv";
            System.out.println(path);
            BufferedReader br = new BufferedReader(new FileReader(path));
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

        } catch(IOException e){
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
