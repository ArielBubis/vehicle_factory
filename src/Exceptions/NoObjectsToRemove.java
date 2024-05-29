/**
 *
 */
package Exceptions;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class NoObjectsToRemove extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public NoObjectsToRemove(String str) {
		super(str+" lists are empty");
	}

}
