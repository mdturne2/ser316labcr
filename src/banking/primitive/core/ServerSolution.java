package banking.primitive.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.*;

import banking.primitive.core.Account.State;

/*
 * File: ServerSolution.java
 * Author: kevingary
 * Date: Unknown
 * 
 * Description: Contains info for the GUI
 */

/**
Class: ServerSolution

Description: Takes care of the "server-side" of the project.
			 Handles the storage of the accounts and its types for the user to access later.
*/
class ServerSolution implements AccountServer {
	static String fileName = "accounts.ser";

	Map<String,Account> accountMap = null;

  	/**
	  Method: ServerSolution
	  Inputs: N/A
	  Returns: N/A

	  Description: Reads from a file.
	*/
	public ServerSolution() {
		accountMap = new HashMap<String,Account>();
		File file = new File(fileName);
		ObjectInputStream in = null;
		try {
			if (file.exists()) {
				System.out.println("Reading from file " + fileName + "...");
				in = new ObjectInputStream(new FileInputStream(file));

				Integer sizeI = (Integer) in.readObject();
				int size = sizeI.intValue();
				for (int i=0; i < size; i++) {
					Account acc = (Account) in.readObject();
					if (acc != null)
						accountMap.put(acc.getName(), acc);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
		}
	}

	/**
	  Method: newAccountFactory
	  Inputs: type, name, balance
	  Returns: True/False

	  Description: Creates a new account of type, type and places it in accountMap
	*/
	private boolean _newAccountFactory(String type, String name, float balance)

	private boolean _newAccountFactory(String type, String name, float balance)

		throws IllegalArgumentException {
		
		if (accountMap.get(name) != null) return false;
		
		Account acc;
		if ("Checking".equals(type)) {
			acc = new Checking(name, balance);

		} else if ("Savings".equals(type)) {
			acc = new Savings(name, balance);

		} else {
			throw new IllegalArgumentException("Bad account type:" + type);
		}
		try {
			accountMap.put(acc.getName(), acc);
		} catch (Exception exc) {
			return false;
		}
		return true;
	}

	/**
	  Method: newAccount
	  Inputs: type, name, balance
	  Returns: True/False

	  Description: Creates a new account. Fails if balance is negative.
	*/
	public boolean newAccount(String type, String name, float balance) 
		throws IllegalArgumentException {
		
		if (balance < 0.0f) throw new IllegalArgumentException("New account may not be started with a negative balance");
		
		return _newAccountFactory(type, name, balance);
	}
	
	/**
	  Method: closeAccount
	  Inputs: name
	  Returns: True/False

	  Description: Sets specified account state to CLOSED.
	*/
	public boolean closeAccount(String name) {
		Account acc = accountMap.get(name);
		if (acc == null) {
			return false;
		}
		acc.setState(State.CLOSED);
		return true;
	}
	/**
	  Method: getAccount
	  Inputs: name
	  Returns: account

	  Description: Returns Account with given name in accountMap
	*/
	public Account getAccount(String name) {
		return accountMap.get(name);
	}
	/**
	  Method: getAllAccounts
	  Inputs: N/A
	  Returns: List

	  Description: Returns a list of all the Account objects in accountMap
	*/
	public List<Account> getAllAccounts() {
		return new ArrayList<Account>(accountMap.values());
	}
	/**
	  Method: getActiveAccounts
	  Inputs: N/A
	  Returns: List

	  Description: Returns a list of all accounts that are not set to CLOSED.
	*/
	public List<Account> getActiveAccounts() {
		List<Account> result = new ArrayList<Account>();

		for (Account acc : accountMap.values()) {
			if (acc.getState() != State.CLOSED) {
				result.add(acc);
			}
		}
		return result;
	}
	
	/**
	  Method: saveAccounts
	  Inputs: N/A
	  Returns: N/A

	  Description: Saves accounts to File.
	*/
	public void saveAccounts() throws IOException {
		ObjectOutputStream out = null; 
		try {
			out = new ObjectOutputStream(new FileOutputStream(fileName));

			out.writeObject(Integer.valueOf(accountMap.size()));
			for (int i=0; i < accountMap.size(); i++) {
				out.writeObject(accountMap.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("Could not write file:" + fileName);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
		}
