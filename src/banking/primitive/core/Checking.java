
package banking.primitive.core;
/*
 * File: Checking.java
 * Author: kevingary
 * Date: Unknown
 * 
 * Description: Contains Checking implementation of Accounts
 */

public class Checking extends Account {

	private static final long _SERIAL_VERSION_UID = 11L;
	private int numWithdraws = 0;
	
	/**
	  Method: Checking
	  Inputs: name
	  Returns: N/A

	  Description: Constructor that instantiates a checking account named after name.
	*/
	private Checking(String name) {
		super(name);
	}

	/**
	  Method: createChecking
	  Inputs: name
	  Returns: Checking

	  Description: Creates and returns a checking account object based on name.
	*/
    public static Checking createChecking(String name) {
        return new Checking(name);
    }

    /**
    Method: Checking
    Inputs: name, balance
    Returns: N/A

    Description: Constructor that instantiates a checking account object based on name and balance.
  */
	public Checking(String name, float balance) {
		super(name, balance);
	}

	/**
	  Method: deposit
	  Inputs: amount
	  Returns: True/False

	  Description: A deposit may be made unless the Checking account is closed.
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
	  Method: withdraw
	  Inputs: amount
	  Returns: True/False

	  Description: After 10 withdrawals a fee of $2 is charged per transaction You may
	  continue to withdraw an overdrawn account until the balance is below -$100
	*/
	public boolean withdraw(float amount) {
		if (amount > 0.0f) {		
			// KG: incorrect, last balance check should be >=
			if (getState() == State.OPEN || (getState() == State.OVERDRAWN && balance > -100.0f)) {
				balance = balance - amount;
				numWithdraws++;
				if (numWithdraws > 10)
					balance = balance - 2.0f;
				if (balance < 0.0f) {
					setState(State.OVERDRAWN);
				}
				return true;
			}
		}
		return false;
	}

	/**
	  Method: getType
	  Inputs: N/A
	  Returns: "Checking"

	  Description: Returns string saying this is a checking account.
	*/
	public String getType() { return "Checking"; }

	public String getType() {
		final String CHECKING_STR = "Checking";
		return CHECKING_STR; 
	}

	
	/**
	  Method: toString
	  Inputs: N/A
	  Returns: String containing name and balance

	  Description: Returns string containing name and balance.
	*/
	public String toString() {
		final String CHECKING_COLON_SPACE_STR = "Checking: ";
		final String COLON_SPACE_STR = ": ";
		return CHECKING_COLON_SPACE_STR + getName() + COLON_SPACE_STR + getBalance();
	}
}

