package entities;
/**
 * A class defining an Staff Account object. Inherits properties from the abstract Account class
 * @author Wei Kang
 * @version 2.5
 * @since 01-11-2022
 */
public class StaffAcc extends Account {
    //constructor

    /**
	 * This constructor is used to create a new Staff Account object
	 * @param username	  This is the staff's username
	 * @param email		  This is the staff's email
	 * @param mobile	  This is the staff's mobile
	 * @param age	      This is the staff's age
	 * @param password	  This is the staff's password
	 * @param accessLevel This is the staff's accessLevel
	 */
    public StaffAcc(String username, String email, int mobile, int age, String password, String accessLevel)
    {
        super(username,email,mobile,age,password,accessLevel);
    }

}
