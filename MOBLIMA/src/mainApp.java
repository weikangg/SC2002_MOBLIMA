
import java.io.File;

import boundaries.MainMenu;
/**
 * The class for our Main app to run 
 * Allows user to either run as Guest or Login to make bookings
 * @author Wei Kang
 * @version 2.5
 * @since 01-11-2022
 */
 
public class mainApp {
    /**
     * Main function to display main menu

     * @param args Arguments of main methods
	 */
    public static void main(String[] args){

        File directory = new File("SC2002_OOP\\MOBLIMA").getAbsoluteFile();
        if (directory.exists()) System.setProperty("user.dir", directory.getAbsolutePath());
        directory = new File("MOBLIMA").getAbsoluteFile();
        if (directory.exists()) System.setProperty("user.dir", directory.getAbsolutePath());
        
        MainMenu.getInstance().display();
    }
}
