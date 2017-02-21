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

