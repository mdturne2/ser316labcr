package banking.primitive.core;

public class Savings extends Account {
	private static final long serialVersionUID = 111L;
	private int numWithdraws = 0;
	
	/**
	  Method: Savings
	  Inputs: name
	  Returns: N/A

	  Description: Constructor creating Savings account object based on name.
	*/
	public Savings(String name) {
		super(name);
	}

	/**
	  Method: Savings
	  Inputs: name, balance
	  Returns: N/A

	  Description: Constructor creating Savings account object based on name and balance.
	*/
	public Savings(String name, float balance) throws IllegalArgumentException {
		super(name, balance);
	}

	/**
	  Method: deposit
	  Inputs: amount
	  Returns: True/False

	  Description: A deposit comes with a fee of 50 cents per deposit
	*/
	public boolean deposit(float amount) {
		if (getState() != State.CLOSED && amount > 0.0f) {
			balance = balance + amount - 0.50F;
			if (balance >= 0.0f) {
				setState(State.OPEN);
			}
		}
		return false;
	}

	/**
	  Method: withdraw
	  Inputs: amount
	  Returns: True/False

	  Description: A withdrawal. After 3 withdrawals a fee of $1 is added to each withdrawal.
	  An account whose balance dips below 0 is in an OVERDRAWN state.
	*/
	public boolean withdraw(float amount) {
		if (getState() == State.OPEN && amount > 0.0f) {
			balance = balance - amount;
			numWithdraws++;
			if (numWithdraws > 3)
				balance = balance - 1.0f;
			// KG BVA: should be < 0
			if (balance <= 0.0f) {
				setState(State.OVERDRAWN);
			}
			return true;
		}
		return false;
	}
	
	/**
	  Method: getType
	  Inputs: N/A
	  Returns: "Checking"

	  Description: Returns "Checking"
	*/
	public String getType() { return "Checking"; }

	/**
	  Method: toString
	  Inputs: N/A
	  Returns: String containing name and balance of the account.

	  Description: String containing name and balance of the account.
	*/
	public String toString() {
		return "Savings: " + getName() + ": " + getBalance();
	}
}
