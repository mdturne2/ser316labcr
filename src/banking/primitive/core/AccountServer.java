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
