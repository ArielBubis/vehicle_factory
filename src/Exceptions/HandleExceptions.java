/**
 *
 */
package Exceptions;

import javax.swing.JOptionPane;

import model.Factory;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class HandleExceptions extends JOptionPane{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	public static void showException(Exception e) {
		//Toolkit.getDefaultToolkit().beep();
		Factory.getInstance().sound.playSound("Alert");
		JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	}

}
