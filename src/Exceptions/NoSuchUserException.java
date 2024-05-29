/**
 *
 */
package Exceptions;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class NoSuchUserException extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public NoSuchUserException() {
		super("This user does not exsist");
	}
}
