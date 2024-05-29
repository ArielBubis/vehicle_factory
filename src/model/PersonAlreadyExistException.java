package model;
/**
 * @author Ariel Bubis 205735749
 *
 */
public class PersonAlreadyExistException extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create exception if the person exist in the list
	 * @param fisrtName
	 * @param lastName
	 */
	public PersonAlreadyExistException(String fisrtName, String lastName) {
		super("The person already exists in the System: " + fisrtName +" "+ lastName+" The person not added to the system");
	}

}
