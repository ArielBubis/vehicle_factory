/**
 *
 */
package model;

import java.util.HashSet;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class ArithmeticMean implements ScoreCalculator{

	@Override
	public float calculate(HashSet<VehicleTransportation> allVehicleTransportation) {
		float arithmeticMean =  0;
		float setSize = allVehicleTransportation.size();
		if (setSize == 0) {
			return 0;
		}
		for (VehicleTransportation vt : allVehicleTransportation) {
			arithmeticMean += vt.getPollutionLevel();
		}
		return arithmeticMean/setSize;
	}

}
