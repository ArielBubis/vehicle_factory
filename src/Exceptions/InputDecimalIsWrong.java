/**
 *
 */
package Exceptions;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class InputDecimalIsWrong extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * Create exception for the wrong number of seats in car
	 */
	public InputDecimalIsWrong() {
		super("You can't put a decimal point here");
	}
}