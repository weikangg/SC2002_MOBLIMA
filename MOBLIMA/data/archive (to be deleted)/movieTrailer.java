package view;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class movieTrailer {
    private String movieTitle;
    int userSelection = 1;
    public movieTrailer(String movieTitle){
        this.movieTitle = movieTitle;
    }    
    public static void watchTrailer(String movieTitle) throws IOException, URISyntaxException{
        movieTitle = movieTitle.toLowerCase();
        switch(movieTitle){
            case "blackPanther":
                if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
                {
                    Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=_Z3QKkl1WyM&ab_channel=MarvelEntertainment"));
                }
                break;
            case "Another movie":
                break;
        }
    }
}
