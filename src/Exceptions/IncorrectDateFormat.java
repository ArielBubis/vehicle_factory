/**
 *
 */
package Exceptions;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class IncorrectDateFormat extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public IncorrectDateFormat() {
		super("This Date is in the future or befor 1/1/1900");
	}
}
