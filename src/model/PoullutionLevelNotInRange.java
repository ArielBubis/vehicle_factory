/**
 *
 */
package model;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class PoullutionLevelNotInRange extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create exception if the pollution level is not in range
	 * @param idVehicle
	 */
	public PoullutionLevelNotInRange(String idVehicle) {
		super("Pollution level not in range. The vehicle " + idVehicle + " not added to the system. ");
	}

}
