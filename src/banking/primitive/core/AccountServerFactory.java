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

Description: This class is what makes AccountServer useful. It will run and make the interface of AccountServer useful.
*/
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
