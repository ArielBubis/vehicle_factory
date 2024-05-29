/**
 *
 */
package model;

import java.util.HashSet;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class GeometricMean implements ScoreCalculator{

	@Override
	public float calculate(HashSet<VehicleTransportation> allVehicleTransportation) {
		float geometricMean = 1;
		float setSize = allVehicleTransportation.size();

		for (VehicleTransportation vt : allVehicleTransportation) {
			geometricMean = geometricMean * vt.getPollutionLevel();
		}
		return (float) (Math.pow(geometricMean,1.0/setSize));
	}


}
