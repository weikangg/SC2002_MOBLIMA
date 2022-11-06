package managers;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StaffLogin {
    public static StaffLogin single_instance = null;

    public static StaffLogin getInstance(){
        if (single_instance == null){
            single_instance = new StaffLogin();
        }
        return single_instance;
    }
    private StaffLogin(){}

    public boolean checkStaffLogin(String username , String password){
        try{
            String line = "";

            Path path = Paths.get(System.getProperty("user.dir")+"\\data\\staffs\\staffsAccount.csv");
            // System.out.println(path.toAbsolutePath().toString());

            BufferedReader br = new BufferedReader(new FileReader(path.toAbsolutePath().toString()));
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                if(values[0].equals(username) && values[1].equals(password)){
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
