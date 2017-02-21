package banking.gui;

import banking.primitive.core.Account;
import banking.primitive.core.AccountServer;
import banking.primitive.core.AccountServerFactory;

import java.io.*;
import java.util.*;
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;

/*
 * File: MainFrame.java
 * Author: kevingary
 * Date: Unknown
 * 
 * Description: Contains info for the GUI
 */

@SuppressWarnings("serial")
class MainFrame extends JFrame {
  private AccountServer _myServer;
	private _Properties props;
	private _JLabel typeLabel;
	private _JLabel nameLabel;
	private _JLabel balanceLabel;
	private _JComboBox typeOptions;
	private _JTextField nameField;
	private _JTextField balanceField;
	private _JButton depositButton;
	private _JButton withdrawButton;
	private _JButton	newAccountButton;
	private _JButton	displayAccountsButton;
	private _JButton	displayODAccountsButton;
	/**
	  Method: MainFrame
	  Inputs: propertyFile
	  Returns: N/A


	  Description: Constructor that creates the server and calls to create JFrame GUI
	*/  
	public MainFrame(String propertyFile) throws IOException {

		//** initialize myServer
		_myServer = AccountServerFactory.getMe().lookup();

		_props = new Properties();


		FileInputStream fis = null; 
		try {
			fis =  new FileInputStream(propertyFile);
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw ioe;
		}
		_constructForm();
	}

	
	private void _constructForm() {
		//*** Make these read from properties
		_typeLabel = new JLabel(props.getProperty("TypeLabel"));
		_nameLabel = new JLabel(props.getProperty("NameLabel"));
		_balanceLabel = new JLabel(props.getProperty("BalanceLabel"));

		Object[] accountTypes = {"Savings", "Checking"};
		_typeOptions = new JComboBox(accountTypes);
		_nameField = new JTextField(20);
		_balanceField = new JTextField(20);

		_newAccountButton = new JButton("New Account");
		JButton depositButton = new JButton("Deposit");
		JButton withdrawButton = new JButton("Withdraw");
		JButton saveButton = new JButton("Save Accounts");
		_displayAccountsButton = new JButton("List Accounts");
		JButton displayAllAccountsButton = new JButton("All Accounts");

		this.addWindowListener(new _FrameHandler());
		_newAccountButton.addActionListener(new _NewAccountHandler());
		_displayAccountsButton.addActionListener(new _DisplayHandler());
		displayAllAccountsButton.addActionListener(new _DisplayHandler());
		depositButton.addActionListener(new _DepositHandler());
		withdrawButton.addActionListener(new _WithdrawHandler());
		saveButton.addActionListener(new _SaveAccountsHandler());		
		
		Container pane = getContentPane();
		pane.setLayout(new FlowLayout());
		
		JPanel panel1 = new JPanel();
		panel1.add(_typeLabel);
		panel1.add(_typeOptions);
		
		JPanel panel2 = new JPanel();
		panel2.add(_displayAccountsButton);
		panel2.add(displayAllAccountsButton);
		panel2.add(saveButton);
		
		JPanel panel3 = new JPanel();
		panel3.add(_nameLabel);
		panel3.add(_nameField);
		
		JPanel panel4 = new JPanel();
		panel4.add(_balanceLabel);
		panel4.add(_balanceField);
		
		JPanel panel5 = new JPanel();
		panel5.add(_newAccountButton);
		panel5.add(depositButton);
		panel5.add(withdrawButton);

		pane.add(panel1);
		pane.add(panel2);
		pane.add(panel3);
		pane.add(panel4);
		pane.add(panel5);
		
		setSize(400, 250);
	}

	class _DisplayHandler implements ActionListener {
    /**
		  Method: actionPerformed
		  Inputs: e
		  Returns: N/A

		  Description: Displays all accounts or all active accounts based on which button was pressed.
		*/
		public void actionPerformed(ActionEvent e) {
			List<Account> accounts = null;
			if (e.getSource() == _displayAccountsButton) {
				accounts = _myServer.getActiveAccounts();
			} else {
				accounts = _myServer.getAllAccounts();
			}
			StringBuffer sb = new StringBuffer();
			Account thisAcct = null;
			for (Iterator<Account> li = accounts.iterator(); li.hasNext();) {
				thisAcct = (Account)li.next();
				sb.append(thisAcct.toString()+"\n");
			}

			JOptionPane.showMessageDialog(null, sb.toString());
		}
	}

	// Complete a handler for new account button
	class _NewAccountHandler implements ActionListener {
    	/**
		  Method: actionPerformed
		  Inputs: e
		  Returns: N/A

		  Description: Opens a window saying whether or not an account was successfully created when the button is clicked.
		*/
		public void actionPerformed(ActionEvent e) {
			String type = _typeOptions.getSelectedItem().toString();
			String name = _nameField.getText();
			String balance = _balanceField.getText();

			if (_myServer.newAccount(type, name, Float.parseFloat(balance))) {
				JOptionPane.showMessageDialog(null, "Account created successfully");
			} else {
				JOptionPane.showMessageDialog(null, "Account not created!");
			}
		}
	}
	
	// Complete a handler for new account button
	class _SaveAccountsHandler implements ActionListener {
    	/**
		  Method: actionPerformed
		  Inputs: e
		  Returns: N/A

		  Description: Opens a window saying whether or not accounts were saved properly when the button is clicked. 
		*/
		public void actionPerformed(ActionEvent e) {
      final String ACCOUNTS_SAVED_STR = "Accounts saved";
			final String ACCOUNT_SAVE_ERR_STR = "Error saving accounts";
			try {
				_myServer.saveAccounts();
				JOptionPane.showMessageDialog(null, "Accounts saved");
			} catch (IOException exc) {
				JOptionPane.showMessageDialog(null, "Error saving accounts");
			}
		}
	}

	// Complete a handler for deposit button
	class _DepositHandler implements ActionListener {
    	/**
		  Method: actionPerformed
		  Inputs: e
		  Returns: N/A

		  Description: Makes a deposit and opens a window saying whether or not a deposit was successful when the button is clicked.
		*/
		public void actionPerformed(ActionEvent e) {
      final String SUCCESSFUL_DEPOSIT_STR = "Deposit successful";
			final String FAILED_DEPOSIT_STR = "Deposit unsuccessful";
			String name = _nameField.getText();
			String balance = _balanceField.getText();
			Account acc = _myServer.getAccount(name);
			if (acc != null && acc.deposit(Float.parseFloat(balance))) {
				JOptionPane.showMessageDialog(null, "Deposit successful");
			} else {
				JOptionPane.showMessageDialog(null, "Deposit unsuccessful");
			}		
		}
	}
	// Complete a handler for deposit button
	class _WithdrawHandler implements ActionListener {
    	/**
		  Method: actionPerformed
		  Inputs: e
		  Returns: N/A

		  Description: Makes a withdrawal and opens a window saying whether or not the withdrawal 
		*/
		public void actionPerformed(ActionEvent e) {
      final String SUCCESSFUL_WITHDRAWAL_STR = "Withdrawal successful";
			final String FAILED_WITHDRAWAL_STR = "Withdrawal unsuccessful";
			String name = _nameField.getText();
			String balance = _balanceField.getText();
			Account acc = _myServer.getAccount(name);
			if (acc != null && acc.withdraw(Float.parseFloat(balance))) {
				JOptionPane.showMessageDialog(null, "Withdrawal successful");
			} else {
				JOptionPane.showMessageDialog(null, "Withdrawal unsuccessful");
			}		
		}
	}
	
	//** Complete a handler for the Frame that terminates 
	//** (System.exit(1)) on windowClosing event
	static class _FrameHandler extends WindowAdapter {
		/**
		  Method: windowClosing
		  Inputs: e
		  Returns: Terminates program.

		  Description:
		*/
		public void windowClosing(WindowEvent e) {
			final int SYSTEM_EXIT_PARAMETER = 0;
			System.exit(SYSTEM_EXIT_PARAMETER);
		}
	}
}

