/**
 *
 */
package Exceptions;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class WrongPassException extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public WrongPassException() {
		super("Wrong password");
	}
}
