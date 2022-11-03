package entities;


public class User {

    private String username;

    public User(String username){
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public static void start(){

        System.out.println("==================================================");
        System.out.println("Starting User Module:");
        System.out.println("==================================================");  

    }


}
