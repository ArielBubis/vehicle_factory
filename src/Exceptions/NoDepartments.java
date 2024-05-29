/**
 *
 */
package Exceptions;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class NoDepartments extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public NoDepartments() {
		super("Please create a department first");
	}


}
