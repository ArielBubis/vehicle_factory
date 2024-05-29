/**
 *
 */
package model;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class IncorrectNumberOfSeats extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * @param idVehicle
	 * Create exception for the wrong number of seats in car
	 */
	public IncorrectNumberOfSeats(String idVehicle) {
		super("The number of seats in car / hybrid car can be a maximum of 7. The vehicle" + idVehicle
				+ " not added to the system.");
	}
}
