/**
 *
 */
package Exceptions;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class NoCarsInDeal extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public NoCarsInDeal() {
		super("No cars Selected for the Deal");
	}

}
