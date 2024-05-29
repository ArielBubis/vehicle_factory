/**
 *
 */
package Exceptions;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class InputSizeException extends Exception {

	private static final long serialVersionUID = 1L;

	public InputSizeException(int max) {
		super("Input is too long, the Input include only " + max + " Characters");
	}
}
