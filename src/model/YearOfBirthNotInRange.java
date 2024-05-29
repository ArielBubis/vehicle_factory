/**
 *
 */
package model;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class YearOfBirthNotInRange extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create exception if the year of birth not in range
	 * @param idPerson
	 */
	public YearOfBirthNotInRange(String idPerson) {
		super("The person's year of birth must be between 1900-2022. The person " + idPerson
				+ "not added to the system");
	}

}
