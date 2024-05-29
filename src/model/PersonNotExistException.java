/**
 *
 */
package model;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class PersonNotExistException extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create exception if the person doesn't exist in the list
	 */
	public PersonNotExistException() {
		super("The person does not exist in the System ");
	}

}
