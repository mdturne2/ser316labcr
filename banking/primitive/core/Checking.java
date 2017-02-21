package banking.primitive.core;
/*
 * File: Checking.java
 * Author: kevingary
 * Date: Unknown
 * 
 * Description: Contains Checking implementation of Accounts
 */

/**
Class: Checking

Description: This class extends from the Account class and includes varies methods.
			 This class provides the user with a different type of account they might want to have.
			 Acts as a checkings account.
*/

public class Checking extends Account {


	private static final long serialVersionUID = 11L;

	
	private Checking(String name) {
		super(name);
	}

    public static Checking createChecking(String name) {
        return new Checking(name);
    }

	public Checking(String name, float balance) {
		super(name, balance);
	}
	
	public String getType() {
		final String CHECKING_STR = "Checking";
		return CHECKING_STR; 
	}

	/**
	 * A deposit may be made unless the Checking account is closed
	 * @param float is the deposit amount
	 */
	public boolean deposit(float amount) {
		if (getState() != State.CLOSED && amount > 0.0f) {
			balance = balance + amount;
			if (balance >= 0.0f) {
				setState(State.OPEN);
			}
			return true;
		}
		return false;
	}

	/**
	 * Withdrawal. After 10 withdrawals a fee of $2 is charged per transaction You may 
	 * continue to withdraw an overdrawn account until the balance is below -$100
	 */
	public boolean withdraw(float amount) {
		if (amount > 0.0f) {		
			// KG: incorrect, last balance check should be >=
			if (getState() == State.OPEN || (getState() == State.OVERDRAWN && balance > -100.0f)) {
				balance = balance - amount;
				numWithdraws++;
				if (numWithdraws > 10){
					balance = balance - 2.0f;
        }
				if (balance < 0.0f) {
					setState(State.OVERDRAWN);
				}
				return true;
			}
		}
		return false;
	}

	
	public String toString() {
		final String CHECKING_COLON_SPACE_STR = "Checking: ";
		final String COLON_SPACE_STR = ": ";
		return CHECKING_COLON_SPACE_STR + getName() + COLON_SPACE_STR + getBalance();
	}
	
	private int numWithdraws = 0;
}
