
package banking.primitive.core;


public class AccountServerFactory {

	protected static AccountServerFactory singleton = null;
	
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

/*
 * File: AccountServerFactory.java
 * Author: kevingary
 * Date: Unknown
 * 
 * Description: contains factory for accountserver
 */

package banking.primitive.core;


public class AccountServerFactory {

	protected static AccountServerFactory singleton = null;

	protected AccountServerFactory() {

	}

	public static AccountServerFactory getMe() {
		if (singleton == null) {
			singleton = new AccountServerFactory();
		}

		return singleton;
	}

	public AccountServer lookup() {
		return new ServerSolution();
	}
}

