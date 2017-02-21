
package banking.primitive.core;

import java.io.IOException;
import java.util.List;

public interface AccountServer {

	/**
	  Method:newAccount
	  Inputs: type, name, balance
	  Returns: True/False

	  Description: Creates a new account object in the server. if an account already exists with the given name
	  then a new account is not created and stored. 
	*/
	public boolean	newAccount(String type, String name, float balance) throws IllegalArgumentException;

	/**
	  Method:closeAccount
	  Inputs: name
	  Returns: True/False

	  Description: Closes an account.
	*/
	public boolean	closeAccount(String name);

	/**
	  Method: getAccount
	  Inputs: name
	  Returns: account

	  Description: Returns account based on name.
	*/
	public Account	getAccount(String name);

	/**
	  Method: getAllAccounts
	  Inputs: N/A
	  Returns: List

	  Description: Returns a list of all accounts
	*/
	public List<Account> getAllAccounts();

	/**
	  Method: getActiveAccounts
	  Inputs: N/A
	  Returns: List

	  Description: returns a list of Accounts inside the server that are not CLOSED.
	*/
	public List<Account> getActiveAccounts();

	/**
	  Method: saveAccounts
	  Inputs: N/A
	  Returns: N/A

	  Description: Saves the state of the server.
	*/
	public void	saveAccounts() throws IOException;
}

/*
 * File: AccountServer.java
 * Author: kevingary
 * Date: Unknown
 * 
 * Description: contains AccountServer interface for handling accounts
 */


package banking.primitive.core;

import java.io.IOException;
import java.util.List;

public interface AccountServer {

	/** 
	 *  Create a new account object in the server. if an account already exists with the given name
	 *  then a new account is not created and stored.
	 *  
		@param type must be one of Savings or Checking
		@param name leading or trailing whitespace is removed
		@param balance must be non-negative
		@throws IllegalArgumentException if the account type is invalid or the balance is non-negative.
		@return boolean true if the account was created and stored, false otherwise
	*/
	public boolean	newAccount(String type, String name, float balance) throws IllegalArgumentException;

	/** Close an account 
		@param name leading or trailing whitespace is removed
	 * @return boolean true if there was an account with this name and close was successful
	*/
	public boolean	closeAccount(String name);

	/**
	 * @param name name of the account 
	 * @return Account object or null if not found. 
	 */
	public Account	getAccount(String name);

	/** 
	 * @return a list of all Accounts inside the server 
	 */
	public List<Account> getAllAccounts();

	/** 
	 * @return a list of Accounts inside the server that are not CLOSED
	 */
	public List<Account> getActiveAccounts();

	/** 
	 * Saves the state of the server
	 * @throws IOException if unable to save the state
	 */
	public void	saveAccounts() throws IOException;
}

