package boundaries;

import entities.Account;
/**
 * The interface for all our other menus with an account as a parameter to implement
 * @author Wei Kang
 * @version 1.0
 * @since 11-11-2022
 */
public interface BaseMenuWithAccount {
    /**
	 * Abstract method for individual menus with accounts to implement
	 */
    public void display(Account account);
}
