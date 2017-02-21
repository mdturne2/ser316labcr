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
