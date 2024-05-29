/**
 *
 */
package model;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class PoullutionLevelHybridVehiclesMustBeOne extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create exception if the pollution level of a hybrid car is wrong
	 * @param idVehicle
	 */
	public PoullutionLevelHybridVehiclesMustBeOne(String idVehicle) {
		super("Pollution level in hybrid vehicles must be 1 " + idVehicle + " not added to the system");
	}

}
