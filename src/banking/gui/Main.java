package banking.gui;

import javax.swing.JFrame;

/**
 * main method for running the program.
 * @author kevinagary
 *
 */
final class Main {
	/**
	  Method: Main()
	  Inputs: N/A
	  Returns: N/A

	  Description: empty private constructor
	*/
	private Main() {
	}
	
	/**
	  Method: main()
	  Inputs: args[]
	  Returns: N/A

	  Description: Main method that reads my.properties and creates JFrame
	*/
	public static void main(final String[] args) throws Exception {

		if (args.length != 1) {
			System.out.println("Usage: java FormMain <property file>");
			System.exit(1);
		}

		String propertyFile = args[0];
		JFrame frame = new MainFrame(propertyFile);
		frame.setVisible(true);

	}
}
