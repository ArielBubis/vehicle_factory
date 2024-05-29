/**
 *
 */
package Exceptions;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class WrongImageFileException extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
public WrongImageFileException() {
	super("File must be an image file");
}
}
