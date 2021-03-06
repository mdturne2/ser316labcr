
/*
 * File: Account.java
 * Author: kevingary
 * Date: Unknown
 * 
 * Description: Contains generic account methods
 */

package banking.primitive.core;

/**
Class: Account

Description: It provides the program with the functionality and methods that an account would have.
			 i.e withdraw, deposit, type of account and such
*/
public abstract class Account implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    protected enum State {
        OPEN, CLOSED, OVERDRAWN
    };

    protected float balance = 0.0F;
    protected String name;
    private State state;
    
    /**
    Method: Account
    Inputs: n
    Returns: N/A

    Description: Constructor to create account using just the name as an argument
  */

    protected Account(String n) {
        name = n;
        state = State.OPEN;
    }


    /**
    Method: Account
    Inputs: n, b
    Returns: N/A

    Description: Constructor to create account based on name and balance.
  */

    protected Account(String n, float b) {
        this(n); 
        balance = b;
    }

    /**


    Method: getName
    Inputs: N/A
    Returns: name

    Description: Returns the name of the account.
    */
    public final String getName() {
        return name;
    }

    /**

    Method: getBalance
    Inputs: N/A
    Returns: balance

    Description: Returns the balance of the account.
    */
    public final float getBalance() {
        return balance;
    }
  
    /**
    Method: getState
    Inputs: N/A
    Returns: state

    Description: Returns the state of the account
    */
    protected final State getState() {
        return state;
    }
    
    /**
     * Adds money to an account. May not be done if the account is CLOSED
     * 
     * @param parameter
     *            amount is a deposit and must be > 0
     * @return true if the deposit was successful, false if not due to amount or
     *         invalid state
     */

      /**
    Method: deposit
    Inputs: amount
    Returns: True/False

    Description: Adds money to an account. May not be done if the account is CLOSED
    */
    public abstract boolean deposit(float amount);

    /**
     * Takes money out of an account. If the balance falls below 0 then the
     * account is moved to an OVERDRAWN state
     * 
     * @param parameter
     *            amount is a withdrawal and must be > 0
     * @return true if the deposit was successful, false if not due to amount or
     *         invalid state
     */

    
    /**
    Method: withdraw
    Inputs: amount
    Returns: True/False

    Description: Takes money out of an account. If the balance falls below 0 then the 
    account is moved to an OVERDRAWN state
    */
    public abstract boolean withdraw(float amount);

    /**
    Method: getType
    Inputs: N/A
    Returns: type

    Description: Returns the type of the account
  */
    public abstract String getType();
    
    /**
    Method: setState
    Inputs: s
    Returns: N/A

    Description: Sets the state of the account.
    */

    protected final void setState(State s) {
        state = s;
    }
    /**
    Method: toString
    Inputs: N/A
    Returns: A string containing the name, balance and state of the account.

    Description: Returns a string containing the name, balance and state of the account.
    */

    public String toString() {
        return "Account " + name + " has $" + balance + "and is " + getState()
                + "\n";
    }

}


