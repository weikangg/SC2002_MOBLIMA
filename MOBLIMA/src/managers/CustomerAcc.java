package managers;

import java.util.LinkedList;
import java.util.ArrayList;
import entities.*;

public class CustomerAcc {
    private String name;
    private String email;
    private int mobile;
    private int age;
    //create transaction history using linked list
    private LinkedList<Transaction> bookingHistory = new LinkedList<Transaction>();

    //constructor
    public CustomerAcc(String name, String email, int mobile, int age)
    {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.age = age;
    }

    public void updateHistory(ArrayList<Ticket> tix, String movieName, String tranDateTime, String cinID){
        //creates transaction id
        String id =  cinID + tranDateTime;
        //create transaction history
        Transaction x = new Transaction(id, movieName, tranDateTime, tix);
        //add the new transaction to history
        bookingHistory.addFirst(x);
        System.out.println("Transaction Complete!");
    }

    public void showBookingHistory(){
        //prints out booking history
        for(int i = 0; i < bookingHistory.size(); i++)
        {
        	bookingHistory.get(i).printTransaction();
        }
        return;
    }

    public void setName(String n){
        this.name = n;
        return;
    }
    public void setEmail(String e){
        this.email = e;
        return;
    }
    public void setMobile(int m){
        this.mobile = m;
        return;
    }
    public void setAge(int a){
        this.age = a;
        return;
    }

    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public int getMobile(){
        return this.mobile;
    }
    public int getAge(){
        return this.age;
    }
}
