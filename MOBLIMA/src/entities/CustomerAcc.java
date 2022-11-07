package entities;
/**
 * A class defining an Customer Account object. Inherits properties from the abstract Account class
 * @author Ling Hin
 * @version 2.0
 * @since 07-11-2022
 */

import java.util.LinkedList;
import java.util.ArrayList;
public class CustomerAcc extends Account {

    //constructor
    /**
	 * This constructor is used to create a new Customer Account object
	 * @param username	  This is the customer's username
	 * @param email		  This is the customer's email
	 * @param mobile	  This is the customer's mobile
	 * @param age	      This is the customer's age
	 * @param password	  This is the customer's password
	 * @param accessLevel This is the customer's accessLevel
	 */
    public CustomerAcc(String name, String email, int mobile, int age, String password, String accessLevel)
    {
        this.username = name;
        this.email = email;
        this.mobile = mobile;
        this.age = age;
        this.password = password;
        this.accessLevel = accessLevel;
    }
}
