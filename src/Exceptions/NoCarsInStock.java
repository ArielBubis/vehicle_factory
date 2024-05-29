/**
 *
 */
package Exceptions;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class NoCarsInStock extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public NoCarsInStock() {
		super("No cars in stock");
	}


}
