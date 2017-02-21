
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
	
	
	AccountServer	myServer;
	Properties		props;
	JLabel			typeLabel;
	JLabel			nameLabel;
	JLabel			balanceLabel;
	JComboBox		typeOptions;
	JTextField		nameField;
	JTextField		balanceField;
	JButton 		depositButton;
	JButton 		withdrawButton;
	JButton			newAccountButton;
	JButton			displayAccountsButton;
	JButton			displayODAccountsButton;

	public MainFrame(String propertyFile) throws IOException {

		//** initialize myServer
		myServer = AccountServerFactory.getMe().lookup();

		props = new Properties();

		FileInputStream fis = null; 
		try {
			fis =  new FileInputStream(propertyFile);
			props.load(fis);
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw ioe;
		}
		constructForm();
	}

	
	private void constructForm() {
		//*** Make these read from properties
		final String TYPE_LABEL_STR = "TypeLabel";
		final String NAME_LABEL_STR = "NameLabek";
		final String BALANCE_LABEL_STR = "BalanceLabel";
		final String SAVINGS_STR = "Savings";
		final String CHECKING_STR = "Checking";
		final int DEFAULT_COLUMN_COUNT = 20;
		final String NEW_ACCOUNT_STR = "New Account";
		final String DEPOSIT_STR = "Deposit";
		final String WITHDRAW_STR = "Withdraw";
		final String SAVE_ACCOUNTS_STR = "Save Accounts";
		final String LIST_ACCOUNTS_STR = "List Accounts";
		final String ALL_ACCOUNTS_STR = "All Accounts";
		final int DEFAULT_WIDTH = 400;
		final int DEFAULT_HEIGHT = 250;
		
		typeLabel		= new JLabel(props.getProperty(TYPE_LABEL_STR));
		nameLabel		= new JLabel(props.getProperty(NAME_LABEL_STR));
		balanceLabel	= new JLabel(props.getProperty(BALANCE_LABEL_STR));

		Object[] accountTypes = {SAVINGS_STR, CHECKING_STR};
		typeOptions = new JComboBox(accountTypes);
		nameField = new JTextField(DEFAULT_COLUMN_COUNT);
		balanceField = new JTextField(DEFAULT_COLUMN_COUNT);

		newAccountButton = new JButton(NEW_ACCOUNT_STR);
		JButton depositButton = new JButton(DEPOSIT_STR);
		JButton withdrawButton = new JButton(WITHDRAW_STR);
		JButton saveButton = new JButton(SAVE_ACCOUNTS_STR);
		displayAccountsButton = new JButton(LIST_ACCOUNTS_STR);
		JButton displayAllAccountsButton = new JButton(ALL_ACCOUNTS_STR);

		this.addWindowListener(new FrameHandler());
		newAccountButton.addActionListener(new NewAccountHandler());
		displayAccountsButton.addActionListener(new DisplayHandler());
		displayAllAccountsButton.addActionListener(new DisplayHandler());
		depositButton.addActionListener(new DepositHandler());
		withdrawButton.addActionListener(new WithdrawHandler());
		saveButton.addActionListener(new SaveAccountsHandler());		
		
		Container pane = getContentPane();
		pane.setLayout(new FlowLayout());
		
		JPanel panel1 = new JPanel();
		panel1.add(typeLabel);
		panel1.add(typeOptions);
		
		JPanel panel2 = new JPanel();
		panel2.add(displayAccountsButton);
		panel2.add(displayAllAccountsButton);
		panel2.add(saveButton);
		
		JPanel panel3 = new JPanel();
		panel3.add(nameLabel);
		panel3.add(nameField);
		
		JPanel panel4 = new JPanel();
		panel4.add(balanceLabel);
		panel4.add(balanceField);
		
		JPanel panel5 = new JPanel();
		panel5.add(newAccountButton);
		panel5.add(depositButton);
		panel5.add(withdrawButton);

		pane.add(panel1);
		pane.add(panel2);
		pane.add(panel3);
		pane.add(panel4);
		pane.add(panel5);
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	class DisplayHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			final String NEW_LINE = "\n";
			List<Account> accounts = null;
			if (e.getSource() == displayAccountsButton) {
				accounts = myServer.getActiveAccounts();
			} else {
				accounts = myServer.getAllAccounts();
			}
			StringBuffer sb = new StringBuffer();
			Account thisAcct = null;
			for (Iterator<Account> li = accounts.iterator(); li.hasNext();) {
				thisAcct = (Account)li.next();
				sb.append(thisAcct.toString()+NEW_LINE);
			}

			JOptionPane.showMessageDialog(null, sb.toString());
		}
	}

	// Complete a handler for new account button
	class NewAccountHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			final String ACCOUNT_CREATED_STR = "Account created successfully";
			final String ACCOUNT_CREATION_FAILED_STR = "Account not created!";
			
			String type = typeOptions.getSelectedItem().toString();
			String name = nameField.getText();
			String balance = balanceField.getText();

			if (myServer.newAccount(type, name, Float.parseFloat(balance))) {
				JOptionPane.showMessageDialog(null, ACCOUNT_CREATED_STR);
			} else {
				JOptionPane.showMessageDialog(null, ACCOUNT_CREATION_FAILED_STR);
			}
		}
	}
	
	// Complete a handler for new account button
	class SaveAccountsHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			final String ACCOUNTS_SAVED_STR = "Accounts saved";
			final String ACCOUNT_SAVE_ERR_STR = "Error saving accounts";
			
			try {
				myServer.saveAccounts();
				JOptionPane.showMessageDialog(null, ACCOUNTS_SAVED_STR);
			} catch (IOException exc) {
				JOptionPane.showMessageDialog(null, ACCOUNT_SAVE_ERR_STR);
			}
		}
	}

	// Complete a handler for deposit button
	class DepositHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			final String SUCCESSFUL_DEPOSIT_STR = "Deposit successful";
			final String FAILED_DEPOSIT_STR = "Deposit unsuccessful";
			String name = nameField.getText();
			String balance = balanceField.getText();
			Account acc = myServer.getAccount(name);
			if (acc != null && acc.deposit(Float.parseFloat(balance))) {
				JOptionPane.showMessageDialog(null, SUCCESSFUL_DEPOSIT_STR);
			} else {
				JOptionPane.showMessageDialog(null, FAILED_DEPOSIT_STR);
			}		
		}
	}
	// Complete a handler for deposit button
	class WithdrawHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			final String SUCCESSFUL_WITHDRAWAL_STR = "Withdrawal successful";
			final String FAILED_WITHDRAWAL_STR = "Withdrawal unsuccessful";
			
			String name = nameField.getText();
			String balance = balanceField.getText();
			Account acc = myServer.getAccount(name);
			if (acc != null && acc.withdraw(Float.parseFloat(balance))) {
				JOptionPane.showMessageDialog(null, SUCCESSFUL_WITHDRAWAL_STR);
			} else {
				JOptionPane.showMessageDialog(null, FAILED_WITHDRAWAL_STR);
			}		
		}
	}
	
	//** Complete a handler for the Frame that terminates 
	//** (System.exit(1)) on windowClosing event

	static class FrameHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			final int SYSTEM_EXIT_PARAMETER = 0;
			System.exit(SYSTEM_EXIT_PARAMETER);
		}
	}
}

