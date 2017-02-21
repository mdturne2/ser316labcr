/*
 * File: AccountServerFactory.java
 * Author: kevingary
 * Date: Unknown
 * 
 * Description: contains factory for accountserver
 */

package banking.primitive.core;

/**
Class: AccountServerFactory

Description: This class helps create the "server-side" of the project.
			 Essentially what it does is it helps build AccountServer.
*/
public class AccountServerFactory {

	private static AccountServerFactory singleton = null;
  
	/**
	  Method: AccountServerFactory
	  Inputs: N/A
	  Returns: N/A

	  Description: Constructor creates Account Server Factory.
	*/
	protected AccountServerFactory() {

	}
  	/**
	  Method: getMe
	  Inputs: N/A
	  Returns: singleton

	  Description: returns the AccountServerFactory.
	*/
	public static AccountServerFactory getMe() {
		if (singleton == null) {
			singleton = new AccountServerFactory();
		}

		return singleton;
	}

	/**
	  Method: lookup
	  Inputs: N/A
	  Returns: ServerSolution

	  Description: Returns a ServerSolution object.
	*/
	public AccountServer lookup() {
		return new ServerSolution();
	}
}
