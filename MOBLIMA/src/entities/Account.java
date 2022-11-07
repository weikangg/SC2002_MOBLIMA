package entities;
/**
 * An abstract class defining an Account entity.
 * @author Wei Kang
 * @version 2.5
 * @since 01-11-2022
 */

public class Account {
	/**
	* This string is used to store the Account's username.
	*/
    private String username;
	/**
	* This string is used to store the Account's email.
	*/
    private String email;
    /**
	* This int is used to store the Account's mobile number.
	*/
    private int mobile;
    /**
	* This int is used to store the Account's age.
	*/
    private int age;
    /**
	* This string is used to store the Account's password.
	*/
    private String password;
    /**
	* This string is used to store the Account's access level.
	*/
    private String accessLevel;
    
	/**
	 * The default constructor for the Account class
	 */
	public Account(String name, String email, int mobile, int age, String password, String accessLevel)
    {
        this.username = name;
        this.email = email;
        this.mobile = mobile;
        this.age = age;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    // Gettors

    /**
	 * Get Account's username (String). Public method.
	 * @return Account's username.
	 */
    public String getUsername(){
        return this.username;
    }
    /**
	 * Get Account's email (String). Public method.
	 * @return Account's email.
	 */
    public String getEmail(){
        return this.email;
    }
    /**
	 * Get Account's mobile (Int). Public method.
	 * @return Account's mobile.
	 */
    public int getMobile(){
        return this.mobile;
    }
    /**
	 * Get Account's age (Int). Public method.
	 * @return Account's age.
	 */
    public int getAge(){
        return this.age;
    }
    /**
	 * Get Account's password (String). Public method.
	 * @return Account's password.
	 */
    public String getPassword(){
        return this.password;
    }
    /**
	 * Get Account's access level (String). Public method.
	 * @return Account's access level.
	 */    
    public String getAccessLevel(){
        return this.accessLevel;
    }

    // Settors

	/**
	 * Set Account's username. Public method.
	 * @param username String containing new username.
	 */
    public void setUsername(String username){
        this.username = username;
    }
	/**
	 * Set Account's email. Public method.
	 * @param email String containing new email.
	 */
    public void setEmail(String email){
        this.email = email;
    }
	/**
	 * Set Account's mobile. Public method.
	 * @param mobile Int containing new mobile.
	 */
    public void setMobile(int mobile){
        this.mobile = mobile;
    }
	/**
	 * Set Account's age. Public method.
	 * @param age Int containing new age.
	 */
    public void setAge(int age){
        this.age = age;
    }
	/**
	 * Set Account's password. Public method.
	 * @param password String containing new password.
	 */
    public void setPassword(String password){
        this.password = password;
    }
	/**
	 * Set Account's accessLevel. Public method.
	 * @param accessLevel String containing new accessLevel.
	 */
    public void setAccessLevel(String accessLevel){
        this.accessLevel = accessLevel;
    }

}
