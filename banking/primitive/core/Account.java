
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

    protected Account(String n) {
        name = n;
        state = State.OPEN;
    }

    protected Account(String n, float b) {
        this(n); 
        balance = b;
    }

    /**
     * @return balance in the Account
     */
    public final float getBalance() {
        return balance;
    }

    
    /**
     * @return name of the Account
     */
    public final String getName() {
        return name;
    }

    protected final State getState() {
        return state;
    }
    
    public abstract String getType();

    protected final void setState(State s) {
        state = s;
    }
    
    /**
     * Adds money to an account. May not be done if the account is CLOSED
     * 
     * @param parameter
     *            amount is a deposit and must be > 0
     * @return true if the deposit was successful, false if not due to amount or
     *         invalid state
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
    public abstract boolean withdraw(float amount);

    /**
     * @return either "Checking" or "Savings"
     */
    


    public String toString() {
        return "Account " + name + " has $" + balance + "and is " + getState()
                + "\n";
    }
    private State state;
    protected float balance = 0.0F;
    protected String name;
}