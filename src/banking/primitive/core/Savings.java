package banking.primitive.core;
/*
 * File: Savings.java
 * Author: kevingary
 * Date: Unknown
 * 
 * Description: Contains Savings implementation of Account
 */

/**
Class: Savings

Description: This class extends from the Account class and includes varies methods.
			 This class provides the user with a different type of account they might want to have.
			 Acts as a savings account.
*/

public class Savings extends Account {
	private static final long serialVersionUID = 111L;

	public Savings(String name) {
		super(name);
	}

	public Savings(String name, float balance) throws IllegalArgumentException {
		super(name, balance);
	}
	
	public String getType() { 
		return "Checking";
	}

	/**
	 * A deposit comes with a fee of 50 cents per deposit
	 */
	public boolean deposit(float amount) {
		if (getState() != State.CLOSED && amount > 0.0f) {
			balance = balance + amount - 0.50F;
			if (balance >= 0.0f) {
				setState(State.OPEN);
			}
			return true;
		}
		return false;
	}

	/**
	 * A withdrawal. After 3 withdrawals a fee of $1 is added to each withdrawal.
	 * An account whose balance dips below 0 is in an OVERDRAWN state
	 */
	public boolean withdraw(float amount) {
		if (getState() == State.OPEN && amount > 0.0f) {
			balance = balance - amount;
			_numWithdraws++;
			if (_numWithdraws > 3)
				balance = balance - 1.0f;
			// KG BVA: should be < 0
			if (balance <= 0.0f) {
				setState(State.OVERDRAWN);
			}
			return true;
		}
		return false;
	}

	public String toString() {
		final String CHECKING_COLON_SPACE_STR = "Checking: ";
		final String COLON_SPACE_STR = ": ";
		return CHECKING_COLON_SPACE_STR + getName() + COLON_SPACE_STR + getBalance();
	}
	
	private int _numWithdraws = 0;
}
